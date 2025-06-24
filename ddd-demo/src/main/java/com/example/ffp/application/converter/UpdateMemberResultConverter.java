package com.example.ffp.application.converter;


import com.example.ffp.application.command.UpdateMemberCommand;
import com.example.ffp.application.result.UpdateMemberResult;
import com.example.ffp.domain.model.Member;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UpdateMemberResultConverter {

    UpdateMemberResult toResult(Member member);

    Member toDomain(UpdateMemberCommand command);
}
