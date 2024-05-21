package com.intergrated.jasoseo.api.service;

public class AuthServiceImpl implements AuthService{
    @Override
    public void updateTokens(String accessToken, String refreshToken, String id) {

    }

    @Override
    public boolean checkPassword(String inputPassword, String id) {
        return false;
    }

    @Override
    public String getAccessToken(String refreshToken) {
        return null;
    }

    @Override
    public boolean validateAccessToken(String accessToken) {
        return false;
    }

    @Override
    public boolean validateRefreshToken(String refreshToken) {
        return false;
    }

    @Override
    public String validateAuth(String accessToken) {
        return null;
    }

    @Override
    public String reissueAccessToken(String accessToken) {
        return null;
    }

    @Override
    public String reissueRefreshToken(String refreshToken) {
        return null;
    }
}
