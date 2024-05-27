package com.intergrated.jasoseo.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class AnnouncementDto {
    private int pk;
    private String title;
    private String content;
    private Date startDay;
    private Date endDay;
    private int companyName;
    private int userPk;
}
