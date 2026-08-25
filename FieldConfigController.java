package com.tt.controller;

import com.tt.common.Result;
import com.tt.entity.FieldVisibilityConfig;
import com.tt.field.FieldVisibilityService;
import com.tt.mapper.FieldVisibilityConfigMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字段可见性配置 Controller
 * 数据驱动的字段级配置管理（加分项）
 *
 * 修改配置后刷新即可生效（无需改代码、无需发版）
 */
@RestController
@RequestMapping("/field-config")
@RequiredArgsConstructor
public class FieldConfigController {

    private final FieldVisibilityConfigMapper fieldConfigMapper;
    private final FieldVisibilityService fieldVisibilityService;

    /** 查询所有字段可见性配置 */
    @GetMapping("/list")
    public Result<List<FieldVisibilityConfig>> list() {
        return Result.ok(fieldConfigMapper.selectAll());
    }

    /** 新增配置 */
    @PostMapping
    public Result<Void> add(@RequestBody FieldVisibilityConfig config) {
        fieldConfigMapper.insert(config);
        fieldVisibilityService.clearCache();
        return Result.ok();
    }

    /** 修改配置 */
    @PutMapping
    public Result<Void> update(@RequestBody FieldVisibilityConfig config) {
        fieldConfigMapper.updateById(config);
        fieldVisibilityService.clearCache();
        return Result.ok();
    }

    /** 删除配置 */
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        fieldConfigMapper.deleteById(id);
        fieldVisibilityService.clearCache();
        return Result.ok();
    }
}
