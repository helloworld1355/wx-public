package com.qsy.public_account.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author qsy
 * @since 2024-03-20
 */
@TableName("init_config")
@Data
//@ApiModel(value = "InitConfig对象", description = "")
public class InitConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

//    @ApiModelProperty("@desc  行业类型数组")
    private String sectors;

//    @ApiModelProperty("@desc  纳税性质数组")
    private String taxables;

//    @ApiModelProperty("@desc  图片路径数组")
    private String imgSrc;

//    @ApiModelProperty("@desc  年份选择器 @sample  1年；2年；3年及之后")
    private String years;

    @Override
    public String toString() {
        return "InitConfig{" +
            "id = " + id +
            ", sectors = " + sectors +
            ", taxables = " + taxables +
            ", imgSrc = " + imgSrc +
            ", years = " + years +
        "}";
    }
}
