package com.intergrated.jasoseo.api.service;

import com.intergrated.jasoseo.api.dto.AnnouncementDto;
import com.intergrated.jasoseo.db.entity.AnnouncementEntity;

import java.util.List;

public interface AnnounceService {
    List<AnnouncementEntity> getAnnouncementAllList();
    List<AnnouncementEntity> getAnnouncementListByAccessToken(String accessToken);
    List<AnnouncementEntity> getAnnouncementListByStartToEnd(String accessToken, int start, int end);

    //
    void setAnnouncement(AnnouncementDto dto);
    void updateAnnouncement(AnnouncementDto dto);
    void deleteAnnouncement(int pk);
}
