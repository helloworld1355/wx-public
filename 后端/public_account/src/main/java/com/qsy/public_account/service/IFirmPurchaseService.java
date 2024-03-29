package com.qsy.public_account.service;


import com.qsy.public_account.entity.FirmPurchase;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qsy
 * @since 2024-03-26
 */
public interface IFirmPurchaseService  {


    //    添加信息
    boolean addFirmPurchase(FirmPurchase firmInfo);

    //    修改信息
    boolean updateFirmPurchase(FirmPurchase firmInfo);
    boolean deleteFirmPurchase(Integer id);

    //      获取信息
    List<FirmPurchase> getFirmPurchaseList(int page, int size);

    FirmPurchase getFirmPurchaseItem(int id);
}
