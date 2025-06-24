package com.example.ffp.application.converter;

import com.example.ffp.application.command.CreateMemberCommand;
import com.example.ffp.application.command.UpdateMemberCommand;
import com.example.ffp.application.result.MemberProfileResult;
import com.example.ffp.domain.model.Member;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberApplicationConverter {
    Member toAggregateRoot(CreateMemberCommand createMemberCommand);

    Member toAggregateRoot(UpdateMemberCommand command);

    MemberProfileResult toResult(Member aggregateRoot);


}
