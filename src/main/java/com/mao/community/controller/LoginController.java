package com.mao.community.controller;


import com.mao.community.entity.GithubAccessTokenEntity;
import com.mao.community.entity.GithubUserEntity;
import com.mao.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class LoginController {

    @Autowired
    private GithubProvider githubProvider;


    /**
    github授权之后的回调地址请求
    返回code和state
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,@RequestParam(name="state") String state){

        GithubAccessTokenEntity githubAccessTokenEntity = new GithubAccessTokenEntity();
        githubAccessTokenEntity.setClient_id("372ef40140dc5c2c9f84");
        githubAccessTokenEntity.setClient_secret("0c812f95aadfcd6c06ada129de1de45bc18aee28");
        githubAccessTokenEntity.setCode(code);
        githubAccessTokenEntity.setRedirect_uri("http://localhost:8080/callback");
        githubAccessTokenEntity.setState(state);
        String accessToken = githubProvider.getAccessToken(githubAccessTokenEntity);
        GithubUserEntity userMessage = githubProvider.getUserMessage(accessToken);
        System.out.println(userMessage.getName());

        return "index";
    }

}
