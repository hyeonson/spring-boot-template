package com.hyeonson.yajalal.controller;

import com.hyeonson.yajalal.service.KakaoService;
import com.hyeonson.yajalal.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class AuthenticationController {
//    private final static Logger log = LoggerFactory.getLogger(AuthenticationController.class);
    @Autowired
    private KakaoService kakaoService;
    @Autowired
    private UserService userService;

    @PostMapping(value="v1")
    public ResponseEntity authenticate(@RequestBody final AuthenticateRequestBody userRequestBody) throws Exception{


        if(userRequestBody.loginType == 0){
            String accessToken = userRequestBody.getAccessToken();
            String thirdId = kakaoService.verifyAccessToken(accessToken);
            if(thirdId == null){
                return ResponseEntity.badRequest().build();
            }
            else{
                return ResponseEntity.ok(userService.login(thirdId, userRequestBody.getLoginType(), userRequestBody.getOsType()));
            }
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }

    private static class AuthenticateRequestBody {
        private String accessToken;
        private Integer loginType;
        private Integer osType;

        public AuthenticateRequestBody() {
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public Integer getLoginType() {
            return loginType;
        }

        public void setLoginType(Integer loginType) {
            this.loginType = loginType;
        }

        public Integer getOsType() {
            return osType;
        }

        public void setOsType(Integer osType) {
            this.osType = osType;
        }
    }
}
