package com.intergrated.jasoseo.api.service;

import com.intergrated.jasoseo.api.dto.LoginDto;
import com.intergrated.jasoseo.api.dto.SignUpDto;
import com.intergrated.jasoseo.api.dto.UserDto;

public interface AuthService {
    String signIn(LoginDto dto);
    boolean signUp(SignUpDto dto);
    String encryptionPassword(String password);
    boolean checkPassword(String inputPassword, String id);
    String getAccessToken(String refreshToken);
    boolean validateAccessToken(String accessToken);
    boolean validateRefreshToken(String refreshToken);
    String reissueAccessToken(String accessToken);
    String reissueRefreshToken(String refreshToken);

}
