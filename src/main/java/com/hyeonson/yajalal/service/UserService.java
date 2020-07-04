package com.hyeonson.yajalal.service;

import com.hyeonson.yajalal.domain.User;
import com.hyeonson.yajalal.dto.DefaultRes;
import com.hyeonson.yajalal.dto.UserNickAndIdx;
import com.hyeonson.yajalal.dto.request.SignupRequestBody;
import com.hyeonson.yajalal.repository.UserRepository;
import com.hyeonson.yajalal.utils.JwtUtil;
import com.hyeonson.yajalal.utils.TimeHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private KakaoService kakaoService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public DefaultRes login(final String thirdId, final Integer loginType, final Integer osType) throws RuntimeException{
        final String curTime = TimeHelper.getNowDateString();
        User user = userRepository.findByThirdIdAndSignUpType(thirdId, loginType);
        if(user == null){
            return DefaultRes.res(404, "no user");
        }
        else{
            if(user.getAlreadyOut() == 0){
                String token = jwtUtil.generateToken(user.getNickname(), user.getId());
                user.setOsType(osType);
                user.setLastLoginDate(curTime);
                user.setLoginCnt(user.getLoginCnt() + 1);

                return DefaultRes.res(200, "login success", token);
            }
            else{
                return DefaultRes.res(404, "no user");
            }
        }
    }

    @Transactional
    public DefaultRes signup(final SignupRequestBody signupRequestBody) throws RuntimeException{
        final String curTime = TimeHelper.getNowDateString();

        String thirdId = kakaoService.verifyAccessToken(signupRequestBody.getAccessToken());
        if(thirdId == null){
            return DefaultRes.FAIL_REQUEST_RES;
        }
        if(!isValidNickname(signupRequestBody.getNickname())){
            return DefaultRes.FAIL_REQUEST_RES;
        }

        User user = User.builder()
                .email(signupRequestBody.getEmail())
                .gender(signupRequestBody.getGender())
                .lastLoginDate(curTime)
                .nickname(signupRequestBody.getNickname())
                .osType(signupRequestBody.getOsType())
                .signInDate(curTime)
                .signUpType(signupRequestBody.getSignUpType())
                .thirdId(thirdId)
                .uuid(signupRequestBody.getUuid())
                .build();

        user = userRepository.save(user);
        userRepository.flush();
//        userRepository.save(user);
        log.error("user id: {}", user.getId());
        return DefaultRes.res(200, "signup success", jwtUtil.generateToken(signupRequestBody.getNickname(), user.getId()));
    }

    boolean isValidNickname(String nickname) throws RuntimeException{
        User user = userRepository.findByNickname(nickname);
        if(user != null){
            return false;
        }
        else{
            return true;
        }
    }
}
