
/**
  1. REC_ID（技术主键）
    定位：物理记录的唯一标识（Surrogate Key）
    用途：
    · 主键：作为表的物理主键，确保每条记录在数据库中的唯一性。
    · 性能优化：通常为自增整数或UUID，索引效率高，适合作为外键引用。
    · 稳定性：与业务无关，即使业务信息（如USER_ID）变更，REC_ID仍保持不变。
    示例场景：
    · 其他表通过 REC_ID 关联用户表（如订单表存储 USER_REC_ID）。
    · 系统内部微服务调用时，通过 REC_ID 精准定位用户记录。
  2. USER_ID（业务标识）
    定位：业务层面的用户唯一标识（Natural Key）
    用途：
    · 业务识别：用户登录账号、员工工号等有业务意义的标识（如 zhangsan001）。
    · 用户可见：暴露给用户或前端系统使用（如登录、个人资料页）。
    · 业务约束：需唯一且不可重复，但允许按规则修改（如用户申请更换ID）。
    示例场景：
    · 用户使用 USER_ID 和 PASSWORD 登录系统。
    · 业务单据中显示操作用户的 USER_ID（而非无意义的 REC_ID）。
 */
CREATE TABLE FFP.SYSTEM_USER (
                                 REC_ID VARCHAR(36) PRIMARY KEY,
                                 USER_ID VARCHAR(36) NOT NULL, /* 业务层面的用户唯一标识（Natural Key）用户登录账号、员工工号等有业务意义的标识（如 zhangsan001）。 */
                                 USER_NAME VARCHAR(36) NOT NULL,
                                 PASSWORD VARCHAR(36) NOT NULL,
                                 EMAIL VARCHAR(36) NOT NULL,
                                 PHONE VARCHAR(36) NOT NULL,
                                 CREATE_DATETIME TIMESTAMP(3) WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                                 UPDATE_DATETIME TIMESTAMP(3) WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                                 CREATE_USER VARCHAR(36) NOT NULL,
                                 UPDATE_USER VARCHAR(36) NOT NULL
);



INSERT INTO FFP.SYSTEM_USER(REC_ID, USER_ID, USER_NAME, PASSWORD, EMAIL, PHONE, CREATE_USER, UPDATE_USER)
VALUES ('5001', 'ADMIN','系统管理员', '123456', 'ADMIN@FFP.COM', '12345678901', 'ADMIN', 'ADMIN');

