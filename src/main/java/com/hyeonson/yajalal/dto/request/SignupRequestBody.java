package com.hyeonson.yajalal.dto.request;

import lombok.Data;

import javax.persistence.Column;

@Data
public class SignupRequestBody {
    private String nickname;
    private String accessToken;
    private String email;
    private Integer gender;
    private Integer signUpType;
    private Integer osType;
    private String uuid;
}
