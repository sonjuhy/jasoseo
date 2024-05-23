package com.intergrated.jasoseo.api.service;

import com.intergrated.jasoseo.api.dto.AnnouncementDto;
import com.intergrated.jasoseo.db.entity.AnnouncementEntity;
import com.intergrated.jasoseo.db.entity.UserEntity;
import com.intergrated.jasoseo.db.entity.UserTokenEntity;
import com.intergrated.jasoseo.db.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnounceServiceImpl implements AnnounceService{
    @Autowired
    private AnnouncementRepository announcementRepository;
    @Autowired
    private UserTokenService userTokenService;
    @Autowired
    private UserService userService;

    @Override
    public List<AnnouncementEntity> getAnnouncementAllList() {
        List<AnnouncementEntity> list = announcementRepository.findAll();
        return list;
    }

    @Override
    public List<AnnouncementEntity> getAnnouncementListByAccessToken(String accessToken) {
        int pk = userTokenService.getAllInfoByAccessToken(accessToken).getTokenPk();
        List<AnnouncementEntity> list = announcementRepository.findByEntityPk(pk);
        return list;
    }

    @Override
    public List<AnnouncementEntity> getAnnouncementListByStartToEnd(String accessToken, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        int pk = userTokenService.getAllInfoByAccessToken(accessToken).getTokenPk();
        List<AnnouncementEntity> list = announcementRepository.findByEntityPk(pk, pageable);
        return list;
    }

    @Override
    public void setAnnouncement(AnnouncementDto dto) {
        UserEntity userEntity = userService.findByPk(dto.getUserPk());
        AnnouncementEntity announcementEntity = new AnnouncementEntity(
                dto.getTitle(),
                dto.getContent(),
                dto.getStartDay(),
                dto.getEndDay(),
                dto.getCompanyName(),
                userEntity
        );
        announcementRepository.save(announcementEntity);
    }

    @Override
    public void updateAnnouncement(AnnouncementDto dto) {
        AnnouncementEntity announcementEntity = announcementRepository.findByPk(dto.getPk());
        announcementEntity.changeInfoByDto(dto);
    }

    @Override
    public void deleteAnnouncement(int pk) {
        announcementRepository.deleteByPk(pk);
    }
}
