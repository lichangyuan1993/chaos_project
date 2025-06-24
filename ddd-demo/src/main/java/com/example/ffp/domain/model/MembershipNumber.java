package com.example.ffp.domain.model;

import com.example.ffp.domain.utils.MembershipNumberGenerator;

public final class MembershipNumber {
    // 领域语义明确的属性名
    private final String value; // 重命名为 memberId

    private MembershipNumber(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("会员ID不能为空");
        }
        this.value = value;
    }

    // 工厂方法语义更清晰
    public static MembershipNumber generate() {
        return new MembershipNumber(MembershipNumberGenerator.getMembershipNumber());
    }

    // 访问方法同步修改
    public String getValue() { // 方法名同步修改
        return value;
    }

    public static MembershipNumber from(String membershipNumber) {
        return new MembershipNumber(membershipNumber);
    }
}
