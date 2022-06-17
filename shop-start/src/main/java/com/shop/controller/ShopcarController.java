package com.shop.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shop.pojo.RespBean;
import com.shop.pojo.Shopcar;
import com.shop.pojo.UserShopCar;
import com.shop.service.IOrderService;
import com.shop.service.IShopcarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping("/shopcar")
public class ShopcarController {
    @Autowired
    IShopcarService shopcarService;
    @Autowired
    IOrderService orderService;
    @PostMapping("/add")
    public RespBean add(@RequestBody Shopcar shopcar){
        return shopcarService.add(shopcar);

    }
    @GetMapping("/showALl")
    public IPage<Shopcar> findAll(Integer userId,Integer CurrentPage,Integer Size,HttpServletRequest httpServletRequest){
        return shopcarService.findAll(userId,CurrentPage,Size,httpServletRequest);
    }
    @GetMapping("/transferOrder")
    public void transfer(@RequestBody Shopcar shopcar, String order_receiver, Integer order_mobile, String order_remark, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String orderCode = orderService.saveOrder(shopcar,order_receiver,order_mobile,order_remark);
        shopcarService.delete(shopcar);
        httpServletResponse.sendRedirect("/order/orderDetail?order_code="+orderCode);
    }
    @GetMapping("/getAll/{id}")
    public List<UserShopCar> getAll(@PathVariable Integer id){
        return shopcarService.getAll(id);
    }
    @DeleteMapping("/deleteShopCar/{id}")
    public RespBean deleteShopCar(@PathVariable Integer id){
        return shopcarService.delete(id);

    }
    @GetMapping("/gets/{id}")
    public int getNum(@PathVariable Integer id){
        return shopcarService.getCarsNum(id);
    }

}
