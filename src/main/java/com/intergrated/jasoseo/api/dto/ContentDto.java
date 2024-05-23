package com.intergrated.jasoseo.api.dto;

import com.intergrated.jasoseo.db.entity.AnnouncementEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ContentDto {
    private int pk;
    private String title;
    private String content;
    private Date registration;
    private int num;
    private int annPk;
}
