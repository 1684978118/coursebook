<template>
  <div>
    <el-row :gutter="10" style="margin-bottom: 10px">
     <el-col :span="8">
       <div class="card" style="padding: 20px; display: flex; align-items: center;">
         <div style="flex: 1; text-align: center">
           <img style="width: 70px; height: 70px" src="@/assets/imgs/1.png" alt="">
         </div>
         <div style="flex: 1; font-size: 20px">
           <div style="margin-bottom: 10px">教材入库数</div>
           <div style="font-weight: bold">{{ data.countData.inStock }}</div>
         </div>
       </div>
     </el-col>
      <el-col :span="8">
        <div class="card" style="padding: 20px; display: flex; align-items: center;">
          <div style="flex: 1; text-align: center">
            <img style="width: 70px; height: 70px" src="@/assets/imgs/2.png" alt="">
          </div>
          <div style="flex: 1; font-size: 20px">
            <div style="margin-bottom: 10px">教材出库数</div>
            <div style="font-weight: bold">{{ data.countData.outStock }}</div>
          </div>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="card" style="padding: 20px; display: flex; align-items: center;">
          <div style="flex: 1; text-align: center">
            <img style="width: 70px; height: 70px" src="@/assets/imgs/3.png" alt="">
          </div>
          <div style="flex: 1; font-size: 20px">
            <div style="margin-bottom: 10px">教材现库存</div>
            <div style="font-weight: bold">{{ data.countData.inventory }}</div>
          </div>
        </div>
      </el-col>

    </el-row>

    <div style="display: flex; grid-gap: 10px">
      <div class="card" style="flex: 1">
        <div id="pie" style=" height: 500px"></div>
      </div>


    </div>

  </div>
</template>

<script setup>
import {reactive, onMounted} from "vue";
import request from "@/utils/request.js";
import * as echarts from 'echarts'

const pieOption = {
  title: {
    text: '教材库存分布',
    subtext: '比例图',
    left: 'center'
  },
  tooltip: {
    trigger: 'item'
  },
  legend: {
    orient: 'vertical',
    left: 'left'
  },
  series: [
    {
      type: 'pie',
      center: ['50%', '65%'],
      radius: '50%',
      data: [],
      label: {
        show: true,
        formatter(param) {
          return param.name + ' (' + param.percent + '%)';
        }
      },
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      }
    }
  ]
}

const data = reactive({
  countData: {}
})

request.get('/selectCount').then(res => data.countData = res.data)


// 等页面所有元素加载完成后再设置 echarts图表
onMounted(() => {
  // 请求数据  初始化图表
  // 饼图
  let pieDom = document.getElementById('pie');
  let pieChart = echarts.init(pieDom);
  request.get('/pie').then(res => {
    pieOption.series[0].data = res.data
    pieChart.setOption(pieOption)
  })


})

</script>