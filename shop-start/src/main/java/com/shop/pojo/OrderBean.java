package com.shop.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderBean {
    private String order_code;
    private String order_reciever;
    private String order_createtime;
    private Integer order_statu;
    private String img_src;
    private String name;
    @ApiModelProperty(value = "商品单价")
    private BigDecimal product_price;
    @ApiModelProperty(value = "商品数量")
    private Integer product_count;
}
