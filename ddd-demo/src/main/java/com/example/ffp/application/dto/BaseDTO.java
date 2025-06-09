package com.example.ffp.application.dto;

import lombok.Data;
import org.hibernate.validator.internal.util.privilegedactions.LoadClass;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BaseDTO {
    private String operationType;
    private String operator;
    private LocalDateTime inboundDateTime;
    private LocalDateTime outboundDateTime;
}
