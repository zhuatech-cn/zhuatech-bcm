/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bcm.service;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class RecoveryGapScenarioService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Result assess(Request request) {
        int rtoGapMinutes = request.estimatedRecoveryMinutes() - request.targetRtoMinutes();
        int riskScore = Math.min(40, Math.max(0, rtoGapMinutes) * 40 / request.targetRtoMinutes());
        if (request.lastExerciseDays() > 180) riskScore += 20;
        riskScore += Math.min(30, request.untestedDependencies() * 15);
        if (request.backupAgeHours() > 24) riskScore += 20;
        if (!request.manualWorkaroundReady()) riskScore += 15;
        if (request.tierOneService()) riskScore += 15;
        riskScore = Math.min(100, riskScore);
        String decision = request.incidentActive() && rtoGapMinutes > 0 ? "ACTIVATE_CRISIS"
            : riskScore >= 60 ? "REMEDIATE" : "READY";

        List<String> actions = new ArrayList<>();
        if (rtoGapMinutes > 0) actions.add("压缩恢复步骤至少 " + rtoGapMinutes + " 分钟");
        if (request.untestedDependencies() > 0) actions.add("补测关键依赖与跨团队恢复顺序");
        if (request.backupAgeHours() > 24) actions.add("刷新备份并执行可恢复性校验");
        if (!request.manualWorkaroundReady()) actions.add("建立最小业务手工替代流程");
        if (actions.isEmpty()) actions.add("保持当前预案并按计划开展演练");
        return new Result(request.serviceCode(), rtoGapMinutes, riskScore,
            decision, actions);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Request(@NotBlank String serviceCode, @Min(1) int targetRtoMinutes,
                          @Min(1) int estimatedRecoveryMinutes,
                          @Min(0) int lastExerciseDays, @Min(0) int untestedDependencies,
                          @Min(0) int backupAgeHours, boolean manualWorkaroundReady,
                          boolean tierOneService, boolean incidentActive) {}

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Result(String serviceCode, int rtoGapMinutes, int riskScore,
                         String decision, List<String> actions) {}
}
