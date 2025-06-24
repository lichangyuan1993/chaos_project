package com.example.ffp.interfaces.web.converter;

import com.example.ffp.application.command.CreateMemberCommand;
import com.example.ffp.application.command.UpdateMemberCommand;
import com.example.ffp.application.query.GetMemberQuery;
import com.example.ffp.application.result.MemberProfileResult;
import com.example.ffp.interfaces.web.dto.request.CreateMemberRequest;
import com.example.ffp.interfaces.web.dto.request.GetMemberRequest;
import com.example.ffp.interfaces.web.dto.request.UpdateMemberRequest;
import com.example.ffp.interfaces.web.dto.response.MemberProfileResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberInterfaceConverter {
    CreateMemberCommand toCommand(CreateMemberRequest request);

    GetMemberQuery toQuery(GetMemberRequest request);

    MemberProfileResponse toResponse(MemberProfileResult memberProfileResult);

    UpdateMemberCommand toCommand(UpdateMemberRequest request);
}
