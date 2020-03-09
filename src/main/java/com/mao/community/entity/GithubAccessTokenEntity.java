package com.mao.community.entity;

/**
 * 封装一个community获取code之后去三方获取accessToken时需要发送的内容对象
 * client_id：客户端id （即个人备案时生成的）
 * client_secret：客户端密码，同上
 * code：授权码
 * redirect_uri：获取accessToken成功后跳转的地址
 * state：上一步state（一个随机数）
 * @author maoea
 */
public class GithubAccessTokenEntity {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

    public GithubAccessTokenEntity(String client_id, String client_secret, String code, String redirect_uri, String state) {
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.code = code;
        this.redirect_uri = redirect_uri;
        this.state = state;
    }
    public GithubAccessTokenEntity(){
    }
    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
