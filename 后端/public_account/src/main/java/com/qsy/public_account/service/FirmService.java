package com.qsy.public_account.service;


import com.qsy.public_account.entity.FirmInfo;

import java.util.List;

/**
 * firm接口
 * */
public interface FirmService {

//    添加信息
    boolean addFirmInfo(FirmInfo firmInfo);

//    修改信息
    boolean updateFirmInfo(FirmInfo firmInfo);

//      获取信息
    List<FirmInfo> getFirmInfoList(int page, int size);


}
