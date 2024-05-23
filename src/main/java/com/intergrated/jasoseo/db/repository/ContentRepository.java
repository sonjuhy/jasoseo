package com.intergrated.jasoseo.db.repository;

import com.intergrated.jasoseo.db.entity.ContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<ContentEntity, Integer> {
    List<ContentEntity> findByEntityPk(int pk);
    ContentEntity findByPk(int pk);
    void deleteByPk(int pk);
}
