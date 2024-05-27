package com.intergrated.jasoseo.db.entity;

import com.intergrated.jasoseo.api.dto.ContentDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Entity
@Table(name = "content")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContentEntity {
    @Id
    @Column(name = "con_pk")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "last_registration_date")
    private Date registration;

    @Column(name = "number")
    private int num;

    @ManyToOne
    @JoinColumn(name = "announcement_fk", referencedColumnName = "ann_pk")
    private AnnouncementEntity entity;

    @Builder
    public ContentEntity(String title, String content, Date registration, int num) {
        this.title = title;
        this.content = content;
        this.registration = registration;
        this.num = num;
    }
    public void changeInfoByDto(ContentDto dto){
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.registration = dto.getRegistration();
        this.num = dto.getNum();
    }
}
