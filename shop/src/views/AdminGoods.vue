<template>
  <div style="padding: 10px">
    <!--    功能区域-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="dialogVisible2 = true">新增</el-button>
      <el-popconfirm title="确定删除吗？" @confirm="deleteBatch">
        <template #reference>
          <el-button type="danger">批量删除</el-button>
        </template>
      </el-popconfirm>
    </div>
    <!--    搜索区域-->
    <div style="margin: 10px 0">
      <el-input
        v-model="search"
        placeholder="请输入关键字"
        style="width: 20%"
      ></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="select()"
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
      <!--        @selection-change="handleSelectionChange"-->

      <el-table-column type="selection" width="40"> </el-table-column>
      <el-table-column prop="gname" label="商品名称"> </el-table-column>
      <el-table-column label="商品图片">
        <template #default="scope">
          <img
            style="width: 100px; height: 100px; text-align: center"
            :src="'/api/files/' + scope.row.gpicture"
            class="image"
          />
        </template>
      </el-table-column>
      <el-table-column prop="gpriceOld" label="原价" width="80">
      </el-table-column>
      <el-table-column prop="gpriceNew" label="现价" width="80">
      </el-table-column>
      <el-table-column prop="gtName" label="商品分类"> </el-table-column>
      <el-table-column prop="gstore" label="库存" width="85"> </el-table-column>
      <el-table-column prop="gdetails" label="商品详情"> </el-table-column>
      <el-table-column prop="isShelf" label="是否上架" width="85">
      </el-table-column>

      <el-table-column label="操作" width="240">
        <template #default="scope">
          <el-button
            type="primary"
            size="mini"
            @click="onShelf(scope.row.gid)"
            v-if="scope.row.isShelf == 0"
            >上架</el-button
          >
          <el-button
            type="primary"
            size="mini"
            @click="offShelf(scope.row.gid)"
            v-if="scope.row.isShelf == 1"
            >下架</el-button
          >
          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm
            v-if="scope.row.isShelf == 0"
            title="确定删除吗？"
            @confirm="handleDelete(scope.row.gid)"
          >
            <template #reference>
              <el-button size="mini" type="danger">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin: 10px 100px">
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
      <!-- 编辑弹窗 -->
      <el-dialog title="编辑商品" v-model="dialogVisible1" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="商品名称">
            <el-input v-model="form.gname" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="原价">
            <el-input v-model="form.gpriceOld" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="现价">
            <el-input v-model="form.gpriceNew" style="width: 80%"></el-input>
          </el-form-item>

          <el-form-item label="库存">
            <el-input v-model="form.gstore" style="width: 80%"></el-input>
          </el-form-item>
 

          <el-form-item label="商品详情">
            <el-input
              v-model="form.gdetails"
              style="width: 80%"
              type="textarea"
            ></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible1 = false">取 消</el-button>
            <el-button type="primary" @click="save">修改</el-button>
          </span>
        </template>
      </el-dialog>
      <!-- 添加弹窗 -->
      <el-dialog title="新增商品" v-model="dialogVisible2" width="30%">
        <el-form :model="form2" label-width="120px" enctype="multipart/form-data">
          <el-form-item label="商品名称">
            <el-input v-model="form2.gName" style="width: 80%"></el-input>
          </el-form-item>

          <el-form-item label="商品分类">
            <el-select
              v-model="form2.gtId"
              filterable
              remote
              reserve-keyword
              :remote-method="remoteMethod"
              :loading="loading"
            >
              <el-option
                v-for="item in options"
                :key="item.gtId"
                :label="item.gtName"
                :value="item.gtId"
              />
            </el-select>
          </el-form-item>

          <el-form-item label="原价">
            <el-input v-model="form2.gPriceOld" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="现价">
            <el-input v-model="form2.gPriceNew" style="width: 80%"></el-input>
          </el-form-item>

          <el-form-item label="库存">
            <el-input v-model="form2.gStore" style="width: 80%"></el-input>
          </el-form-item>

          <el-form-item label="商品图片">
            <el-input
              id="file"
              v-model="form2.image"
              style="width: 80%"
              type="file"
            ></el-input>
          </el-form-item>

          <el-form-item label="商品详情">
            <el-input
              v-model="form2.gDetails"
              type="textarea"
              style="width: 80%"
            ></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible2 = false">取 消</el-button>
            <el-button type="primary" @click="add">添加</el-button>
          </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
