package com.example.ffp.application.result;

import java.util.List;

public class MemberProfileResult {
    private String memberId;
    private String membershipNumber;

    private MemberBasicInformationResult memberBasicInformationResult;
    private List<MemberIdentityDocumentResult> memberIdentityDocumentResultList;
    private List<MemberFileResult> memberFileResultList;
}
