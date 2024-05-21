package com.intergrated.jasoseo.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Getter
@Entity
@Table(name = "announcement")
@ToString
@NoArgsConstructor
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

}
