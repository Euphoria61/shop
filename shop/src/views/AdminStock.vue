<template>
  <div class="Echarts">
    <el-tabs
      style="margin-left: 20px"
      v-model="activeName"
      class="demo-tabs"
      @tab-click="handleClick"
    >
      <el-tab-pane label="当前库存" name="first">
        <div v-show="1 == 0">{{ textadd }}</div>
        <div id="main" style="width: 600px; height: 400px"></div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import request from "@/utils/request";
import * as echarts from "echarts";

export default {
  name: "AdminStock.vue",
  components: {},
  data() {
    return {
      activeName: "first",
      day: "",
      option: {
        title: {
          text: "",
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "shadow",
          },
        },
        toolbox: {
          feature: {
            dataView: { show: true, readOnly: false },
          },
        },
        xAxis: {
          type: "category",
          data: [],
        },

        yAxis: {
          type: "value",
          axisLabel: {
            formatter: "{value} 件",
          },
        },
        series: {
          type: "bar",
          data: [],
        },
      },
    };
  },
  computed: {
    textadd: function () {
      return (this.option.title.text = this.day + "当前库存(商品分类)");
    },
  },
  created() {
    let yy = new Date().getFullYear();
    let mm =
      new Date().getMonth() + 1 < 10
        ? "0" + (new Date().getMonth() + 1)
        : new Date().getMonth() + 1;
    let date =
      new Date().getDate() < 10
        ? "0" + new Date().getDate()
        : new Date().getDate();
    this.day = yy + "年" + mm + "月" + date + "日";
    this.load();
  },

  methods: {
    load() {
      request.get("/admin/stockReport").then((res) => {
        console.log(res);
        console.log();
        var myChart = echarts.init(document.getElementById("main"));
        for (let i = 0; i < res.data.length; i++) {
          this.option.xAxis.data[i] = res.data[i].gtName;
          this.option.series.data[i] = res.data[i].sales;
        }

        myChart.setOption(this.option);
      });
    },
  },
};
</script>