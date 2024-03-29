package com.qsy.public_account.service;


import com.qsy.public_account.entity.FirmInfo;
import com.qsy.public_account.entity.FirmShow;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qsy
 * @since 2024-03-23
 */
public interface IFirmShowService {

    public boolean addFirmShow(FirmShow firmInfo);
    public boolean updateFirmShow(FirmShow firmInfo);
    public List<FirmShow> getFirmInfoList(int page, int size, String location, String year, String sector, String taxable);
    public boolean deleteFirmShow(Integer id);

    List<FirmShow> getMyFirmInfoList(List<Integer> idlist);
}
