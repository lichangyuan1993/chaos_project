<script setup lang="ts" xmlns="http://www.w3.org/1999/html">
import { onMounted, reactive, ref } from "vue";

const canvasState = reactive({
  canvasWidth: 0,
  canvasHeight: 0
})

function initCanvas(canvas: HTMLCanvasElement) {
  // 使用 Vue ref 获取元素

  // 检查元素是否存在
  if (!canvas) {
    console.error('Canvas element not found');
    return;
  }

  // 设置画布尺寸
  canvas.width = canvas.clientWidth;
  canvas.height = canvas.clientHeight;

  // 获取上下文
  const ctx = canvas.getContext('2d');

  if (!ctx) {
    console.error('Failed to get 2D context');
    return;
  }

  // 绘制裂纹
  drawCracks(ctx, canvas.width, canvas.height);
}
// 生成裂纹函数
function drawCracks(ctx, width, height)  {

  ctx.clearRect(0, 0, width, height);

  // 绘制汝瓷底色
  const gradient = ctx.createLinearGradient(0, 0, width, height);
  gradient.addColorStop(0, '#a3c1ad');
  gradient.addColorStop(1, '#7a9e9b');
  ctx.fillStyle = gradient;
  ctx.fillRect(0, 0, width, height);

  // 裂纹参数
  const crackConfig = {
    count: 80,          // 裂纹数量
    minLength: 30,      // 最小长度
    maxLength: 100,     // 最大长度
    segments: 5,        // 每条裂纹的段数
    color: 'rgba(50, 50, 50, 0.15)'
    // count: 100,          // 增加裂纹密度
    // minLength: 20,
    // maxLength: 150,
    // segments: 7,         // 更多分段使裂纹更自然
    // color: 'rgba(30, 30, 30, 0.2)', // 加深裂纹颜色
    // widthVariation: 0.6  // 添加线宽随机变化
  };

  // 绘制裂纹
  ctx.strokeStyle = crackConfig.color;
  ctx.lineWidth = 1.8;
  ctx.lineCap = 'round';

  for (let i = 0; i < crackConfig.count; i++) {
    ctx.beginPath();

    // 随机起点
    let x = Math.random() * width;
    let y = Math.random() * height;
    ctx.moveTo(x, y);

    // 生成随机裂纹路径
    for (let s = 0; s < crackConfig.segments; s++) {
      const angle = (Math.random() - 0.5) * Math.PI * 1.5;
      const length = crackConfig.minLength +
        Math.random() * (crackConfig.maxLength - crackConfig.minLength);

      x += Math.cos(angle) * length;
      y += Math.sin(angle) * length;

      // 确保在画布范围内
      x = Math.max(0, Math.min(width, x));
      y = Math.max(0, Math.min(height, y));

      ctx.lineTo(x, y);
    }
    ctx.stroke();
  }

  // 添加釉面光泽感
  const gloss = ctx.createRadialGradient(
    width * 0.3, height * 0.3, 0,
    width * 0.3, height * 0.3, width * 0.8
  );
  gloss.addColorStop(0, 'rgba(255, 255, 255, 0.15)');
  gloss.addColorStop(1, 'rgba(255, 255, 255, 0)');

  ctx.fillStyle = gloss;
  ctx.globalCompositeOperation = 'overlay';
  ctx.fillRect(0, 0, width, height);
  ctx.globalCompositeOperation = 'source-over';
}

const canvasRef = ref<HTMLCanvasElement | null>(null);

onMounted(() => {
  console.log("MemberNomineeView mounted")

  initCanvas(canvasRef.value  as HTMLCanvasElement | null )
})
</script>

<template>
  <div class="nominee-view">
    Member Nominee View
    <canvas style="width: 100%;height: 100%; border: 1px solid #ccc;" ref="canvasRef" width="100%" height="100%"></canvas>
  </div>
</template>

<style lang="scss" scoped>
.member-search {
  width: 100%;
  background-color: #282828;
  color: #1a1a1a;

  &__header {
    display: flex;
    justify-content: flex-end;
    align-items: center;
    padding: 16px;
    background-color: #f8f8f8;
    color: greenyellow;
  }

  &__member-table {
    overflow-x: auto;
    width: 100%;
    background-color: #1e5489;
    color: yellow;
  }
}
</style>
