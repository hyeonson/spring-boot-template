package com.hyeonson.yajalal.controller;

import com.hyeonson.yajalal.dto.request.SignupRequestBody;
import com.hyeonson.yajalal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Column;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value="v1")
    public ResponseEntity signup(@RequestBody SignupRequestBody signupRequestBody) throws RuntimeException{
        return ResponseEntity.ok(userService.signup(signupRequestBody));
    }

}
