# CRM
CRM
### tih
```




// 响应式状态
const gridContainer = ref(null) // 网格容器引用
const expanded = ref(false) // 是否展开状态
const rowCount = ref(0) // 实际行数
const itemsPerRow = ref(3) // 每行项目数，默认为3，将根据屏幕尺寸动态调整

// 计算是否显示切换按钮
const showToggleButton = computed(() => {
  return rowCount.value > props.maxRows
})

// 计算网格样式，包括列数和高度
const gridStyle = computed(() => {
  // 根据当前的itemsPerRow设置grid-template-columns
  const gridColumns = `repeat(${itemsPerRow.value}, 1fr)`

  // 如果未展开且行数超过最大行数，限制高度
  let maxHeight = 'none'
  if (!expanded.value && rowCount.value > props.maxRows) {
    maxHeight = `${props.maxRows * (props.rowHeight + 4)}px` // 4px是gap的值
  }

  return {
    gridTemplateColumns: gridColumns,
    maxHeight: maxHeight
  }
})

// 判断项目是否应该隐藏（超过最大行数且未展开）
const isHidden = (index) => {
  const maxVisibleItems = props.maxRows * itemsPerRow.value
  return index >= maxVisibleItems
}

// 切换展开/收起状态
const toggleExpand = () => {
  // 切换展开状态
  expanded.value = !expanded.value

  // 如果是展开操作，立即更新；如果是收起操作，添加延迟以便动画效果完成
  if (expanded.value) {
    // 展开时立即更新布局
    nextTick(() => {
      calculateLayout()
    })
  } else {
    // 收起时等待动画完成后再更新布局
    setTimeout(() => {
      calculateLayout()
    }, 500) // 与CSS过渡时间一致
  }
}

// 计算实际行数和每行项目数
const calculateLayout = async () => {
  await nextTick()
  if (!gridContainer.value) return

  // 获取容器宽度
  const containerWidth = gridContainer.value.clientWidth

  // 根据容器宽度确定列数
  if (containerWidth < 576) {
    // xs: 手机屏幕
    itemsPerRow.value = props.columns.xs
  } else if (containerWidth < 768) {
    // sm: 平板屏幕
    itemsPerRow.value = props.columns.sm
  } else if (containerWidth < 992) {
    // md: 小型笔记本
    itemsPerRow.value = props.columns.md
  } else if (containerWidth < 1200) {
    // lg: 桌面显示器
    itemsPerRow.value = props.columns.lg
  } else {
    // xl: 大型显示器
    itemsPerRow.value = props.columns.xl
  }

  // 计算总行数（基于过滤后的数据）
  rowCount.value = Math.ceil(filteredItems.value.length / itemsPerRow.value)
}

// 监听过滤后的items变化，重新计算布局
watch(filteredItems, () => {
  calculateLayout()
}, { deep: true })

// 组件挂载后计算布局
onMounted(() => {
  calculateLayout()

  // 添加窗口大小变化监听器
  window.addEventListener('resize', calculateLayout)
})

// 在组件卸载前移除事件监听器
onUnmounted(() => {
  window.removeEventListener('resize', calculateLayout)
})

// 暴露组件内部的状态和方法，供父组件使用
defineExpose({
  // 当前每行的项目数
  getCurrentItemsPerRow: () => itemsPerRow.value,
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
