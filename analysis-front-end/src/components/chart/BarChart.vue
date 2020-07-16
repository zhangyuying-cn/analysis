<template>
        <div id="barChart" style="width:100% ;height:600px;"></div>
</template>

<script>
import * as service from '../../analysis/analysisService'
export default {
  name: 'BarChart',
  // mounted () {
  //   // 调用绘制图表的方法
  //   this.getBarOrLine()
  // },
  data () {
    return {
      barData: {
        nameData: [],
        xAxisData: [],
        barDataValue: [{
          name: '',
          type: '',
          data: []
        }]
      }
    }
  },
  props: {
    analysisType: {
      type: String,
      required: true
    },
    xAxis: {
      type: String,
      required: true
    },
    // 柱状图(bar)/线形图（line）
    chartType: {
      type: String,
      required: true
    }
  },
  watch: {
    analysisType (newValue, oldValue) {
      if (newValue !== oldValue) {
        this.getAnalysis()
      }
    },
    xAxis (newValue, oldValue) {
      if (newValue !== oldValue) {
        this.getAnalysis()
      }
    }
  },
  methods: {
    getAnalysis () {
      let _this = this
      console.log(this.analysisType)
      service.getBarAnalysis(this.analysisType, this.xAxis, analysisResult => {
        let nameList = []
        let xAxisDataList = []
        let barDataValueList = []
        analysisResult.forEach(result => {
          nameList.push(result.name)
          let xAxisCount = []
          if (result.barDatas) {
            result.barDatas.forEach(
              data => {
                xAxisDataList.push(data.xAxis)
                xAxisCount.push(data.count)
              }
            )
          }
          let entry = {
            name: result.name,
            type: this.chartType,
            data: xAxisCount
          }
          barDataValueList.push(entry)
        })
        _this.barData = {
          nameData: nameList,
          xAxisData: [...new Set(xAxisDataList)],
          barDataValue: barDataValueList
        }
        console.log(_this.barData)
        this.getBarOrLine()
      })
    },
    getBarOrLine () {
      // barChart
      let barChartDrawer = this.$echarts.init(document.getElementById('barChart'))

      // 绘制条形图
      let option = {
        title: {
          text: '分析结果'
          // top: 5,
          // left: 'center'
        },
        legend: {// 图例  标注各种颜色代表的模块
          // orient: 'vertical',//图例的显示方式  默认横向显示
          bottom: 0, // 控制图例出现的距离  默认左上角
          left: 'center', // 控制图例的位置
          // itemWidth: 16,//图例颜色块的宽度和高度
          // itemHeight: 12,
          textStyle: {// 图例中文字的样式
            color: '#000',
            fontSize: 16
          },
          // 显示的名字
          // data: ['衣服', '帽子']
          data: this.barData.nameData
        },
        // X轴
        xAxis: {
          // 横轴数据
          // data: [
          //   '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月'
          // ]
          data: this.barData.xAxisData
        },
        // Y轴
        yAxis: {
        },
        // 数据
        series: this.barData.barDataValue
        // series: [
        //   {
        //     name: '衣服',
        //     type: 'bar',
        //     data: [120, 100, 440, 320, 150]
        //   },
        //   {
        //     name: '帽子',
        //     type: 'bar',
        //     data: [200, 120, 240, 330, 170]
        //   }
        //   // TODO 下面是线形图
        //   // {
        //   //   name: 'bar',
        //   //   type: 'line',
        //   //   data: [120, 200, 240, 260, 300]
        //   // },
        //   // {
        //   //   name: 'bar',
        //   //   type: 'line',
        //   //   data: [120, 200, 300, 140, 260]
        //   // }
        // ]
      }

      barChartDrawer.setOption(option)
    }
  }
}
</script>

<style scoped>
  /*#myChart {*/
    /*width: 70%;*/
    /*min-height: 300px;*/
    /*clear: both;*/
    /*!*box-sizing: border-box;*!*/
    /*!*margin: auto;*!*/
  /*}*/

</style>
