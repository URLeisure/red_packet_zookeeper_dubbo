package com.shi.main.provider.service;

import com.shi.api.dto.RedPacketDto;
import com.shi.api.model.RedDetail;
import com.shi.api.model.RedRecord;
import com.shi.api.model.RedRobRecord;
import com.shi.api.serivce.IRedService;
import com.shi.main.provider.mapper.RedDetailMapper;
import com.shi.main.provider.mapper.RedRecordMapper;
import com.shi.main.provider.mapper.RedRobRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author: 扑腾的江鱼
 * @description: TODO 类描述
 * @create: 2023/06/20 16:50
 **/
@Service
public class RedServiceImp implements IRedService {
    //定义日志
    private static final Logger log = LoggerFactory.getLogger(RedServiceImp.class);

    @Autowired
    private RedDetailMapper redDetailMapper;//小红包

    @Autowired
    private RedRecordMapper redRecordMapper;//红包

    @Autowired
    private RedRobRecordMapper redRobRecordMapper;//抢红包

    @Async
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void recordRedPacket(RedPacketDto dto, String redId, List<Integer> list) {
        RedRecord redRecord = new RedRecord();
        redRecord.setUserId(dto.getUserId());
        redRecord.setAmount(new BigDecimal(dto.getAmount()));
        redRecord.setTotal(dto.getTotal());
        redRecord.setRedPacket(redId);
        redRecord.setCreateTime(new Date());
        //存红包
        redRecordMapper.insertSelective(redRecord);

        //存小红包
        for(Integer i : list){
            RedDetail redDetail = new RedDetail();
            redDetail.setRecordId(redRecord.getId());
            redDetail.setCreateTime(new Date());
            redDetail.setAmount(new BigDecimal(i));
            redDetailMapper.insertSelective(redDetail);
        }
    }
    @Async
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void recordRobRedPacket(Integer userId, String redId, BigDecimal amount) throws Exception {
        RedRobRecord redRobRecord = new RedRobRecord();
        redRobRecord.setUserId(userId);
        redRobRecord.setRedPacket(redId);
        redRobRecord.setAmount(amount);
        redRobRecord.setRobTime(new Date());
        redRobRecordMapper.insertSelective(redRobRecord);
    }
}
