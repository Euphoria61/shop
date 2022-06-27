<template>
  <div>
    <div><Header /></div>
    <div class="layout">
      <div
        style="
          margin-left: 200px;
          width: 70%;
          height: 1700px;
          margin-top: 10px;
          flex: 1;
        "
      >
        <!-- 搜素 -->
        <div class="s">
          <el-input
            class="input"
            style="width: 400px; margin-top: 20px; margin-left: 300px"
            v-model="word"
            placeholder="请输入关键词"
          />
          <el-button type="danger" @click="load()"
            ><svg
              style="width: 1em"
              t="1648280972926"
              class="icon"
              viewBox="0 0 1024 1024"
              version="1.1"
              xmlns="http://www.w3.org/2000/svg"
              p-id="5351"
              width="200"
              height="200"
            >
              <path
                d="M1009.856 942.4l-190.4-187.2c160-180.8 148.8-454.4-24-620.8-182.4-179.2-475.2-179.2-659.2 0-177.6 172.8-182.4 457.6-9.6 635.2l9.6 9.6c166.4 160 425.6 177.6 611.2 40l192 188.8c8 9.6 20.8 14.4 33.6 16 12.8 0 25.6-4.8 35.2-14.4 19.2-17.6 20.8-48 1.6-67.2z m-912-486.4c3.2-200 168-360 369.6-358.4 200-1.6 363.2 158.4 368 358.4-3.2 200-168 360-368 358.4-201.6 3.2-366.4-156.8-369.6-358.4z"
                fill="#333333"
                p-id="5352"
              ></path></svg
          ></el-button>
        </div>

        <!-- 标签 -->
        <div style="padding-left: 300px; margin-top: 10px">
          <el-tag hit="true" class="ml-2">张子枫同款</el-tag>
          <el-tag class="ml-2" type="success">iphone12</el-tag>
          <el-tag class="ml-2" type="info">华为P50</el-tag>
          <el-tag class="ml-2" type="warning">联想</el-tag>
          <el-tag class="ml-2" type="danger">华硕</el-tag>
        </div>

        <el-page-header
          @back="$router.push('/')"
          :content="word"
          title="返回"
          style="margin-top: 20px"
        />

        <div class="good">
          <el-alert
            :title="word"
            type="success"
            :closable="false"
            center
          />
          <el-row>
            <el-col v-for="goodsList in goodsLists" :key="goodsList" :span="6" style="margin-top: 10px">
              <el-card
                @click="$router.push({path:'/goodsDetails',query:{gId:goodsList.gid}})"
                shadow=" hover"
                :body-style="{ height: '300px' }"
              >
                <img style="width:200px;height:200px;text-align:center" :src="'/api/files/'+goodsList.gpicture" class="image" />
                <p>{{goodsList.gname}}</p>
               <div> <s >¥{{goodsList.gpriceOld}}</s>
                <span class="good-span">¥{{goodsList.gpriceNew}}</span></div>
              </el-card>
            </el-col>
          </el-row>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import request from "@/utils/request.js";
import Header from "@/components/Header.vue";
export default {
  name: "GoodsByWord.vue",
  components: {
    Header,
  },
  data() {
    return {
      word: this.$route.query.word,
      img: "", 
     goodsLists:[], 
      goodsList:[], 
 
   
    };
  },
  methods: {
    load(){
      request.get("/goodsByWord/" + this.word).then((res) => {
      console.log(res);
      this.goodsList = res.data
      this.goodsLists = JSON.parse(JSON.stringify(this.goodsList)) 
      console.log(this.goodsLists);
       });
    }
  },

  created() {
    this.load()
    
   
      
    
     
   
  },
};
</script>

<style>
.good-span{
  margin-top:5px;
  margin-left: 10px;
  color:red;
  font-size: 16px;
  text-align: center;
}
 .el-card { 
  border: none;
      --el-card-padding: 0px !important;
}
s{
   margin-left: 120px;
  font-size: 8px;
  color:gray
}
</style>