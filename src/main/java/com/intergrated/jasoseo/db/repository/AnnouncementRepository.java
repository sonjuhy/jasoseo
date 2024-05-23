package com.intergrated.jasoseo.db.repository;

import com.intergrated.jasoseo.db.entity.AnnouncementEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Integer> {
    AnnouncementEntity findByPk(int pk);
    List<AnnouncementEntity> findByEntityPk(int pk);
    List<AnnouncementEntity> findByEntityPk(int pk, Pageable pageable);
    void deleteByPk(int pk);
}
