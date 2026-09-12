# CRM
CRM
### tih
```
11
```
<html style="margin:0;padding:0;">
<div style="width:100%;box-sizing:border-box;font-family:'Microsoft YaHei',system-ui,sans-serif;background:#f7f9fc;padding:16px;border-radius:12px;">
  <div style="font-size:15px;font-weight:700;color:#1f2937;margin-bottom:2px;">物流调度 Agent 角色分工与协同链路</div>
  <div style="font-size:12px;color:#6b7280;margin-bottom:14px;">依据项目PPT整理（示意）</div>

  <div style="display:flex;justify-content:center;margin-bottom:6px;">
    <div style="background:#111827;color:#fff;padding:8px 20px;border-radius:10px;font-size:14px;font-weight:600;">📞 司机 / 客户来电</div>
  </div>
  <div style="text-align:center;color:#9ca3af;font-size:14px;line-height:1.2;">▼</div>

  <div style="border:2px dashed #0e7c86;border-radius:12px;padding:12px;margin:6px 0;background:#f0fbfc;">
    <div style="font-size:13px;font-weight:700;color:#0e7c86;margin-bottom:10px;">本地边缘侧 · 机密数据不出网</div>
    <div style="display:flex;flex-wrap:wrap;gap:10px;justify-content:center;">
      <div style="flex:1;min-width:200px;max-width:260px;background:#fff;border-left:5px solid #0e7c86;border-radius:8px;padding:10px 12px;">
        <div style="font-size:14px;font-weight:700;color:#0e7c86;">语音呼叫 Agent</div>
        <div style="font-size:12px;color:#9ca3af;margin:2px 0 4px;">本地 · 全双工</div>
        <div style="font-size:13px;color:#374151;">接听 / 外呼司机电话，语音↔文字实时互转</div>
      </div>
      <div style="flex:1;min-width:200px;max-width:260px;background:#fff;border-left:5px solid #2f6fed;border-radius:8px;padding:10px 12px;">
        <div style="font-size:14px;font-weight:700;color:#2f6fed;">意图识别 Agent</div>
        <div style="font-size:12px;color:#9ca3af;margin:2px 0 4px;">本地 · 核心①</div>
        <div style="font-size:13px;color:#374151;">听懂司机需求：问价、接单、改地址…</div>
      </div>
      <div style="flex:1;min-width:200px;max-width:260px;background:#fff;border-left:5px solid #1a8f5a;border-radius:8px;padding:10px 12px;">
        <div style="font-size:14px;font-weight:700;color:#1a8f5a;">财务 Agent</div>
        <div style="font-size:12px;color:#9ca3af;margin:2px 0 4px;">本地 · 敏感数据</div>
        <div style="font-size:13px;color:#374151;">查动态底价、核算成本、敏感费用审计</div>
      </div>
    </div>
  </div>
  <div style="text-align:center;color:#9ca3af;font-size:14px;line-height:1.2;">▼</div>

  <div style="border:2px dashed #d97706;border-radius:12px;padding:12px;margin:6px 0;background:#fffaf0;">
    <div style="font-size:13px;font-weight:700;color:#d97706;margin-bottom:10px;">云端侧 · 复杂调度借云力</div>
    <div style="display:flex;justify-content:center;">
      <div style="flex:1;max-width:320px;background:#fff;border-left:5px solid #d97706;border-radius:8px;padding:10px 12px;">
        <div style="font-size:14px;font-weight:700;color:#d97706;">路径规划 Agent（调度Agent）</div>
        <div style="font-size:12px;color:#9ca3af;margin:2px 0 4px;">云端大模型 · 核心②</div>
        <div style="font-size:13px;color:#374151;">算最优线路、成本与报价；与本地财务 Agent 实时协同</div>
      </div>
    </div>
  </div>
  <div style="text-align:center;color:#9ca3af;font-size:14px;line-height:1.2;">▼</div>

  <div style="display:flex;justify-content:center;margin:6px 0;">
    <div style="max-width:430px;background:#fff;border:2px solid #c0392b;border-radius:10px;padding:10px 16px;">
      <div style="font-size:14px;font-weight:700;color:#c0392b;">风控审计 Agent（核心③）</div>
      <div style="font-size:13px;color:#374151;margin-top:4px;">Maker-Checker 博弈：给方案挑成本/安全毛病，多轮对齐后再下发</div>
    </div>
  </div>
  <div style="text-align:center;color:#9ca3af;font-size:14px;line-height:1.2;">▼</div>

  <div style="display:flex;justify-content:center;">
    <div style="background:#059669;color:#fff;padding:8px 20px;border-radius:10px;font-size:14px;font-weight:600;">📄 输出：电子工单 + 自动记账 + AI 回拨确认</div>
  </div>
</div>
</html>
