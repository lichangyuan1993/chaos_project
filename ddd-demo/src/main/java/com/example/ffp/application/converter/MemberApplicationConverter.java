package com.example.ffp.application.converter;

import com.example.ffp.application.command.CreateMemberCommand;
import com.example.ffp.application.command.UpdateMemberCommand;
import com.example.ffp.application.result.MemberBasicInfoResult;
import com.example.ffp.application.result.MemberProfileResult;
import com.example.ffp.application.result.PageResult;
import com.example.ffp.domain.model.Member;
import com.example.ffp.infrastructure.persistence.mybatis.entity.MemberEntity;
import com.github.pagehelper.Page;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import javax.xml.transform.Result;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberApplicationConverter {
    Member toAggregateRoot(CreateMemberCommand createMemberCommand);

    Member toAggregateRoot(UpdateMemberCommand command);

    MemberProfileResult toResult(Member aggregateRoot);

    PageResult<MemberBasicInfoResult> toResult(Page<MemberEntity> source);
}
