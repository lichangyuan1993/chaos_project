package com.example.ffp.interfaces.web.converter;

import com.example.ffp.application.query.GetMemberQuery;
import com.example.ffp.interfaces.web.dto.request.GetMemberRequest;

public interface GetMemberRequestConverter {
    GetMemberQuery toQuery(GetMemberRequest request);
}
