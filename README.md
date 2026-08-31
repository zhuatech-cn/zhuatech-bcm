# ZhuaTech BCM｜企业业务连续性管理

**社区源码版 · Java 21 + Vue 3 · 管理端与移动响应端**

ZhuaTech BCM 是知华科技（上海如静知华信息科技有限公司）面向企业韧性建设发布的业务连续性管理平台。它不只是预案文档库，更强调业务影响分析、恢复策略、演练验证、真实事件响应和整改闭环。

[访问知华科技官网](https://www.zhuatech.cn/)　[阅读部署文档](deploy/README.md)　[查看使用许可](LICENSE)

## 当前态势：企业韧性指挥中心

![知华科技 BCM 企业韧性指挥中心](docs/images/bcm-resilience-dashboard.png)

管理端按业务影响、恢复目标和资源准备度呈现全局态势，支持关键业务清单、外部依赖、演练计划和重大缺口跟踪。

## 事件现场：连续性协调员工作台

![知华科技 BCM 连续性协调员移动工作台](docs/images/bcm-coordinator-h5.png)

移动工作台适合事件期间快速执行行动清单、请求恢复资源、更新预计恢复时间并升级重大中断。

## 一套可验证的连续性闭环

| 阶段 | 主要能力 | 关键结果 |
| --- | --- | --- |
| 识别 | 业务影响分析、依赖关系 | 关键业务、MTPD、RTO、RPO |
| 准备 | 恢复策略、预案、资源包 | 可执行恢复步骤与联络链 |
| 验证 | 桌面推演、技术切换、综合演练 | 恢复表现与能力缺口 |
| 响应 | 事件分级、指挥协同、状态更新 | 业务恢复时间线 |
| 改进 | 复盘、整改、复验 | 可持续提升的韧性评分 |

示例中的组织、事件和指标为虚构数据。

## 技术底座

- 后端：Java 21、Spring Boot、Spring Security、JWT、JPA、Flyway
- 前端：Vue 3、Pinia、Vue Router、Axios、Vite
- 数据：MySQL 8，H2 集成测试
- 运行：Docker Compose、Nginx、环境变量配置

Java 包名为 `cn.zhuatech.bcm`，数据库名为 `zhuatech_bcm`。

## 本地启动

```bash
cd frontend
npm install
npm run dev:demo
```

浏览器访问 `http://localhost:5173`。管理端演示账号为 `planner / Demo@2026`，响应端为 `operator / Demo@2026`。完整部署请执行 `cp .env.example .env && docker compose up --build`。

## 版权、许可与授权

Copyright © 2026 上海如静知华信息科技有限公司。

该工程仅允许个人学习、研究和非商业技术交流，**禁止商业使用**。企业生产部署、内部业务使用、SaaS、项目交付、品牌替换、收费培训、咨询实施或商业再分发，必须事先获得上海如静知华信息科技有限公司书面授权，完整条款见 [LICENSE](LICENSE)。

如需业务连续性咨询、灾备系统集成、预案体系建设、私有化部署与深度开发，请访问[知华科技官网](https://www.zhuatech.cn/)或扫码联系：

| 微信咨询一 | 微信咨询二 |
| --- | --- |
| ![知华科技微信咨询二维码一](docs/images/zhuatech-wechat-consulting.png) | ![知华科技微信咨询二维码二](docs/images/zhuatech-wechat-consulting-2.png) |

SEO：BCM 系统源码、业务连续性管理、企业韧性、业务影响分析、应急预案、灾难恢复、Java BCM、Vue 业务连续性、知华科技。

## 恢复准备度体检

`POST /api/admin/recovery-readiness` 根据 RTO、备份新鲜度、演练间隔、备用场所和受训人员计算恢复准备度。关键能力不达标时返回备份验证、恢复演练、备用场所启用和人员补位动作。

## 恢复差距情景评估

新增 `POST /api/bcm/insights/recovery-gap-scenario`，比较目标 RTO 与预计恢复时间，并结合演练间隔、未测试依赖、备份新鲜度和手工替代流程输出 `READY / REMEDIATE / ACTIVATE_CRISIS`，量化恢复差距并生成补救动作。

## 企业级连续性演练认证

新增 `POST /api/enterprise/bcm/exercise-certification`，覆盖关键流程、RTO、RPO、依赖、沟通、证据和整改，返回 `CERTIFY / REMEDIATE / BLOCKED`。详见 [演练认证说明](docs/ENTERPRISE_EXERCISE_CERTIFICATION.md)。
