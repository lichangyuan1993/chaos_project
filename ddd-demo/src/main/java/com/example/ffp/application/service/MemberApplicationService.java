package com.example.ffp.application.service;

import com.example.ffp.application.command.CreateMemberCommand;
import com.example.ffp.application.command.UpdateMemberCommand;
import com.example.ffp.application.converter.MemberApplicationConverter;
import com.example.ffp.application.query.GetMemberQuery;
import com.example.ffp.application.result.MemberProfileResult;
import com.example.ffp.domain.model.*;
import com.example.ffp.domain.repository.MemberBasicInformationRepository;
import com.example.ffp.domain.service.MemberDomainService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberApplicationService {
    @Resource
    private MemberBasicInformationRepository memberBasicInformationRepository;
    @Resource
    private MemberDomainService memberDomainService;
    @Resource
    private MemberApplicationConverter memberApplicationConverter;

    /**
     * 获取会员基础信息
     */
    public MemberProfileResult getMemberBasicInformation(GetMemberQuery getMemberQuery) {
        // memberId和membershipNumber，全部转换成memberId
        MemberId memberId = getMemberId(getMemberQuery.getMemberId(), getMemberQuery.getMembershipNumber());
        MemberAggregateRoot memberAggregateRoot = memberBasicInformationRepository.getMemberBasicInformation(memberId);
        return memberApplicationConverter.toResult(memberAggregateRoot);
    }

    /**
     * 获取会员详细信息
     */
    public MemberProfileResult getMemberProfile(GetMemberQuery getMemberQuery) {
        MemberId memberId = getMemberId(getMemberQuery.getMemberId(), getMemberQuery.getMembershipNumber());
        MemberAggregateRoot memberAggregateRoot = memberBasicInformationRepository.getMemberProfile(memberId);
        return memberApplicationConverter.toResult(memberAggregateRoot);
    }


    private MemberId getMemberId(String memberId,String membershipNumber) {
        if (memberId != null) {
            return MemberId.from(memberId);
        }
        if(membershipNumber != null) {
            return memberBasicInformationRepository.getMemberId(MembershipNumber.from(membershipNumber));
        }
        throw new RuntimeException("会员ID或会员号不能同时为空");
    }

    public List<Object> queryMember() {

//        List<Member> memberList =
//                memberDomainService.queryMember(GetMemberResultConverter.INSTANCE.toDomain(memberRequest));
//
//        List<MemberResponse> memberResponseList = memberList.stream().map(GetMemberResultConverter.INSTANCE::toResponse).toList();
//        return memberResponseList;
        return List.of();
    }

    /**
     * 更新会员信息
     */
    public MemberProfileResult updateMember(UpdateMemberCommand updateMemberCommand) throws Throwable {
        MemberAggregateRoot aggregateRoot = memberApplicationConverter.toAggregateRoot(updateMemberCommand);
        memberDomainService.updateMember(aggregateRoot);
        MemberAggregateRoot memberAggregateRoot = memberBasicInformationRepository.getMemberProfile(aggregateRoot.getMemberId());
        return memberApplicationConverter.toResult(memberAggregateRoot);
    }

    public MemberProfileResult createMember(CreateMemberCommand creatememberCommand) throws Throwable {
        MemberAggregateRoot memberAggregateRoot = memberApplicationConverter.toAggregateRoot(creatememberCommand);
        memberDomainService.enrollMember(memberAggregateRoot);
        // 确保MemberId不为空
        MemberAggregateRoot memberBasicInformation = memberBasicInformationRepository.getMemberBasicInformation(memberAggregateRoot.getMemberId());
        return memberApplicationConverter.toResult(memberBasicInformation);
    }
}
