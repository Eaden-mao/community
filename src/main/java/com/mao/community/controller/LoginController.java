package com.mao.community.controller;


import com.mao.community.dto.GithubAccessTokenDTO;
import com.mao.community.dto.GithubUserDTO;
import com.mao.community.mapper.UserMapper;
import com.mao.community.model.User;
import com.mao.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


@Controller
public class LoginController {

    @Autowired
    private GithubProvider githubProvider;


    @Value("${github.client_id}")
    private  String clientId;


    @Value("${github.client_secret}")
    private  String clientSecret;

    @Value("${github.redirect_uri}")
    private  String redirectUri;

    @Autowired
    private UserMapper userMapper;

    /**
    github授权之后的回调地址请求
    返回code和state
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){

        GithubAccessTokenDTO githubAccessTokenDTO = new GithubAccessTokenDTO();
        githubAccessTokenDTO.setClient_id(clientId);
        githubAccessTokenDTO.setClient_secret(clientSecret);
        githubAccessTokenDTO.setCode(code);
        githubAccessTokenDTO.setRedirect_uri(redirectUri);
        githubAccessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(githubAccessTokenDTO);
        GithubUserDTO userMessage = githubProvider.getUserMessage(accessToken);
        System.out.println(userMessage.getName());
        if(userMessage!=null){
            //登录成功，将user信息存到session和发放cookie
            User user = new User();
            user.setAccountId(String.valueOf(userMessage.getId()));
            user.setName(userMessage.getName());
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            //根据三方获取的user信息，插入自己的数据库
            userMapper.insert(user);
            //将token放在本地浏览器，方便服务器重启后仍然在登录状态
            response.addCookie(new Cookie("token",token));
            //将用户信息放到session域中
            request.getSession().setAttribute("user",userMessage);
            return "redirect:/";
        }else{
            //登录失败
            return "redirect:/";
        }


    }

}
