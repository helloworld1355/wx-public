package com.qsy.public_account.controller;

import com.qsy.public_account.common.Result;
import com.qsy.public_account.entity.FirmInfo;
import com.qsy.public_account.entity.FirmRecive;

import com.qsy.public_account.service.impl.FirmImpl;
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

    private static final Logger logger = LogManager.getLogger(UploadController.class);

    /**
     * @desc 上传公司信息接口
     * @param recive 接收的参数
     * @return Result<Boolean>
     * */
    @PostMapping("/addFirmInfo")
    @ResponseBody
    public Result<Boolean> uploadFirmInfo(@RequestBody FirmRecive recive)  {
        logger.info("receive : /upload/addFirmInfo");
        Result<Boolean> rs = new Result<>();
        FirmInfo info = new FirmInfo();
        RecToEntity.firmTransfer(recive, info);

//      添加创建时间
        info.setCreateTime(new Date());
        if(firmservice.addFirmInfo(info)){
            rs.setCode(200);
            rs.setMsg("添加成功");
            rs.setData(true);
        }else{
            rs.setCode(500);
            rs.setMsg("插入失败");
            rs.setData(false);
        }

        return rs;
    }

    /**
     * @desc 修改信息
     *
     */
    @PostMapping("/modifyFirmInfo")
    @ResponseBody
    public Result<Boolean> modifyFirmInfo(@RequestBody FirmInfo recive)  {
        logger.info("receive : /upload/modifyFirmInfo");
        Result<Boolean> rs = new Result<>();

        if(firmservice.updateFirmInfo(recive)){
            rs.setCode(200);
            rs.setMsg("修改成功");
            rs.setData(true);
        }else{
            rs.setCode(500);
            rs.setMsg("修改失败");
            rs.setData(false);
        }

        return rs;
    }


}
