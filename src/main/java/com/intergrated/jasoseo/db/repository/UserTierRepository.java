package com.intergrated.jasoseo.db.repository;

import com.intergrated.jasoseo.db.entity.UserTierInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserTierRepository extends JpaRepository<UserTierInfoEntity, Long> {
    UserTierInfoEntity findById(String id);
}
