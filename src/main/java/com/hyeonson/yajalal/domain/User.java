package com.hyeonson.yajalal.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nickname", columnDefinition = "VARCHAR(31)", nullable = false)
    private String nickname;

    @Column(name = "third_id", columnDefinition = "VARCHAR(255)", nullable = false)
    private String thirdId;

    @Column(name = "email", columnDefinition = "VARCHAR(255)", nullable = true)
    private String email;

    @Column(name = "gender", columnDefinition = "TINYINT", nullable = true)
    private Integer gender;

    @Column(name = "sign_in_date", columnDefinition = "TIMESTAMP", nullable = false)
    private String signInDate;

    @Column(name = "sign_out_date", columnDefinition = "TIMESTAMP", nullable = true)
    private String signOutDate;

    @Column(name = "signup_type", columnDefinition = "TINYINT", nullable = false)
    private Integer signUpType;

    @Column(name = "os_type", columnDefinition = "TINYINT", nullable = false)
    private Integer osType;

    @Column(name = "uuid", columnDefinition = "VARCHAR(255)", nullable = false)
    private String uuid;

    @Column(name = "last_login_date", columnDefinition = "TIMESTAMP", nullable = false)
    private String lastLoginDate;

    @Column(name = "login_cnt", columnDefinition = "INT", nullable = true)
    @ColumnDefault(value = "0")
    private Long loginCnt;

    @Column(name = "already_out", columnDefinition = "TINYINT", nullable = true)
    @ColumnDefault(value = "0")
    private Integer alreadyOut;

    @Column(name = "rank_score", columnDefinition = "INT", nullable = true)
    @ColumnDefault(value = "0")
    private Integer rankScore;

    @Builder
    public User(String nickname, String thirdId, String email, Integer gender, String signInDate, Integer signUpType, Integer osType, String uuid, String lastLoginDate) {
        this.nickname = nickname;
        this.thirdId = thirdId;
        this.email = email;
        this.gender = gender;
        this.signInDate = signInDate;
        this.signUpType = signUpType;
        this.osType = osType;
        this.uuid = uuid;
        this.lastLoginDate = lastLoginDate;
    }
}
