package com.intergrated.jasoseo.api.dto;

import com.intergrated.jasoseo.db.entity.UserEntity;

import java.util.Date;

public class AnnouncementDto {
    private int pk;
    private String title;
    private String content;
    private Date startDay;
    private Date endDay;
    private int companyName;
    private UserEntity entity;
}
