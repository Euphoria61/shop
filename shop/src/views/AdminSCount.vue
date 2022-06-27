<template>
  <div class="Echarts"> 
      <el-tabs style="margin-left:20px" v-model="activeName" class="demo-tabs" @tab-click="handleClick">
    <el-tab-pane label="商品分类" name="first"> <div style="flex: 1">
       <span style="margin-left:10px">请选择月份：</span><el-date-picker
        v-model="year"
        type="month"
        value-format="YYYY-MM"
        @change="load()"
        placeholder="选择月份"
      />
    </div>
    <div id="main" style="width: 1200px; height: 500px; flex: 1;margin-top:20px"></div></el-tab-pane>
    <el-tab-pane label="商品分类（月）" name="second">
       <div style="flex: 1">
          <span>请选择年份：</span
          ><el-date-picker
            v-model="yy"
            type="year"
            value-format="YYYY"
            @change="selectBymonth()"
            placeholder="请选择年份"
          />
        </div>
        <div
          id="main3"
          style="width: 1200px; height: 500px; flex: 1; margin-top: 20px"
        ></div>
      </el-tab-pane>
       <el-tab-pane label="指定商品" name="third">
       <el-button  type="primary" plain @click="dialog =true">更换商品</el-button>
        <div
          id="main2"
          style="width:700px; height: 500px;float:left; margin-top: 40px"
        ></div>
        <div
          id="main4"
          style="margin-left:10px;width: 520px; height: 500px; float:left; margin-top: 10px"
        ></div>
      </el-tab-pane>
  </el-tabs>
    
   <el-dialog
    v-model="dialog"
    title="提示"
    width="30%"
    :before-close="handleClose"
  >
    <el-input v-model="gId" placeholder="请输入想查看的商品Id" />
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialog = false">取消</el-button>
        <el-button type="primary" @click="selectSCountByGoods"
          >确定</el-button
        >
      </span>
    </template>
  </el-dialog>
  </div>
</template>

<script>
import request from "@/utils/request";
import * as echarts from "echarts";

