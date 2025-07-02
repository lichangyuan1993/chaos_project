package com.example.ffp.infrastructure.persistence.mybatis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @TableName MEMBER_IDENTITY_DOCUMENT
 */
@Data
@Alias("MemberIdentityDocumentEntity")
public class MemberIdentityDocumentEntity{
    private String recId;

    private String memberRecId;

    private String idType;

    private String idNumber;

    private String issuingCountry;

    private LocalDate issueDate;

    private LocalDate expireDate;

    private Integer isPrimary;

    private String verifyStatus;

    private LocalDateTime verifiedAt;

    private BigDecimal sequenceNumber;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String createdBy;

    private String updatedBy;

    private Integer versionNumber;

    private Integer isDeleted;


}