package com.example.ffp.interfaces.web.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateMemberRequest extends Request {
    private MemberBasicInfoRequest memberBasicInfoRequest;
    private MemberIdentityDocumentRequest memberIdentityDocumentRequest;
    private List<MemberFileRequest> memberFileRequestList;
}
