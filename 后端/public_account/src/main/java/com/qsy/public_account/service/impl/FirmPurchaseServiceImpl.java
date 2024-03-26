package com.qsy.public_account.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qsy.public_account.controller.UploadController;
import com.qsy.public_account.entity.FirmInfo;
import com.qsy.public_account.entity.FirmPurchase;
import com.qsy.public_account.mapper.FirmInfomapper;
import com.qsy.public_account.mapper.FirmPurchaseMapper;
import com.qsy.public_account.service.IFirmPurchaseService;
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
 * @since 2024-03-26
 */
@Service
public class FirmPurchaseServiceImpl  implements IFirmPurchaseService {

    @Resource
    private FirmPurchaseMapper firmInfoMapper;

    private static final Logger logger = LogManager.getLogger(UploadController.class);

    /**
     * @desc 添加公司信息
     * @author qsy
     * @date 2023/03/28
     * */
    @Override
    public boolean addFirmPurchase(FirmPurchase firmInfo) {
        int ret = firmInfoMapper.insert(firmInfo);
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
    public boolean updateFirmPurchase(FirmPurchase firmInfo){
        QueryWrapper<FirmPurchase> wrapper = new QueryWrapper<>();
        wrapper.eq("id",firmInfo.getId());
        int ret = firmInfoMapper.update(firmInfo,wrapper);
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
    public List<FirmPurchase> getFirmPurchaseList(int page, int size){
        List<FirmPurchase> list;
        Wrapper<FirmPurchase> wrapper = new QueryWrapper<>();
        Page<FirmPurchase> pageFirm = new Page<>(page ,size);

        IPage<FirmPurchase> resultPage = firmInfoMapper.selectPage(pageFirm,wrapper);

        list = resultPage.getRecords();
        return list;
    }

    /**
     * @desc 根据id查询单个公司信息
     * @author qsy
     * @date 2023/03/28
     * */
    @Override
    public FirmPurchase getFirmPurchaseItem(int id){
        return firmInfoMapper.selectById(id);
    }
}
