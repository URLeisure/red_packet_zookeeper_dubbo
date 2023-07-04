package com.shi.main.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shi.api.dto.RedPacketDto;
import com.shi.api.enums.StatusCode;
import com.shi.api.response.BaseResponse;
import com.shi.api.serivce.IRedPacketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


/**
 * @author: 扑腾的江鱼
 * @description: TODO 类描述
 * @create: 2023/06/20 14:53
 **/
@RestController
public class RedPacketController {
    //定义日志
    private static final Logger log = LoggerFactory.getLogger(RedPacketController.class);

    //定义请求路径前缀
    private static final String prefix ="red/packet";

    //注入红包逻辑
    @Reference(
            version = "1.0.0",interfaceClass = IRedPacketService.class,
            interfaceName = "com.shi.api.service.IRedPacketService",
            timeout =120000
    )
    private IRedPacketService iRedPacketService;

    //发红包
    @PostMapping(value = prefix + "/hand/out",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse handOut(@Validated  @RequestBody RedPacketDto dto, BindingResult result) {
        if(result.hasErrors()){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
        try{
            String redId = iRedPacketService.handOut(dto);
            baseResponse.setData(redId);
        }catch(Exception e){
            log.error("发红包异常：dto={}",dto,e.fillInStackTrace());
            baseResponse = new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return baseResponse;
    }

    //抢红包
    @GetMapping(value = prefix + "/rob")
    public BaseResponse robLock(@RequestParam Integer userId,@RequestParam String redId) {
        BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
        try{
            //尝试获取小红包
            BigDecimal result = iRedPacketService.robLock(userId,redId);
            if(result != null){
                baseResponse.setData(result);
            }else{
                log.error("红包已抢完");
                baseResponse = new BaseResponse(StatusCode.Fail.getCode(),"红包已抢完");
            }
        }catch (Exception e){
            log.error("抢红包异常，userId = {} redId = {}",userId,redId,e.fillInStackTrace());
            baseResponse = new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }

        return baseResponse;
    }

}
