package com.tt.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tt.common.Result;
import com.tt.entity.ApprovalRecord;
import com.tt.mapper.ApprovalRecordMapper;
import com.tt.security.SecurityUtils;
import com.tt.service.ContractService;
import com.tt.vo.ContractCreateRequest;
import com.tt.vo.ContractVO;
import com.tt.vo.ApprovalRecordVO;
import com.tt.workflow.ApprovalFlowService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 合同 Controller
 *
 * 三层强制之第3层：后端方法级鉴权（@PreAuthorize）
 * - 每个接口显式声明所需权限码
 * - deny-by-default：未标注 @PreAuthorize 的接口默认需要认证
 *
 * 数据范围在 Service 层施加（ContractService → DataScopeResolver）
 */
@RestController
@RequestMapping("/contract")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;
    private final ApprovalFlowService approvalFlowService;
    private final ApprovalRecordMapper approvalRecordMapper;

    /**
     * 合同列表（施加数据范围）
     * 功能权限：contract:view
     * 数据范围：在 Service 层施加
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('contract:view')")
    public Result<IPage<ContractVO>> list(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String status) {
        Long userId = SecurityUtils.getCurrentUserId();
        return Result.ok(contractService.listContracts(userId, page, size, status));
    }

    /**
     * 合同详情（施加数据范围，防 IDOR → 越权返回 404）
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('contract:view')")
    public Result<ContractVO> detail(@PathVariable Long id) {
        Long userId = SecurityUtils.getCurrentUserId();
        return Result.ok(contractService.getContractDetail(userId, id));
    }

    /**
     * 创建合同
     */
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('contract:create')")
    public Result<ContractVO> create(@RequestBody ContractCreateRequest request) {
        Long userId = SecurityUtils.getCurrentUserId();
        return Result.ok(contractService.createContract(userId, request));
    }

    /**
     * 提交合同（发起审批流）
     */
    @PostMapping("/{id}/submit")
    @PreAuthorize("hasAuthority('contract:create')")
    public Result<Void> submit(@PathVariable Long id,
                               @RequestParam(required = false) String comment) {
        Long userId = SecurityUtils.getCurrentUserId();
        String username = SecurityUtils.getCurrentUsername();
        approvalFlowService.executeAction(userId, username, id, "SUBMIT", comment);
        return Result.ok();
    }

    /**
     * 处理合同（合同管理员节点）
     */
    @PostMapping("/{id}/process")
    @PreAuthorize("hasAuthority('contract:process')")
    public Result<Void> process(@PathVariable Long id,
                                @RequestParam(required = false) String comment) {
        Long userId = SecurityUtils.getCurrentUserId();
        String username = SecurityUtils.getCurrentUsername();
        approvalFlowService.executeAction(userId, username, id, "PROCESS", comment);
        return Result.ok();
    }

    /**
     * 审批合同（部门经理/领导节点）
     * 注意：功能权限 contract:approve 相同，但状态机门控会区分是哪个节点
     */
    @PostMapping("/{id}/approve")
    @PreAuthorize("hasAuthority('contract:approve')")
    public Result<Void> approve(@PathVariable Long id,
                                @RequestParam(required = false) String comment) {
        Long userId = SecurityUtils.getCurrentUserId();
        String username = SecurityUtils.getCurrentUsername();
        approvalFlowService.executeAction(userId, username, id, "APPROVE", comment);
        return Result.ok();
    }

    /**
     * 撤回合同（申请人节点）
     */
    @PostMapping("/{id}/withdraw")
    @PreAuthorize("hasAuthority('contract:withdraw')")
    public Result<Void> withdraw(@PathVariable Long id,
                                 @RequestParam(required = false) String comment) {
        Long userId = SecurityUtils.getCurrentUserId();
        String username = SecurityUtils.getCurrentUsername();
        approvalFlowService.executeAction(userId, username, id, "WITHDRAW", comment);
        return Result.ok();
    }

    /**
     * 删除合同（领导节点）
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('contract:delete')")
    public Result<Void> delete(@PathVariable Long id,
                               @RequestParam(required = false) String comment) {
        Long userId = SecurityUtils.getCurrentUserId();
        String username = SecurityUtils.getCurrentUsername();
        approvalFlowService.executeAction(userId, username, id, "DELETE", comment);
        return Result.ok();
    }

    /**
     * 查询合同审批记录
     */
    @GetMapping("/{id}/records")
    @PreAuthorize("hasAuthority('contract:view')")
    public Result<List<ApprovalRecordVO>> records(@PathVariable Long id) {
        List<ApprovalRecord> records = approvalRecordMapper.selectByContractId(id);
        List<ApprovalRecordVO> voList = records.stream().map(r -> {
            ApprovalRecordVO vo = new ApprovalRecordVO();
            vo.setId(r.getId());
            vo.setContractId(r.getContractId());
            vo.setFromStatus(r.getFromStatus());
            vo.setToStatus(r.getToStatus());
            vo.setAction(r.getAction());
            vo.setOperatorId(r.getOperatorId());
            vo.setOperatorName(r.getOperatorName());
            vo.setComment(r.getComment());
            vo.setCreateTime(r.getCreateTime() != null ? r.getCreateTime().toString() : null);
            return vo;
        }).collect(Collectors.toList());
        return Result.ok(voList);
    }
}
