package com.example.ffp.infrastructure.persistence.mybatis.converter;

import com.example.ffp.domain.model.MemberBasicInformation;
import com.example.ffp.infrastructure.persistence.mybatis.entity.MemberEntity;
import com.example.ffp.interfaces.web.dto.request.PageMemberRequest;
import com.github.pagehelper.Page;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

//@Mapper
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberPersistenceConverter {

    MemberBasicInformation toDomain(MemberEntity source);

    MemberEntity toEntity(PageMemberRequest request);


}
