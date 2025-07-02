package com.example.ffp.domain.repository;

import com.example.ffp.domain.model.*;
import com.example.ffp.interfaces.web.dto.request.PageMemberRequest;
import com.example.ffp.infrastructure.persistence.mybatis.entity.MemberEntity;

import java.util.List;


public interface MemberBasicInfoRepository {

    Member getMemberBasicInformation(MemberId memberId);

    Member getMemberProfile(MemberId memberId);

//    List<MemberBasicInformation> queryMember(Member member);

//    void updateMember(Member member) throws Throwable;

    List<MemberEntity> queryMember(PageMemberRequest pageMemberRequest);

//    void createMember(Member domain) throws Exception, Throwable;


    MemberId getMemberId(MembershipNumber membershipNumber);
}
