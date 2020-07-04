package com.hyeonson.yajalal.dto;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public class UserNickAndIdx {
    private String nickname;
    private Integer id;
    private List<SimpleGrantedAuthority> authorities;

    public UserNickAndIdx(String nickname, Integer id) {
        this.nickname = nickname;
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public Integer getId() {
        return id;
    }

    public void setAuthorities(List<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }
}
