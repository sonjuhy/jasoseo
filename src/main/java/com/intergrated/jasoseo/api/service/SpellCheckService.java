package com.intergrated.jasoseo.api.service;

import com.intergrated.jasoseo.api.dto.SpellCheckDto;
import reactor.core.publisher.Mono;

public interface SpellCheckService {
    SpellCheckDto check(String msg);
    String getPassportKey();
    String removeTag(String msg);
}
