package com.ryan.suns.common.model.enums.suport;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * @author lr
 * @date 2018/4/6.
 */
public class EnumDeserializer extends JsonDeserializer<SuperEnum> {
    
    @Override
    public SuperEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        int value = (Integer) node.get("value").numberValue();
        String className = node.get("className").asText();
        return findEnum(className, value);
    }
    
    private SuperEnum findEnum(String className, int value) {
        Class c = null;
        try {
            c = Class.forName(className);
            if (SuperEnum.class.isAssignableFrom(c)) {
                return EnumUtil.getEnumByValue(c, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
