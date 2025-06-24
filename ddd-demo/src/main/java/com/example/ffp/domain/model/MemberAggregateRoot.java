package com.example.ffp.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class MemberAggregateRoot {

    private MemberId memberId;
    private MembershipNumber membershipNumber;
    private MemberBasicInformation memberBasicInformation; // 不允许出现memberId和membershipNumber
    private List<MemberIdentityDocument> memberIdentityDocumentList;
    private List<MemberFile> memberFileList;

    public static MemberAggregateRoot fromMemberBasicInformation(String memberId, String membershipNumber, MemberBasicInformation memberBasicInformation) {
        MemberAggregateRoot memberAggregateRoot = new MemberAggregateRoot();
        memberAggregateRoot.setMemberId(MemberId.from(memberId));
        memberAggregateRoot.setMembershipNumber(MembershipNumber.from(membershipNumber));
        memberAggregateRoot.setMemberBasicInformation(memberBasicInformation);
        return memberAggregateRoot;
    }

    public static MemberAggregateRoot fromMemberProfile(
            String memberId,
            String membershipNumber,
            MemberBasicInformation memberBasicInformation,
            List<MemberIdentityDocument> memberIdentityDocumentList,
            List<MemberFile> memberFileList) {
        MemberAggregateRoot memberAggregateRoot = new MemberAggregateRoot();
        memberAggregateRoot.setMemberId(MemberId.from(memberId));
        memberAggregateRoot.setMembershipNumber(MembershipNumber.from(membershipNumber));
        memberAggregateRoot.setMemberBasicInformation(memberBasicInformation);
        memberAggregateRoot.setMemberIdentityDocumentList(memberIdentityDocumentList);
        memberAggregateRoot.setMemberFileList(memberFileList);
        return memberAggregateRoot;
    }
}
