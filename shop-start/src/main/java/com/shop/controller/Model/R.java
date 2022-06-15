package com.shop.controller.Model;


import lombok.Data;

@Data
//模型类，用于后端与前端进行数据统一
public class R {
    //判断是发生异常导致的null还是未取到导致的null
    private Boolean flag;
    //将向前端传的json格式各数据封装到data里，前端取数据只要取data里数据使得格式统一
    private Object data;
    //向前端传一个信息
    private String msg;
    public  R(){}
    public R(Boolean flag){
        this.flag=flag;
    }
    public R(Boolean flag,Object data){
        this.data=data;
        this.flag=flag;
    }
    public R(Boolean flag,String msg){
        this.flag=flag;
        this.msg=msg;
    }
    public R(String msg){
        this.flag=false;
        this.msg=msg;
    }
}
