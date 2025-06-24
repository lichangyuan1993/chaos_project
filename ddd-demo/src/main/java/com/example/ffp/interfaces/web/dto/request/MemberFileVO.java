package com.example.ffp.interfaces.web.dto.request;

import java.time.LocalDateTime;

import com.example.ffp.infrastructure.config.JacksonSerializerAndDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @TableName MEMBER_FILE
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MemberFileVO extends Request {
    private String recId;

    private String memberRecId;

    private String relationTable;

    private String fileName;

    private String fileType;

    private Integer fileSize;

    private LocalDateTime uploadDatetime;

    private LocalDateTime createDatetime;

    private LocalDateTime updateDatetime;

    private String createUser;

    private String updateUser;

    private Integer versionNumber;

    private String deleted;

    @JsonSerialize(using = JacksonSerializerAndDeserializer.Base64ValueSerializer.class)
    @JsonDeserialize(using = JacksonSerializerAndDeserializer.Base64ValueDeserializer.class)
    private byte[] fileContent;

}