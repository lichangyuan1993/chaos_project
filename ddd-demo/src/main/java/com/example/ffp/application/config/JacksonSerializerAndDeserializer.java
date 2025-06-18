package com.example.ffp.application.config;

import com.example.ffp.application.util.DateTimeUtils;
import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;



/**
 * 自定义jackson序列化反序列化
 *
 * @author LIZHANGYUAN
 *
 */
public class JacksonSerializerAndDeserializer {
    /**
     * byte[] 序列化
     */
    public static class Base64ValueSerializer extends JsonSerializer<byte[]> {
        @Override
        public void serialize(byte[] value, JsonGenerator jg, SerializerProvider serializers) throws IOException {
            jg.writeString(Base64Variants.MIME.encode(value));
        }
    }

    /**
     * byte[] 反序列化
     */
    public static class Base64ValueDeserializer extends JsonDeserializer<byte[]> {

        @Override
        public byte[] deserialize(JsonParser jp, DeserializationContext dc) throws IOException {
//            return jp.getText().getBytes(); // 容易超出字符串最大长度
            //可以避免将Base64编码的数据转换为字符串
            return jp.getBinaryValue(Base64Variants.MIME); // 使用base64 MIME 解码字符串
        }
    }

}
