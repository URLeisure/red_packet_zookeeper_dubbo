package com.shi.api.serivce;

import com.shi.api.dto.RedPacketDto;

import java.math.BigDecimal;

/**
 * @author: 扑腾的江鱼
 * @description: TODO 类描述
 * @create: 2023/06/20 15:30
 **/

public interface IRedPacketService {
    //发红包核心业务
    public String handOut(RedPacketDto dto) throws Exception;

    BigDecimal robLock(Integer userId, String redId) throws Exception;
}
