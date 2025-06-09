package com.example.ffp.application.service;

import com.example.ffp.application.dto.request.MemberRequest;
import com.example.ffp.application.dto.response.MemberResponse;
import com.example.ffp.application.converter.MemberConverter;
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

    public MemberResponse getMember(MemberRequest memberRequest) {
        Member domainModel = memberDomainService.getMember(MemberConverter.INSTANCE.toDomain(memberRequest));
        MemberResponse apiResponse = MemberConverter.INSTANCE.toResponse(domainModel);
        return apiResponse;
    }

    public List<MemberResponse> queryMember(MemberRequest memberRequest) {

        List<Member> memberList =
                memberDomainService.queryMember(MemberConverter.INSTANCE.toDomain(memberRequest));
        List<MemberResponse> memberResponseList = memberList.stream().map(MemberConverter.INSTANCE::toResponse).toList();
        return memberResponseList;
    }

    public MemberResponse updateMember(MemberRequest memberRequest) throws Throwable {
        Member member = MemberConverter.INSTANCE.toDomain(memberRequest);
        memberDomainService.updateMember(member);
        Member updatedMember = memberRepository.getMember(member);
        MemberResponse memberResponse = MemberConverter.INSTANCE.toResponse(updatedMember);
        return memberResponse;
    }

    public MemberResponse createMember(MemberRequest memberRequest) throws Throwable {
        Member member = MemberConverter.INSTANCE.toDomain(memberRequest);
        memberDomainService.enrollMember(member);
        MemberResponse response = MemberConverter.INSTANCE.toResponse(memberRepository.getMember(member));
        return response;
    }
}
