<template>
  <div>
  <div><Header/></div>
<svg style="margin-left:700px;margin-top:50px" t="1648185146158" class="icon" viewBox="0 0 1870 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3885" width="100" height="100"><path d="M154.691125 898.451578c42.194767-101.861343 69.541948-189.634745 84.458592-307.241435L362.557324 591.210143c-16.574049 115.051524-43.92123 225.268951-83.629889 307.241435L154.691125 898.451578zM225.890477 298.816293 172.922579 128.172646l125.065012 0L350.126787 298.816293 225.890477 298.816293zM237.492312 532.30321l-54.694362-183.005125 125.065012 0 53.796601 183.005125L237.492312 532.30321zM408.135959 602.811977 408.135959 555.575937l184.66253 0L592.798489 320.293499 390.733207 320.293499 390.733207 273.126517l533.408147 0 0 47.23604-204.551389 0 0 235.282439 186.388994 0 0 47.23604-186.388994 0 0 235.21338 220.296736 0 0 47.23604L375.816563 885.330456l0-47.23604 216.981926 0L592.798489 602.811977 408.135959 602.811977zM594.455894 242.464527 561.376855 110.769895l127.55112 0 32.319396 131.694632L594.455894 242.464527z" p-id="3886"></path><path d="M984.153224 495.080658l31.559752 0L1015.712975 188.598867c0-30.592932 24.032371-55.454006 54.625303-55.454006l226.926356 0c30.661991 0 54.694362 24.792015 54.694362 55.454006l0 306.481791 48.824386 0L1400.783383 188.598867c0-30.592932 25.689776-55.454006 55.523064-55.454006l226.926356 0c30.592932 0 55.523064 24.792015 55.523064 55.454006l0 306.481791 28.106825 0 0 47.23604-28.106825 0 0 301.509576c0 29.833288-24.861074 55.454006-55.523064 55.454006l-123.407607 0 0-47.23604 30.592932 0c13.259239 0 24.032371-10.773132 24.032371-24.032371L1614.450499 542.316698l-90.259509 0 0 179.690316c0 73.754518-13.190181 135.838144-30.592932 177.204208l-122.578905 0c16.574049-40.60642 29.833288-101.861343 29.833288-177.204208L1400.852441 542.316698l-48.824386 0 0 301.509576c0 29.833288-24.032371 55.454006-54.694362 55.454006l-122.509846 0 0-47.23604 30.592932 0c12.430537 0 23.203669-10.773132 23.203669-24.032371L1228.620448 542.316698l-90.328568 0 0 179.690316c0 73.754518-12.499595 135.838144-29.833288 177.204208l-122.578905 0c16.574049-40.60642 29.902347-101.861343 29.902347-177.204208L1015.782034 542.316698l-31.559752 0L984.222282 495.080658zM1138.29188 495.080658l90.328568 0 0-290.736445c0-13.259239-10.773132-24.032371-23.203669-24.032371L1162.255193 180.311842c-14.087942 0-24.032371 10.704073-24.032371 24.032371L1138.222822 495.080658zM1524.260049 495.080658l90.259509 0 0-290.736445c0-13.259239-10.704073-24.032371-24.032371-24.032371l-43.023469 0c-13.190181 0-23.203669 10.704073-23.203669 24.032371L1524.260049 495.080658z" p-id="3887"></path></svg>
     <div style="margin:auto 500px;margin-top:100px">
       <el-form ref="form"  label-width="60px" style="padding-top:40px">
    <el-form-item label="账号">
      <el-input  v-model="form.userEmail" placeholder="请输入用户名"></el-input>
    </el-form-item>
    <el-form-item label="密码">
      <el-input v-model="form.userPwd" type="password"  placeholder="请填写密码"></el-input>
    </el-form-item>
      <el-form-item label="密码">
      <el-input v-model="form.userPwd2" type="password"  placeholder="请再次填写密码"></el-input>
    </el-form-item>

    <el-form-item label="验证码:">
          <el-input v-model="form.code"   placeholder="请填写验证码"></el-input>
          <img id="code" :src="'/api/validateCode'" />
          <el-link @click="refreshCode();">看不清，换一个！</el-link><br />
        </el-form-item>

    <el-form-item style="margin:auto 150px">
      <el-button round type="success" @click="register">注册</el-button>
      <el-button  round type="info" @click="$router.push('/')">取消</el-button>
      
    </el-form-item>
  </el-form>
   </div>
  
   </div>
</template>

<script>
import Header from "@/components/Header.vue";
import request from "@/utils/request.js";
export default {
    name: "Register.vue",   
  components: {
       Header,  
 },
  data() {
    return {
      form:[]
    }},
 methods: {
    getCode() {
      request.get("/validateCode").then((res) => {
        console.log(res);
        if (res.code === "0") {
          this.$message({
            type: "success",
            message: "更新成功！",
          });
          this.img = res;
          console.log(res);
        }
      })
    },
    refreshCode(){
         document.getElementById("code").src = "/api/validateCode?" + Math.random()
      },

      register(){
        console.log(this.form);
        request.post("/user/register",this.form).then((res) => {
        console.log(res);
         if (res.code === '0') {
            this.$message({
              type: "success",
              message: "注册成功！"
            })
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }
        })
       }
  },

  
  created() {
    this.getCode()
  }
};
</script>

<style>
</style>