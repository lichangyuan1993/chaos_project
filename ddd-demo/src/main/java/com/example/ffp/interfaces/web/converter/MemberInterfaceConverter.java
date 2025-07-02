package com.example.ffp.interfaces.web.converter;

import com.example.ffp.application.command.*;
import com.example.ffp.application.query.GetMemberQuery;
import com.example.ffp.application.result.MemberProfileResult;
import com.example.ffp.application.result.PageResult;
import com.example.ffp.interfaces.web.dto.request.*;
import com.example.ffp.interfaces.web.dto.response.MemberProfileResponse;
import com.example.ffp.interfaces.web.dto.response.PageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberInterfaceConverter {
    CreateMemberCommand toCommand(CreateMemberRequest source);
    MemberBasicInfoCommand toCommand(MemberBasicInfoRequest source);
    MemberIdentityDocumentCommand toCommand(MemberIdentityDocumentRequest source);
    MemberFileCommand toCommand(MemberFileRequest source);

    GetMemberQuery toQuery(GetMemberRequest source);

    MemberProfileResponse toResponse(MemberProfileResult source);

    UpdateMemberCommand toCommand(UpdateMemberRequest source);


    <S,T> PageResponse<T> toResponse(PageResult<S> source);



}
