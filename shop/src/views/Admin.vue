<template>
  <div>
    <!--   头部-->
    <AdminHeader/>
    <!--   主体-->
    <div style="display: flex">
      <!--     侧边栏-->
      <Aside/>
      <!--     内容-->
      <router-view style="flex: 1"/>
    </div>
  </div>

</template>

<style>


</style>
<script>
import AdminHeader from "@/components/AdminHeader.vue";
import Aside from "@/components/Aside.vue";

export default {
  name: "Admin.vue",
  components: {
    AdminHeader,
    Aside
  }
}
</script>
