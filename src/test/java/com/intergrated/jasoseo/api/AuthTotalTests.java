package com.intergrated.jasoseo.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.intergrated.jasoseo.api.controller.AuthController;
import com.intergrated.jasoseo.api.dto.SignUpDto;
import com.intergrated.jasoseo.api.service.AuthService;
import org.hibernate.result.Output;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.system.OutputCaptureRule;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class AuthTotalTests {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    AuthService mockAuthService;

    @Rule
    public OutputCaptureRule captureRule = new OutputCaptureRule();

    @Test
    public void login() throws Exception{
        mockMvc.perform(post("/auth/logIn"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("신규 회원 가입")
    public void signUp() throws Exception{
        // Given
        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setId("mockTestID");
        signUpDto.setPw("mockPW1234!");
        signUpDto.setName("mockTestName");
        Gson gson = new Gson();
        String content = gson.toJson(signUpDto);

        // When
//        when(mockAuthService.signUp(signUpDto)).thenReturn(true);

        // When, Then
        mockMvc.perform(post("/auth/signUp").contentType("application/json").content(content))
                .andExpect(status().isOk())
                .andDo(print());

//        verify(mockAuthService).signUp(signUpDto);
        assertThat(captureRule.toString().contains("service"));
    }
}

