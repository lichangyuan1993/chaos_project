1.领域层纯净性

 · 绝不出现@Configuration、@Bean等技术注解

 · 工具类必须与业务强相关（如InventoryCalculator）

2.基础设施层兜底

 · 所有技术细节（Redis、MyBatis、HTTP客户端等）在此层实现

 · 工具类以XxxUtils命名，明确无状态性

3.按职责分层存放

 · 全局配置 → infrastructure/config/

 · 技术工具 → infrastructure/util/

 · 领域工具 → domain/shared/

 · 应用工具 → application/util/