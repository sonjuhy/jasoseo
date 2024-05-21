package com.intergrated.jasoseo.api.service;

import com.intergrated.jasoseo.api.dto.ContentDto;
import com.intergrated.jasoseo.db.entity.ContentEntity;

import java.util.List;

public interface ContentService {
    List<ContentEntity> getContentEntityAll(int pk);
    void setContent(ContentDto dto);
    void updateContent(ContentDto dto);
    void deleteContent(int pk);
}
