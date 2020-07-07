<template>
  <div>
    <el-input v-model="analysisType" placeholder="请输入内容"/>
    <el-button type="primary"
               @click="generatePie">生成饼图</el-button>
    <pie-chart
      v-show="pieShow"
      :analysisType="analysisType">
    </pie-chart>
  </div>
</template>

<script>
import PieChart from './chart/PieChart'
export default {
  name: 'GenerateChart',
  data () {
    return {
      pieShow: true,
      analysisType: ''
    }
  },
  methods: {
    generatePie () {
      this.pieShow = true
    }
  },

  components: {
    PieChart
  }
}

</script>

<style scoped>

</style>
