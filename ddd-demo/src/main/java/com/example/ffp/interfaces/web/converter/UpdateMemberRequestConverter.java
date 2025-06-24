package com.example.ffp.interfaces.web.converter;

import com.example.ffp.application.command.UpdateMemberCommand;
import com.example.ffp.interfaces.web.dto.request.UpdateMemberRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UpdateMemberRequestConverter {
    UpdateMemberCommand toCommand(UpdateMemberRequest request);
}
