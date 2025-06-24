package com.example.ffp.interfaces.web.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class Request {
    private String operationType;
    private String operator;
    private LocalDateTime inboundDateTime;
    private LocalDateTime outboundDateTime;
}
