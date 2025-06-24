package com.example.ffp.interfaces.web.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @TableName MEMBER_IDENTITY_DOCUMENT
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MemberIdentityDocumentVO extends Request {
    private String recId;

    private String memberRecId;

    private String idType;

    private String idNumber;

    private String countryOfIssue;

    private LocalDate issueDate;

    private LocalDate expireDate;

    private String primaryInd;

    private String verifyStatus;

    private LocalDateTime verifiedDatetime;

    private Float sequenceNumber;

    private LocalDateTime createDatetime;

    private LocalDateTime updateDatetime;

    private String createUser;

    private String updateUser;

    private Integer versionNumber;

    private String deleted;
}