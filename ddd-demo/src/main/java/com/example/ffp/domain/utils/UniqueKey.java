package com.example.ffp.domain.utils;

import com.github.f4b6a3.ulid.Ulid;
import com.github.f4b6a3.ulid.UlidCreator;

public class UniqueKey {

    public static String getUniqueKey(){
        return UlidCreator.getMonotonicUlid().toString();
    }
    public static void main(String[] args) {
        // 生成单调递增的 ULID
        Ulid ulid1 = UlidCreator.getMonotonicUlid();
        Ulid ulid2 = UlidCreator.getMonotonicUlid();
        
        System.out.println(ulid1); // 例如: 01H5ZYXB2TMJN7X9HSKGMK4Z
        System.out.println(ulid2); // 同一毫秒内，ulid2 > ulid1

//        Ulid prev = UlidCreator.getMonotonicUlid();
//        for (int i = 0; i < 1000; i++) {
//            Ulid current = UlidCreator.getMonotonicUlid();
//            assert  current.compareTo(prev) > 0 : "ULID 必须递增";
//            prev = current;
//        }
    }
}