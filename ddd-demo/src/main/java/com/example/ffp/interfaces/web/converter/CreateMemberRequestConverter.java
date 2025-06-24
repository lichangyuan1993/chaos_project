package com.example.ffp.interfaces.web.converter;

import com.example.ffp.interfaces.web.dto.request.CreateMemberRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public class CreateMemberRequestConverter {
    public CreateMemberCommand toCommand(CreateMemberRequest request);
}
