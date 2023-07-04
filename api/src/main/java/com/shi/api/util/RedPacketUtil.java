package com.shi.api.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author: 扑腾的江鱼
 * @description: TODO 类描述
 * @create: 2023/06/20 16:25
 **/

public class RedPacketUtil {

    public static List<Integer> divideRedPacket(Integer totalAmount, Integer totalPeopleNum) {
        List<Integer> list = new ArrayList<>();

        //再次判断合法性
        if (totalAmount > 0 && totalPeopleNum > 0) {
            //记录剩余总金额
            Integer restAmount = totalAmount;
            //记录剩余中人数
            Integer restPeopleNum = totalPeopleNum;
            //定义产生随机数的实例对象
            Random random = new Random();
            //先生成 totalPeopleNum - 1 个小红包，剩下的就是最后一个红包(也是防止分母为 0)
            for (int i = 0; i < totalPeopleNum - 1; i++) {
                //红包金额不能是 0
                int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
                list.add(amount);
                //更新剩余金额
                restAmount -= amount;
                restPeopleNum --;
            }
            list.add(restAmount);
        }
        return list;
    }
}
