/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bcm.controller;

import cn.zhuatech.bcm.common.ApiResponse;
import cn.zhuatech.bcm.service.RecoveryGapScenarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/bcm/insights")
public class RecoveryGapScenarioController {
    private final RecoveryGapScenarioService service;

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public RecoveryGapScenarioController(RecoveryGapScenarioService service) {
        this.service = service;
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/recovery-gap-scenario")
    public ApiResponse<RecoveryGapScenarioService.Result> assess(
        @Valid @RequestBody RecoveryGapScenarioService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
