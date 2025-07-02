package com.example.ffp.interfaces.web.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class MemberBasicInfoRequest {
    private String recId;

    private String memberId;

    private String membershipNumber;

    private String gender;

    private LocalDate birthDate;

    private String familyNameCn;

    private String givenNameCn;

    private String familyNameEn;

    private String givenNameEn;

    private String nationality;

    private LocalDateTime enrollmentDate;

    private String sourceChannel;

    private String membershipTier;

    private String membershipStatus;

    private LocalDate tierEffectiveDate;

    private LocalDate tierExpiryDate;

    private Integer isLifetimeElite;

    private BigDecimal currentNauticalMile;

    private BigDecimal eligibleNauticalMile;

    private BigDecimal lifetimeNauticalMile;

    private LocalDate nextNauticalMileExpiry;

    private BigDecimal currentTierQualMile;

    private Integer currentTierQualSegment;

    private LocalDate tierQualStartDate;

    private LocalDate tierQualEndDate;

    private String pinCode;

    private LocalDateTime lastActivityDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String createdBy;

    private String updatedBy;

    private Integer versionNumber;

}
