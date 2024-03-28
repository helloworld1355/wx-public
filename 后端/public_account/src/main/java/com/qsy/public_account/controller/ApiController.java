package com.qsy.public_account.controller;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.qsy.public_account.common.Result;
import com.qsy.public_account.entity.*;
import com.qsy.public_account.service.impl.*;
import com.qsy.public_account.utils.HttpTemplate;
import jakarta.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpRequest;
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

    @Value("${auth.appid}")
    String appid;
    @Value("${auth.secret}")
    String secret;


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

    /** @desc 通过id列表查询数据
     * */
    @ResponseBody
    @PostMapping("/myFirmInfoList")
    public Result<FirmInfo> getMyFirmInfoList(List<Integer> idlist){
        Result<FirmInfo> rs = new Result<>();
        logger.info("接收到api请求 : /api/myFirmInfoList;"+idlist);
        rs.setMsg("成功！");
        rs.setCode(200);
        rs.setData(firmshowimpl.getMyFirmInfoList(idlist));
        return rs;
    }

    @ResponseBody
    @PostMapping("/myFirmPurchaseList")
    public Result<FirmPurchase> getMyFirmPurchaseList(List<Integer> idlist){
        Result<FirmPurchase> rs = new Result<>();
        logger.info("接收到api请求 : /api/myFirmInfoList;");
        rs.setMsg("成功！");
        rs.setCode(200);
        rs.setData(firmPurchaseShowService.getMyFirmInfoList(idlist));
        return rs;
    }


    /** @desc 前端发起请求拉取微信登录，经过处理获取微信用户数据
     * @param code 接收code
     *
     * */
    @ResponseBody
    @GetMapping("/login")
    public void wxH5(@RequestParam("code") String code){
        System.out.println("code ::: "+code);
//        发送请求
        String temp_token = HttpTemplate.httpGet("https://api.weixin.qq.com/sns/oauth2/access_token?"
                + "appid="+ appid
                + "&secret="+secret
                + "&code="+code
                + "&grant_type=authorization_code");
        // 使用 Gson 解析 JSON 字符串为 Token 对象
        Gson gson = new Gson();
        Token token = gson.fromJson(temp_token, Token.class);
        System.out.println("temp_token ::"+temp_token);
        System.out.println("token ::: "+token);

        // 获取用户信息
        System.out.println("getopenid::"+token.getOpenid());
        String user = HttpTemplate.httpGet("https://api.weixin.qq.com/sns/userinfo?" +
                "access_token=" + token.getAccess_token() +"&openid="+token.getOpenid()+"&lang=zh_CN");
        System.out.println("user ::: "+user);
    }

}
