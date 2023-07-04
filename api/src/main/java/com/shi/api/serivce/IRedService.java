package com.shi.api.serivce;

import com.shi.api.dto.RedPacketDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: 扑腾的江鱼
 * @description: TODO 类描述
 * @create: 2023/06/20 16:42
 **/

public interface IRedService {
    void recordRedPacket(RedPacketDto dto, String redId, List<Integer> list);

    void recordRobRedPacket(Integer userId, String redId, BigDecimal amount) throws Exception;
}
