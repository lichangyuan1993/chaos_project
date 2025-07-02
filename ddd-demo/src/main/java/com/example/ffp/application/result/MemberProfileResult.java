package com.example.ffp.application.result;

import java.util.List;

public class MemberProfileResult {
    private String memberId;
    private String membershipNumber;

    private MemberBasicInfoResult memberBasicInfoResult;
    private List<MemberIdentityDocumentResult> memberIdentityDocumentResultList;
    private List<MemberFileResult> memberFileResultList;
}
