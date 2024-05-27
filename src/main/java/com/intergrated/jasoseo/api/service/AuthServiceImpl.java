package com.intergrated.jasoseo.api.service;

import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.intergrated.jasoseo.api.dto.LoginDto;
import com.intergrated.jasoseo.api.dto.SignUpDto;
import com.intergrated.jasoseo.config.JWTFilter;
import com.intergrated.jasoseo.db.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.nio.charset.StandardCharsets;

@Service
public class AuthServiceImpl implements AuthService{
    Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
//    @Autowired
//    private JWTFilter jwtFilter;
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private UserTokenService userTokenService;

    private JWTFilter jwtFilter;
    private UserService userService;
    private UserTokenService userTokenService;

    @Autowired
    public AuthServiceImpl(JWTFilter jwtFilter, UserService userService, UserTokenService userTokenService) {
        this.jwtFilter = jwtFilter;
        this.userService = userService;
        this.userTokenService = userTokenService;
    }

    @Override
    public String signIn(LoginDto dto) {
        System.out.println("AuthServiceImpl dto : " + dto.toString());
        logger.info("service signIn dto : "+dto.toString());
        String resultJson = userService.signIn(dto);
        return resultJson;
    }

    @Override
    public boolean signUp(SignUpDto dto) {
        logger.info("service dto : "+dto.toString());
        boolean resultBoolean = userService.signUp(dto);
        return resultBoolean;
    }

    @Override
    public String encryptionPassword(String password) {
        String encryptionPW = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
        return encryptionPW;
    }

    @Override
    public boolean checkPassword(String inputPassword, String id) {
        String encryptionPW = encryptionPassword(inputPassword);
        boolean checkResult = userService.checkPassword(id, encryptionPW);
        return checkResult;
    }

    @Override
    public String getAccessToken(String refreshToken) {
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        boolean validateCheck = jwtFilter.checkValidateToken(refreshToken);
        if(validateCheck){
            int userId = jwtFilter.parseToken(refreshToken).get("id", Integer.class); // get userId from refreshToken
            UserEntity userEntity =  userService.findByPk(userId);// get userInfo with refreshToken for getting authList

            String newAccessToken = jwtFilter.createToken(userEntity.getName(), userId, true); // re-create AccessToken
            jsonObject.addProperty("accessToken", newAccessToken); //save accessToken
        }
        else{
            jsonObject.addProperty("error","need login again"); //expired refreshToken
        }
        return gson.toJson(jsonObject);
    }

    @Override
    public boolean validateAccessToken(String accessToken) {
        return jwtFilter.checkValidateToken(accessToken);
    }

    @Override
    public boolean validateRefreshToken(String refreshToken) {
        return jwtFilter.checkValidateToken(refreshToken);
    }

    @Override
    public String reissueAccessToken(String accessToken) {
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        int userId = jwtFilter.parseToken(accessToken).get("id", Integer.class);
        UserEntity userEntity = userService.findByPk(userId);
        String newAccessToken = jwtFilter.createToken(userEntity.getName(), userId,true);

        try{
            userTokenService.updateToken(userId, newAccessToken, null);
            jsonObject.addProperty("accessToken", newAccessToken);
        }
        catch(Exception e){
            jsonObject.addProperty("error", "failed");
            return gson.toJson(jsonObject);
        }
        return gson.toJson(jsonObject);
    }

    @Override
    public String reissueRefreshToken(String refreshToken) {
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        int userId = jwtFilter.parseToken(refreshToken).get("id", Integer.class);
        UserEntity userEntity = userService.findByPk(userId);
        String newRefreshToken = jwtFilter.createToken(userEntity.getName(), userId,false);

        try{
            userTokenService.updateToken(userId, null, newRefreshToken);
            jsonObject.addProperty("accessToken", newRefreshToken);
        }
        catch(Exception e){
            jsonObject.addProperty("error", "failed");
            return gson.toJson(jsonObject);
        }
        return gson.toJson(jsonObject);
    }
}
