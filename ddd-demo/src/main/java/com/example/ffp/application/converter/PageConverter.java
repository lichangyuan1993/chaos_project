package com.example.ffp.application.converter;

import com.example.ffp.application.dto.response.MemberResponse;
import com.example.ffp.application.dto.response.PageResponse;
import com.example.ffp.infrastructure.persistence.entity.MemberEntity;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

//@Mapper
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PageConverter {

    PageConverter INSTANCE = Mappers.getMapper(PageConverter.class);

    @Mappings(value =  {
            @Mapping(source = "total", target = "total"),
            @Mapping(source = "pages", target = "pages"),
            @Mapping(source = "pageNum", target = "pageNum"),
            @Mapping(source = "pageSize", target = "pageSize"),
            @Mapping(expression = "java(source.getList().stream().map(MemberConverter.INSTANCE::toResponse).toList())", target = "list")
    })
    PageResponse<MemberResponse> toResponse(PageInfo<MemberEntity> source);
}
