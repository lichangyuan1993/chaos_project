package com.example.ffp.domain.model;

import com.example.ffp.domain.utils.MembershipNumberGenerator;

public final class MemberId {
    // 领域语义明确的属性名
    private final String value; // 重命名为 memberId

    private MemberId(String memberId) {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("会员ID不能为空");
        }
        this.value = memberId;
    }

    // 工厂方法语义更清晰
    public static MemberId generate() {
        return new MemberId(MembershipNumberGenerator.getMembershipNumber());
    }

    // 访问方法同步修改
    public String getValue() { // 方法名同步修改
        return value;
    }
    public static MemberId from(String memberId){
        return new MemberId(memberId);
    }
}
