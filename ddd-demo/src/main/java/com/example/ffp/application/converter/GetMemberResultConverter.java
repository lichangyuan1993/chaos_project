package com.example.ffp.application.converter;


import com.example.ffp.application.query.GetMemberQuery;
import com.example.ffp.application.result.GetMemberResult;
import com.example.ffp.interfaces.web.dto.request.MemberRequest;
import com.example.ffp.interfaces.web.dto.response.MemberResponse;
import com.example.ffp.domain.model.Member;
import com.example.ffp.infrastructure.persistence.mybatis.entity.MemberEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

//@Mapper
//@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GetMemberResultConverter {


    Member toDomain(GetMemberQuery query);
    GetMemberResult toResult(Member member);

    MemberResponse toResponse(Member source);

    MemberResponse toResponse(MemberEntity source);

    MemberEntity toEntity(MemberRequest source);
}
