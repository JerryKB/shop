package com.shop.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.Api;
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
@TableName("tb_order")
@ApiModel(value="Order对象", description="")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "订单号")
    private String order_code;

    @ApiModelProperty(value = "收货人")
    private String order_reciever;

    @ApiModelProperty(value = "电话号码")
    private Integer order_mobile;

    @ApiModelProperty(value = "创建时间")
    private String order_createtime;

    @ApiModelProperty(value = "支付时间")
    private String order_paytime;

    @ApiModelProperty(value = "订单状态")
    private Integer order_statu;

    @ApiModelProperty(value = "用户id")
    private Integer user_id;

    @ApiModelProperty(value = "订单id")
    private Integer product_id;

    @ApiModelProperty(value = "订单备注")
    private String order_remark;
    @ApiModelProperty(value = "商品单价")
    private BigDecimal product_price;
    @ApiModelProperty(value = "商品数量")
    private Integer product_count;

}
