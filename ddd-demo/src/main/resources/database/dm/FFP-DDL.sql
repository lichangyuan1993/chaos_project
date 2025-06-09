/**
  *达梦V8数据库：
  * VARCHAR2(1) 和VARCHAR(1),都分配1个字节
  * VARCHAR(1) 和NVARCHAR(1),都分配4个字节
  * CHAR(1) 分配一个字节
  * 为了统一, 建议允许ascii以外字符，使用NVARCHAR（推荐） VARCHAR
  * 为了统一，建议不允许ASCII以外字符，使用VARCHAR（推荐） VARCHAR2
  * VARCHAR 和 VARCHAR 的区别，目前只有oracle数据库才有此差异
  * VARCHAR 和 VARCHAR2的区别，目前只有oracle数据库才有此差异
 */

/**
  * 表字段设计规则:
  * DELETED 字段必须出现在允许删除数据的表中，逻辑删除字段，默认为N，N为正常数据，Y为逻辑删除数据
  * VERSION_NUMBER 必须出现在允许编辑数据的表中，版本号默认为0
  *
 */


DROP TABLE IF EXISTS FFP.MEMBER;

CREATE TABLE FFP.MEMBER
(
    -- 会员基础信息
    REC_ID                    VARCHAR(36) PRIMARY KEY                          NOT NULL,
    MEMBER_ID                 VARCHAR(32)                                      NOT NULL,
    MEMBERSHIP_NUMBER         VARCHAR(12)                                      NOT NULL,
    GENDER                    CHAR(1)                     DEFAULT 'U'          NOT NULL,
    DATE_OF_BIRTH             DATE                                             NOT NULL,
    FAMILY_NAME_CN            NVARCHAR(32) DEFAULT '' NOT NULL,
    GIVEN_NAME_CN             NVARCHAR(32) DEFAULT '' NOT NULL,
    FAMILY_NAME_EN            VARCHAR(32)                 DEFAULT ''           NOT NULL,
    GIVEN_NAME_EN             VARCHAR(32)                 DEFAULT ''           NOT NULL,
    NATIONALITY               VARCHAR(12)                 DEFAULT ''           NOT NULL,
    ENROLLMENT_DATE           TIMESTAMP(0)                                     NOT NULL,
    SOURCE_CHANNEL            VARCHAR(12)                 DEFAULT ''           NOT NULL,

    -- 等级与状态
    MEMBERSHIP_TIER           VARCHAR(12)                 DEFAULT 'STANDARD'   NOT NULL,
    MEMBERSHIP_STATUS         VARCHAR(12)                 DEFAULT 'ACTIVE'     NOT NULL,
    TIER_EFFECTIVE_DATE       DATE                        DEFAULT SYSDATE      NOT NULL,
    TIER_EXPIRY_DATE          DATE                        DEFAULT '9999-12-31' NOT NULL,
    LIFETIME_ELITE_IND        CHAR(1)                     DEFAULT 'N'          NOT NULL,

    -- 里程账户
    CURRENT_NAUTICAL_MILE     DECIMAL(10, 3)              DEFAULT 0.0,
    ELIGIBLE_NAUTICAL_MILE    DECIMAL(10, 3)              DEFAULT 0.0,
    LIFETIME_NAUTICAL_MILE    DECIMAL(10, 3)              DEFAULT 0.0,
    NEXT_NAUTICAL_MILE_EXPIRY DATE,

    -- 保级进度
    CURRENT_TIER_QUAL_MILE    DECIMAL(10, 2)              DEFAULT 0.0,
    CURRENT_TIER_QUAL_SEGMENT INT                         DEFAULT 0,
    TIER_QUAL_START_DATE      DATE,
    TIER_QUAL_END_DATE        DATE,

    -- 安全审计
    PIN_CODE                  VARCHAR(12), -- BCRYPT加密存储
    LAST_ACTIVITY_DATE        DATETIME(0) DEFAULT CURRENT_TIMESTAMP,
    CREATE_TIMESTAMP          TIMESTAMP(3) WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    UPDATE_TIMESTAMP          TIMESTAMP(3) WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CREATE_USER               VARCHAR(24)                                      NOT NULL,
    UPDATE_USER               VARCHAR(24)                                      NOT NULL,
    VERSION_NUMBER            INTEGER                     DEFAULT 0            NOT NULL
);

