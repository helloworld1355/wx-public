package com.qsy.public_account.service;


import com.qsy.public_account.entity.FirmShow;
import com.qsy.public_account.entity.FirmShowPurchase;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qsy
 * @since 2024-03-26
 */
public interface IFirmShowPurchaseService  {

    public boolean addFirmShow(FirmShowPurchase firmInfo);
    public boolean updateFirmShow(FirmShowPurchase firmInfo);
    boolean deleteFirmShowPurchase(Integer id);
    public List<FirmShowPurchase> getFirmInfoList(int page, int size, String location, String year, String sector, String taxable);

    public List<FirmShowPurchase> getMyFirmInfoList(List<Integer> idlist);
}
