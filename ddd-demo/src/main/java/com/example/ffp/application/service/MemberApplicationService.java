package com.example.ffp.application.service;

import com.example.ffp.application.command.CreateMemberCommand;
import com.example.ffp.application.command.UpdateMemberCommand;
import com.example.ffp.application.converter.MemberApplicationConverter;
import com.example.ffp.application.query.GetMemberQuery;
import com.example.ffp.application.result.PageResult;
import com.example.ffp.application.result.MemberBasicInfoResult;
import com.example.ffp.application.result.MemberProfileResult;
import com.example.ffp.domain.model.*;
import com.example.ffp.domain.repository.MemberBasicInfoRepository;
import com.example.ffp.domain.service.MemberDomainService;
import com.example.ffp.infrastructure.persistence.mybatis.entity.MemberEntity;
import com.example.ffp.interfaces.web.dto.request.PageMemberRequest;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberApplicationService {
    @Resource
    private MemberBasicInfoRepository memberBasicInfoRepository;
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
        Member member = memberBasicInfoRepository.getMemberBasicInformation(memberId);
        return memberApplicationConverter.toResult(member);
    }

    /**
     * 获取会员详细信息
     */
    public MemberProfileResult getMemberProfile(GetMemberQuery getMemberQuery) {
        MemberId memberId = getMemberId(getMemberQuery.getMemberId(), getMemberQuery.getMembershipNumber());
        Member member = memberBasicInfoRepository.getMemberProfile(memberId);
        return memberApplicationConverter.toResult(member);
    }


    private MemberId getMemberId(String memberId,String membershipNumber) {
        if (memberId != null) {
            return MemberId.from(memberId);
        }
        if(membershipNumber != null) {
            return memberBasicInfoRepository.getMemberId(MembershipNumber.from(membershipNumber));
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
        Member aggregateRoot = memberApplicationConverter.toAggregateRoot(updateMemberCommand);
        memberDomainService.updateMember(aggregateRoot);
        Member member = memberBasicInfoRepository.getMemberProfile(aggregateRoot.getMemberId());
        return memberApplicationConverter.toResult(member);
    }

    public MemberProfileResult createMember(CreateMemberCommand creatememberCommand) throws Throwable {
        Member member = memberApplicationConverter.toAggregateRoot(creatememberCommand);
        memberDomainService.enrollMember(member);
        // 确保MemberId不为空
        Member memberBasicInformation = memberBasicInfoRepository.getMemberBasicInformation(member.getMemberId());
        return memberApplicationConverter.toResult(memberBasicInformation);
    }

    public PageResult<MemberBasicInfoResult> pageMember(PageMemberRequest pageMemberRequest) {

        // 构建分页参数

        PageHelper.startPage(pageMemberRequest.getPageNum(), pageMemberRequest.getPageSize(),true);
        PageHelper.orderBy(pageMemberRequest.getSort());
        // 分页查询
        Page<MemberEntity> entityPage = (Page<MemberEntity>) memberBasicInfoRepository.queryMember(pageMemberRequest);
        PageResult<MemberBasicInfoResult> pageResult = memberApplicationConverter.toResult( entityPage);
        // 分装成PageInfo
//        PageInfo<MemberEntity> pageInfo = PageInfo.of(memberEntityList);
//        // 转换VO
        return pageResult;
    }
}
