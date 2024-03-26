package com.qsy.public_account.controller;


import com.qsy.public_account.common.Result;
import com.qsy.public_account.entity.*;
import com.qsy.public_account.service.impl.*;
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
    private FirmShowServiceImpl firmshowimpl;
    @Resource
    private FirmShowPurchaseServiceImpl firmPurchaseShowService;
    @Resource FirmPurchaseServiceImpl firmPurchaseService;
    @Resource
    private InitConfigServiceImpl configimpl;


    /**
     * @desc 获取展示首页公司信息
     * */
    @ResponseBody
    @PostMapping("/firmShowInfo")
    public List<FirmShow> firmInfoList(Integer page, Integer size, String location,  String year, String sector, String taxable){
        logger.info("接收到api请求 : /api/firmInfoList;");
        System.out.println("location::"+location+"  year::"+year+"  sector::"+sector+"  taxable::"+taxable);
        List<FirmShow> list = firmshowimpl.getFirmInfoList(page,size,location,year,sector,taxable);
        System.out.println("list::"+list);
        return list;
    }

    /**
     * @desc 获取展示首页求购公司信息
     * */
    @ResponseBody
    @PostMapping("/firmShowPurchase")
    public List<FirmShowPurchase> firmPurchaseList(Integer page, Integer size, String location, String year, String sector, String taxable){
        logger.info("接收到api请求 : /api/FirmShowPurchase;");
        System.out.println("location::"+location+"  year::"+year+"  sector::"+sector+"  taxable::"+taxable);
        List<FirmShowPurchase> list = firmPurchaseShowService.getFirmInfoList(page,size,location,year,sector,taxable);
        System.out.println("list::"+list);
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

    /**
     * @desc 通过id获取单个求购公司数据
     * */
    @ResponseBody
    @PostMapping("/firmPurchaseItem")
    public Result<FirmPurchase> firmPurchaseItem(Integer id){
        Result<FirmPurchase> rs = new Result<>();
        logger.info("接收到api请求 : /api/FirmPurchase;"+id);
        rs.setData(firmPurchaseService.getFirmPurchaseItem(id));
        System.out.println("rs = "+rs);
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
