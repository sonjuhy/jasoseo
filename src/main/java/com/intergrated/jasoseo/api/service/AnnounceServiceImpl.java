package com.intergrated.jasoseo.api.service;

import com.intergrated.jasoseo.api.dto.AnnouncementDto;
import com.intergrated.jasoseo.db.entity.AnnouncementEntity;

import java.util.List;

public class AnnounceServiceImpl implements AnnounceService{
    @Override
    public List<AnnouncementEntity> getAnnouncementAllList() {
        return null;
    }

    @Override
    public List<AnnouncementEntity> getAnnouncementListByAccessToken(String accessToken) {
        return null;
    }

    @Override
    public List<AnnouncementEntity> getAnnouncementListByStartToEnd(String accessToken, int start, int end) {
        return null;
    }

    @Override
    public void setAnnouncement(AnnouncementDto dto) {

    }

    @Override
    public void updateAnnouncement(AnnouncementDto dto) {

    }

    @Override
    public void deleteAnnouncement(int pk) {

    }
}
