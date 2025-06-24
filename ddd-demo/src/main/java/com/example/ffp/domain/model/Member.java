package com.example.ffp.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class Member {

    private MemberId memberId;
    private MembershipNumber membershipNumber;
    private MemberBasicInformation memberBasicInformation; // 不允许出现memberId和membershipNumber
    private List<MemberIdentityDocument> memberIdentityDocumentList;
    private List<MemberFile> memberFileList;

    public static Member fromMemberBasicInformation(String memberId, String membershipNumber, MemberBasicInformation memberBasicInformation) {
        Member member = new Member();
        member.setMemberId(MemberId.from(memberId));
        member.setMembershipNumber(MembershipNumber.from(membershipNumber));
        member.setMemberBasicInformation(memberBasicInformation);
        return member;
    }

    public static Member fromMemberProfile(
            String memberId,
            String membershipNumber,
            MemberBasicInformation memberBasicInformation,
            List<MemberIdentityDocument> memberIdentityDocumentList,
            List<MemberFile> memberFileList) {
        Member member = new Member();
        member.setMemberId(MemberId.from(memberId));
        member.setMembershipNumber(MembershipNumber.from(membershipNumber));
        member.setMemberBasicInformation(memberBasicInformation);
        member.setMemberIdentityDocumentList(memberIdentityDocumentList);
        member.setMemberFileList(memberFileList);
        return member;
    }
}
