package com.mao.community.provider;


import com.alibaba.fastjson.JSON;
import com.mao.community.dto.GithubAccessTokenDTO;
import com.mao.community.dto.GithubUserDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author maoea
 * 提供github三方登录的具体实现方法，类似一个工具类
 */
@Component
public class GithubProvider {


    /**
     * 通过okhttp发送post请求获取access token
     * @param githubAccessTokenDTO
     * @return
     * JSON.toJSONString(githubAccessTokenEntity) 将对象转换为json字符串
     * JSON.parseObject(string, GithubUserEntity.class) 把json字符串转化为实体类（当前方法为GithubUserEntity）
     */
    public String getAccessToken(GithubAccessTokenDTO githubAccessTokenDTO){
        MediaType mediaType= MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(githubAccessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string=response.body().string();

           String[] split= string.split("&");
           String tokenString=split[0];
           String accessToken=tokenString.split("=")[1];
            return accessToken;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过okhttp get请求获取GitHub个人用户信息
     * @param accessToken
     * @return
     */
    public GithubUserDTO getUserMessage(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string=response.body().string();
            GithubUserDTO githubUserDTO = JSON.parseObject(string, GithubUserDTO.class);
            return githubUserDTO;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
