package com.example.ffp.interfaces.web.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetMemberRequest extends Request {
    private String memberId;
    private String membershipNumber;
}
