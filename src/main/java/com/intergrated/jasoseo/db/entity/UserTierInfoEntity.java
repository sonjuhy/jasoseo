package com.intergrated.jasoseo.db.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user_tier")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserTierInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "info_id")
    private long infoId;

    @Column(name = "id")
    private String id;

    @Column(name = "tier")
    private String tier;

    @Builder
    public UserTierInfoEntity(String id, String tier) {
        this.id = id;
        this.tier = tier;
    }

    public void changeTier(String tier){
        this.tier = tier;
    }
}
