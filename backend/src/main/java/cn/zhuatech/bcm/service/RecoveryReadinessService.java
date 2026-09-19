/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bcm.service;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class RecoveryReadinessService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Result assess(Request request) {
        int risk = (request.backupAgeHours() > request.rtoHours() ? 25 : 0)
            + Math.min(30, request.recoveryTestDaysAgo() / 5)
            + (request.alternateSiteReady() ? 0 : 30)
            + (request.trainedResponders() >= 3 ? 0 : 20);
        risk = Math.min(100, risk);
        int readiness = 100 - risk;
        String level = risk >= 75 ? "CRITICAL" : risk >= 50 ? "HIGH" : risk >= 25 ? "WATCH" : "READY";
        List<String> actions = new ArrayList<>();
        if (request.backupAgeHours() > request.rtoHours()) actions.add("刷新关键数据备份并验证可恢复性");
        if (request.recoveryTestDaysAgo() > 90) actions.add("安排端到端恢复演练");
        if (!request.alternateSiteReady()) actions.add("启用并验证备用办公或生产场所");
        if (request.trainedResponders() < 3) actions.add("补充至少三名受训应急响应人员");
        if (actions.isEmpty()) actions.add("保持季度连续性演练节奏");
        return new Result(request.processName(), readiness, risk, level, actions);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Request(@NotBlank String processName, @Positive int rtoHours,
                          @Min(0) int backupAgeHours, @Min(0) int recoveryTestDaysAgo,
                          boolean alternateSiteReady, @Min(0) int trainedResponders) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Result(String processName, int readinessScore, int riskScore,
                         String level, List<String> actions) {}
}
