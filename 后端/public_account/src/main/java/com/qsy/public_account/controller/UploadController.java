package com.qsy.public_account.controller;

import com.qsy.public_account.common.Result;
import com.qsy.public_account.entity.FirmInfo;
import com.qsy.public_account.entity.FirmRecive;
import com.qsy.public_account.utils.RecToEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;


@RestController("/upload")

public class UploadController {

    private static final Logger logger = LogManager.getLogger(UploadController.class);


    @PostMapping("")
    @ResponseBody
    public Result<Boolean> uploadFirmInfo(@RequestBody FirmRecive recive)  {
        Result<Boolean> rs = new Result<>();
        FirmInfo info = new FirmInfo();
        RecToEntity.firmTransfer(recive, info);
        logger.info("上传数据:{}",info);
        rs.setCode(200);
        rs.setMsg("成功");
        rs.setData(null);
        return rs;
    }



}
