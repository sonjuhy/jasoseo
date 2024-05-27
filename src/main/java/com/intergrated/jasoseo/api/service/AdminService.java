package com.intergrated.jasoseo.api.service;

import com.intergrated.jasoseo.api.dto.UserTierInfo;
import com.intergrated.jasoseo.db.entity.UserTierInfoEntity;
import com.intergrated.jasoseo.db.repository.UserTierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminService {

    @Autowired
    private UserTierRepository userTierRepository;
//    private  final UserTierRepository userTierRepository;
//
//    @Autowired
//    public AdminService(UserTierRepository userTierRepository){
//        this.userTierRepository = userTierRepository;
//    }
    public void advanceUserTier(UserTierInfo userTierInfo){
        UserTierInfoEntity entity = userTierRepository.findById(userTierInfo.getId());
        entity.changeTier("regular");
        userTierInfo.setTier(entity.getTier());
    }
}
