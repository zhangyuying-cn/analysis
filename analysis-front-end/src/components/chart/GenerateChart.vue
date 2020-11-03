<template>
  <div>
    <el-input v-model="analysisValue" placeholder="请输入分析内容"/>
    <el-input v-model="xAxisValue" placeholder="请输入横轴"/>
    <el-button type="primary"
               @click="generatePie">生成饼图</el-button>
    <el-button type="primary"
               @click="generateBar">生成柱状图</el-button>
    <el-button type="primary"
               @click="generateLine">生成线性图</el-button>
    <!--<router-view/>-->
    <pie-chart
      :analysisType="analysisType">
    </pie-chart>
    <bar-chart
      :analysisType="analysisType"
      :xAxis="xAxis"
      :chartType="chartType">
    </bar-chart>
  </div>
</template>

<script>
import PieChart from './PieChart'
import BarChart from './BarChart'
export default {
  name: 'GenerateChart',
  data () {
    return {
      chartShow: '',
      analysisValue: '',
      analysisType: '',
      xAxis: '',
      xAxisValue: '',
      chartType: ''
    }
  },
  methods: {
    generatePie () {
      this.chartShow = 'pie'
      this.analysisType = this.analysisValue
    },
    generateBar () {
      // this.chartShow = 'bar'
      this.analysisType = this.analysisValue
      this.xAxis = this.xAxisValue
      this.chartType = 'bar'
    },
    generateLine () {
      // this.chartShow = 'bar'
      this.analysisType = this.analysisValue
      this.xAxis = this.xAxisValue
      this.chartType = 'line'
    }
  },

  components: {
    BarChart,
    PieChart
  }
}

</script>

<style scoped>

</style>
