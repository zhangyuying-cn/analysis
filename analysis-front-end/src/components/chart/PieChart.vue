<template>
    <div id="pieChart" style="width:100% ;height:600px;"></div>
</template>

<script>
import * as service from '../../analysis/analysisService'
export default {
  name: 'PieChart',
  data () {
    return {
      pieData: {
        nameData: [],
        pieDataValue: [{
          name: '',
          value: ''
        }]
      }
    }
  },
  props: {
    analysisType: {
      type: String,
      required: true
    }

  },
  watch: {
    analysisType (newValue, oldValue) {
      if (newValue !== oldValue) {
        this.getAnalysis()
      }
    }
  },
  methods: {
    getAnalysis () {
      let _this = this
      console.log(this.analysisType)
      service.getAnalysis(this.analysisType, analysisResult => {
        // analysisResult.push(...analysisResult)
        let name = []
        let nameAndPercentage = []
        analysisResult.forEach(result => {
          name.push(result.name)
          let entry = {
            name: result.name,
            value: result.count
          }
          nameAndPercentage.push(entry)
        })
        _this.preData = {
          nameData: name,
          pieDataValue: nameAndPercentage
        }
        this.getPie()
      })
    },
    getPie () {
      // 绘制图表
      let pieChart = this.$echarts.init(document.getElementById('pieChart'))
      // 指定图表的配置项和数据
      let option = {
        // 标题
        title: {
          text: '分析结果'
        },
        // stillShowZeroSum: true,
        // 鼠标划过时饼状图上显示的数据
        tooltip: {
          // trigger: 'item',
          formatter: '{b}:{c} ({d}%)'
        },
        // 图例
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
          // data: ['未领取', '处理中', '已完成']// 图例上显示的饼图各模块上的名字
          data: this.preData.nameData
        },
        // 饼图数据
        series: {
          // name: 'bug分布',
          type: 'pie', // echarts图的类型   pie代表饼图
          radius: '80%', // 饼图中饼状部分的大小所占整个父元素的百分比
          center: ['50%', '50%'], // 整个饼图在整个父元素中的位置
          label: {
            normal: {
              formatter: '{b}:{c}: ({d}%)',
              textStyle: {
                fontWeight: 'normal',
                fontSize: 15
              }
            }
          },
          // 饼图数据
          data: this.preData.pieDataValue
        }
      }
      // 使用刚指定的配置项和数据显示图表。
      pieChart.setOption(option)
    }
  }
}

</script>

<style scoped>

</style>
