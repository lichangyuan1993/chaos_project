package com.example.ffp.interfaces.web.dto.response;

import com.example.ffp.interfaces.web.dto.request.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
public class  PageResponse<T> {

    private Integer pageSize;
    private Integer pageNum;
    private Integer total;
    private Integer pages;

    private List<T> list;
}
