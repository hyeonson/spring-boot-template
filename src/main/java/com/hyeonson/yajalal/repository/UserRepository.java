package com.hyeonson.yajalal.repository;

import com.hyeonson.yajalal.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
    User findByThirdIdAndSignUpType(final String thirdId, final Integer signUpType);
    User findByNickname(final String nickname);
}
