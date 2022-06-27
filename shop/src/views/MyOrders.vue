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
          content="我的订单"
          title="返回"
          style="margin-top: 20px"
        />
        <!-- 表格 -->
        <el-table
          v-loading="loading"
          :data="tableData"
          size="mini"
          border
          stripe
          style="width: 100%; margin-top: 20px"
        >
          <el-table-column type="selection" width="55"> </el-table-column>
          <el-table-column prop="gName" label="商品名称" width="130">
          </el-table-column>
          <el-table-column label="商品图片" width="150">
            <template #default="scope">
              <img
                style="width: 100px; height: 100px; text-align: center"
                :src="'/api/files/' + scope.row.gPicture"
                class="image"
              />
            </template>
          </el-table-column>

          <el-table-column prop="sCount" label="购买数量" width="120">
          </el-table-column>

          <el-table-column label="金额" width="100">
            <template #default="scope"> ¥{{ scope.row.gPrice_new }} </template>
          </el-table-column>
          <el-table-column
            :formatter="stateFormat"
            prop="status"
            label="状态"
            width="120"
          >
          </el-table-column>

          <el-table-column prop="createDate" width="200" label="时间" sortable>
          </el-table-column>

          <el-table-column label="可选操作">
            <template #default="scope">
              <el-button
                type="primary"
                size="mini"
                v-if="scope.row.status == 0"
                @click="buyGood(scope.row)"
                >付款</el-button
              >

              <el-button
                type="success"
                size="mini"
                @click="commentview(scope.row)"
                v-if="scope.row.status == 6"
                >评价</el-button
              >
              <el-popconfirm
                v-if="scope.row.status == 1"
                title="确定退款吗？"
                @confirm="handleDelete(scope.row)"
              >
                <template #reference>
                  <el-button size="mini" type="danger">退款</el-button>
                </template>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>

        <el-dialog title="评价该商品" v-model="diacomment" width="30%">
          <el-form :model="commentform" label-width="200px">
            商品名称：{{ tablerow.gName }}<br /><br />
            <img
              style="width: 100px; height: 100px; text-align: center"
              :src="'/api/files/' + tablerow.gPicture"
              class="image"
            /><br />
            评分：<el-rate
              v-model="commentform.comment_rate"
              :texts="['差', '较差', '一般', '好', '很好']"
              show-text
            /><br />
            <el-input
              v-model="commentform.comment_content"
              :autosize="{ minRows: 4, maxRows: 14 }"
              type="textarea"
              placeholder="请输入您的评价"
            />
          </el-form>
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="diacomment = false">取 消</el-button>
              <el-button type="primary" @click="comment">评价</el-button>
            </span>
          </template>
        </el-dialog>
        <div style="margin: 10px 300px">
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[5, 10, 20]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total"
          >
          </el-pagination>
        </div>
        <div ref="reportHTML" v-html="htmlText" class="web-con"></div>
      </div>
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
      userId: 0,
      status: [
        "未发货",
        "已撤销订单",
        "已发货",
        "派送中",
        "待取件",
        "已签收",
        "订单完成",
      ],
      form: [],
      commentform: {},
      tableData: [],
      tablerow: {},
      currentPage: 1,
      pageSize: 10,
      total: 0,
      diacomment: false,
      comment1: "",
      htmlText: "",
    };
  },
  created() {
    this.load();
  },
  methods: {
    comment() {
      this.commentform.comment_gId = this.tablerow.gId;
      this.commentform.comment_userId = this.tablerow.userId;
      console.log(this.commentform);
      request
        .put("/receivedGoods/" + this.tablerow.gOId, this.commentform)
        .then((res) => {
          if (res.code === "0") {
            this.diacomment = false;
            this.load();
            this.$message({
              type: "success",
              message: "评价成功！",
            });
          } else {
            this.$message({
              type: "error",
              message: res.msg,
            });
          }
        });
    },

    commentview(row) {
      this.tablerow = JSON.parse(JSON.stringify(row));
      console.log(this.tablerow);
      this.diacomment = true;
    },
    stateFormat(row) {
      if (row.status == -2) return "已退款";
      if (row.status == -1) return "付款失败";
      if (row.status == 0) return "未付款";
      if (row.status == 1) return "未发货";
      if (row.status == 2) return "已撤销订单";
      if (row.status == 3) return "已发货";
      if (row.status == 4) return "派送中";
      if (row.status == 5) return "待取件";
      if (row.status == 6) return "已签收";
      if (row.status == 7) return "订单完成";
    },
    load() {
      if(JSON.parse(sessionStorage.getItem("user")) != null)this.userId =JSON.parse(sessionStorage.getItem("user")).userId
      console.log(this.userId);
      if( this.userId !=0){
        request
          .get(
            "/myGoodsOrder/" +
              JSON.parse(sessionStorage.getItem("user")).userId,
            {
              params: {
                pageNum: this.currentPage,
                pageSize: this.pageSize,
              },
            }
          )
          .then((res) => {
            this.tableData = res.data.data;
            this.total = res.data.total;
            console.log(res);
          });
      } else {
        this.$router.push("/login");
      }
    },
    buyGood(row) {
      console.log(row);

      var formData = new FormData();
      formData.append("code", row.gOId);
      formData.append("subject", row.gName);
      formData.append("money", row.gPrice_new);
 
      request.put("/payGoods", formData).then((res) => {
        if (res.code === "0") {
      
          document.write(res.data);
        } else {
          this.$message({
            type: "error",
            message: res.msg,
          });
        }
      });
    },
    handleDelete(row) {
      console.log(row.gOId);
      if (this.userId != 0) {
        request.put("/cancelGoodsOrder/" + row.gOId).then((res) => {
          if (res.code === "0") {
            this.load();
            this.$message({
              type: "success",
              message: "发货成功！",
            });
          } else {
            this.$message({
              type: "error",
              message: res.msg,
            });
          }
        });
      } else {
        this.$router.push("/login");
      }
    },
  },
};
</script>

<style>
</style>