package com.example.ffp.infrastructure.persistence.impl;

import com.example.ffp.interfaces.web.dto.request.MemberRequest;
import com.example.ffp.interfaces.web.dto.request.PageRequest;
import com.example.ffp.domain.model.Member;
import com.example.ffp.domain.repository.MemberRepository;
import com.example.ffp.infrastructure.persistence.mybatis.converter.MemberPersistenceConverter;
import com.example.ffp.infrastructure.persistence.mybatis.entity.MemberEntity;
import com.example.ffp.infrastructure.persistence.mybatis.mapper.MemberMapper;
import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class MemberRepositoryMybatisImpl implements MemberRepository {

    @Resource
    private MemberMapper memberMapper;


    @Override
    public Member getMember(Member member) {
        MemberEntity dataEntity = MemberPersistenceConverter.INSTANCE.toEntity(member);
        MemberEntity memberEntity = memberMapper.getOne(dataEntity);
        Member domainEntity = MemberPersistenceConverter.INSTANCE.toDomain(memberEntity);
        return domainEntity;
    }

    @Override
    public List<Member> queryMember(Member member) {
        MemberEntity dataEntity = MemberPersistenceConverter.INSTANCE.toEntity(member);
        List<MemberEntity> memberEntityList = memberMapper.query(dataEntity);
        List<Member> memberList = memberEntityList.stream().map(MemberPersistenceConverter.INSTANCE::toDomain).toList();
        return memberList;
    }

    @Override
    public void updateMember(Member member) throws Exception {
        MemberEntity dataEntity = MemberPersistenceConverter.INSTANCE.toEntity(member);
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

        List<MemberEntity> pageInfo = memberMapper.query(MemberPersistenceConverter.INSTANCE.toEntity(memberRequest));
        PageHelper.clearPage();
        return pageInfo;
    }

    @Override
    public void createMember(Member domain) throws Throwable {
      int affectRows =  memberMapper.create(MemberPersistenceConverter.INSTANCE.toEntity(domain));
      if(affectRows != 1){
          throw new Exception("创建会员信息失败");
      }
    }
}
