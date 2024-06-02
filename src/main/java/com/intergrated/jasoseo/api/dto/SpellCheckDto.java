package com.intergrated.jasoseo.api.dto;

import lombok.ToString;

import java.util.LinkedHashMap;
import java.util.Map;

@ToString
public class SpellCheckDto {
    public boolean result;
    public String original;
    public String checked;
    public int errors;
    public long time;
    public Map<String, CheckResult> words;

    public SpellCheckDto(boolean result) {
        this.result = result;
        this.words = new LinkedHashMap<>();
    }
}
