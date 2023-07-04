package com.shi.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author: 扑腾的江鱼
 * @description: TODO 类描述
 * @create: 2023/06/20 15:33
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RedPacketDto implements Serializable {

    @NotNull
    private Integer userId;
    @NotNull
    private Integer total;
    @NotNull
    private Integer amount;
}
