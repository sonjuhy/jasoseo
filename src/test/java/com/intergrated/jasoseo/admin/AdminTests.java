package com.intergrated.jasoseo.admin;

import com.intergrated.jasoseo.api.dto.UserTierInfo;
import com.intergrated.jasoseo.api.service.AdminService;
import com.intergrated.jasoseo.db.entity.UserTierInfoEntity;
import com.intergrated.jasoseo.db.repository.UserTierRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AdminTests {
    @Mock
    UserTierRepository userTierRepository;
    @InjectMocks
    AdminService adminService;

    @Test
    public void advancementTestLater(){
        UserTierInfo userTierInfo = UserTierInfo.builder()
                .id("hong1234")
                .tier("associate")
                .build();
        when(userTierRepository.findById("hong1234")).thenReturn(UserTierInfoEntity.builder()
                .id("hong1234")
                .tier("regular")
                .build());
        adminService.advanceUserTier(userTierInfo);
        String userTier = userTierInfo.getTier();

        assertEquals("regular", userTier,  "tier compare");
    }


//    @Mock
//    UserTierRepository repository = Mockito.mock(UserTierRepository.class);
//
//    @Test
//    public void advancementTest(){
//        // Given
//        AdminService adminService = new AdminService(repository);
//        UserTierInfo userTierInfo = UserTierInfo.builder()
//                .id("hong1234")
//                .tier("associate")
//                .build();
//        // When
//        Mockito.doAnswer(i -> UserTierInfoEntity.builder()
//                .id("hong1234")
//                .tier("regular")
//                .build()).when(repository).findById("hong1234");
//
//        adminService.advanceUserTier(userTierInfo);
//        String userTier = userTierInfo.getTier();
//        // Then
//        assertEquals("regular", userTier,  "tier compare");
//    }
}
