package com.example.ffp.application.result;

import lombok.Data;

@Data
public class MemberFileResult {
    private String memberId;
    private String fileName;

    private MemberBasicInformationResult memberBasicInformationResult;
    private MemberIdentityDocumentResult memberIdentityDocumentResult;
    private MemberProfileResult memberProfileResult;
}
