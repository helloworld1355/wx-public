package com.qsy.public_account.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author qsy
 * @since 2024-03-18
 */
@TableName("firm_info")
@Data
//@ApiModel(value = "FirmInfo对象", description = "")
public class FirmInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

//    @ApiModelProperty("@desc 主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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

//    @ApiModelProperty("@desc 公司成立日期	@sample  \"1年\"")
    private String firmEstablishDate;

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

//    @ApiModelProperty("@desc  暂不使用；公司转让状态")
    private Integer firmStatusTransfer;

//    @ApiModelProperty("@desc  暂不使用；创建字段时间")
    private Date createTime;

//    @ApiModelProperty("@desc  暂不使用；最后修改字段时间")
    private Date modifyTime;


    @Override
    public String toString() {
        return "FirmInfo{" +
            "id = " + id +
            ", firmName = " + firmName +
            ", firmLocation = " + firmLocation +
            ", firmLocationDetail = " + firmLocationDetail +
            ", firmSectorType = " + firmSectorType +
            ", firmTaxableType = " + firmTaxableType +
            ", firmEstablishDate = " + firmEstablishDate +
            ", firmBusinessScope = " + firmBusinessScope +
            ", firmTaxBelong = " + firmTaxBelong +
            ", firmContacts = " + firmContacts +
            ", firmContactsPhone = " + firmContactsPhone +
            ", firmPriceTransfer = " + firmPriceTransfer +
            ", firmRegistCapital = " + firmRegistCapital +
            ", firmStatusTransfer = " + firmStatusTransfer +
            ", createTime = " + createTime +
            ", modifyTime = " + modifyTime +
        "}";
    }
}
