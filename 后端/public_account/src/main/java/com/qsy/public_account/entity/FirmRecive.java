package com.qsy.public_account.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FirmRecive {
    //    @ApiModelProperty("@desc  公司名称	@sample  xxx科技公司")
    private String firmName;

    //    @ApiModelProperty("@desc  公司位置	@sample 河北省-廊坊市-广阳区 ")
    private String firmLocation;

    //    @ApiModelProperty("@desc  公司位置详细地址	@sample  爱民东道133号北华航天工业学院")
    private String firmLocationDetail;

    //    @ApiModelProperty("@desc  公司行业性质	@sample  科技")
    private String firmSectorType;

    //    @ApiModelProperty("@desc  公司纳税性质	@sample  一般纳税")
    private String firmTaxableType;

    //    @ApiModelProperty("@desc 公司成立日期	@sample  \"2021-01-01 00:00:00\"")
    private LocalDate firmEstablishDate;

    //    @ApiModelProperty("@desc  公司营业范围	@sample  科技、餐饮")
    private String firmBusinessScope;

    //    @ApiModelProperty("@desc  公司归属税局	@sample  廊坊市税务局")
    private String firmTaxBelong;

    //    @ApiModelProperty("@desc  公司联系人姓名	@sample  张三")
    private String firmContacts;

    //    @ApiModelProperty("@desc  公司联系人电话	@sample  12345678910")
    private String firmContactsPhone;

    //    @ApiModelProperty("@desc  公司转让价格(单位：万元）	@sample  20.2")
    private Double firmPriceTransfer;

    //    @ApiModelProperty("@desc  公司注册资本	@sample  300.02")
    private Double firmRegistCapital;

}
