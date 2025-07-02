package com.example.ffp.infrastructure.persistence.impl;

import com.example.ffp.domain.model.*;
import com.example.ffp.infrastructure.persistence.mybatis.entity.MemberFileEntity;
import com.example.ffp.infrastructure.persistence.mybatis.entity.MemberIdentityDocumentEntity;
import com.example.ffp.infrastructure.persistence.mybatis.mapper.MemberFileMapper;
import com.example.ffp.infrastructure.persistence.mybatis.mapper.MemberIdentityDocumentMapper;
import com.example.ffp.interfaces.web.dto.request.PageMemberRequest;
import com.example.ffp.domain.repository.MemberBasicInfoRepository;
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
public class MemberBasicInfoRepositoryMybatisImpl implements MemberBasicInfoRepository {
    @Resource
    private MemberMapper memberMapper;
    @Resource
    private MemberPersistenceConverter memberPersistenceConverter;
    @Resource
    private MemberIdentityDocumentMapper memberIdentityDocumentMapper;
    @Resource
    private MemberFileMapper memberFileMapper;

    @Override
    public Member getMemberBasicInformation(MemberId memberId) {
        MemberEntity  memberEntity = memberMapper.getOneByMemberId(memberId.getValue());
        MemberBasicInformation memberBasicInformation = memberPersistenceConverter.toDomain(memberEntity);
        return Member.fromMemberBasicInformation(
                memberEntity.getMemberId(),
                memberEntity.getMembershipNumber(),
                memberBasicInformation);
    }

    @Override
    public Member getMemberProfile(MemberId memberId) {
        // 查询基础信息
        MemberEntity  memberEntity = memberMapper.getOneByMemberId(memberId.getValue());

        MemberBasicInformation memberBasicInformation = memberPersistenceConverter.toDomain(memberEntity);
        // 查询证件信息
        List<MemberIdentityDocumentEntity> memberIdentityDocumentEntityList = memberIdentityDocumentMapper.selectByMemberRecId(memberEntity.getMemberId());
        // 查询附件信息
        List<MemberFileEntity> memberFileEntityList = memberFileMapper.selectByMemberRecId(memberEntity.getMemberId());
        List<MemberIdentityDocument> memberIdentityDocumentList = memberIdentityDocumentEntityList.stream().map(memberPersistenceConverter::toDomain).toList();

        List<MemberFile> memberFileList = memberFileEntityList.stream().map(memberPersistenceConverter::toDomain).toList();
        return Member.fromMemberProfile(
                memberEntity.getMemberId(),
                memberEntity.getMembershipNumber(),
                memberBasicInformation,
                memberIdentityDocumentList,
                memberFileList);
    }


//    @Override
//    public List<MemberBasicInformation> queryMember(Member member) {
//        MemberEntity dataEntity = memberPersistenceConverter.toEntity(member);
//        List<MemberEntity> memberEntityList = memberMapper.query(dataEntity);
//        List<MemberBasicInformation> memberList = memberEntityList.stream().map(memberPersistenceConverter::toDomain).toList();
//        return memberList;
//    }

//    @Override
//    public void updateMember(Member member) throws Exception {
//        MemberEntity dataEntity = memberPersistenceConverter.toEntity(member);
//        int affectRows = memberMapper.update(dataEntity);
//        if(affectRows != 1){
//            throw new Exception("更新会员信息失败");
//        }
//    }

    @Override
    public List<MemberEntity> queryMember(PageMemberRequest pageMemberRequest) {

        List<MemberEntity> pageInfo = memberMapper.select(memberPersistenceConverter.toEntity(pageMemberRequest));
        PageHelper.clearPage();
        return pageInfo;
    }

//    @Override
//    public void createMember(Member domain) throws Throwable {
//      int affectRows =  memberMapper.create(memberPersistenceConverter.toEntity(domain));
//      if(affectRows != 1){
//          throw new Exception("创建会员信息失败");
//      }
//    }

    @Override
    public MemberId getMemberId(MembershipNumber membershipNumber) {
        String memberId = memberMapper.getMemberId(membershipNumber.getValue());
        return MemberId.from(memberId);
    }
}
