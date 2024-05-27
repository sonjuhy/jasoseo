package com.intergrated.jasoseo.db.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "user_token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class UserTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_pk")
    private int tokenPk;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @OneToOne
    @JoinColumn(name = "user_fk", referencedColumnName = "user_pk")
    private UserEntity userEntity;

    @Builder
    public UserTokenEntity(int tokenPk, String accessToken, String refreshToken, UserEntity userEntity) {
        this.tokenPk = tokenPk;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.userEntity = userEntity;
    }

    public void changeAccessToken(String accessToken){
        this.accessToken = accessToken;
    }

    public void changeRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }
}
