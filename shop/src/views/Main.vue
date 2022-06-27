<template>
  <div>
    <!-- 导航栏 -->
    <div>
      <Header/>
    </div>
    <!-- 内容 -->
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
        <div class="search">
          <el-input
              class="input"
              style="width: 400px; margin-top: 20px; margin-left: 300px"
              v-model="word"
              placeholder="请输入关键词"
          />
          <el-button
              type="danger"
              @click="
              $router.push({ path: '/GoodsByWord', query: { word: word } })
            "
          >
            <svg
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
              ></path>
            </svg
            >
          </el-button>
        </div>

        <!-- 标签 -->
        <div style="padding-left: 300px; margin-top: 10px">
          <el-tag hit="true" class="ml-2">张子枫同款</el-tag>
          <el-tag class="ml-2" type="success">iphone12</el-tag>
          <el-tag class="ml-2" type="info">华为P50</el-tag>
          <el-tag class="ml-2" type="warning">联想</el-tag>
          <el-tag class="ml-2" type="danger">华硕</el-tag>
        </div>

        <!-- 导航展示 -->
        <div style="margin-top: 30px;">
          <!-- 左菜单栏 -->
          <div style="float: left; min-height: 400px">
            <el-menu
                :default-active="path"
                style="width: 200px"
                router
                class="el-menu-vertical-demo"
            >
              <el-sub-menu index="1">
                <template #title>
                  <span>数码产品</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="/GoodsByWord?word=电脑"
                  >电脑
                  </el-menu-item
                  >
                  <el-menu-item index="/GoodsByWord?word=手机"
                  >手机
                  </el-menu-item
                  >
                </el-menu-item-group>
              </el-sub-menu>
              <el-sub-menu index="2">
                <template #title>
                  <span>家用电器</span>
                </template>
                <el-menu-item-group title="Group One">
                  <el-menu-item index="2-1">item one</el-menu-item>
                  <el-menu-item index="2-2">item one</el-menu-item>
                </el-menu-item-group>
                <el-menu-item-group title="Group Two">
                  <el-menu-item index="2-3">item three</el-menu-item>
                </el-menu-item-group>
              </el-sub-menu>
              <el-sub-menu index="3">
                <template #title>
                  <span>生活用品</span>
                </template>
                <el-menu-item-group title="Group One">
                  <el-menu-item index="3-1">item one</el-menu-item>
                  <el-menu-item index="3-2">item one</el-menu-item>
                </el-menu-item-group>
                <el-menu-item-group title="Group Two">
                  <el-menu-item index="3-3">item three</el-menu-item>
                </el-menu-item-group>
              </el-sub-menu>
              <el-sub-menu index="4">
                <template #title>
                  <span>玩具乐器</span>
                </template>
                <el-menu-item-group title="Group One">
                  <el-menu-item index="4-1">item one</el-menu-item>
                  <el-menu-item index="4-2">item one</el-menu-item>
                </el-menu-item-group>
                <el-menu-item-group title="Group Two">
                  <el-menu-item index="4-3">item three</el-menu-item>
                </el-menu-item-group>
              </el-sub-menu>
              <el-sub-menu index="5">
                <template #title>
                  <span>医药保健</span>
                </template>
                <el-menu-item-group title="Group One">
                  <el-menu-item index="5-1">item one</el-menu-item>
                  <el-menu-item index="5-2">item one</el-menu-item>
                </el-menu-item-group>
                <el-menu-item-group title="Group Two">
                  <el-menu-item index="5-3">item three</el-menu-item>
                </el-menu-item-group>
              </el-sub-menu>
              <el-sub-menu index="6">
                <template #title>
                  <span>医药保健</span>
                </template>
                <el-menu-item-group title="Group One">
                  <el-menu-item index="6-1">item one</el-menu-item>
                  <el-menu-item index="6-2">item one</el-menu-item>
                </el-menu-item-group>
                <el-menu-item-group title="Group Two">
                  <el-menu-item index="6-3">item three</el-menu-item>
                </el-menu-item-group>
              </el-sub-menu>
              <el-sub-menu index="7">
                <template #title>
                  <span>医药保健</span>
                </template>
                <el-menu-item-group title="Group One">
                  <el-menu-item index="7-1">item one</el-menu-item>
                  <el-menu-item index="7-2">item one</el-menu-item>
                </el-menu-item-group>
                <el-menu-item-group title="Group Two">
                  <el-menu-item index="7-3">item three</el-menu-item>
                </el-menu-item-group>
              </el-sub-menu>
            </el-menu>
          </div>
          <!-- 轮播图 -->
          <div style="float: left; width: 660px">
            <el-carousel height="400px" autoplay>
              <el-carousel-item v-for="item in imgs" :key="item">
                <img style="width: 665px; height: 400px" :src="item.url"/>
              </el-carousel-item>
            </el-carousel>
          </div>
          <!-- 右菜单栏 -->
          <div style="float: left">
            <el-menu
                style="width: 200px"
                default-active="2"
                class="el-menu-vertical-demo"
            >
              <el-sub-menu index="1">
                <template #title>
                  <span>数码产品</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="1-1">电脑</el-menu-item>
                  <el-menu-item index="1-2">手机</el-menu-item>
                </el-menu-item-group>
              </el-sub-menu>
              <el-sub-menu index="2">
                <template #title>
                  <span>家用电器</span>
                </template>
                <el-menu-item-group title="Group One">
                  <el-menu-item index="2-1">item one</el-menu-item>
                  <el-menu-item index="2-2">item one</el-menu-item>
                </el-menu-item-group>
                <el-menu-item-group title="Group Two">
                  <el-menu-item index="2-3">item three</el-menu-item>
                </el-menu-item-group>
              </el-sub-menu>
              <el-sub-menu index="3">
                <template #title>
                  <span>生活用品</span>
                </template>
                <el-menu-item-group title="Group One">
                  <el-menu-item index="3-1">item one</el-menu-item>
                  <el-menu-item index="3-2">item one</el-menu-item>
                </el-menu-item-group>
                <el-menu-item-group title="Group Two">
                  <el-menu-item index="3-3">item three</el-menu-item>
                </el-menu-item-group>
              </el-sub-menu>

              <el-sub-menu index="4">
                <template #title>
                  <span>玩具乐器</span>
                </template>
                <el-menu-item-group title="Group One">
                  <el-menu-item index="4-1">item one</el-menu-item>
                  <el-menu-item index="4-2">item one</el-menu-item>
                </el-menu-item-group>
                <el-menu-item-group title="Group Two">
                  <el-menu-item index="4-3">item three</el-menu-item>
                </el-menu-item-group>
              </el-sub-menu>

              <el-sub-menu index="5">
                <template #title>
                  <span>医药保健</span>
                </template>
                <el-menu-item-group title="Group One">
                  <el-menu-item index="5-1">item one</el-menu-item>
                  <el-menu-item index="5-2">item one</el-menu-item>
                </el-menu-item-group>
                <el-menu-item-group title="Group Two">
                  <el-menu-item index="5-3">item three</el-menu-item>
                </el-menu-item-group>
              </el-sub-menu>

              <el-sub-menu index="6">
                <template #title>
                  <span>医药保健</span>
                </template>
                <el-menu-item-group title="Group One">
                  <el-menu-item index="6-1">item one</el-menu-item>
                  <el-menu-item index="6-2">item one</el-menu-item>
                </el-menu-item-group>
                <el-menu-item-group title="Group Two">
                  <el-menu-item index="6-3">item three</el-menu-item>
                </el-menu-item-group>
              </el-sub-menu>

              <el-sub-menu index="7">
                <template #title>
                  <span>医药保健</span>
                </template>
                <el-menu-item-group title="Group One">
                  <el-menu-item index="7-1">item one</el-menu-item>
                  <el-menu-item index="7-2">item one</el-menu-item>
                </el-menu-item-group>
                <el-menu-item-group title="Group Two">
                  <el-menu-item index="7-3">item three</el-menu-item>
                </el-menu-item-group>
              </el-sub-menu>
            </el-menu>
          </div>
        </div>
 <div class="good">
          <el-alert title="猜你喜欢" type="success" :closable="false" center/>
          <el-row>
            <el-col
                v-for="goodsList in goodsLists"
                :key="goodsList"
                :span="6"
                style="margin-top: 10px"
            >
              <el-card
                  @click="$router.push({path:'/goodsDetails',query:{gId:goodsList.gid}})"
                  shadow=" hover"
                  :body-style="{ height: '300px' }"
              >
                <img
                    style="width: 200px; height: 200px; text-align: center"
                    :src="'/api/files/' + goodsList.gpicture"
                    class="image"
                />
                <p>{{ goodsList.gname }}</p>
                <div>
                  <s>¥{{ goodsList.gpriceOld }}</s>
                  <span class="good-span">¥{{ goodsList.gpriceNew }}</span>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </div>
        <!-- 商品 -->
        <div class="good">
          <el-alert title="全部商品" type="success" :closable="false" center/>
          <el-row>
            <el-col
                v-for="goodsList in goodsLists"
                :key="goodsList"
                :span="6"
                style="margin-top: 10px"
            >
              <el-card
                  @click="$router.push({path:'/goodsDetails',query:{gId:goodsList.gid}})"
                  shadow=" hover"
                  :body-style="{ height: '300px' }"
              >
                <img
                    style="width: 200px; height: 200px; text-align: center"
                    :src="'/api/files/' + goodsList.gpicture"
                    class="image"
                />
                <p>{{ goodsList.gname }}</p>
                <div>
                  <s>¥{{ goodsList.gpriceOld }}</s>
                  <span class="good-span">¥{{ goodsList.gpriceNew }}</span>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </div>
        <div class="pagination">
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="currentPage"
              :page-sizes="[20, 30, 40]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total"
          >
          </el-pagination>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import request from "@/utils/request.js";
