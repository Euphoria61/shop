<template>
  <div
    style="
      height:75px;
      line-height: 75px;
      display: flex;
      background-color:    #c2d6d6;
    "
  >
    <!-- <div style="width: 200px; padding-left: 50px">
      <el-link
        style="font-size: 4px"
        :underline="false"
        @click="$router.push('/login')"
        >{{ userEmail == "" ? "欢迎您，请先登录" : userEmail }}</el-link
      >
    </div> -->
<h2 style="margin-left:50px">电商后台管理系统</h2>
    </div>
  
</template>
<script>
export default {
  name: "AdminHeader.vue",
  data() {
    return {
      userEmail: "",
   
    };
  },
  methods: {
    load() {
      if(JSON.parse(sessionStorage.getItem("user")) != null)this.userEmail =JSON.parse(sessionStorage.getItem("user")).userEmail
    
    },
  },
  created() {
    this.load();
  },
};
</script>

<style scoped>
</style>