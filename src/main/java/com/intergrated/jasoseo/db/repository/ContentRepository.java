package com.intergrated.jasoseo.db.repository;

import com.intergrated.jasoseo.db.entity.ContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<ContentEntity, Integer> {
}
