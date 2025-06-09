package com.example.ffp.infrastructure.persistence.converter;

import com.example.ffp.application.dto.request.MemberRequest;
import com.example.ffp.domain.model.Member;
import com.example.ffp.infrastructure.persistence.entity.MemberEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

//@Mapper
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberConverter {

    MemberConverter INSTANCE = Mappers.getMapper(MemberConverter.class);

    MemberEntity toEntity(Member source);


    MemberEntity toEntity(MemberRequest source);

    Member toDomain(MemberEntity source);

}
