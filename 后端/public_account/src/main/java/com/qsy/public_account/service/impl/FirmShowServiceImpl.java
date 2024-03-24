package com.qsy.public_account.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qsy.public_account.controller.UploadController;
import com.qsy.public_account.entity.FirmInfo;
import com.qsy.public_account.entity.FirmShow;
import com.qsy.public_account.mapper.FirmShowMapper;
import com.qsy.public_account.service.IFirmShowService;
import jakarta.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qsy
 * @since 2024-03-23
 */
@Service
public class FirmShowServiceImpl implements IFirmShowService {

    @Resource
    private FirmShowMapper firmShowMapper;

    private static final Logger logger = LogManager.getLogger(UploadController.class);

    /**
     * @desc 添加公司信息
     * @author qsy
     * @date 2023/03/28
     * */
    @Override
    public boolean addFirmShow(FirmShow firmInfo) {
        int ret = firmShowMapper.insert(firmInfo);
        if (ret > 0){
            logger.info("插入成功！");
            return true;
        }else{
            logger.error("插入失败");
            return false;
        }
    }

    /**
     * @desc 更新公司信息
     * @author qsy
     * @date 2023/03/28
     * */
    @Override
    public boolean updateFirmShow(FirmShow firmInfo){
        QueryWrapper<FirmShow> wrapper = new QueryWrapper<>();
        wrapper.eq("id",firmInfo.getId());
        int ret = firmShowMapper.update(firmInfo,wrapper);
        if (ret > 0){
            logger.info("更新成功！");
            return true;
        }else{
            return false;
        }
    }


    /**
     * @desc 分页查询公司信息
     * @author qsy
     * @date 2023/03/28
     * */
    @Override
    public List<FirmShow> getFirmInfoList(int page, int size, String location, String year, String sector, String taxable){
        List<FirmShow> list;
        QueryWrapper<FirmShow> wrapper = new QueryWrapper<>();
        wrapper.eq("firm_status_transfer",0);
        if(!"".equals(location))
            wrapper.eq("firm_location",location);
        if(!"".equals(sector))
        wrapper.eq("firm_sector_type",sector);
        if (!"".equals(taxable))
        wrapper.eq("firm_taxable_type",taxable);
        if (!"".equals(year))
        wrapper.eq("firm_establish_date",year);
        Page<FirmShow> pageFirm = new Page<>(page ,size);

        IPage<FirmShow> resultPage = firmShowMapper.selectPage(pageFirm,wrapper);
        list = resultPage.getRecords();
        return list;
    }



}
