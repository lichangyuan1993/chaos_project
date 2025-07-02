package com.example.ffp.interfaces.web.dto.response;

import com.example.ffp.interfaces.web.dto.request.Request;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class  PageResponse<T> extends PageInfo<T> {

}
