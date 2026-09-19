/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bcm.config;

import cn.zhuatech.bcm.model.*;
import cn.zhuatech.bcm.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Configuration
public class DataInitializer {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Bean
    CommandLineRunner seed(OperatingUnitRepository operatingUnits, WorkRecordRepository orders,
                           ResourceRegisterRepository resources, ReviewRecordRepository reviewRecords,
                           UserRepository users, PasswordEncoder encoder) {
        return args -> {
            if (operatingUnits.count() > 0) return;
            OperatingUnit primaryUnit = operatingUnits.save(new OperatingUnit("BCM-EAST", "华东运营基地", "供应链运营", 180));
            OperatingUnit secondaryUnit = operatingUnits.save(new OperatingUnit("BCM-IT", "信息技术部", "信息技术部", 120));
            OperatingUnit tertiaryUnit = operatingUnits.save(new OperatingUnit("BCM-SUPPLY", "全国服务中心", "客户运营", 96));

            WorkRecord t1 = orders.save(new WorkRecord("FC-260801-018", "BIZ-ORDER-FUL", "华东订单履约连续性响应", tertiaryUnit, 24, 16, 1, LocalDate.now().plusDays(1), WorkRecord.Status.RUNNING, "GW-Q3"));
            WorkRecord t2 = orders.save(new WorkRecord("FC-260801-021", "BIZ-CUSTOMER", "客户服务异地坐席切换", primaryUnit, 18, 8, 0, LocalDate.now().plusDays(1), WorkRecord.Status.RUNNING, "TERM-12"));
            WorkRecord t3 = orders.save(new WorkRecord("BUD-260802-006", "BIZ-TREASURY", "资金结算业务影响分析", secondaryUnit, 12, 0, 0, LocalDate.now().plusDays(3), WorkRecord.Status.RELEASED, "SP-2026"));
            WorkRecord t4 = orders.save(new WorkRecord("FC-260728-015", "SYS-ORDER-DB", "核心订单数据库切换演练", primaryUnit, 20, 20, 1, LocalDate.now(), WorkRecord.Status.COMPLETED, "SEA-09"));

            resources.saveAll(List.of(
                new ResourceRegister("CAT-HPLC-03", "恢复资源包", primaryUnit, ResourceRegister.Status.RUNNING, 88),
                new ResourceRegister("CAT-ICP-02", "异地灾备中心", primaryUnit, ResourceRegister.Status.IDLE, 76),
                new ResourceRegister("CAT-UTM-05", "替代供应商资源池", tertiaryUnit, ResourceRegister.Status.RUNNING, 91),
                new ResourceRegister("CAT-INC-08", "业务影响模型", secondaryUnit, ResourceRegister.Status.ALARM, 62)
            ));
            reviewRecords.saveAll(List.of(
                new ReviewRecord("ISS-260801-032", t1, "业务影响复核", 6, 0, ReviewRecord.Result.PASSED, "沈知"),
                new ReviewRecord("ISS-260801-011", t2, "恢复模型校验", 3, 0, ReviewRecord.Result.PASSED, "叶川"),
                new ReviewRecord("ISS-260801-018", t4, "演练关闭复核", 5, 1, ReviewRecord.Result.FAILED, "沈知"),
                new ReviewRecord("ISS-260802-003", t3, "恢复目标确认", 4, 0, ReviewRecord.Result.PENDING, "叶川")
            ));
            String demo = encoder.encode("Demo@2026");
            users.saveAll(List.of(
                new UserAccount("operator", demo, "叶川", UserAccount.Role.DOMAIN_USER, "BCM-EAST"),
                new UserAccount("planner", demo, "沈知", UserAccount.Role.DOMAIN_OPERATOR, null),
                new UserAccount("quality", demo, "顾清", UserAccount.Role.QUALITY, null),
                new UserAccount("admin", encoder.encode("ZhuaTech@2026"), "系统管理员", UserAccount.Role.ADMIN, null)
            ));
        };
    }
}
