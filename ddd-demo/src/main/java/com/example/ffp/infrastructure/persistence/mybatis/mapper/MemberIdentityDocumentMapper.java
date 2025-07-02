package com.example.ffp.infrastructure.persistence.mybatis.mapper;

import com.example.ffp.infrastructure.persistence.mybatis.entity.MemberIdentityDocumentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.type.Alias;

import java.util.List;

/**
* @author clayton
* @description 针对表【MEMBER_IDENTITY_DOCUMENT】的数据库操作Mapper
* @createDate 2025-06-29 23:01:56
* @Entity generator.domain.MemberIdentityDocumentEntity
*/
@Mapper
@Alias("MemberIdentityDocumentMapper")
public interface MemberIdentityDocumentMapper {

    MemberIdentityDocumentEntity getOneByRecId(String memberId);
    MemberIdentityDocumentEntity getOneByMemberRecId(String memberRecId);

    List<MemberIdentityDocumentEntity> selectByMemberRecId(String memberRecId);

}




