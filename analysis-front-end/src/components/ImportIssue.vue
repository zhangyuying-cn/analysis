<template>
  <div>
    <el-upload
      class="mt-4"
      drag
      action="/analysis/api/import/excel"
      :on-success="handlePreview"
      :on-error="handlePreview"
      :before-upload="beforeExcelUploadFun"
      :show-file-list="false"
    >
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">
        将 <span class="font-weight-bold text-primary">需要进行统计分析的数据excel</span>
        拖到此处，或<em>点击上传</em>
      </div>
      <div class="el-upload__tip" slot="tip">
        上传包含
        <span class="font-weight-bold text-primary">需要进行统计分析</span> 的
        <code>excel</code> 文件
      </div>
    </el-upload>
  </div>
</template>

<script>
export default {
  name: 'ImportIssue',
  methods: {
    handlePreview (response, file) {
      if (file.status === 'success') {
        if (file.response.status === 201) {
          // this.fullscreenLoading = false
          this.$message({
            message: '上传成功',
            type: 'success'
          })
        } else {
          this.$message.error(file.response.message)
        }
      } else {
        this.$message.error('上传错误')
      }
    },
    beforeExcelUploadFun (file) {
      if (!this.isExcelFile(file)) {
        this.$message({
          message: '上传文件类型不符合要求，只能上传 excel 格式的文件',
          type: 'warning'
        })
        return false
      }
    },
    isExcelFile (file) {
      if (console) {
        console.log(file)
      }
      // 后缀
      let suffix = ''
      try {
        let fileName = file.name
        console.log(fileName)
        let fileArr = fileName.split('.')
        if (fileArr.length > 1) {
          suffix = fileArr[fileArr.length - 1]
        }
      } catch (e) {
        console.error(e)
      }
      return suffix === 'xlsx'
    }
  }
}
</script>

<style scoped>

</style>
