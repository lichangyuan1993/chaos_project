package com.example.ffp.interfaces.web.converter;

import com.example.ffp.application.query.PageMemberQuery;
import com.example.ffp.interfaces.web.dto.request.PageMemberRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

// TODO 合并到MemberInterfaceConverter
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PageMemberRequestConverter {

    PageMemberQuery toQuery(PageMemberRequest request);


}
