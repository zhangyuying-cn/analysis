<template>
  <div>
    <div class="row justify-content-between my-3">
      <div class="col">
        <span class="d-inline-block text-muted lead font-weight-bold">
          待分析记录
        </span>
        <div
          class="d-inline-block ml-3"
        >
          <el-button
            type="primary"
            size="mini"
            @click.prevent="handleAddField(1)"
          >
            增加字段
          </el-button>
          <el-button
            type="primary"
            size="mini"
            @click.prevent="handleAddField(5)"
          >
            增加 5 个字段
          </el-button>
        </div>
      </div>
      <div
        class="col text-right"
      >
        <el-button
          size="mini"
          type="success"
          icon="el-icon-upload"
          @click.prevent="handleSave"
          class="mr-3"
        >
          保存
        </el-button>
        <!--<el-button-->
        <!--size="mini"-->
        <!--type="success"-->
        <!--icon="el-icon-document"-->
        <!--@click.prevent="handleExport"-->
        <!--&gt;-->
        <!--导出-->
        <!--</el-button>-->
      </div>
    </div>
    <el-table
      border
      style="width: 100%"
      max-height="800"
      size="small"
      empty-text="请点击 增加字段 按钮添加数据"
      :data="recordData"
      row-class-name="table-font bg-valid"
      header-row-class-name="table-header-font"
      @cell-dblclick="handleCellEdit"
    >
      <div
        class="row"
        v-for="(field, index) in metadataFields"
        :key="index"
      >
        <!--字符串类型-->
        <el-table-column v-if="field.type==='string'" :label="field.name" align="center">
          <template slot-scope="scope">
            <!-- 显示 -->
            <span v-show="!scope.row[field.name].edit">
              {{ scope.row[field.name].value }}
          </span>
            <!-- 编辑 -->
            <div v-show="scope.row[field.name].edit">
              <div class="float-left w-75">
                <el-input size="small" v-model="scope.row[field.name].value"></el-input>
              </div>
              <div class="float-right text-right">
                <el-button
                  @click.prevent="handleFieldChange(scope.row[field.name])"
                  type="success"
                  size="mini"
                  icon="el-icon-success"
                ></el-button>
              </div>
            </div>
          </template>
        </el-table-column>
        <!--枚举类型 -->
        <el-table-column v-if="field.type==='enum'" :label="field.name" align="center">
          <template slot-scope="scope">
            <!-- 显示 -->
            <span v-show="!scope.row[field.name].edit">
              <el-select :disabled="true" v-model="scope.row[field.name].value">
                <div
                  class="row"
                  v-for="(fieldEnum, index) in field.enums"
                  :key="index"
                >
                  <el-option :label="fieldEnum.name" :value="fieldEnum.code"></el-option>
                </div>
              </el-select>
          </span>
            <!-- 编辑 -->
            <div v-show="scope.row[field.name].edit">
              <div class="float-left w-75">
                <!--<el-input size="small" v-model="scope.row[field.name].value"></el-input>-->
                <el-select v-model="scope.row[field.name].value">
                  <div
                    class="row"
                    v-for="(fieldEnum, index) in field.enums"
                    :key="index"
                  >
                    <el-option :label="fieldEnum.name" :value="fieldEnum.code"></el-option>
                  </div>
                </el-select>
              </div>
              <div class="float-right text-right">
                <el-button
                  @click.prevent="handleFieldChange(scope.row[field.name])"
                  type="success"
                  size="mini"
                  icon="el-icon-success"
                ></el-button>
              </div>
            </div>
          </template>
        </el-table-column>

        <!--日期类型-->
        <el-table-column v-if="field.type==='date'" :label="field.name" align="center">
          <template slot-scope="scope">
            <!-- 显示 -->
            <span v-show="!scope.row[field.name].edit">
              <!--{{ scope.row[field.name].value }}-->
              <el-date-picker type="date" size="small" :disabled="true" placeholder="选择日期" v-model="scope.row[field.name].value"></el-date-picker>
          </span>
            <!-- 编辑 -->
            <div v-show="scope.row[field.name].edit">
              <div class="float-left w-75">
                <!--<el-input size="small" v-model="scope.row[field.name].value"></el-input>-->
                <el-date-picker type="date" size="small" placeholder="选择日期" v-model="scope.row[field.name].value"></el-date-picker>
              </div>
              <div class="float-right text-right">
                <el-button
                  @click.prevent="handleFieldChange(scope.row[field.name])"
                  type="success"
                  size="mini"
                  icon="el-icon-success"
                ></el-button>
              </div>
            </div>
          </template>
        </el-table-column>
      </div>

      <el-table-column label="操作" align="center">
        <template slot-scope="scope">

          <!-- common -->
          <el-button
            plain=""
            size="mini"
            type="danger"
            style="margin-left: 10px"
            @click.prevent="handleAction('delete', scope.row, scope.$index)"
          >
            <span>删除</span>
          </el-button>
        </template>
      </el-table-column>

    </el-table>

  </div>
</template>

<script>
import metadataService from '../../metadata/metadataService'
import service from '../../record/recordService'
import * as util from '../../record/recordUtil'
export default {
  name: 'RecordMaintain',

  data () {
    return {
      metadataFields: [],
      recordData: []
    }
  },
  created () {
    //  获取元数据 TODO 元数据要不要启动获取 放在vuex中
    this.metadataFields = []
    this.recordData = []
    metadataService.getMetadata()
      .then((data) => {
        let oneData = data[0]
        this.metadataFields.push(...util.warpMetadataFields(oneData.field))
        // TODO 需要分页
        service.getAllRecord(10, 1)
          .then((data) => {
            // data需要拼接上是否可以编辑的属性：edit
            let warpData = util.warpRecords(data, this.metadataFields)
            this.recordData.push(...warpData)
          })
          .catch((error) => {
            this.$message.error(error.response)
          })
      })
      .catch((error) => {
        this.$message.error(error.response)
      })
  },
  methods: {
    handleAddField (count) {
      const newFields = util.createRecordFields(count, this.metadataFields)
      this.recordData.push(...newFields)
    },
    handleFieldChange (field) {
      // 对输入字段进行校验，暂时 do nothing
      field.edit = false
    },
    handleCellEdit (row, column, test2) {
      row[column.label].edit = true
    },
    // 保存是对表的当页数据来说的
    handleSave () {
      this.$confirm('对于未填写的空数据将不会保存，请检查',
        '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
        let body = util.unwarpRecords(this.recordData, this.metadataFields)
        service.addOrUpdateRecord(body)
          .then(() => {
            this.$message.success('保存成功！')
            this.fetchMetadata()
          })
          .catch((error) => {
            this.$message.error(error.response)
          })
      }).catch(() => {
      })
    }
  }
}
</script>

<style scoped>

</style>
