import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
const path = require('path')
// https://vitejs.dev/config/

export default defineConfig({
  plugins: [vue()],
  
  server: {                //记住，别写错了server//设置本地默认端口  选填
    port: 9876,
    proxy: {                 //设置代理，必须填
        '/api': {              //设置拦截器  拦截器格式   斜杠+拦截器名字，名字可以自己定
            target: 'http://192.168.154.100:9090/',     //代理的目标地址
            changeOrigin: true,              //是否设置同源，输入是的
             rewrite: (path) => path.replace(/^\/api/, '')    //路径重写 选择忽略拦截器里面的单词
           
             
            
        }
    }
  },
  resolve: {
    alias: {
      "@": path.resolve(__dirname, "src"),
      "assets": path.resolve(__dirname, "src/assets"),
      "components": path.resolve(__dirname, "src/components"),
      "styles": path.resolve(__dirname, "src/styles"),
      "plugins": path.resolve(__dirname, "src/plugins"),
      "views": path.resolve(__dirname, "src/views"),
      "layouts": path.resolve(__dirname, "src/layouts"),
      "utils": path.resolve(__dirname, "src/utils"),
     

    }
  },

})
