package com.tt.controller;

import com.tt.common.Result;
import com.tt.entity.Dept;
import com.tt.mapper.DeptMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 部门 Controller
 * 提供部门列表（用于创建合同时选择归属部门）
 */
@RestController
@RequestMapping("/dept")
@RequiredArgsConstructor
public class DeptController {

    private final DeptMapper deptMapper;

    @GetMapping("/list")
    public Result<List<Dept>> list() {
        return Result.ok(deptMapper.selectList(null));
    }
}