INSERT INTO FFP.MEMBER (REC_ID, MEMBER_ID, MEMBERSHIP_NUMBER, DATE_OF_BIRTH, FAMILY_NAME_EN, GIVEN_NAME_EN,
                        FAMILY_NAME_CN,
                        GIVEN_NAME_CN, ENROLLMENT_DATE, CREATE_USER, UPDATE_USER)
VALUES ('1A1234GAS126', '2A1234GAS126', '601234567890', '1997-11-28', 'Thompson', 'Emily Charlotte', '勤勉', '自由人',
        CURRENT_TIMESTAMP, 'ADMIN', 'ADMIN'),
       ('1A1234GAS127', '2A1234GAS127', '601234567891', '1997-06-01', 'Han', 'Li', '韩', '立', CURRENT_TIMESTAMP,
        'ADMIN', 'ADMIN'),
       ('1A1234GAS128', '2A1234GAS128', '601234567892', '1997-07-21', 'Carter', 'James Alexander', '代替者', '保护者',
        CURRENT_TIMESTAMP, 'ADMIN', 'ADMIN'),
       ('1A1234GAS129', '2A1234GAS129', '601234567893', '1997-08-01', 'Li', 'FeiYu', '厉', '飞雨', CURRENT_TIMESTAMP,
        'ADMIN',
        'ADMIN'),
       ('1A1234GAS130', '2A1234GAS130', '601234567894', '1997-10-01', 'Zhang', 'Tie', '张', '铁', CURRENT_TIMESTAMP,
        'ADMIN',
        'ADMIN');


-- 1. 创建证件类型字典表
DROP TABLE IF EXISTS FFP.DICTIONARY_IDENTITY_DOCUMENT_TYPE;
CREATE TABLE FFP.DICTIONARY_IDENTITY_DOCUMENT_TYPE
(
    REC_ID            VARCHAR(36) PRIMARY KEY,
    TYPE_CODE         VARCHAR(36) UNIQUE,                      -- 枚举代码
    TYPE_NAME         NVARCHAR(36) NOT NULL,                   -- 类型名称
    EFFECTIVITY_IND   CHAR(1)                     DEFAULT 'Y', -- 是否启用
    VERIFIED_DATETIME TIMESTAMP,
    SEQUENCE_NUMBER   DECIMAL(10, 3)              DEFAULT 0.0, -- 排序号
    CREATE_DATETIME   TIMESTAMP(3) WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    UPDATE_DATETIME   TIMESTAMP(3) WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CREATE_USER       VARCHAR(24)                           NOT NULL,
    UPDATE_USER       VARCHAR(24)                           NOT NULL,
    VERSION_NUMBER    INTEGER                     DEFAULT 0 NOT NULL
);

-- 2. 插入初始枚举值
INSERT INTO FFP.DICTIONARY_IDENTITY_DOCUMENT_TYPE (REC_ID, TYPE_CODE, TYPE_NAME, CREATE_USER, UPDATE_USER)
VALUES ('1001', 'PASSPORT', '护照', 'ADMIN', 'ADMIN'),
       ('1002', 'NATIONAL_ID', '居民身份证', 'ADMIN', 'ADMIN'),
       ('1003', 'RESIDENCE_PERMIT', '外国人永久居留证', 'ADMIN', 'ADMIN'),
       ('1004', 'DRIVING_LICENSE', '机动车驾驶证', 'ADMIN', 'ADMIN'),
       ('1005', 'MILITARY_ID', '军官证/士兵证', 'ADMIN', 'ADMIN'),
       ('1006', 'OTHER', '其他证件', 'ADMIN', 'ADMIN');


DROP TABLE IF EXISTS FFP.MEMBER_IDENTITY_DOCUMENT;
-- 创建会员证件表 (兼容达梦V7/V8)
CREATE TABLE FFP.MEMBER_IDENTITY_DOCUMENT
(
    REC_ID            VARCHAR(36) PRIMARY KEY                       NOT NULL,
    MEMBER_REC_ID     VARCHAR(36)                                   NOT NULL,
    ID_TYPE           VARCHAR(36)                                   NOT NULL,
    ID_NUMBER         VARCHAR(24) ENCRYPT WITH AES256_ECB,                    -- 达梦加密语法修正
    COUNTRY_OF_ISSUE  CHAR(2)                                       NOT NULL, -- 签发国(ISO)
    EXPIRE_DATE       DATE                                          NOT NULL,
    PRIMARY_IND       CHAR(1)                     DEFAULT 'N'       NOT NULL,
    VERIFY_STATUS     VARCHAR(12)                 DEFAULT 'PENDING' NOT NULL,
    VERIFIED_DATETIME TIMESTAMP,
    SEQUENCE_NUMBER   DECIMAL(10, 3)              DEFAULT 0.0,
    CREATE_DATETIME   TIMESTAMP(3) WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    UPDATE_DATETIME   TIMESTAMP(3) WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CREATE_USER       VARCHAR(24)                                   NOT NULL,
    UPDATE_USER       VARCHAR(24)                                   NOT NULL,
    VERSION_NUMBER    INTEGER                     DEFAULT 0         NOT NULL,
    DELETED           CHAR(1)                     DEFAULT 'N'       NOT NULL
);

