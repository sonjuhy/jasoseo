package com.intergrated.jasoseo.db.repository;

import com.intergrated.jasoseo.db.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
