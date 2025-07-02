package com.example.ffp.application.converter;

import com.example.ffp.application.result.MemberBasicInfoResult;
import com.example.ffp.infrastructure.persistence.mybatis.entity.MemberEntity;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

//@Mapper
// TODO 合并到 MemberResultConverter
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PageMemberResultConverter {

    PageMemberResultConverter INSTANCE = Mappers.getMapper(PageMemberResultConverter.class);

//    @Mappings(value =  {
//            @Mapping(source = "total", target = "total"),
//            @Mapping(source = "pages", target = "pages"),
//            @Mapping(source = "pageNum", target = "pageNum"),
//            @Mapping(source = "pageSize", target = "pageSize"),
//            @Mapping(expression = "java(source.getList().stream().map(MemberConverter.INSTANCE::toResponse).toList())", target = "list")
//    })
//    PageResponse<MemberResponse> toResponse(PageInfo<MemberEntity> source);

    PageInfo<MemberBasicInfoResult> toResult(PageInfo<MemberEntity> source);
}
