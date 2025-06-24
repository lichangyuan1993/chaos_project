package com.example.ffp.infrastructure.persistence.mybatis.converter;

import com.example.ffp.interfaces.web.dto.request.MemberRequest;
import com.example.ffp.domain.model.Member;
import com.example.ffp.infrastructure.persistence.mybatis.entity.MemberEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

//@Mapper
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberPersistenceConverter {

    MemberPersistenceConverter INSTANCE = Mappers.getMapper(MemberPersistenceConverter.class);

    MemberEntity toEntity(Member source);


    MemberEntity toEntity(MemberRequest source);

    Member toDomain(MemberEntity source);

}
