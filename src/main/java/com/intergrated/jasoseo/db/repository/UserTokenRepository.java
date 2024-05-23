package com.intergrated.jasoseo.db.repository;

import com.intergrated.jasoseo.db.entity.UserTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTokenRepository extends JpaRepository<UserTokenEntity, Integer> {
    UserTokenEntity findByTokenPk(int pk);
    UserTokenEntity findByAccessToken(String token);
}
