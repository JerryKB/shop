package com.shop.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("tb_shopcar")
@ApiModel(value="Shopcar对象", description="")
public class Shopcar implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "购物车id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商品数量")
    private Integer product_num;

    @ApiModelProperty(value = "商品id")
    private Integer product_id;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal products_price;

    @ApiModelProperty(value = "订单地址")
    private String order_address;

    @ApiModelProperty(value = "用户id")
    private Integer user_id;
}
