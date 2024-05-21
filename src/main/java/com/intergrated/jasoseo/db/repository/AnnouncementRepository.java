package com.intergrated.jasoseo.db.repository;

import com.intergrated.jasoseo.db.entity.AnnouncementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Integer> {
}
