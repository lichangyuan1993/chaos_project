package com.example.ffp.application.result;

import lombok.Data;

@Data
public class MemberFileResult {
    private String memberId;
    private String fileName;

    private MemberBasicInfoResult memberBasicInfoResult;
    private MemberIdentityDocumentResult memberIdentityDocumentResult;
    private MemberProfileResult memberProfileResult;
}
