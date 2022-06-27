<template>
  <div>
    <el-menu
        class="el-menu-vertical-demo"
        :default-active="path"
        router
        style="width: 200px;min-height: calc(100vh - 50px)">
      

     <el-menu-item index="/adminGoods">商品管理</el-menu-item>
     <el-menu-item index="/adminGType">商品分类管理</el-menu-item>
     <el-menu-item index="/adminOrders">订单管理</el-menu-item>
     <el-menu-item index="/adminSCount">销量报表</el-menu-item>
     <el-menu-item index="/adminSales">销售额报表</el-menu-item>
     <el-menu-item index="/adminStock">库存报表</el-menu-item>
     
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "Aside.vue",
  data(){
    return {
      path: this.$route.path
    }
  }
}
</script>

<style scoped>

</style>