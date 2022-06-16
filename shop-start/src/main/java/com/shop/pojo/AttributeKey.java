package com.shop.pojo;

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
@TableName("tb_attribute_key")
@ApiModel(value="AttributeKey对象", description="")
public class AttributeKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type= IdType.AUTO)
    @ApiModelProperty(value = "商品属性id")
    private Integer id;

    @ApiModelProperty(value = "商品属性名")
    private String attribute_name;

    @ApiModelProperty(value = "分类id")
    private Integer category_id;


}
