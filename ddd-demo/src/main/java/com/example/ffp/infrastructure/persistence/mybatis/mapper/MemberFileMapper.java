package com.example.ffp.infrastructure.persistence.mybatis.mapper;


import com.example.ffp.infrastructure.persistence.mybatis.entity.MemberFileEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author clayton
* @description 针对表【MEMBER_FILE】的数据库操作Mapper
* @createDate 2025-06-30 00:18:55
* @Entity generator.domain.MemberFile
*/
@Mapper
public interface MemberFileMapper {

    List<MemberFileEntity> selectByMemberRecId(String memberId);
}




