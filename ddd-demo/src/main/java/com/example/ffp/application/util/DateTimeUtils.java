package com.example.ffp.application.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.regex.Pattern;

public class DateTimeUtils {

    // 预定义的日期格式（静态final，避免重复创建）
    public static final DateTimeFormatter DASH_DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter SLASH_DATE_FMT = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    public static final DateTimeFormatter DOT_DATE_FMT = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    public static final DateTimeFormatter PLAIN_DATE_FMT = DateTimeFormatter.ofPattern("yyyyMMdd");



    public static final DateTimeFormatter PLAIN_DATE_TIME_FMT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public static final DateTimeFormatter PLAIN_DATE_TIME_MILLI_FMT = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
    public static final DateTimeFormatter PLAIN_DATE_TIME_MICRO_FMT = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSSSS");
    public static final DateTimeFormatter PLAIN_DATE_TIME_NANO_FMT = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSSSSSSS");

    public static final DateTimeFormatter ISO_DATE_TIME_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    public static final DateTimeFormatter VARIANT_DATE_TIME_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final DateTimeFormatter ISO_DATE_TIME_MILLI_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
    public static final DateTimeFormatter VARIANT_DATE_TIME_MILLI_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static final DateTimeFormatter ISO_DATE_TIME_MICRO_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
    public static final DateTimeFormatter VARIANT_DATE_TIME_MICRO_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

    public static final DateTimeFormatter ISO_DATE_TIME_NANO_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS");
    public static final DateTimeFormatter VARIANT_DATE_TIME_NANO_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS");

    // 预编译的正则表达式
    public static final Pattern DIGITS_ONLY = Pattern.compile("\\d+");

