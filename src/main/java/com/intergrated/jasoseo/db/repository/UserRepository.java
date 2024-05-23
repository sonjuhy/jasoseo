package com.intergrated.jasoseo.db.repository;

import com.intergrated.jasoseo.db.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByPk(int pk);
    UserEntity findById(String id);
    boolean existsById(int id);
    @Query(value = "SELECT IF(id=:ID) FROM users WHERE password_hash =:PW", nativeQuery = true)
    boolean checkPassword(@Param("ID") String id,@Param("PW") String pw);

}
