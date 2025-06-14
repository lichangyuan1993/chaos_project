SELECT
    COLUMN_NAME AS "字段名",
    COMMENTS AS "字段注释",
    DATA_TYPE AS "数据类型",
    DATA_LENGTH AS "字节长度",
    CHAR_LENGTH AS "字符长度",
    CASE
        WHEN DATA_TYPE IN ('NVARCHAR', 'NVARCHAR2')
            THEN CAST(DATA_LENGTH / 4 AS VARCHAR) || '字符'  -- UTF-8编码下每个字符约3字节
        WHEN DATA_TYPE IN ('CHAR', 'VARCHAR','VARCHAR')
            THEN CAST(CHAR_LENGTH AS VARCHAR) || '字符'
        ELSE CAST(DATA_LENGTH AS VARCHAR) || '字节'
        END AS "Unicode字符长度",
    DATA_PRECISION AS "精度",
    NULLABLE AS "是否为空"
FROM
    ALL_TAB_COLUMNS
        LEFT JOIN
    ALL_COL_COMMENTS USING (OWNER, TABLE_NAME, COLUMN_NAME)
WHERE
        OWNER = 'FFP'
  AND TABLE_NAME = 'MEMBER_IDENTITY_DOCUMENT'
ORDER BY
    COLUMN_ID;  -- 按字段定义顺序排序


-- 使用达梦内置函数获取DDL
CALL SP_TABLEDEF('FFP', 'MEMBER');