package com.intergrated.jasoseo.api.service;

import com.intergrated.jasoseo.api.dto.LoginDto;
import com.intergrated.jasoseo.api.dto.UserDto;
import com.intergrated.jasoseo.db.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserEntity findByUserId(long userId);
    UserEntity findByAccessToken(String uuid);
    Optional<UserEntity> findById(String id);
    List<UserEntity> findAll();
    int updateUser(UserDto userDto);
    UserEntity getUserInfo(String accessToken);
    String signIn(LoginDto dto);
    String signUp(UserDto dto);
}
