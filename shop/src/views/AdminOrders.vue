<template>
  <div style="padding: 10px">
    <!--    搜索区域-->
    <div style="margin: 10px 0">
      商品Id：
      <el-input
        v-model="goodsOrder.gId"
        placeholder="想查看的商品Id"
        style="width: 20%"
        clearable
      ></el-input
      ><br />
      用户Id：
      <el-input
        v-model="goodsOrder.userId"
        placeholder="想查看的用户Id"
        style="width: 20%"
        clearable
      ></el-input
      ><br />
      订单状态：
      <el-select
        v-model="goodsOrder.status"
        placeholder="想查看的订单状态"
        size="large"
        clearable
      >
        <el-option
          v-for="item in status"
          :value="item.id"
          :key="item.id"
          :label="item.status"
        /> </el-select
      ><br />
      订单时间：
      <el-date-picker
        v-model="goodsOrder.createDate"
        type="daterange"
         value-format="YYYY-MM-DD hh:mm:ss "
        range-separator="To"
        start-placeholder="Start date"
        end-placeholder="End date"
      />
      <el-button
        type="primary"
        style="margin-left: 5px"
        @click="selectGoodsOrder"
        >查询</el-button
      >
    </div>
    <el-table
      v-loading="loading"
      :data="tableData"
      size="mini"
      border
      stripe
      style="width: 100%"
    >
      <el-table-column type="selection" width="40"> </el-table-column>
      <el-table-column prop="gOId" label="订单号"> </el-table-column>

      <el-table-column prop="userId" label="用户Id"> </el-table-column>
      <el-table-column prop="gId" label="商品Id"> </el-table-column>
      <el-table-column :formatter="stateFormat" prop="status" label="订单状态">
      </el-table-column>
      <el-table-column prop="createDate" label="创建时间"> </el-table-column>

      <el-table-column label="操作" width="240">
        <template #default="scope">
          <el-popconfirm
            v-if="scope.row.status == 1"
            title="确定发货吗？"
            @confirm="deliverGoods(scope.row.gOId)"
          >
            <template #reference>
              <el-button
                size="mini"
                type="primary"
     
                >发货</el-button
              >
            </template>
          </el-popconfirm>

          <el-popconfirm
            v-if="scope.row.status == 1"
            title="确定撤单吗？"
            @confirm="refundGoods(scope.row.gOId)"
          >
            <template #reference>
              <el-button
                size="mini"
                type="danger"
                >撤单</el-button
              >
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin: 10px 400px">
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
  </div>
</template>

<script>
import request from "@/utils/request";
import qs from "qs";
//     （订单取消）-2 付款失败-1   未付款：0  (已支付)未发货：1  撤销订单（退款）：2  已发货：3 派送中：4  待取件：5   已签收：6 "订单完成"7
export default {
  name: "AdminOrders.vue",
  components: {},
  data() {
    return {
      loading: true,
      status: [
        { id: 0, status: "未付款" },
        { id: 1, status: "未发货" },
        { id: 2, status: "已撤销订单" },
        { id: 3, status: "已发货" },
        { id: 4, status: "派送中" },
        { id: 5, status: "待取件" },
        { id: 6, status: "已签收" },
        { id: 7, status: "订单完成" },
      ],
      selectStatus: "",
      selectUserName: "",
      selectGoodsName: "",
      selectDate: "",
      goodsOrder: {},
      currentPage: 1,
      pageSize: 10,
      total: 0,
      tableData: [],
    };
  },
  created() {
    this.load();
  },
  methods: {
    selectGoodsOrder() {
      this.load();

     
    },
    deliverGoods(code) {
      request.put("/admin/deliverGoods/" + code).then((res) => {
        if (res.code === "0") {
          this.load()
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
    },

    refundGoods(code) {
      request.put("/admin/refundGoods/" +  code).then((res) => {
        if (res.code === "0") {
           this.load()
          this.$message({
            type: "success",
            message: "撤单成功！",
          });
        } else {
          this.$message({
            type: "error",
            message: res.msg,
          });
        }
      });
    },
    //     （订单取消）-2 付款失败-1   未付款：0  (已支付)未发货：1  撤销订单（退款）：2  已发货：3 派送中：4  待取件：5   已签收：6 "订单完成"
    stateFormat(row) {
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
      this.loading = true;
      console.log(JSON.stringify(this.goodsOrder));
      request
        .post(
          "/admin/goodsOrder/" + this.currentPage + "/" + this.pageSize,
         this.goodsOrder
        )
        .then((res) => {
          this.loading = false;
          this.tableData = res.data.data;
          console.log(this.tableData);
          this.total = res.data.total;
        });
    },
  
  },
};
</script>