import Header from "@/components/Header.vue";

export default {
  name: "Main.vue",
  components: {
    Header,
  },
  data() {
    return {
      userName: "",
      currentPage: 1,
      pageSize: 20,
      total: 0,
      user: [],
      goodsType: "",
      path: this.$route.path,
      word: "",
      goodsLists: [],
      imgs: [
        {url: new URL("../assets/images/L1.jpg", import.meta.url).href},
        {url: new URL("../assets/images/L2.jpg", import.meta.url).href},
        {url: new URL("../assets/images/L3.jpg", import.meta.url).href},
        {url: new URL("../assets/images/L4.jpg", import.meta.url).href},
      ],
    };
  },
  methods: {
    load() {
      request
          .get("/goodsShelfed", {
            params: {
              pageNum: this.currentPage,
              pageSize: this.pageSize,
            },
          })
          .then((res) => {
            this.goodsList = res.data.data;
            this.goodsLists = JSON.parse(JSON.stringify(this.goodsList));
            this.total = res.data.total;

          });
    },
    handleSizeChange(pageSize) {
      // 改变当前每页的个数触发
      this.pageSize = pageSize;
      this.load();
    },
    handleCurrentChange(pageNum) {
      // 改变当前页码触发
      this.currentPage = pageNum;
      this.load();
    },
  },
  created() {
    // let userStr = sessionStorage.getItem("user") || "{}"
    // this.userName = JSON.parse(userStr)

    this.load();
  },
};
</script>

<style>
.good {
  margin-top: 10px;
}

.layout {
  background-color: rgb(red, green, blue);
}

.good-span {
  margin-top: 5px;
  margin-left: 10px;
  color: red;
  font-size: 16px;
  text-align: center;
}

.el-card {
  border: none;
  --el-card-padding: 0px !important;
}

s {
  margin-left: 120px;
  font-size: 8px;
  color: gray;
}

.pagination {
  margin-left: 300px;
  margin-top: 20px;
  height: 170px;
}
</style>