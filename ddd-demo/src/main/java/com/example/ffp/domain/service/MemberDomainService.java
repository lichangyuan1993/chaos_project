package com.example.ffp.domain.service;

import com.example.ffp.domain.model.*;
import com.example.ffp.domain.repository.MemberBasicInfoRepository;
import com.example.ffp.domain.utils.MembershipNumberGenerator;
import com.example.ffp.application.util.UniqueKey;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MemberDomainService {

    @Resource
    private MemberBasicInfoRepository memberBasicInfoRepository;

    /**
     * 查询会员基础信息
     *
     */
//    @Deprecated // TODO 删除, 因为可以直接调用repository获取会员基础信息
//    public MemberAggregateRoot getMemberBasicInformation(MemberId memberId){
//       return memberBasicInformationRepository.getMemberBasicInformation(memberId);
//    };

    /**
     * 查询会员档案信息
     */
//    @Deprecated // TODO 删除, 因为可以直接调用repository获取会员基础信息
//    public MemberAggregateRoot getMemberProfile(MemberId memberId){
//        return memberBasicInformationRepository.getMemberProfile(memberId);
//    }


    /**
     * 更新会员档案
     */
    public void updateMember(Member member) throws Throwable {
        // TODO
        memberBasicInfoRepository.updateMemberBasicInformation(member.getMemberBasicInformation());
        memberBasicInfoRepository.updateMemberIdentityDocumentList(member.getMemberIdentityDocumentList());
        memberBasicInfoRepository.updateMemberFileList(member.getMemberId());
//       memberRepository.updateMember(memberAggregateRoot);
    }

    /**
     * 会员入会
     */
    public void enrollMember(Member member) throws Throwable {
        MemberBasicInformation memberBasicInformation = member.getMemberBasicInformation();
        LocalDateTime now = LocalDateTime.now();
        // TODO
        memberBasicInformation.setEnrollmentDate(now);
        memberBasicInformation.setRecId(UniqueKey.getUniqueKey());
        memberBasicInformation.setMemberId(UniqueKey.getUniqueKey());
        memberBasicInformation.setMembershipNumber(MembershipNumberGenerator.getMembershipNumber());
        memberBasicInformation.setUpdateUser("ADMIN");
        memberBasicInformation.setCreateUser("ADMIN");
        memberBasicInformation.setCreateTimestamp(now);
        memberBasicInformation.setUpdateTimestamp(now);
        // TODO
        memberBasicInfoRepository.addMemberBasicInformation(memberBasicInformation);
        memberBasicInfoRepository.addMemberIdentityDocumentList(member.getMemberId(), member.getMemberIdentityDocumentList());
        memberBasicInfoRepository.addMemberFileList(member.getMemberId(), member.getMemberFileList());
    }
}
