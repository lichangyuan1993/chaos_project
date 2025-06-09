package com.example.ffp.infrastructure.persistence.impl;

import com.example.ffp.application.dto.request.MemberRequest;
import com.example.ffp.application.dto.request.PageRequest;
import com.example.ffp.application.dto.response.MemberResponse;
import com.example.ffp.domain.model.Member;
import com.example.ffp.domain.repository.MemberRepository;
import com.example.ffp.infrastructure.persistence.converter.MemberConverter;
import com.example.ffp.infrastructure.persistence.entity.MemberEntity;
import com.example.ffp.infrastructure.persistence.mapper.MemberMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class MemberRepositoryImpl implements MemberRepository {

    @Resource
    private MemberMapper memberMapper;


    @Override
    public Member getMember(Member member) {
        MemberEntity dataEntity = MemberConverter.INSTANCE.toEntity(member);
        MemberEntity memberEntity = memberMapper.getOne(dataEntity);
        Member domainEntity = MemberConverter.INSTANCE.toDomain(memberEntity);
        return domainEntity;
    }

    @Override
    public List<Member> queryMember(Member member) {
        MemberEntity dataEntity = MemberConverter.INSTANCE.toEntity(member);
        List<MemberEntity> memberEntityList = memberMapper.query(dataEntity);
        List<Member> memberList = memberEntityList.stream().map(MemberConverter.INSTANCE::toDomain).toList();
        return memberList;
    }

    @Override
    public void updateMember(Member member) throws Exception {
        MemberEntity dataEntity = MemberConverter.INSTANCE.toEntity(member);
        int affectRows = memberMapper.update(dataEntity);
        if(affectRows != 1){
            throw new Exception("更新会员信息失败");
        }
    }

    @Override
    public List<MemberEntity> listMember(MemberRequest memberRequest, PageRequest pageRequest) {
        PageHelper.clearPage();
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize(),true);

        PageHelper.orderBy(pageRequest.getSort());

        List<MemberEntity> pageInfo = memberMapper.query(MemberConverter.INSTANCE.toEntity(memberRequest));
        PageHelper.clearPage();
        return pageInfo;
    }

    @Override
    public void createMember(Member domain) throws Throwable {
      int affectRows =  memberMapper.create(MemberConverter.INSTANCE.toEntity(domain));
      if(affectRows != 1){
          throw new Exception("创建会员信息失败");
      }
    }
}
