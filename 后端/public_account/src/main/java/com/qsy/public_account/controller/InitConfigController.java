package com.qsy.public_account.controller;

import com.qsy.public_account.common.Result;
import com.qsy.public_account.entity.InitConfig;
import com.qsy.public_account.service.impl.InitConfigServiceImpl;
import jakarta.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @desc 配置api
 * @author qsy
 * @since 2024-03-20
 */
@Controller
@RequestMapping("/initConfig")
public class InitConfigController {

    private static final Logger logger = LogManager.getLogger(UploadController.class);
    @Resource
    private InitConfigServiceImpl configimpl;


    /**
     * @desc 上传配置，基本上就第一次使用，后续不使用
     * */
    @ResponseBody
    @PostMapping("/add")
    public Result<Boolean> add(InitConfig config){
        logger.info("接收到api请求 : /initConfig/add;");
        Result<Boolean> rs = new Result<>();
        if ( configimpl.addinitConfig(config) == 0){
            rs.setCode(500);
            rs.setMsg("添加初始化列表失败！");
            rs.setData(false);
        }else {
            rs.setCode(200);
            rs.setMsg("添加初始化列表成功！");
            rs.setData(true);
        }

        return rs;
    }

    /**
     * @desc 修改配置
     * */
    @ResponseBody
    @PostMapping("/update")

    public Result<Boolean> update(InitConfig config){
        logger.info("接收到api请求 : /initConfig/update;");
        Result<Boolean> rs = new Result<>();
        if ( configimpl.updateinitConfig(config) == 0){
            rs.setCode(500);
            rs.setMsg("修改初始化列表失败！");
            rs.setData(false);
        }else {
            rs.setCode(200);
            rs.setMsg("修改初始化列表成功！");
            rs.setData(true);
        }
        return rs;

    }


}
