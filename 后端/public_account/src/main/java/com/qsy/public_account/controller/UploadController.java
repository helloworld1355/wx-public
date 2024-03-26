package com.qsy.public_account.controller;

import com.qsy.public_account.common.Result;
import com.qsy.public_account.entity.*;

import com.qsy.public_account.service.impl.FirmImpl;
import com.qsy.public_account.service.impl.FirmPurchaseServiceImpl;
import com.qsy.public_account.service.impl.FirmShowPurchaseServiceImpl;
import com.qsy.public_account.service.impl.FirmShowServiceImpl;
import com.qsy.public_account.utils.RecToEntity;
import jakarta.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


/**
 * 上传接口
 * */
@Controller
@RequestMapping("/upload")
public class UploadController {

    @Resource
    private FirmImpl firmservice;
    @Resource
    private FirmShowServiceImpl showService;
    @Resource
    private FirmShowPurchaseServiceImpl showPurchaseSevice;
    @Resource
    private FirmPurchaseServiceImpl firmPurchaseService;

    private static final Logger logger = LogManager.getLogger(UploadController.class);

    /**
     * @desc 上传公司信息接口
     * @param recive 接收的参数
     * @return Result<Boolean>
     * */
    @PostMapping("/addFirmInfo")
    @ResponseBody
    public Result<Boolean> uploadFirmInfo(@RequestBody FirmRecive recive)  {
        logger.info("接收到api请求 : /upload/addFirmInfo;");
        Result<Boolean> rs = new Result<>();
        FirmInfo info = new FirmInfo();
        RecToEntity.firmTransfer(recive, info);
        FirmShow firmshow = new FirmShow();
        info.setCreateTime(new Date());
        info.setFirmStatusTransfer(0);

        if (firmservice.addFirmInfo(info)){
            RecToEntity.firmTransfer(info, firmshow);
            // 1表示转让类型

            if(showService.addFirmShow(firmshow)){
                rs.setCode(200);
                rs.setMsg("添加成功");
                rs.setData(true);
            }else{
                rs.setCode(500);
                rs.setMsg("插入失败");
                rs.setData(false);
            }
        }else{
            rs.setCode(500);
            rs.setMsg("插入失败");
            rs.setData(false);
        }
//      添加创建时间



        return rs;
    }

    /**
     * @desc 修改公司详情信息
     *
     */
    @PostMapping("/modifyFirmInfo")
    @ResponseBody
    public Result<Boolean> modifyFirmInfo(@RequestBody FirmInfo recive)  {
        logger.info("receive : /upload/modifyFirmInfo");
        Result<Boolean> rs = new Result<>();
        FirmShow firmshow = new FirmShow();
        if(firmservice.updateFirmInfo(recive)){
            RecToEntity.firmTransfer(recive, firmshow);

            if(showService.updateFirmShow(firmshow)){
                rs.setCode(200);
                rs.setMsg("修改成功");
                rs.setData(true);
            }else{
                rs.setCode(500);
                rs.setMsg("修改失败");
                rs.setData(false);
            }
        }else{
            rs.setCode(500);
            rs.setMsg("修改失败");
            rs.setData(false);
        }

        return rs;
    }


    /**
     * @desc 上传求购公司信息
     * @param recive
     * @return true or false
     * */
    @PostMapping("/addFirmPurchase")
    @ResponseBody
    public Result<Boolean> uploadFirmPurchase(@RequestBody FimrRecivePurchase recive)  {
        logger.info("接收到api请求 : /upload/addFirmInfo;");
        Result<Boolean> rs = new Result<>();
        FirmPurchase purchase = new FirmPurchase();
        RecToEntity.firmTransfer(recive, purchase);
        FirmShowPurchase firmshow = new FirmShowPurchase();
        purchase.setCreateTime(new Date());
        purchase.setFirmStatusTransfer(0);

        if (firmPurchaseService.addFirmPurchase(purchase)){
            RecToEntity.firmTransfer(purchase, firmshow);
            // 0表示求购类型
            if(showPurchaseSevice.addFirmShow(firmshow)){
                rs.setCode(200);
                rs.setMsg("添加成功");
                rs.setData(true);
            }else{
                rs.setCode(500);
                rs.setMsg("插入失败");
                rs.setData(false);
            }
        }else{
            rs.setCode(500);
            rs.setMsg("插入失败");
            rs.setData(false);
        }
//      添加创建时间



        return rs;
    }


}
