package com.intergrated.jasoseo.api.service;

import com.intergrated.jasoseo.api.dto.LoginDto;
import com.intergrated.jasoseo.api.dto.UserDto;
import com.intergrated.jasoseo.db.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService{
    @Override
    public UserEntity findByUserId(long userId) {
        return null;
    }

    @Override
    public UserEntity findByAccessToken(String uuid) {
        return null;
    }

    @Override
    public Optional<UserEntity> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<UserEntity> findAll() {
        return null;
    }

    @Override
    public int updateUser(UserDto userDto) {
        return 0;
    }

    @Override
    public UserEntity getUserInfo(String accessToken) {
        return null;
    }

    @Override
    public String signIn(LoginDto dto) {
        return null;
    }

    @Override
    public String signUp(UserDto dto) {
        return null;
    }
}