    /**
     * 灵活解析日期/日期时间字符串
     * @param dateTimeStr 日期时间字符串，输入null或空字符串返回null
     * @param targetType 目标类型 (LocalDate.class 或 LocalDateTime.class)
     * @return 解析后的时间对象
     * @throws IllegalArgumentException 当无法解析时抛出
     */
    @SuppressWarnings("unchecked")
    public static <T> T parse(String dateTimeStr, Class<T> targetType) throws IllegalArgumentException {
        if (dateTimeStr == null || dateTimeStr.isBlank()) {
            return null;
        }

        if (!Objects.equals(targetType, LocalDate.class)  &&  !Objects.equals(targetType,LocalDateTime.class)) {
            throw new IllegalArgumentException("不支持的目标类型: " + targetType +
                    "，仅支持 LocalDate.class 和 LocalDateTime.class");
        }

        dateTimeStr = dateTimeStr.trim();
        final int length = dateTimeStr.length();
        final boolean localDateFlag = Objects.equals(targetType, LocalDate.class);
        final boolean localDateTimeFlag = Objects.equals(targetType, LocalDateTime.class);

        try {
            switch (length) {
                // yyyyMMdd
                case 8:
                    if (DIGITS_ONLY.matcher(dateTimeStr).matches()) {
                        LocalDate date = LocalDate.parse(dateTimeStr, PLAIN_DATE_FMT);
                        return localDateFlag ? (T) date : (T) date.atStartOfDay();
                    }
                    break;
                // yyyy-MM-dd
                case 10:
                    if (dateTimeStr.contains("-")) {
                        LocalDate date = LocalDate.parse(dateTimeStr, DASH_DATE_FMT);
                        return localDateFlag ? (T) date : (T) date.atStartOfDay();
                    } else if (dateTimeStr.contains("/")) {
                        LocalDate date = LocalDate.parse(dateTimeStr, SLASH_DATE_FMT);
                        return localDateFlag ? (T) date : (T) date.atStartOfDay();
                    } else if (dateTimeStr.contains(".")) {
                        LocalDate date = LocalDate.parse(dateTimeStr, DOT_DATE_FMT);
                        return localDateFlag ? (T) date : (T) date.atStartOfDay();
                    }
                    break;
                // yyyyMMddHHmmss
                case 14:
                    if (DIGITS_ONLY.matcher(dateTimeStr).matches()) {
                        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, PLAIN_DATE_TIME_FMT);
                        return localDateTimeFlag ? (T) dateTime : (T) LocalDate.from(dateTime);

                    }
                    break;
                // yyyyMMddHHmmssSSS
                case 17:
                    if (DIGITS_ONLY.matcher(dateTimeStr).matches()) {
                        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, PLAIN_DATE_TIME_MILLI_FMT);
                        return localDateTimeFlag ? (T) dateTime : (T) LocalDate.from(dateTime);
                    }
                    break;
                // yyyy-MM-dd HH:mm:ss
                case 19:
                    if (dateTimeStr.contains("T")) {
                        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, ISO_DATE_TIME_FMT);
                        return localDateTimeFlag ? (T) dateTime : (T) LocalDate.from(dateTime);

                    } else if (dateTimeStr.contains(" ")) {
                        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, VARIANT_DATE_TIME_FMT);
                        return localDateTimeFlag ? (T) dateTime : (T) dateTime.toLocalDate();
                    }
                    break;
                // yyyy-MM-dd HH:mm:ss.SSS
                case 23:
                    if (dateTimeStr.contains("T")) {
                        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, ISO_DATE_TIME_MILLI_FMT);
                        return localDateTimeFlag ? (T) dateTime : (T) LocalDate.from(dateTime);
                    } else if (dateTimeStr.contains(" ")) {
                        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, VARIANT_DATE_TIME_MILLI_FMT);
                        return localDateTimeFlag ? (T) dateTime : (T) LocalDate.from(dateTime);
                    }
                    break;
                // yyyy-MM-dd HH:mm:ss.SSSSSS
                case 26:
                    if (dateTimeStr.contains("T")) {
                        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, ISO_DATE_TIME_MICRO_FMT);
                        return localDateTimeFlag ? (T) dateTime : (T) dateTime.toLocalDate();
                    }
                    if (dateTimeStr.contains(" ")) {
                        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, VARIANT_DATE_TIME_MICRO_FMT);
                        return localDateTimeFlag ? (T) dateTime : (T) dateTime.toLocalDate();
                    }
                    break;
                // yyyy-MM-dd HH:mm:ss.SSSSSSSSS
                case 29:
                    if (dateTimeStr.contains(" ")) {
                        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, VARIANT_DATE_TIME_NANO_FMT);
                        return localDateTimeFlag ? (T) dateTime : (T) dateTime.toLocalDate();
                    }
                    break;
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(createErrorMessage(dateTimeStr, targetType), e);
        }

        throw new IllegalArgumentException(createErrorMessage(dateTimeStr, targetType));
    }

    private static String createErrorMessage(String input, Class<?> targetType) {
        String baseMsg = "无法解析" + (targetType == LocalDateTime.class ? "日期时间" : "日期") +
                ": " + input + "\n支持的格式: yyyy-MM-dd, yyyy/MM/dd, yyyy.MM.dd, yyyyMMdd";

        if (targetType == LocalDateTime.class) {
            baseMsg += ", yyyy-MM-dd HH:mm:ss, yyyy-MM-dd'T'HH:mm:ss, yyyyMMddHHmmss, " +
                    "yyyy-MM-dd HH:mm:ss.SSS, yyyy-MM-dd'T'HH:mm:ss.SSS, " +
                    "yyyy-MM-dd HH:mm:ss.SSSSSS, yyyy-MM-dd HH:mm:ss.SSSSSSSSS";
        }

        return baseMsg;
    }

    // 便捷方法，保持原有API
    public static LocalDate parseToLocalDate(String dateStr) throws IllegalArgumentException {
        return parse(dateStr, LocalDate.class);
    }

    public static LocalDateTime parseToLocalDateTime(String dateTimeStr) throws IllegalArgumentException {
        return parse(dateTimeStr, LocalDateTime.class);
    }

    // 测试用例
    public static void main(String[] args) {
        String[] testDates = {
                "20230515",
                "2023-05-15",
                "2023/05/15",
                "2023.05.15",
                "2023-05-15T14:30:00",
                "2023-05-15 14:30:00",
                "2023-05-15T14:30:00.123",
                "2023-05-15 14:30:00.123",
                "20230515143000",
                "20230515143010123",
                "2023-05-15 14:30:00.123456",
                "2023-05-15 14:30:00.123456789"
        };

        for (String dateStr : testDates) {
            try {
                System.out.println("作为日期: " + dateStr + " → " + parse(dateStr, LocalDate.class));

            } catch (Exception e) {
                System.err.println("解析失败: " + dateStr + " - " + e.getMessage());
            }
            try {
                System.out.println("作为日期时间: " + dateStr + " → " + parse(dateStr, LocalDateTime.class));
            } catch (Exception e) {
                System.err.println("解析失败: " + dateStr + " - " + e.getMessage());
            }
        }
    }
}