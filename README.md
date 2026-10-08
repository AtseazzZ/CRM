# CRM
CRM
### tih
```


# 创建工具注册表
tool_registry = ToolRegistry()

    llm=llm,
    system_prompt="你是一个有记忆和知识检索能力的AI助手",
    tool_registry=tool_registry
)

# 开始对话
response = agent.run("你好！请记住我叫张三，我是一名Python开发者")
print(response)


// 计算实际行数和每行项目数
const calculateLayout = async () => {
  await nextTick()
  if (!gridContainer.value) return

private class VoucherOrderHandler implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    // 1.获取消息队列中的订单信息 XREADGROUP GROUP g1 c1 COUNT 1 BLOCK 2000 STREAMS stream.orders >
                    List<MapRecord<String, Object, Object>> list = stringRedisTemplate.opsForStream().read(
                            Consumer.from("g1", "c1"),
                            StreamReadOptions.empty().count(1).block(Duration.ofSeconds(2)),
                            StreamOffset.create("stream.orders", ReadOffset.lastConsumed())
                    );
                    // 2.判断订单信息是否为空
                    if (list == null || list.isEmpty()) {
                        // 如果为null，说明没有消息，继续下一次循环
                        continue;
                    }
                    // 解析数据
                    MapRecord<String, Object, Object> record = list.get(0);
                    Map<Object, Object> value = record.getValue();
                    VoucherOrder voucherOrder = BeanUtil.fillBeanWithMap(value, new VoucherOrder(), true);
                    // 3.创建订单
                    createVoucherOrder(voucherOrder);
                    // 4.确认消息 XACK
                    stringRedisTemplate.opsForStream().acknowledge("stream.orders", "g1", record.getId());
                } catch (Exception e) {
                    log.error("处理订单异常", e);
                    handlePendingList();
                }
            }
        }

// 在组件卸载前移除事件监听器
onUnmounted(() => {
  window.removeEventListener('resize', calculateLayout)
})
: () => itemsPerRow.value,
  // 当前的行数
  getCurrentRowCount: () => rowCount.value,
  // 是否展开状态
  isExpanded: () => expanded.value,
  // 手动触发展开/收起
  toggleExpand
})
</script>

<style scoped>
.expandable-grid-container {
  width: 100%;
}

.grid-container {
  display: grid;
  gap: 4px;
  /* 与计算高度时保持一致 */
  transition: max-height 0.5s ease-in-out;
  overflow: hidden;
}

.grid-item {
  border-radius: 4px;
  padding: 4px;
  display: flex;
  flex-direction: column;
  transition: opacity 0.3s ease, transform 0.3s ease;
  margin-bottom: 0px !important;
}

.grid-item.hidden {
  /* 使用opacity和transform代替display:none以实现平滑过渡 */
  opacity: 0;
  transform: translateY(-10px);
  /* 保留position以维持布局空间 */
  position: absolute;
  visibility: hidden;
  pointer-events: none;
}

/* 添加展开/收起的动画效果 */
.grid-container:not(.expanded) {
  transition: max-height 0.5s ease-in-out;
}

.grid-container.expanded {
  max-height: none !important;
  transition: max-height 0.5s ease-in-out;
}

.toggle-button-container {
  width: 100%;
  margin-top: 10px;
}

.toggle-button {
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  overflow: hidden;
  transition: all 0.3s ease;
}

.toggle-icon {
  margin-left: 4px;
  transition: transform 0.3s ease;
}

.toggle-icon.rotate {
  transform: rotate(180deg);
}

.toggle-text {
  display: inline-block;
  min-width: 32px;
  /* 确保文字宽度一致，防止宽度变化导致的跳动 */
  transition: all 0.3s ease;
}


:deep(.el-form-item) {
  margin-bottom: 0px !important;
}
</style>

```
