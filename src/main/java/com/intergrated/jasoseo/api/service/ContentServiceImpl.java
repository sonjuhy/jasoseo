package com.intergrated.jasoseo.api.service;

import com.intergrated.jasoseo.api.dto.ContentDto;
import com.intergrated.jasoseo.db.entity.ContentEntity;
import com.intergrated.jasoseo.db.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ContentServiceImpl implements ContentService{

    private ContentRepository contentRepository;

    @Autowired
    public ContentServiceImpl(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public List<ContentEntity> getContentEntityAll(int pk) {
        List<ContentEntity> list = contentRepository.findByEntityPk(pk);
        return list;
    }

    @Override
    public void setContent(ContentDto dto) {
        Date now = new Date();
        ContentEntity entity = new ContentEntity(dto.getTitle(), dto.getContent(), now, dto.getNum());
        contentRepository.save(entity);
    }

    @Override
    public void updateContent(ContentDto dto) {
        ContentEntity contentEntity = contentRepository.findByPk(dto.getPk());
        Date now = new Date();
        dto.setRegistration(now);
        contentEntity.changeInfoByDto(dto);
    }

    @Override
    public void deleteContent(int pk) {
        contentRepository.deleteByPk(pk);
    }
}
