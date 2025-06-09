package com.example.ffp.domain.service;

import com.example.ffp.application.dto.response.MemberResponse;
import com.example.ffp.domain.model.Member;
import com.example.ffp.domain.repository.MemberRepository;
import com.example.ffp.infrastructure.persistence.entity.MemberEntity;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class MemberDomainService {

    @Resource
    private MemberRepository memberRepository;

    public Member getMember(Member member){
       return memberRepository.getMember(member);
    };

    public List<Member> queryMember(Member member) {
        return memberRepository.queryMember(member);
    }

    public void updateMember(Member member) throws Throwable {
       memberRepository.updateMember(member);
    }

    public void enrollMember(Member member) throws Throwable {
        member.setEnrollmentDate(LocalDateTime.now());
        memberRepository.createMember(member);
    }
}
