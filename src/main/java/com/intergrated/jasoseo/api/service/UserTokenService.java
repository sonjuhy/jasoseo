package com.intergrated.jasoseo.api.service;

import com.intergrated.jasoseo.db.entity.UserEntity;
import com.intergrated.jasoseo.db.entity.UserTokenEntity;

public interface UserTokenService {
    UserTokenEntity getAllInfoByAccessToken(String token);
    void updateAccessToken(int userPk, String token);
    void updateRefreshToken(int userPk, String token);
    void updateToken(int userPk, String accessToken, String refreshToken);
    int setToken(int userPk, String accessToken, String refreshToken, UserEntity userEntity);
    void deleteToken(int userPk);
}
