package com.intergrated.jasoseo.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserTierInfo {
    private long infoId;
    private String id;
    private String tier;
}
