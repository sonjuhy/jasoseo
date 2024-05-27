package com.intergrated.jasoseo.api.controller;

import com.intergrated.jasoseo.api.dto.UserDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getUserInfo")
    public UserDto getUserInfo(){
        UserDto dto = new UserDto();
        return dto;
    }

    @PostMapping("/setUserInfo")
    public void setUserInfo(@RequestBody UserDto dto){

    }

    @PutMapping("/updateUserInfo")
    public void updateUserInfo(@RequestBody UserDto dto){

    }

    @DeleteMapping("/deleteUserInfo")
    public void deleteUserInfo(){

    }
}
