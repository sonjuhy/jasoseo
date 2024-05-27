package com.intergrated.jasoseo.db.entity;

import com.intergrated.jasoseo.api.dto.UserDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Entity
@Table(name = "users")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public UserEntity(int pk, String name, String id, String pw, Date registration) {
        this.pk = pk;
        this.name = name;
        this.id = id;
        this.pw = pw;
        this.registration = registration;
    }

    public void changeInfoByDto(UserDto dto){
        this.name = dto.getName();
        this.id = dto.getId();
        this.pw = dto.getPw();
        this.registration = dto.getRegistration();
    }
}
