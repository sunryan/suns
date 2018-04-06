package com.ryan.suns.common.model.enums.suport;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * @author lr
 * @date 2018/4/6.
 */
public class EnumSerializer extends StdSerializer<SuperEnum> {
    
    public EnumSerializer() {
        super(SuperEnum.class);
    }
    
    @Override
    public void serialize(SuperEnum enumItem, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeFieldName("value");
        gen.writeObject(enumItem.getValue());
        gen.writeFieldName("name");
        gen.writeString(enumItem.getName());
        gen.writeFieldName("enumName");
        gen.writeString(enumItem.getEnumName());
        gen.writeFieldName("className");
        gen.writeString(enumItem.getClassName());
        gen.writeEndObject();
    }
    
}