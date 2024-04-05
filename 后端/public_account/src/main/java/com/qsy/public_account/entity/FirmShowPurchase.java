package com.qsy.public_account.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author qsy
 * @since 2024-03-26
 */
@TableName("firm_show_purchase")
@Data
//@ApiModel(value = "FirmShowPurchase对象", description = "")
public class FirmShowPurchase implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

//    @ApiModelProperty("@desc  公司位置	@sample 河北省-廊坊市-广阳区 ")
    private String firmLocation;

//    @ApiModelProperty("@desc  公司行业性质	@sample  科技")
    private String firmSectorType;

//    @ApiModelProperty("@desc  公司纳税性质	@sample  一般纳税")
    private String firmTaxableType;

//    @ApiModelProperty("@desc 公司成立日期	@sample  1年")
    private String firmEstablishDate;

//    @ApiModelProperty("@desc  公司转让状态；1为已转让，0为未转让	@sample 1")
    private Integer firmStatusTransfer;

//    @ApiModelProperty("@desc  公司转让价格(单位：元）	@sample  2000")
    private Double firmPriceTransfer;

//    @ApiModelProperty("@desc  创建字段时间	@sample  1年")
    private Date createTime;
    private Date modifyTime;

    @Override
    public String toString() {
        return "FirmShowPurchase{" +
            "id = " + id +
            ", firmLocation = " + firmLocation +
            ", firmSectorType = " + firmSectorType +
            ", firmTaxableType = " + firmTaxableType +
            ", firmEstablishDate = " + firmEstablishDate +
            ", firmStatusTransfer = " + firmStatusTransfer +
            ", firmPriceTransfer = " + firmPriceTransfer +
            ", createTime = " + createTime +
        "}";
    }
}
