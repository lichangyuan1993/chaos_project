package com.example.ffp.infrastructure.config;

import org.mapstruct.MapperConfig;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@MapperConfig(
        unmappedTargetPolicy = ReportingPolicy.ERROR, // 未映射目标字段报错
        nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT, // 数组映射，空数组映射为空集合
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL // 属性映射，空值映射为null
)
public interface CentralConfig {

}