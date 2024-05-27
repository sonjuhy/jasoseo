package com.intergrated.jasoseo.api.service;

import com.intergrated.jasoseo.api.dto.LoginDto;
import com.intergrated.jasoseo.api.dto.SignUpDto;
import com.intergrated.jasoseo.api.dto.UserDto;
import com.intergrated.jasoseo.db.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserEntity findByPk(int userId);
    UserEntity findById(String id);
    List<UserEntity> findAll();
    void updateUser(UserDto userDto);
    UserEntity getUserInfo(String accessToken);
    String signIn(LoginDto dto);
    boolean signUp(SignUpDto dto);
    boolean checkPassword(String id, String pw);
}
