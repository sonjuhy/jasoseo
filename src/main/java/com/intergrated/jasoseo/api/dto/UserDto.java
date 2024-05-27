package com.intergrated.jasoseo.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDto {
    private int pk;
    private String name;
    private String id;
    private String pw;
    private Date registration;
}
