package com.shi.main.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.shi.api.dto.RedPacketDto;
import com.shi.api.serivce.IRedPacketService;
import com.shi.api.serivce.IRedService;
import com.shi.api.util.RedPacketUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;


import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @author: 扑腾的江鱼
 * @description: TODO 类描述
 * @create: 2023/06/20 16:15
 **/
@Service(
        version = "1.0.0",
        interfaceName = "com.shi.api.service.IRedPacketService",
        interfaceClass = IRedPacketService.class
)
public class RedPacketServiceImp implements IRedPacketService {

    //定义日志
    private static final Logger log = LoggerFactory.getLogger(RedPacketServiceImp.class);

    //定义红包全局唯一识别码前缀
    private static final String keyPrefix = "redis:red:packet:";

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IRedService iRedService;

    @Override
    public String handOut(RedPacketDto dto) throws Exception {
        //判断是否合法
        if (dto.getTotal() > 0 && dto.getAmount() > 0) {
            //采用二倍均值法生成随机金额
            List<Integer> list = RedPacketUtil.divideRedPacket(dto.getAmount(), dto.getTotal());
            //生成红包全局唯一识别码
            String timestamp = String.valueOf(System.nanoTime());
            String redId = new StringBuffer(keyPrefix).append(dto.getUserId()).append(":").append(timestamp).toString().trim();
            //使用redis 存储小红包们
            redisTemplate.opsForList().leftPushAll(redId, list.toArray());
            //根据缓存的 key 的前缀和其他信息拼接一个接收小红包总数的key
            String redisTotalKey = redId + ":total";
            redisTemplate.opsForValue().set(redisTotalKey, dto.getTotal());
            //异步存入红包记录，红包个数，红包钱数
            iRedService.recordRedPacket(dto, redId, list);
            return redId;
        } else {
            log.error("系统异常，参数不合法");
            throw new Exception("系统异常，参数不合法");
        }
    }

    @Override
    public BigDecimal robLock(Integer userId, String redId) throws Exception {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //判断当前用户是否已经抢了红包
        Object obj = valueOperations.get(redId + userId + ":rob");
        if (obj != null) {
            return new BigDecimal(obj.toString());
        }
        //开抢，同时判断是否有剩余红包
        boolean flag = click(redId);
        if (flag) {
            //上分布式锁
            final String lockKey = redId + userId + ":lock";
            Boolean lock = valueOperations.setIfAbsent(lockKey, redId, 24L, TimeUnit.HOURS);
            try {
                if (lock) {//加上锁了
                    Object value = redisTemplate.opsForList().rightPop(redId);
                    if (value != null) {//还有包
                        //小红包数量 -1
                        String redTotalKey = redId + ":total";
                        valueOperations.decrement(redTotalKey);
                        //把分换成元显示
                        BigDecimal result = new BigDecimal(value.toString()).divide(new BigDecimal(100));
                        //数据送入数据库
                        iRedService.recordRobRedPacket(userId, redId, new BigDecimal(value.toString()));
                        //抢到的金额送到 redis 中
                        valueOperations.set(redId + userId + ":rob", result, 24L, TimeUnit.HOURS);
                        //记录日志
                        log.info("用户抢到红包了：userId = {} key = {} amount = {}", userId, redId, result);

                        return result;
                    }
                }
            } catch (Exception e) {
                log.error("系统发生异常-抢红包-加分布式锁失败！{}", e.fillInStackTrace());
                throw new Exception();
            }
        }
        return new BigDecimal(0);
    }

    private Boolean click(String redId) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String redTotalKey = redId + ":total";
        Object total = valueOperations.get(redTotalKey);
        if (total != null && Integer.valueOf(total.toString()) > 0) {
            return true;
        }
        return false;
    }
}