ALTER TABLE FFP.MEMBER_IDENTITY_DOCUMENT
    ADD CONSTRAINT FK_MID_ID_TYPE
        FOREIGN KEY (ID_TYPE) REFERENCES FFP.DICTIONARY_IDENTITY_DOCUMENT_TYPE (TYPE_CODE);


INSERT INTO FFP.MEMBER_IDENTITY_DOCUMENT(REC_ID, MEMBER_REC_ID, ID_TYPE, ID_NUMBER, COUNTRY_OF_ISSUE, EXPIRE_DATE,
                                         CREATE_USER, UPDATE_USER)
VALUES ('2001', '1A1234GAS126', 'PASSPORT', 'E123456789', 'CN', '2026-12-31', 'ADMIN', 'ADMIN'),
       ('2002', '1A1234GAS126', 'NATIONAL_ID', '110101199001011234', 'CN', '2026-12-31', 'ADMIN', 'ADMIN'),
       ('2003', '1A1234GAS127', 'PASSPORT', 'E987654321', 'US', '2026-12-31', 'ADMIN', 'ADMIN');



DROP TABLE IF EXISTS FFP.MEMBER_FILE;
CREATE TABLE FFP.MEMBER_FILE
(
    REC_ID          VARCHAR(36) PRIMARY KEY                 NOT NULL,
    MEMBER_REC_ID   VARCHAR(36)                             NOT NULL,
    RELATION_TABLE  VARCHAR(64)                             NOT NULL,
    FILE_NAME       NVARCHAR(255) DEFAULT '',
    FILE_TYPE       VARCHAR(5)                  DEFAULT '',
    FILE_SIZE       INTEGER                     DEFAULT 0,
    FILE_CONTENT    BLOB                        DEFAULT 0x0000,
    UPLOAD_DATETIME TIMESTAMP(3) WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CREATE_DATETIME TIMESTAMP(3) WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    UPDATE_DATETIME TIMESTAMP(3) WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CREATE_USER     VARCHAR(24)                             NOT NULL,
    UPDATE_USER     VARCHAR(24)                             NOT NULL,
    VERSION_NUMBER  INTEGER                     DEFAULT 0,
    DELETED         CHAR(1)                     DEFAULT 'N' NOT NULL
);


INSERT INTO FFP.MEMBER_FILE(REC_ID, MEMBER_REC_ID, RELATION_TABLE, FILE_NAME, FILE_TYPE, FILE_SIZE, FILE_CONTENT,
                            CREATE_USER, UPDATE_USER)
VALUES ('3001', '1A1234GAS126', 'MEMBER_IDENTITY_DOCUMENT', '重生之参加抗日战争', 'pdf', 10, 0x0000, 'ADMIN', 'ADMIN'),
       ('3002', '1A1234GAS126', 'MEMBER_IDENTITY_DOCUMENT', '重生之参加抗美援朝', 'pdf', 10, 0x0000, 'ADMIN', 'ADMIN'),
       ('3003', '1A1234GAS127', 'MEMBER_IDENTITY_DOCUMENT', '重生之参加对越反击自卫战', 'pdf', 10, 0x0000, 'ADMIN',
        'ADMIN');


