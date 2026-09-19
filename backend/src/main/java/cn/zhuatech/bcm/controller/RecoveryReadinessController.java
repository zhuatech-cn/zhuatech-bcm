/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bcm.controller;

import cn.zhuatech.bcm.common.ApiResponse;
import cn.zhuatech.bcm.service.RecoveryReadinessService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/admin")
public class RecoveryReadinessController {
    private final RecoveryReadinessService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public RecoveryReadinessController(RecoveryReadinessService service) { this.service = service; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/recovery-readiness")
    public ApiResponse<RecoveryReadinessService.Result> assess(@Valid @RequestBody RecoveryReadinessService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
