package com.shop.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserShopCar {
    private Integer id;
    private Integer product_num;
    private BigDecimal products_price;
    private String img_src;
    private String name;
}
