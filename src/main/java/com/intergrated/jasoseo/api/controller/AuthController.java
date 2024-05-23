package com.intergrated.jasoseo.api.controller;

import com.intergrated.jasoseo.api.dto.LoginDto;
import com.intergrated.jasoseo.api.dto.SignUpDto;
import com.intergrated.jasoseo.api.dto.UserDto;
import com.intergrated.jasoseo.api.service.AuthService;
import com.intergrated.jasoseo.config.JWTFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JWTFilter jwtFilter;
    @Autowired
    private AuthService authService;

    @GetMapping("/validateAccessToken")
    public boolean checkValidateAccessToken(@RequestHeader("Authorization") String accessToken){
        boolean checkResult = jwtFilter.checkValidateToken(accessToken);
        return checkResult;
    }

    @GetMapping("/validateRefreshToken/{token}")
    public boolean checkValidateRefreshToken(@PathVariable String token){
        boolean checkResult = jwtFilter.checkValidateToken(token);
        return checkResult;
    }

    @GetMapping("/reissueAccessToken")
    public String reissueAccessToken(@RequestHeader("Authorization") String accessToken){
        String reissueResult = authService.reissueAccessToken(accessToken);
        return reissueResult;
    }

    @GetMapping("/reissueRefreshToken/{token}")
    public String reissueRefreshToken(@PathVariable String token){
        String reissueResult = authService.reissueRefreshToken(token);
        return reissueResult;
    }

    @PostMapping("/logIn")
    public String login(@RequestBody LoginDto dto){
        String resultStr = authService.signIn(dto);
        return resultStr;
    }
    @PostMapping("/signUp")
    public Boolean signUp(@RequestBody SignUpDto dto){
        boolean resultBoolean = authService.signUp(dto);
        return resultBoolean;
    }
}
