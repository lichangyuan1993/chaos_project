package com.example.ffp.interfaces.web.converter;

import com.example.ffp.application.result.UpdateMemberResult;
import com.example.ffp.interfaces.web.dto.request.UpdateMemberRequest;
import com.example.ffp.interfaces.web.dto.response.UpdateMemberResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UpdateMemberResponseConverter {
    UpdateMemberResponse toResponse(UpdateMemberResult request);
}
