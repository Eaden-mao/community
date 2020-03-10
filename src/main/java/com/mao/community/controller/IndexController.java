package com.mao.community.controller;

import com.mao.community.mapper.UserMapper;
import com.mao.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author maoea
 */
@Controller
public class IndexController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        //获取当前浏览器cookies
        Cookie[] cookies = request.getCookies();
        //遍历获取的cookies数组，找到需要的token
        for (Cookie cookie:cookies) {
            if(cookie.getName().equals("token")){
                String token=cookie.getValue();
                //根据token去数据库查找，获取当前用户信息，存在则在session中插入
                User user=userMapper.findUserByToken(token);
                if(user!=null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }


        return "index";
    }
}
