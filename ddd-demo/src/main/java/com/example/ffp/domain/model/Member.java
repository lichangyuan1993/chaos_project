package com.example.ffp.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @TableName MEMBER
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Member extends BaseModel {
    private String recId;

    private String memberId;

    private String membershipNumber;

    private String gender;

    private LocalDate dateOfBirth;

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

    private String lifetimeEliteInd;

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

    private LocalDateTime createTimestamp;

    private LocalDateTime updateTimestamp;

    private String createUser;

    private String updateUser;





}