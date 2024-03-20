package com.qsy.public_account.controller;


import com.qsy.public_account.common.Result;
import com.qsy.public_account.entity.FirmInfo;
import com.qsy.public_account.entity.InitConfig;
import com.qsy.public_account.service.impl.FirmImpl;
import com.qsy.public_account.service.impl.InitConfigServiceImpl;
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
    @Resource
    private InitConfigServiceImpl configimpl;

    /**
     * @desc 获取全部公司信息
     * */
    @ResponseBody
    @PostMapping("/firmInfoList")
    public List<FirmInfo> firmInfoList(Integer page, Integer size){
        logger.info("接收到api请求 : /api/firmInfoList;");
        List<FirmInfo> list = firmimpl.getFirmInfoList(page,size);
        return list;
    }

    /**
     * @desc 获取选项列表
     * */
    @ResponseBody
    @PostMapping("/configList")
    public Result<InitConfig> configList(){
        logger.info("接收到api请求 : /api/configList;");
        return configimpl.getinitConfig();
    }

    /**
     * @desc 通过id获取单个公司详情数据
     * */
    @ResponseBody
    @PostMapping("/firmInfoItem")
    public Result<FirmInfo> firmInfoItem(Integer id){
        Result<FirmInfo> rs = new Result<>();
        logger.info("接收到api请求 : /api/firmInfoItem;");
        rs.setData(firmimpl.getFirmInfoItem(id));
        if(rs.getData()== null){
            rs.setCode(500);
            rs.setMsg("查询失败！");
        }else {
            rs.setCode(200);
            rs.setMsg("查询成功！");
        }
        return rs;

    }


}
