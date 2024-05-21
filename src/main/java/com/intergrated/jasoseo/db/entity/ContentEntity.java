package com.intergrated.jasoseo.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Getter
@Entity
@Table(name = "content")
@ToString
@NoArgsConstructor
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

    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "announcement_fk", referencedColumnName = "ann_pk")
    private AnnouncementEntity announcementEntity;
}
