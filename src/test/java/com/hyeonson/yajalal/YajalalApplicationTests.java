package com.hyeonson.yajalal;

import com.hyeonson.yajalal.dto.UserNickAndIdx;
import com.hyeonson.yajalal.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class YajalalApplicationTests {

    private final static Logger log = LoggerFactory.getLogger(YajalalApplicationTests.class);

    @Autowired
    JwtUtil jwtUtil;

    @Test
    void contextLoads() {
    }

    @Test
    void JWT테스트(){
//        String JWT  = jwtUtil.generateToken("김현슨", 1);
//        log.error(JWT);
    }

    @Test
    void JWT테스트2(){
//        String JWT = "";
//        if(jwtUtil.validateToken(JWT)) log.error("valid");
//        else log.error("invalid");
    }

    @Test
    void JWT테스트3(){
//        String JWT  = jwtUtil.generateToken("김현슨", 1);
//        if(jwtUtil.validateToken(JWT)) log.error("valid");
//        else log.error("invalid");
    }

    @Test
    void JWT테스트4(){
//        String JWT  = jwtUtil.generateToken("김현슨", 1);
//        UserNickAndIdx userNickAndIdx = jwtUtil.getUserNickAndIdxFromToken(JWT);
//        log.error("useNickname: {}", userNickAndIdx.getUserNickname());
//        log.error("userIdx: {}", userNickAndIdx.getUserIdx());
    }

}
