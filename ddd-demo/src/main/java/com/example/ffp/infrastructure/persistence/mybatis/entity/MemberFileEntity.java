package com.example.ffp.infrastructure.persistence.mybatis.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @TableName MEMBER_FILE
 */
@Data
@Alias("MemberFileEntity")
public class MemberFileEntity {
    private String recId;

    private String memberRecId;

    private String relationTable;

    private String fileName;

    private String fileType;

    private Integer fileSize;

    private LocalDateTime uploadAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String createdBy;

    private String updatedBy;

    private Integer versionNumber;

    private Integer isDeleted;

    private byte[] fileContent;

}