package com.example.ffp.application.service;

import com.example.ffp.application.command.UpdateMemberCommand;
import com.example.ffp.application.converter.GetMemberResultConverter;
import com.example.ffp.application.converter.UpdateMemberResultConverter;
import com.example.ffp.application.query.GetMemberQuery;
import com.example.ffp.application.result.GetMemberResult;
import com.example.ffp.application.result.UpdateMemberResult;
import com.example.ffp.interfaces.web.dto.request.MemberRequest;
import com.example.ffp.interfaces.web.dto.response.MemberResponse;
import com.example.ffp.domain.model.Member;
import com.example.ffp.domain.repository.MemberRepository;
import com.example.ffp.domain.service.MemberDomainService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberApplicationService {

    @Resource
    private MemberRepository memberRepository;

    @Resource
    private MemberDomainService memberDomainService;

    @Resource
    private GetMemberResultConverter getMemberResultConverter;

    @Resource
    private UpdateMemberResultConverter updateMemberResultConverter;

    public GetMemberResult getMember(GetMemberQuery getMemberQuery) {
        Member getMember = getMemberResultConverter.toDomain(getMemberQuery);
        Member member = memberDomainService.getMember(getMember);
        return getMemberResultConverter.toResult(member);
    }

    public List<MemberResponse> queryMember(MemberRequest memberRequest) {

        List<Member> memberList =
                memberDomainService.queryMember(GetMemberResultConverter.INSTANCE.toDomain(memberRequest));
        List<MemberResponse> memberResponseList = memberList.stream().map(GetMemberResultConverter.INSTANCE::toResponse).toList();
        return memberResponseList;
    }

    public UpdateMemberResult updateMember(UpdateMemberCommand updateMemberCommand) throws Throwable {
//        Member member = GetMemberResultConverter.INSTANCE.toDomain(updateMemberCommand);
        Member updateMember = updateMemberResultConverter.toDomain(updateMemberCommand);
        memberDomainService.updateMember(updateMember);
        Member member = memberDomainService.getMember(updateMember);
        return updateMemberResultConverter.toResult(member);
    }

    public MemberResponse createMember(MemberRequest memberRequest) throws Throwable {
        Member member = GetMemberResultConverter.INSTANCE.toDomain(memberRequest);
        memberDomainService.enrollMember(member);
        MemberResponse response = GetMemberResultConverter.INSTANCE.toResponse(memberRepository.getMember(member));
        return response;
    }
}
