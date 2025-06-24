package com.example.ffp.application.util;

import com.github.f4b6a3.ulid.Ulid;
import com.github.f4b6a3.ulid.UlidCreator;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class UniqueKey {

    public static String getUniqueKey(){
        /**
         * 防回拨选项 UlidCreator.getMonotonicUlid();
         */
        return UlidCreator.getMonotonicUlid().toString();
        /**
         * 任何子公司系统可独立生成
         */

//        String userGuid = UlidCreator.getUlid().toString();
    }

    /**
     * 从ULID提取创建时间（可选）
     * @param ulid ULID字符串
     * @return 创建时间的Instant对象
     */
    public static Instant getCreateTime(String ulid) {
        return Ulid.from(ulid).getInstant();
    }
    public static void main(String[] args) throws InterruptedException {
        // 生成单调递增的 ULID
        Ulid ulid1 = UlidCreator.getMonotonicUlid();
        Thread.onSpinWait();
        Thread.sleep(1000);
        Ulid ulid2 = UlidCreator.getMonotonicUlid();
        
        System.out.println(ulid1); // 例如: 01H5ZYXB2TMJN7X9HSKGMK4Z
        System.out.println(ulid2); // 同一毫秒内，ulid2 > ulid1

//        Ulid prev = UlidCreator.getMonotonicUlid();
//        for (int i = 0; i < 1000; i++) {
//            Ulid current = UlidCreator.getMonotonicUlid();
//            assert  current.compareTo(prev) > 0 : "ULID 必须递增";
//            prev = current;
//        }

        System.out.println("---------- 从ULID获取ID生成的时间 --------------");
        ZonedDateTime zonedDateTime1 = getCreateTime("01JY3CSVBTCQRRCGHA6BPWMFVD").atZone(ZoneId.systemDefault());
        ZonedDateTime zonedDateTime2 = getCreateTime("01JY3CSWB6DX5R7E6A002WYCSV").atZone(ZoneId.systemDefault());
        System.out.println(zonedDateTime1.toLocalDateTime());
        System.out.println(zonedDateTime2.toLocalDateTime());
    }
}