package com.example.ffp.domain.utils;
import org.apache.commons.lang3.RandomStringUtils;

public class MembershipNumberGenerator {
   private static final RandomStringUtils INSTANCE  = RandomStringUtils.secureStrong();
    public static String getMembershipNumber()
    {
        String randomDigits = INSTANCE.next(12, false,true);
        System.out.println(randomDigits); // 输出：如 492830175629
        return randomDigits;
    }
}
