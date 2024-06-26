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
    public Result<Integer> uploadFirmInfo(@RequestBody FirmRecive recive)  {
        logger.info("接收到api请求 : /upload/addFirmInfo;");
        Result<Integer> rs = new Result<>();
        FirmInfo info = new FirmInfo();
        RecToEntity.firmTransfer(recive, info);
        FirmShow firmshow = new FirmShow();
        info.setCreateTime(new Date());
        info.setModifyTime(info.getCreateTime());
        info.setFirmStatusTransfer(0);

        if (firmservice.addFirmInfo(info)){
            RecToEntity.firmTransfer(info, firmshow);
            // 1表示转让类型

            if(showService.addFirmShow(firmshow)){
                rs.setCode(200);
                rs.setMsg("添加成功");
                rs.setData(firmshow.getId());
            }else{
                rs.setCode(500);
                rs.setMsg("插入失败");
                rs.setData(false);
            }
        }else{
            rs.setCode(500);
            rs.setMsg("插入失败");
            rs.setData(-1);
        }
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
        recive.setModifyTime(new Date());
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

    @PostMapping("/deleteFirmInfo")
    @ResponseBody
    public Result<Boolean> deleteFirmInfo(@RequestBody Integer id)  {
        logger.info("接收到api请求 : /upload/deleteFirmInfo;");
        Result<Boolean> rs = new Result<>();
        boolean ret = firmPurchaseService.deleteFirmPurchase(id);
        showService.deleteFirmShow(id);
        if(ret){
            rs.setData(ret);
            rs.setCode(200);
            rs.setMsg("删除成功！");
        }else{
            rs.setData(ret);
            rs.setCode(500);
            rs.setMsg("删除失败！");
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
    public Result<Integer> uploadFirmPurchase(@RequestBody FimrRecivePurchase recive)  {
        logger.info("接收到api请求 : /upload/addFirmInfo;");
        Result<Integer> rs = new Result<>();
        FirmPurchase purchase = new FirmPurchase();
        RecToEntity.firmTransfer(recive, purchase);
        FirmShowPurchase firmshow = new FirmShowPurchase();
        purchase.setCreateTime(new Date());
        purchase.setModifyTime(purchase.getCreateTime());
        purchase.setFirmStatusTransfer(0);

        if (firmPurchaseService.addFirmPurchase(purchase)){
            RecToEntity.firmTransfer(purchase, firmshow);
            // 0表示求购类型
            if(showPurchaseSevice.addFirmShow(firmshow)){
                rs.setCode(200);
                rs.setMsg("添加成功");
                rs.setData(firmshow.getId());
            }else{
                rs.setCode(500);
                rs.setMsg("插入失败");
                rs.setData(-1);
            }
        }else{
            rs.setCode(500);
            rs.setMsg("插入失败");
            rs.setData(-1);
        }
        return rs;
    }







    /**
     * @desc 修改公司详情信息
     *
     */
    @PostMapping("/modifyFirmPurchase")
    @ResponseBody
    public Result<Boolean> modifyFirmPurchase(@RequestBody FirmPurchase recive)  {
        logger.info("receive : /upload/modifyFirmPurchase");
        Result<Boolean> rs = new Result<>();
        FirmShowPurchase firmshow = new FirmShowPurchase();
        recive.setModifyTime(new Date());
        if(firmPurchaseService.updateFirmPurchase(recive)){
            RecToEntity.firmTransfer(recive, firmshow);

            if(showPurchaseSevice.updateFirmShow(firmshow)){
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

    @PostMapping("/deleteFirmPurchase")
    @ResponseBody
    public Result<Boolean> deleteFirmPurchase(@RequestBody Integer id)  {
        Result<Boolean> rs = new Result<>();
        boolean ret = firmPurchaseService.deleteFirmPurchase(id);
        showPurchaseSevice.deleteFirmShowPurchase(id);
        if(ret){
            rs.setData(ret);
            rs.setCode(200);
            rs.setMsg("删除成功！");
        }else{
            rs.setData(ret);
            rs.setCode(500);
            rs.setMsg("删除失败！");
        }
        return rs;
    }





}
