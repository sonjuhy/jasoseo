package com.intergrated.jasoseo.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Getter
@Entity
@Table(name = "users")
@ToString
@NoArgsConstructor
public class UserEntity {
    @Id
    @Column(name = "user_pk")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;

    @Column(name = "username")
    private String name;

    @Column(name = "id")
    private String id;

    @Column(name = "password_hash")
    private String pw;

    @Column(name = "registration_date")
    private Date registration;
}
