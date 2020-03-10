package com.mao.community.dto;

/**
 * @author maoea
 * 获取accessToken后，具体想要获得的用户信息
 * name：github用户名
 * id：github id号（唯一）后期可用于数据库
 * bio：github个人简介
 */
public class GithubUserDTO {

    private String name;
    private Long id;
    private  String bio;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
    @Override
    public String toString() {
        return "GithubUserDTO{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", bio='" + bio + '\'' +
                '}';
    }

}
