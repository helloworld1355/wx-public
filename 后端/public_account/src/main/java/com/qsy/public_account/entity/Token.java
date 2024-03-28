package com.qsy.public_account.entity;

import lombok.Data;

@Data
public class Token {
    // {"errcode":40163,"errmsg":"code been used, rid: 660556fb-256121bc-6902deca"}
    private String access_token;//获取到的凭证
    private String openid;
}
