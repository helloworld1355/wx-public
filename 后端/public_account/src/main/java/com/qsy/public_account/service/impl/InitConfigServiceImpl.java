package com.qsy.public_account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qsy.public_account.common.Result;
import com.qsy.public_account.entity.InitConfig;
import com.qsy.public_account.mapper.InitConfigMapper;
import com.qsy.public_account.service.IInitConfigService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qsy
 * @since 2024-03-20
 */
@Service
public class InitConfigServiceImpl implements IInitConfigService {

    @Resource
    private InitConfigMapper initConfigMapper;

    @Override
    public Result<InitConfig> getinitConfig(){
        Result<InitConfig> rs = new Result<>();
        QueryWrapper<InitConfig> queryWrapper = new QueryWrapper<>();
        InitConfig config = initConfigMapper.selectOne(queryWrapper);
        if(config == null){
            rs.setCode(500);
            rs.setMsg("获取初始化列表失败！");
            rs.setData("");
        }else{
            rs.setCode(200);
            rs.setMsg("获取初始化列表成功！");
            rs.setData(config);
        }
        System.out.println("config = "+config);
        return rs;
    }

    @Override
    public int addinitConfig(InitConfig initConfig){
        return initConfigMapper.insert(initConfig);
    }


    @Override
    public int updateinitConfig(InitConfig initConfig){
        return initConfigMapper.updateById(initConfig);
    }
}
