package com.example.ffp.application.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SortItem {
    private String field; // 前端字段名
    @Pattern(regexp = "^(asc|desc)$", message = "无效的排序方向")
    private String direction; // 应用排序方向
}
