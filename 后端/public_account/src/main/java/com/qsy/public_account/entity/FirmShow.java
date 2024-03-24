package com.qsy.public_account.entity;

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
 * @since 2024-03-23
 */
@TableName("firm_show")
@Data
//@ApiModel(value = "FirmShow对象", description = "")
public class FirmShow implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

//    @ApiModelProperty("@desc  公司id，与公司信息id相同")
    private Integer id;

//    @ApiModelProperty("@desc  公司名称	@sample  xxx科技公司")
    private String firmName;

//    @ApiModelProperty("@desc  公司位置	@sample 河北省-廊坊市-广阳区 ")
    private String firmLocation;

//    @ApiModelProperty("@desc  创建字段时间	@sample  1年")
    private Date createTime;

//    @ApiModelProperty("@desc  公司行业性质	@sample  科技")
    private String firmSectorType;

//    @ApiModelProperty("@desc  公司纳税性质	@sample  一般纳税")
    private String firmTaxableType;

//    @ApiModelProperty("@desc  公司转让状态，0表示未转让，1表示已转让")
    private Integer firmStatusTransfer;

    private String firmEstablishDate;

    private Double firmPriceTransfer;

    @Override
    public String toString() {
        return "FirmShow{" +
            "id = " + id +
            ", firmName = " + firmName +
            ", firmLocation = " + firmLocation +
            ", createTime = " + createTime +
            ", firmSectorType = " + firmSectorType +
            ", firmTaxableType = " + firmTaxableType +
            ", firmStatusTransfer = " + firmStatusTransfer +
        "}";
    }
}
