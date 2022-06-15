package com.shop.controller;

import com.shop.pojo.RespBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * @author wyx
 * @date 2022/6/13 11:33
 */
@RestController
@RequestMapping("/mail")
public class MailController {
    private MailSender mailSender;
    private SimpleMailMessage simpleMailMessage;
    @ApiOperation(value = "邮箱验证码")
    @GetMapping(value = "/getInfo/{email}")
    public  RespBean send( @PathVariable String email, HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println(email);
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host","smtp.qq.com");
        properties.setProperty("mail.smtp.port","25");
        properties.setProperty("mail.smtp.auth","true");
        Session session = Session.getInstance(properties);
        session.setDebug(true);
        Transport transport = session.getTransport();
        transport.connect("smtp.qq.com","1584472908","mqxosqijjqvajhef");

        Message message = createSimpleMail(email,request,response,session);

        transport.sendMessage(message,message.getAllRecipients());
        transport.close();
        return RespBean.success("发送成功","");
    }
    private static Message createSimpleMail(String email,HttpServletRequest request, HttpServletResponse response,Session session) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom("1584472908@qq.com");
        System.out.println(email+"111111111111111");
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(email));
        message.setSubject("注册验证");
        message.setSentDate(new Date());
        String str="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb=new StringBuilder(4);
        for(int i=0;i<4;i++)
        {
            char ch=str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        System.out.println(sb.toString());
        HttpSession session1 =request.getSession();
        session1.setAttribute("checkCode",sb.toString());
        System.out.println(sb.toString());
        message.setText("验证码是："+sb.toString());
        return message;
    }
}