-- 创建状态字典表
DROP TABLE IF EXISTS FFP.SYSTEM_DICTIONARY;
CREATE TABLE FFP.SYSTEM_DICTIONARY
(
    REC_ID          VARCHAR(36) PRIMARY KEY,
    RELATION_TABLE  VARCHAR(64)                             NOT NULL,
    TABLE_FIELD     VARCHAR(36)                             NOT NULL,
    STATUS_CODE     VARCHAR(36)                             NOT NULL,
    STATUS_NAME     NVARCHAR(12) NOT NULL,
    REMARK          NVARCHAR(128) NOT NULL,
    EXTRA_ATTRIBUTE CLOB,
    SYSTEM_IND      CHAR(1)                     DEFAULT 'N' NOT NULL, -- Y 禁止编辑
    EFFECTIVITY_IND CHAR(1)                     DEFAULT 'Y' NOT NULL,
    SEQUENCE_NUMBER DECIMAL(10, 3)              DEFAULT 0.0 NOT NULL,
    CREATE_DATETIME TIMESTAMP(3) WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    UPDATE_DATETIME TIMESTAMP(3) WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CREATE_USER     VARCHAR(24)                             NOT NULL,
    UPDATE_USER     VARCHAR(24)                             NOT NULL,
    VERSION_NUMBER  INTEGER                     DEFAULT 0   NOT NULL
);

-- 插入状态数据
INSERT INTO FFP.SYSTEM_DICTIONARY
(REC_ID, RELATION_TABLE, TABLE_FIELD, STATUS_CODE, STATUS_NAME, REMARK, SYSTEM_IND, CREATE_USER, UPDATE_USER)
VALUES ('4001', 'MEMBER_IDENTITY_DOCUMENT', 'VERIFY_STATUS', 'PENDING', '待核验（初始状态）',
        '会员新上传证件后自动进入此状态', 'Y', 'ADMIN', 'ADMIN'),
       ('4002', 'MEMBER_IDENTITY_DOCUMENT', 'VERIFY_STATUS', 'IN_REVIEW', '审核中（人工介入流程）',
        '证件模糊或信息存疑时转人工审核', 'Y', 'ADMIN', 'ADMIN'),
       ('4003', 'MEMBER_IDENTITY_DOCUMENT', 'VERIFY_STATUS', 'VERIFIED', '已核验通过（完整通过）', '航司地勤可信任此证件',
        'Y', 'ADMIN', 'ADMIN'),
       ('4004', 'MEMBER_IDENTITY_DOCUMENT', 'VERIFY_STATUS', 'PARTIAL_VERIFY', '部分核验通过',
        '护照核验通过但签证页未验证', 'Y', 'ADMIN', 'ADMIN'),
       ('4005', 'MEMBER_IDENTITY_DOCUMENT', 'VERIFY_STATUS', 'EXPIRED', '已过期（系统自动标记）',
        '证件超过有效期自动进入此状态', 'Y', 'ADMIN', 'ADMIN'),
       ('4006', 'MEMBER_IDENTITY_DOCUMENT', 'VERIFY_STATUS', 'REJECTED', '核验驳回', '证件照片与会员人脸不匹配', 'Y',
        'ADMIN', 'ADMIN'),
       ('4007', 'MEMBER_IDENTITY_DOCUMENT', 'VERIFY_STATUS', 'SUSPENDED', '已暂停使用', '证件涉嫌欺诈被临时冻结', 'Y',
        'ADMIN', 'ADMIN'),
       ('4008', 'MEMBER_IDENTITY_DOCUMENT', 'VERIFY_STATUS', 'GOV_API_FAIL', '政府系统核验失败',
        '公安接口核验失败(例如：证件不存在)', 'Y', 'ADMIN', 'ADMIN'),
       ('4009', 'MEMBER_IDENTITY_DOCUMENT', 'VERIFY_STATUS', 'THIRD_PARTY_PENDING', '第三方核验中',
        '第三方核验中（如对接外籍证件验证服务）', 'Y', 'ADMIN', 'ADMIN'),
       ('4010', 'MEMBER_IDENTITY_DOCUMENT', 'VERIFY_STATUS', 'RENEWAL_REQUIRED', '需更新证件',
        '需更新证件（如护照换发后关联的居留证）', 'Y', 'ADMIN', 'ADMIN'),
       ('4011', 'MEMBER_IDENTITY_DOCUMENT', 'VERIFY_STATUS', 'ARCHIVED', '已归档（历史记录）',
        '会员更换新证件后，旧证件的状态', 'Y', 'ADMIN', 'ADMIN');


-- 可以看作数组或者字典，以及两者的混合体
SELECT REC_ID,
       JSON_VALUE(EXTRA_ATTRIBUTE, '$.gender')
FROM FFP.SYSTEM_DICTIONARY;

SELECT REC_ID, EXTRA_ATTRIBUTE
FROM FFP.SYSTEM_DICTIONARY;