package com.shop.pojo;

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
@TableName("tb_attribute_value")
@ApiModel(value="AttributeValue对象", description="")
public class AttributeValue implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "属性值id")
    private Integer id;

    @ApiModelProperty(value = "属性值")
    private String attribute_value;

    @ApiModelProperty(value = "属性名id")
    private Integer attribute_id;


}
