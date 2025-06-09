package com.example.ffp.infrastructure.persistence.mapper;

import com.example.ffp.infrastructure.persistence.entity.MemberEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author clayton
 * @description 针对表【MEMBER】的数据库操作Mapper
 * @createDate 2025-06-07 19:12:20
 * @Entity com.example.ffp.domain.model.Member
 */
@Mapper
public interface MemberMapper {

    MemberEntity getOne(MemberEntity entity);

    List<MemberEntity> query(MemberEntity entity);

    int update(MemberEntity entity);

    int create(MemberEntity entity);
}




