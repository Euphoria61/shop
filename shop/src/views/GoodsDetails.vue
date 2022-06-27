<template>
  <div>
    <div><Header /></div>
    <div class="layout">
      <div
        style="
          margin-left: 200px;
          width: 70%;
          height: 500px;
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
          <el-button
            type="danger"
            @click="
              $router.push({ path: '/GoodsByWord', query: { word: word } })
            "
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
          title="返回"
          style="margin-top: 20px"
        />
        <div>
          <div style="float: left; margin-top: 20px; margin-left: 120px">
            <img
              style="width: 300px; height: 300px"
              :src="'/api/files/' + goodsLists.gpicture"
              alt=""
            />

            <el-link
              v-if="isCollect == 0"
              @click="collection()"
              :underline="false"
            >
              <svg
                style="width: 3em"
                t="1648695732203"
                class="icon"
                viewBox="0 0 1024 1024"
                version="1.1"
                xmlns="http://www.w3.org/2000/svg"
                p-id="5737"
                width="200"
                height="200"
              >
                <path
                  d="M935.424 192c-53.76-53.76-124.928-82.944-200.192-82.944-75.776 0-146.944 29.696-200.192 82.944l-20.48 20.48-20.48-20.48C440.832 138.24 369.664 108.544 293.888 108.544S146.944 138.24 93.184 192c-110.08 110.592-109.568 290.304 0.512 400.896l389.632 389.632c8.704 8.704 19.968 13.312 31.744 13.312 11.776 0 23.04-4.608 31.744-13.312l389.632-389.632c110.08-111.104 109.568-290.816-1.024-400.896z m-41.984 357.376l-377.856 377.856-377.856-377.856c-86.528-86.528-87.04-227.328-1.024-314.368 41.984-41.984 97.792-65.024 157.184-65.024s115.2 23.04 157.184 65.024l63.488 63.488 63.488-63.488c41.984-41.984 97.792-65.024 157.184-65.024 59.392 0 115.2 23.04 157.184 65.024 86.016 86.528 86.528 227.328 1.024 314.368z"
                  fill="#333333"
                  p-id="5738"
                ></path></svg
            ></el-link>

            <el-link
              v-if="isCollect == 1"
              @click="collection()"
              :underline="false"
            >
              <svg
                style="width: 3em"
                t="1648695695605"
                class="icon"
                viewBox="0 0 1024 1024"
                version="1.1"
                xmlns="http://www.w3.org/2000/svg"
                p-id="5602"
                width="200"
                height="200"
              >
                <path
                  d="M913.92 208.384c-98.816-98.816-258.56-98.816-357.376 0l-41.984 41.984-41.984-41.984c-98.816-98.816-258.56-98.816-357.376 0-98.304 98.816-98.304 258.56 0.512 357.376l52.224 52.224 337.408 337.408c5.632 5.632 14.336 5.632 19.968 0l337.408-337.408 52.224-52.224c97.792-98.816 97.792-258.56-1.024-357.376z"
                  fill="#FC053B"
                  p-id="5603"
                ></path></svg
            ></el-link>
          </div>
          <!-- 商品信息 -->
          <div style="float: left; margin-top: 20px; margin-left: 80px">
            <el-descriptions column="1" style="margin-left: 20px">
              <el-descriptions-item label-class-name="my-label">
                <h3
                  style="
                    color: rgb(102, 102, 102);

                    margin-left: 20px;
                    float: left;
                  "
                >
                  {{ goodsLists.gname }}
                </h3></el-descriptions-item
              >

              <el-descriptions-item label-class-name="my-label" label="分类："
                ><el-link
                  :underline="false"
                  href=""
                  style="margin-left: 50px"
                  >{{ goodsLists.gtName }}</el-link
                ></el-descriptions-item
              >

              <el-descriptions-item label-class-name="my-label" label="价格:"
                ><s style="margin-left: 50px"
                  >¥{{ goodsLists.gpriceOld }}</s
                ></el-descriptions-item
              >

              <el-descriptions-item label-class-name="my-label" label="促销价:"
                ><h1 style="margin-left: 100px; color: rgb(255, 0, 54)">
                  ¥{{ goodsLists.gpriceNew }}
                </h1></el-descriptions-item
              >
              <el-descriptions-item
                label="剩余："
                style="color: rgb(243, 243, 243)"
                label-class-name="my-label"
                >{{ goodsList.gstore }}件</el-descriptions-item
              >

              <el-descriptions-item
                label="购买数量："
                label-class-name="my-label"
              >
                <el-input-number v-model="sCount"
              /></el-descriptions-item>
            </el-descriptions>
            <div style="margin-top: 20px; margin-left: 40px">
              <el-button @click="dialog = true" type="success" plain
                >立即购买</el-button
              >
              <el-button @click="addCartGoods()" type="danger" plain
                >加入购物车</el-button
              >
            </div>

            <el-dialog v-model="dialog" title="确认订单" width="30%">
              <h3>商品名称：</h3>
              {{ goodsLists.gname }} <br />
              <h3>商品价格：</h3>
              ¥{{ goodsLists.gpriceNew }}<br />
              <h3>购买数量：</h3>
              {{ buyCount }}
              <h3>总金额：</h3>
              ¥{{ all }}
              <template #footer>
                <span class="dialog-footer">
                  <el-button @click="dialog = false">取消</el-button>
                  <el-button type="primary" @click="buyGood()">确认</el-button>
                </span>
              </template>
            </el-dialog>
          </div>
        </div>
      </div>

      <div>
        <el-tabs
          v-model="activeName"
          type="card"
          class="demo-tabs"
          @tab-click="handleClick"
        >
          <!-- <el-tab-pane label="商品详情" name="first">User</el-tab-pane> -->

          <el-tab-pane label="商品评价" name="second">
            <div style="margin-left: 500px; height: 500px">
              <el-row>
                <el-col
                  v-for="comment in comments"
                  :key="comment"
                  :span="24"
                  :offset="1"
                >
                  <el-card>
                    <div style="padding: 14px">
                      <div style="float: left">
                        <el-avatar :size="40" :src="circleUrl" />
                      </div>
                      <h4>{{ comment.comment_userId }}刘杰</h4>
                      <el-rate
                        v-model="comment.comment_rate"
                        disabled
                        show-score
                        text-color="#ff9900"
                        score-template="{value} 分"
                      />
                      <div class="bottom">
                        {{ comment.comment_content }}
                      </div>
                    </div>
                  </el-card>
                </el-col>
              </el-row>
            </div>
          </el-tab-pane>
        </el-tabs>
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
      word: "",
      gId: this.$route.query.gId,
      isCollect: 0,
      img: "",
      userId: 0,
      code: 0,
      sCount: 1,
      buyCount: 1,
      comments: [],

      goodsLists: [],
      goodsList: [],
      dialog: false,
    };
  },
  methods: {
    load() {
      if (JSON.parse(sessionStorage.getItem("user")) != null)
        this.userId = JSON.parse(sessionStorage.getItem("user")).userId;
      request.get("/selectGoodsDetails/" + this.gId).then((res) => {
        this.goodsList = res.data;
        this.goodsLists = JSON.parse(JSON.stringify(this.goodsList));
        console.log(this.goodsLists);
      });
      if (this.userId != 0) {
        request.get("/queryIsCollected/" + this.gId).then((res) => {
          this.isCollect = res.data;
        });
      }

      request.get("/queryComment/" + this.gId).then((res) => {
        this.comment = res.data;
        this.comments = JSON.parse(JSON.stringify(this.comment));

        console.log(this.comments);

        console.log(this.comments[0].comment_rate);
      });
    },
    collection() {
      if (this.isCollect == 1) {
        request
          .delete("/deleteCollections/" + this.userId + "/" + this.gId)
          .then((res) => {});
        this.isCollect = 0;
      } else {
        request
          .put("/addCollections/" + this.userId + "/" + this.gId)
          .then((res) => {
            if (res.code === "0") {
              this.$message.success("收藏成功！");
              this.isCollect = 1;
              this.load();
            } else {
              this.$message.error("收藏失败，请先登录！");
              this.$router.push("/login");
            }
          });
      }
    },
    addCartGoods() {
      request
        .put(
          "/addCartGoods/" + this.userId + "/" + this.gId + "/" + this.sCount
        )
        .then((res) => {
          if (res.code === "0") {
            this.$message.success("添加成功！");

            this.load();
          } else {
            this.$message({
              type: "error",
              message: res.msg,
            });
            this.$router.push("/login");
          }
        });
    },
    buyGood() {
     
      request
        .put(
          "/confirmGoodsOrder/" +
            this.userId +
            "/" +
            this.gId +
            "/" +
            this.buyCount
        )
        .then((res) => {
          if (res.code === "0") {
           

            var formData = new FormData();
            formData.append("code", res.data);
            formData.append("subject",this.goodsLists.gname);
            formData.append("money", this.goodsLists.gpriceNew);
            request.put("/payGoods" , formData).then((res) => {
              this.dialog = false;
            });
            this.code = 0;
          } else {
            this.$message({
              type: "error",
              message: res.msg,
            });
          }
        });
    },
  },
  created() {
    this.load();
  },
  computed: {
    all: function () {
      return parseInt(this.goodsLists.gpriceNew) * parseInt(this.buyCount);
    },
  },
};
</script>

<style>
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

el-descriptions-item {
  display: block;
}

.my-label {
  color: rgb(153, 153, 153) !important;
}
</style>