package com.intergrated.jasoseo.api.service;

import com.intergrated.jasoseo.db.entity.UserEntity;
import com.intergrated.jasoseo.db.entity.UserTokenEntity;
import com.intergrated.jasoseo.db.repository.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
public class UserTokenServiceImpl implements UserTokenService {
    @Autowired
    private UserTokenRepository userTokenRepository;

    @Override
    public UserTokenEntity getAllInfoByAccessToken(String token) {
        UserTokenEntity entity = userTokenRepository.findByAccessToken(token);
        return entity;
    }

    @Transactional
    @Override
    public void updateAccessToken(int userPk, String token) {
        Optional<UserTokenEntity> entity = userTokenRepository.findById(userPk);
        entity.ifPresent(userTokenEntity -> userTokenEntity.changeAccessToken(token));
    }
    @Transactional
    @Override
    public void updateRefreshToken(int userPk, String token) {
        Optional<UserTokenEntity> entity = userTokenRepository.findById(userPk);
        entity.ifPresent(userTokenEntity -> userTokenEntity.changeRefreshToken(token));
    }

    @Override
    public void updateToken(int userPk, String accessToken, String refreshToken) {
        if(ObjectUtils.isEmpty(accessToken)){
            updateRefreshToken(userPk, refreshToken);
        }
        else if(ObjectUtils.isEmpty(refreshToken)){
            updateAccessToken(userPk, accessToken);
        }
        else {
            updateAccessToken(userPk, accessToken);
            updateRefreshToken(userPk, refreshToken);
        }
    }

    @Override
    public int setToken(int userPk, String accessToken, String refreshToken, UserEntity userEntity) {
        UserTokenEntity entity = userTokenRepository.save(new UserTokenEntity(userPk, accessToken, refreshToken, userEntity));
        return ObjectUtils.isEmpty(entity) ? 1 : 0;
    }

    @Override
    public void deleteToken(int userPk) {
        userTokenRepository.deleteById(userPk);
    }
}
