package com.example.ffp.application.converter;


import com.example.ffp.application.dto.request.MemberRequest;
import com.example.ffp.application.dto.response.MemberResponse;
import com.example.ffp.domain.model.Member;
import com.example.ffp.infrastructure.persistence.entity.MemberEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

//@Mapper
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberConverter {

    MemberConverter INSTANCE = Mappers.getMapper(MemberConverter.class);


    Member toDomain(MemberRequest source);

    MemberResponse toResponse(Member source);

    MemberResponse toResponse(MemberEntity source);

    MemberEntity toEntity(MemberRequest source);
}
