package com.jiang.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CustomMapper extends ObjectMapper{ 
    public CustomMapper() {
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // ���� SerializationFeature.FAIL_ON_EMPTY_BEANS Ϊ false
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }
}