import { onMounted, ref } from "vue";
export default {
  name: "AdminGoods.vue",
  components: {},
  data() {
    return {
      loading: true,
      form: {},
      form2: {},
      // formData:{},
      dialogVisible1: false,
      dialogVisible2: false,
      search: "",
      currentPage: 1,
      pageSize: 10,
      total: 0,

      tableData: [],
      selectType: "",
      file: "",
      options: "",
      
    };
  },
  created() {
    this.load();
  },
  methods: {
    remoteMethod(selectType) {
      request.get("/admin/allGoodsType").then((res) => {
        this.options = res.data.filter((item) => {
          return item.gtName.includes(selectType);
        });
      });
    },

    select() {
      request
        .get("/admin/search/" + this.search, {
          params: {
            pageNum: this.currentPage,
            pageSize: this.pageSize,
          },
        })
        .then((res) => {
          this.tableData = res.data.data;
          this.total = res.data.total;
          console.log(this.tableData);
        });
    },

    offShelf(gId) {
      request.put("/admin/offShelf/" + gId).then((res) => {
        if (res.code === "0") {
          this.$message.success("下架成功！");
          this.load();
        } else {
          this.$message.error(res.msg);
        }
      });
    },

    onShelf(gId) {
      request.put("/admin/onShelf/" + gId).then((res) => {
        if (res.code === "0") {
          this.$message.success("上架成功！");
          this.load();
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    //     deleteBatch() {
    //       if (!this.ids.length) {
    //         this.$message.warning("请选择数据！")
    //         return
    //       }
    //       request.post("/book/deleteBatch", this.ids).then(res => {
    //         if (res.code === '0') {
    //           this.$message.success("批量删除成功")
    //           this.load()
    //         } else {
    //           this.$message.error(res.msg)
    //         }
    //       })
    //     },
    //     handleSelectionChange(val) {
    //       this.ids = val.map(v => v.id)   // [{id,name}, {id,name}] => [id,id]
    //     },
    //      filesUploadSuccess(res) {
    //       console.log(res)
    //       this.form.cover = res.data
    //     },
    load() {
      this.loading = true;
      request
        .get("/admin/allGoods", {
          params: {
            pageNum: this.currentPage,
            pageSize: this.pageSize,
          },
        })
        .then((res) => {
          this.loading = false;
          this.tableData = res.data.data;
          this.total = res.data.total;
        });
    },
    add() {
      let forms = new FormData();
      //文件部分
      forms.append("image",document.querySelector('input[type=file]').files[0]);   
      forms.append("gDetails", this.form2.gDetails);
      forms.append("gName", this.form2.gName);
      forms.append("gtId", this.form2.gtId);
      forms.append("gStore", this.form2.gStore);
      forms.append("gPriceNew", this.form2.gPriceNew);
      forms.append("gPriceOld", this.form2.gPriceOld);
      request
        .post(
          "/admin/addGoods",forms
        )
        .then((res) => {
            this.dialogVisible2 =false
           if (res.code === "0") {
          this.$message({
            type: "success",
            message: "添加成功！",
          });
        } else {
          this.$message({
            type: "error",
            message: res.msg,
          });
        }
        });
    },
    save() {
      console.log(this.form);
      request.put("/admin/updateGoods", this.form).then((res) => {
        if (res.code === "0") {
          this.$message({
            type: "success",
            message: "更新成功",
          });
        } else {
          this.$message({
            type: "error",
            message: res.msg,
          });
        }
        this.load(); // 刷新表格的数据
        this.dialogVisible1 = false; // 关闭弹窗
      });
    },

    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row));
      this.dialogVisible1 = true;
      this.$nextTick(() => {
        if (this.$refs["upload"]) {
          this.$refs["upload"].clearFiles(); // 清除历史文件列表
        }
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
};
</script>