export default {
  name: "AdminSCount.vue",
  components: {},
  data() {
    return {
      yy:0,
      gId:"",
      dialog:false,
      activeName:"first",
      year: "",
      x: [],
      x2: [],
      option: {
        title: {
          text: "日销量(商品分类)",
        },
        toolbox: {
          feature: {
            dataView: { readOnly: false },
          },
        },
        tooltip: {
          trigger: "axis",
        },
        legend: {
          data: ["电脑", "手机", "衣服", "鞋包"],
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true,
        },
        xAxis: {
          type: "category",
          boundaryGap: false,
          data: [],
        },
        yAxis: {
          type: "value",
          axisLabel: {
            formatter: "{value}件 ",
          },
        },
        series: [
          {
            name: "电脑",
            type: "line",
            data: [],
          },
          {
            name: "衣服",
            type: "line",
            data: [],
          },
          {
            name: "手机",
            type: "line",

            data: [],
          },
          {
            name: "鞋包",
            type: "line",
            data: [],
          },
        ],
      },

       option2: {
        title: {
          text: "日销售额（单个商品）",
        },
        toolbox: {
          feature: {
            dataView: { readOnly: false },
          },
        },
        tooltip: {
          trigger: "axis",
        },
        legend: {
          data: ["电脑"],
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true,
        },
        xAxis: {
          type: "category",
          boundaryGap: false,
          data: [],
        },
        yAxis: {
          type: "value",
          axisLabel: {
            formatter: "{value}件 ",
          },
        },
        series: [
          {
            name: "电脑",
            type: "line",
            data: [],
          },
        ],
      },
        option3: {
        title: {
          text: "月销售额(商品分类)",
        },
        toolbox: {
          feature: {
            dataView: { readOnly: false },
          },
        },
        tooltip: {
          trigger: "axis",
        },
        legend: {
          data: ["电脑", "手机", "衣服", "鞋包"],
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true,
        },
        xAxis: {
          type: "category",
          boundaryGap: false,
          data: [],
        },
        yAxis: {
          type: "value",
          axisLabel: {
            formatter: "{value}件 ",
          },
        },
        series: [
          {
            name: "电脑",
            type: "line",

            data: [],
          },
          {
            name: "衣服",
            type: "line",
            data: [],
          },
          {
            name: "手机",
            type: "line",

            data: [],
          },
          {
            name: "鞋包",
            type: "line",
            data: [],
          },
        ],
      },
      option4: {
        title: {
          text: "月销售额",
        },
        toolbox: {
          feature: {
            dataView: { readOnly: false },
          },
        },
        tooltip: {
          trigger: "axis",
        },
        legend: {
          data: [""],
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true,
        },
        xAxis: {
          type: "category",
          boundaryGap: false,
          data: [],
        },
        yAxis: {
          type: "value",
          axisLabel: {
            formatter: "{value}件 ",
          },
        },
        series: [
          {
            name: "",
            type: "line",

            data: [],
          },
        ],
      },
    };
  },
  created() { 
    let yy =new Date().getFullYear();
      let mm=new Date().getMonth()+1<10 ? '0'+(new Date().getMonth()+1) : (new Date().getMonth()+1)      
      this.year =yy+'-'+mm      
      this.yy =yy
    this.load();
  },
 
  computed: {
    
  },
  methods: {
     handleClick(tab) {
      if (tab.props.name =="third"){
        this.dialog =true
      }
       if (tab.props.name =="second"){
        
        this.selectBymonth()
      }
    },
    selectBymonth(){      
       request.get("/admin/sCountReport/" + this.yy+'/1').then((res) => {
        console.log(res);
        var myChart = echarts.init(document.getElementById("main3"));
        //坐标
        for (let i = 1; i <= 12; i++) {
          if (i < 10) this.x2[i - 1] = this.yy + "-0" + i;
          else this.x2[i - 1] = this.yy + "-" + i;
        }
        
        this.option3.xAxis.data = this.x2;
        this.option3.series[0].data = new Array(12).fill(0);
        this.option3.series[1].data = new Array(12).fill(0);
        this.option3.series[2].data = new Array(12).fill(0);
        this.option3.series[3].data = new Array(12).fill(0);
        for (let i = 0; i < res.data.length; i++) {
          for (let j = 0; j < this.option3.series.length; j++) {
            if (res.data[i].gtName == this.option3.series[j].name)
              this.option3.series[j].data[res.data[i].day.substr(6, 2) - 1] =
                res.data[i].sales;
          }
        }
        myChart.setOption(this.option3);
      });
      console.log(this.yy);
    },
     selectSCountByGoods(){
      request.get("/admin/selectSCountByGoods/" + this.year+'/'+this.gId+'/0').then((res) => {
        console.log(res);
          var myChart = echarts.init(document.getElementById("main2"));
                 for (let i = 1; i <= 32; i++) {
          if (i < 10) this.x[i - 1] = this.year + "-0" + i;
          else this.x[i - 1] = this.year + "-" + i;
        }
        this.option2.legend.data[0]=this.gId
        this.option2.series[0].name=this.gId
        this.option2.xAxis.data = this.x;
        this.option2.series[0].data = new Array(31).fill(0);
        for (let i = 0; i < res.data.length; i++) {
             this.option2.series[0].data[res.data[i].day.substr(8, 2) - 1] =
                res.data[i].sales;
          
        }
        myChart.setOption(this.option2);

           request.get("/admin/selectSCountByGoods/" + this.yy+'/'+this.gId+'/1').then((res) => {
        console.log(res);
          var myChart = echarts.init(document.getElementById("main4"));
          for (let i = 1; i <= 12; i++) {
          if (i < 10) this.x2[i - 1] = this.yy + "-0" + i;
          else this.x2[i - 1] = this.yy + "-" + i;
        }
          this.option4.legend.data[0]=this.gId
        this.option4.series[0].name=this.gId
       this.option4.xAxis.data = this.x2;
        this.option4.series[0].data = new Array(12).fill(0);
     
        for (let i = 0; i < res.data.length; i++) {
             this.option4.series[0].data[res.data[i].day.substr(6, 2) - 1] =
                res.data[i].sales;
          
        }
        myChart.setOption(this.option4);
    }),
     this.dialog =false
    })},
    load() {   
      request.get("/admin/sCountReport/" + this.year+'/0').then((res) => {
        console.log(res);
        var myChart = echarts.init(document.getElementById("main"));
        //坐标
        for (let i = 1; i <= 32; i++) {
          if (i < 10) this.x[i - 1] = this.year + "-0" + i;
          else this.x[i - 1] = this.year + "-" + i;
        }
        this.option.xAxis.data = this.x;
        this.option.series[0].data = new Array(31).fill(0);
        this.option.series[1].data = new Array(31).fill(0);
        this.option.series[2].data = new Array(31).fill(0);
        this.option.series[3].data = new Array(31).fill(0);

        for (let i = 0; i < res.data.length; i++) {
          for (let j = 0; j < this.option.series.length; j++) {
            if (res.data[i].gtName == this.option.series[j].name)
              this.option.series[j].data[res.data[i].day.substr(8, 2) - 1] =
                res.data[i].sales;
          }
        }
      
        myChart.setOption(this.option);
      });
    },

  },
};
</script>