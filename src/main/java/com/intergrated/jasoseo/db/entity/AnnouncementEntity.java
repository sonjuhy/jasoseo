package com.intergrated.jasoseo.db.entity;

import com.intergrated.jasoseo.api.dto.AnnouncementDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Entity
@Table(name = "announcement")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnnouncementEntity {
    @Id
    @Column(name = "ann_pk")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "start_day")
    private Date startDay;

    @Column(name = "end_day")
    private Date endDay;

    @Column(name = "company_name")
    private int companyName;

    @ManyToOne
    @JoinColumn(name = "user_fk", referencedColumnName = "user_pk")
    private UserEntity entity;

    @Builder
    public AnnouncementEntity(String title, String content, Date startDay, Date endDay, int companyName, UserEntity entity) {
        this.title = title;
        this.content = content;
        this.startDay = startDay;
        this.endDay = endDay;
        this.companyName = companyName;
        this.entity = entity;
    }
    public void changeInfoByDto(AnnouncementDto dto){
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.startDay = dto.getStartDay();
        this.endDay = dto.getEndDay();
        this.companyName = dto.getCompanyName();
    }
}
