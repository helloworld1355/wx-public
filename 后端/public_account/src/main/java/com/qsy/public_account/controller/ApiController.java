package com.qsy.public_account.controller;


import com.qsy.public_account.entity.FirmInfo;
import com.qsy.public_account.service.impl.FirmImpl;
import jakarta.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 公共api，供公众号网站调用
 * @author qsy
 * @version 1.0
 * @date 2023/4/13 15:09
 * @TODO     添加缓存
 *  */
@RequestMapping("/api")
@Controller
public class ApiController {

    private static final Logger logger = LogManager.getLogger(UploadController.class);

    @Resource
    private FirmImpl firmimpl;

    /**
     * 获取全部公司信息
     * */
    @ResponseBody
    @PostMapping("/firmInfoList")
    public List<FirmInfo> firmInfoList(Integer page, Integer size){
        logger.info("接收到api请求 : /api/firmInfoList;");
        List<FirmInfo> list = firmimpl.getFirmInfoList(page,size);
        return list;
    }


}
