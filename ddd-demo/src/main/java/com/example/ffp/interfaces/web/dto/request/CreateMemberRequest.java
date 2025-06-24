package com.example.ffp.interfaces.web.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateMemberRequest extends Request {
    private MemberBasicInfoDTO memberBasicInfoDTO;
    private MemberIdentityDocumentDTO memberIdentityDocumentDTO;
    private List<MemberFileDTO> memberFileDTOList;
}
