package com.shop.pojo;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Wyx
 * @since 2022-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_product_specs")
@ApiModel(value="ProductSpecs对象", description="")
public class ProductSpecs implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type= IdType.AUTO)
    @ApiModelProperty(value = "商品规格id")
    private Integer id;

    @ApiModelProperty(value = "商品id")
    private Integer product_id;

    @ApiModelProperty(value = "商品规格名称")
    private String name;

    @ApiModelProperty(value = "商品规格信息")
    private String product_specs;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;

    @ApiModelProperty(value = "库存")
    private Integer amount;

    @ApiModelProperty(value = "商品图片")
    private String img;


}
