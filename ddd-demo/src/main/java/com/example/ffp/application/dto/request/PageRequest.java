package com.example.ffp.application.dto.request;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class PageRequest {
    @Max(value = 100)
    private Integer pageNum;
    private Integer pageSize;
    @Valid
    private List<SortItem> sortItems;
    @Max(value = 5,message = "搜索关键字不能超过5个字符")
    private String keyword;


    @Pattern(
            regexp = "^(?:[a-zA-Z0-9_]+:[a-zA-Z0-9_]+(?:,[a-zA-Z0-9_]+:[a-zA-Z0-9_]+)*)?$",
            message = "无效的排序参数"
    )
    private String sort;


    // 允许的排序字段白名单
    private static final Set<String> ALLOWED_FIELDS = Set.of(
            "createTime", "mileage", "status", "tier"
    );
    private static final Set<String> ALLOWED_DIRECTIONS = Set.of(
            "createTime", "mileage", "status", "tier"
    );

    public void setSort(String sort) throws Exception {
        this.sort = sort;
        if (sort != null && !sort.isEmpty()) {
            String[] sortItems = sort.split(",");
            for (String sortItem : sortItems) {
                String[] sortItemArr = sortItem.split(":");
                if (sortItemArr.length != 2) {
                    break;
                }
                if(this.sortItems == null){
                    this.sortItems = new ArrayList<>();
                }
                this.sortItems.add(new SortItem(sortItemArr[0],sortItemArr[1]));
            }
        }
    }

    private static String[] getStrings(String sortItem) throws Exception {
//        BindingResult result = new BeanPropertyBindingResult(sortItem, "sort");
        String[] sortItemArr = sortItem.split(":");
        if (sortItemArr.length != 2) {
//            result.addError(new FieldError("",  "sort", "无效的排序参数"));
            throw new Exception("无效的排序参数");

        }
//        if (!ALLOWED_FIELDS.contains(sortItemArr[0])) {
////            result.addError(new FieldError("","sort",  "无效的排序字段["+sortItemArr[0]+"]"));
//            throw new Exception("无效的排序字段["+sortItemArr[0]+"]");
//        }
//        if(!ALLOWED_DIRECTIONS.contains(sortItemArr[1])){
//            throw new Exception("无效的排序方向["+sortItemArr[1]+"]");
//        }

        return sortItemArr;
    }
}
