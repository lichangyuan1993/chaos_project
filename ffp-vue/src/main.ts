import '@/assets/base.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'

const app = createApp(App)

app.use(createPinia())
app.use(router)
// 注册 element-plus
app.use(ElementPlus)

app.mount('#app')

app.config.errorHandler = (err, instance, info) => {
  // 向追踪服务报告错误
  console.error(err, instance, info)
}
