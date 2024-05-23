package com.intergrated.jasoseo.api.service;

import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.intergrated.jasoseo.api.dto.LoginDto;
import com.intergrated.jasoseo.api.dto.SignUpDto;
import com.intergrated.jasoseo.api.dto.UserDto;
import com.intergrated.jasoseo.config.JWTFilter;
import com.intergrated.jasoseo.db.entity.UserEntity;
import com.intergrated.jasoseo.db.repository.UserRepository;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService{

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTFilter jwtFilter;
    @Autowired
    private UserTokenService userTokenService;

    @Override
    public UserEntity findByPk(int userId) {
        UserEntity entity = userRepository.findByPk(userId);
        return entity;
    }

    @Override
    public UserEntity findById(String id) {
        UserEntity entity = userRepository.findById(id);
        return entity;
    }

    @Override
    public List<UserEntity> findAll() {
        List<UserEntity> list = userRepository.findAll();
        return list;
    }

    @Transactional
    @Override
    public void updateUser(UserDto userDto) {
        UserEntity entity = userRepository.findById(userDto.getId());
        String encryptionPW = Hashing.sha256().hashString(userDto.getPw(), StandardCharsets.UTF_8).toString();
        userDto.setPw(encryptionPW);
        entity.changeInfoByDto(userDto);
    }

    @Override
    public UserEntity getUserInfo(String accessToken) {
        int pk = userTokenService.getAllInfoByAccessToken(accessToken).getTokenPk();
        UserEntity entity = userRepository.findByPk(pk);
        return entity;
    }

    @Override
    public String signIn(LoginDto dto) {
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        if(checkPassword(dto.getId(), dto.getPw())){
            UserEntity userEntity = findById(dto.getId());
            String accessToken = jwtFilter.createToken(userEntity.getId(), userEntity.getPk(), true);
            String refreshToken = jwtFilter.createToken(userEntity.getId(), userEntity.getPk(), false);
            userTokenService.updateToken(userEntity.getPk(), accessToken, refreshToken);
            jsonObject.addProperty("result", "error");
            jsonObject.addProperty("msg","Failed to Update Token");
        }
        else{ // wrong password
            jsonObject.addProperty("result", "error");
            jsonObject.addProperty("msg","Wrong id & password");
        }
        return gson.toJson(jsonObject);
    }

    @Override
    public boolean signUp(SignUpDto dto) {
        Date now = new Date();
        String encryptionPW = Hashing.sha256().hashString(dto.getPw(), StandardCharsets.UTF_8).toString();
        UserEntity entity = new UserEntity(0, dto.getName(), dto.getId(), encryptionPW, now);
        System.out.println("service impl : "+entity.toString());
        logger.info("service : "+entity);
        try{
            userRepository.save(entity);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean checkPassword(String id, String pw) {
        return userRepository.checkPassword(id, pw);
    }
}
