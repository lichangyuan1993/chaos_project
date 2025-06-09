package com.example.ffp.application.dto.response;

import com.example.ffp.application.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class  PageResponse<T> extends BaseDTO {

    private Integer pageSize;
    private Integer pageNum;
    private Integer total;
    private Integer pages;

    private List<T> list;
}
