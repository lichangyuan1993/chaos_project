package com.example.ffp.interfaces.web.dto.request;

import com.example.ffp.domain.model.MemberId;
import com.example.ffp.domain.model.MembershipNumber;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateMemberRequest  extends Request {
    private String memberId;
    private String membershipNumber;
    private MemberBasicInfoRequest memberBasicInfoRequest;
    private MemberIdentityDocumentRequest memberIdentityDocumentRequest;
    private List<MemberFileRequest> memberFileRequestList;
}
