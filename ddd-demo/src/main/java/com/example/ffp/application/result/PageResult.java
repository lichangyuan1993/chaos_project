package com.example.ffp.application.result;

import com.github.pagehelper.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PageResult<T> extends Page<T> {
//    private Page<T> resultPage;
//
//    public static <T> PageResult<T> of(Page<T> pageInfo){
//        PageResult<T> pageResult = new PageResult<>();
//        pageResult.setResultPage(pageInfo);
//        return pageResult;
//    }
}
