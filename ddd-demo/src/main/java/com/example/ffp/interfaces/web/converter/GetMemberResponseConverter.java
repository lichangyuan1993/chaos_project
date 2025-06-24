package com.example.ffp.interfaces.web.converter;

import com.example.ffp.application.result.GetMemberResult;
import com.example.ffp.domain.model.Member;
import com.example.ffp.interfaces.web.dto.response.GetMemberResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GetMemberResponseConverter {
    GetMemberResponse toResponse(GetMemberResult result);
}
