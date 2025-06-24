package com.example.ffp.infrastructure.persistence.impl;

import com.example.ffp.domain.model.*;
import com.example.ffp.interfaces.web.dto.request.PageMemberRequest;
import com.example.ffp.domain.repository.MemberBasicInformationRepository;
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
public class MemberBasicInformationRepositoryMybatisImpl implements MemberBasicInformationRepository {
    @Resource
    private MemberMapper memberMapper;
    @Resource
    private MemberPersistenceConverter memberPersistenceConverter;

    @Override
    public MemberAggregateRoot getMemberBasicInformation(MemberId memberId) {
        MemberEntity  memberEntity = memberMapper.getOneByMemberId(memberId.getValue());
        MemberBasicInformation memberBasicInformation = memberPersistenceConverter.toDomain(memberEntity);
        return MemberAggregateRoot.fromMemberBasicInformation(
                memberEntity.getMemberId(),
                memberEntity.getMembershipNumber(),
                memberBasicInformation);
    }

    @Override
    public MemberAggregateRoot getMemberProfile(MemberId memberId) {
        MemberEntity  memberEntity = memberMapper.getOneByMemberId(memberId.getValue());
        List<MemberIdentityDocumentEntity> memberIdentityDocumentEntityList = memberIdentityDocumentMapper.query(memberEntity.getMemberId());
        List<MemberFileEntity> memberFileEntityList =  memberFileEntity = memberFileMapper.query(memberEntity.getMemberId());
        MemberBasicInformation memberBasicInformation = memberPersistenceConverter.toDomain(memberEntity);
        return MemberAggregateRoot.fromMemberBasicInformation(
                memberEntity.getMemberId(),
                memberEntity.getMembershipNumber(),
                memberBasicInformation);
    }


    @Override
    public List<MemberBasicInformation> queryMember(Member member) {
        MemberEntity dataEntity = memberPersistenceConverter.toEntity(member);
        List<MemberEntity> memberEntityList = memberMapper.query(dataEntity);
        List<MemberBasicInformation> memberList = memberEntityList.stream().map(memberPersistenceConverter::toDomain).toList();
        return memberList;
    }

    @Override
    public void updateMember(Member member) throws Exception {
        MemberEntity dataEntity = memberPersistenceConverter.toEntity(member);
        int affectRows = memberMapper.update(dataEntity);
        if(affectRows != 1){
            throw new Exception("更新会员信息失败");
        }
    }

    @Override
    public List<MemberEntity> listMember(PageMemberRequest pageMemberRequest) {
        PageHelper.clearPage();
        PageHelper.startPage(pageMemberRequest.getPageNum(), pageMemberRequest.getPageSize(),true);
        PageHelper.orderBy(pageMemberRequest.getSort());

        List<MemberEntity> pageInfo = memberMapper.query(memberPersistenceConverter.toEntity(pageMemberRequest));
        PageHelper.clearPage();
        return pageInfo;
    }

    @Override
    public void createMember(Member domain) throws Throwable {
      int affectRows =  memberMapper.create(memberPersistenceConverter.toEntity(domain));
      if(affectRows != 1){
          throw new Exception("创建会员信息失败");
      }
    }

    @Override
    public MemberId getMemberId(MembershipNumber membershipNumber) {
        String memberId = memberMapper.getMemberId(membershipNumber.getValue());
        return MemberId.from(memberId);
    }
}
