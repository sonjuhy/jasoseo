package com.intergrated.jasoseo.api;

import com.google.gson.Gson;
import com.intergrated.jasoseo.api.controller.AuthController;
import com.intergrated.jasoseo.api.dto.LoginDto;
import com.intergrated.jasoseo.api.dto.SignUpDto;
import com.intergrated.jasoseo.api.service.*;
import com.intergrated.jasoseo.config.JWTFilter;
import com.intergrated.jasoseo.db.entity.UserEntity;
import com.intergrated.jasoseo.db.repository.UserRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.system.OutputCaptureRule;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.AssertionErrors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthController.class)
public class AuthUnitTests {

    Logger logger = LoggerFactory.getLogger(AuthUnitTests.class);
    private final String testID = "mockTestID";
    private final String testPW = "mockPW1234!";
    private final String testName = "mockTestName";

    @Rule
    public OutputCaptureRule captureRule = new OutputCaptureRule();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AuthService authService;

    @MockBean
    UserService userService;

    @MockBean
    JWTFilter jwtFilter;

    @MockBean
    UserTokenService userTokenService;

    @MockBean
    UserRepository userRepository = Mockito.mock(UserRepository.class);
    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp(){
        System.out.println("setUp running");
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .alwaysDo(print())
                .build();
    }

    @DisplayName("회원가입 컨트롤러 테스트")
    @Test
    public void controllerSignUpTest() throws Exception{
        System.out.println("signup test");
        // Given
        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setId(testID);
        signUpDto.setPw(testPW);
        signUpDto.setName(testName);
        Gson gson = new Gson();
        String content = gson.toJson(signUpDto);

        // When, Then
        when(authService.signUp(signUpDto)).thenReturn(true);

        mockMvc.perform(post("/auth/signUp").contentType("application/json").content(content))
                .andExpect(status().isOk())
                .andDo(print());

        verify(authService, times(1)).signUp(refEq(signUpDto));
    }

    @DisplayName("로그인 컨트롤러 테스트")
    @Test
    public void controllerLoginTest() throws Exception{

        // Given
        LoginDto loginDto = new LoginDto();
        loginDto.setId(testID);
        loginDto.setPw(testPW);
        Gson gson = new Gson();
        String content = gson.toJson(loginDto);

        // When
        String accessToken = authService.signIn(loginDto);
        when(authService.signIn(loginDto)).thenReturn(accessToken);

        mockMvc.perform(post("/auth/logIn").contentType("application/json").content(content))
                .andExpect(status().isOk())
                .andDo(print());

        // Then
        verify(authService, times(2)).signIn(refEq(loginDto));
    }

    @DisplayName("로그인 서비스 테스트")
    @Test
    public void serviceLoginTest(){
        logger.info("LogIn Test Start");
        // Given
        String encryPW = authService.encryptionPassword(testPW);
        LoginDto loginDto = new LoginDto();
        loginDto.setId(testID);
        loginDto.setPw(testPW);

        UserEntity userEntity = UserEntity.builder()
                .pk(1)
                .id(testID)
                .pw(encryPW)
                .build();

        String defaultAccessToken = jwtFilter.createToken(testID, 1, true);

        // When
        when(userRepository.findById(testID)).thenReturn(userEntity);
        when(userService.checkPassword(testID, testPW)).thenReturn(true);
        when(authService.checkPassword(testPW, testID)).thenReturn(true);

        String accessToken = userService.signIn(loginDto);

        // Then
        assertThat(captureRule.toString().contains("service"));
        logger.info("accessToken : "+accessToken+", defaultAccessToken : "+defaultAccessToken);
        verify(userService, times(1)).signIn(loginDto);
//        assertNotNull("accessToken null check", accessToken);
        assertEquals("compare accessToken", accessToken, defaultAccessToken);
    }
}
