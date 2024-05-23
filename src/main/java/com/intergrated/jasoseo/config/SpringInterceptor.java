package com.intergrated.jasoseo.config;

import com.intergrated.jasoseo.db.entity.UserEntity;
import com.intergrated.jasoseo.db.repository.UserRepository;
import io.netty.util.internal.ObjectUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Configuration
public class SpringInterceptor implements HandlerInterceptor {


    @Autowired
    private JWTFilter jwtFilter;
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = jwtFilter.extractTokenFromHeader(request);
        if(ObjectUtils.isEmpty(token) || ObjectUtils.isEmpty(jwtFilter.checkValidateToken(token))){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        int userId = jwtFilter.parseToken(token).get("id", Integer.class);
        boolean existUser = userRepository.existsById(userId);
        if(existUser){
            return true;
        }
        else{
            throw new BadRequestException("Not Found User");
        }
//        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
