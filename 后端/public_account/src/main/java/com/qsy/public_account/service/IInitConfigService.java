package com.qsy.public_account.service;

import com.qsy.public_account.common.Result;
import com.qsy.public_account.entity.InitConfig;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qsy
 * @since 2024-03-20
 */
public interface IInitConfigService  {

    Result<InitConfig> getinitConfig();

    int  addinitConfig(InitConfig initConfig);

    int updateinitConfig(InitConfig initConfig);
}
