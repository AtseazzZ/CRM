package com.tt.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tt.common.Result;
import com.tt.entity.AuditLog;
import com.tt.mapper.AuditLogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 审计日志 Controller
 * 仅 U4（领导）或有 audit:view 权限的用户可查
 */
@RestController
@RequestMapping("/audit")
@RequiredArgsConstructor
public class AuditLogController {

    private final AuditLogMapper auditLogMapper;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('audit:view')")
    public Result<Page<AuditLog>> list(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String action,
            @RequestParam(required = false) String targetType,
            @RequestParam(required = false) Long targetId) {

        LambdaQueryWrapper<AuditLog> wrapper = new LambdaQueryWrapper<>();
        if (action != null && !action.isEmpty()) {
            wrapper.eq(AuditLog::getAction, action);
        }
        if (targetType != null && !targetType.isEmpty()) {
            wrapper.eq(AuditLog::getTargetType, targetType);
        }
        if (targetId != null) {
            wrapper.eq(AuditLog::getTargetId, targetId);
        }
        wrapper.orderByDesc(AuditLog::getCreateTime);

        Page<AuditLog> result = auditLogMapper.selectPage(new Page<>(page, size), wrapper);
        return Result.ok(result);
    }
}
