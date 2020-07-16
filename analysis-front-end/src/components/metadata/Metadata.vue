<template>
  <div>
    <div class="row justify-content-between my-3">
      <div class="col">
        <span class="d-inline-block text-muted lead font-weight-bold">
          元数据
        </span>
        <div
          class="d-inline-block ml-3"
          v-if="canEdit"
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
        v-if="canEdit"
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
  <!-- 元数据列表 start -->
    <el-table
      border
      style="width: 100%"
      max-height="800"
      size="small"
      empty-text="请点击 增加字段 按钮添加数据"
      :data="data"
      row-class-name="table-font bg-valid"
      header-row-class-name="table-header-font"
      @cell-dblclick="handleCellEdit"
    >

      <!--<el-table-column label="字段关键字" width="250" align="center">-->
        <!--<template slot-scope="scope">-->
          <!--&lt;!&ndash; 显示 &ndash;&gt;-->
          <!--<span v-show="!scope.row.key.edit">-->
              <!--{{ scope.row.key.value }}-->
          <!--</span>-->
          <!--&lt;!&ndash; 编辑 &ndash;&gt;-->
          <!--<div v-show="scope.row.key.edit">-->
            <!--<div class="float-left w-75">-->
              <!--<el-input size="small" v-model="scope.row.key.value"></el-input>-->
            <!--</div>-->
            <!--<div class="float-right text-right">-->
              <!--<el-button-->
                <!--@click.prevent="handleFieldChange(scope.row.key, '字段关键字', true)"-->
                <!--type="success"-->
                <!--size="mini"-->
                <!--v-show="scope.row.key.edit"-->
                <!--icon="el-icon-success"-->
              <!--&gt;</el-button>-->
            <!--</div>-->
          <!--</div>-->
        <!--</template>-->
      <!--</el-table-column>-->

      <el-table-column label="字段名称" width="250" align="center">
        <template slot-scope="scope">
          <!-- 显示 -->
          <span v-show="!scope.row.name.edit">{{ scope.row.name.value }}</span>

          <!-- 编辑 -->
          <div v-show="scope.row.name.edit">
            <div class="float-left w-75">
              <el-input size="small" v-model="scope.row.name.value"></el-input>
            </div>
            <div class="float-right text-right">
              <el-button
                @click.prevent="handleFieldChange(scope.row.name)"
                type="success"
                size="mini"
                icon="el-icon-success"
              >
              </el-button>
            </div>
          </div>
        </template>
      </el-table-column>

      <el-table-column label="字段类型" width="230" align="center">
        <template slot-scope="scope">
          <!-- 显示 -->
          <div v-show="!scope.row.type.edit">
            <field-tag :type="scope.row.type.value" />
          </div>
          <!-- 编辑 -->
          <div v-show="scope.row.type.edit">
            <div class="float-left w-75">
              <el-select
                size="small"
                :value="scope.row.type.value"
                v-model="scope.row.type.value"
                placeholder="字段类型"
              >
                <el-option
                  v-for="item in types"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                >
                </el-option>
              </el-select>
            </div>
            <div class="float-right text-right">
              <el-button
                @click.prevent="handleFieldChange(scope.row.type)"
                type="success"
                size="mini"
                icon="el-icon-success"
              >
              </el-button>
            </div>
          </div>
        </template>
      </el-table-column>

      <el-table-column label="默认值" width="250" align="center">
        <template slot-scope="scope">
          <!-- 显示 -->
          <span v-show="!scope.row.defaultValue.edit">
              {{ scope.row.defaultValue.value }}
            </span>

          <!-- 编辑 -->
          <div v-show="scope.row.defaultValue.edit">
            <div class="float-left w-75">
              <el-input size="small" v-model="scope.row.defaultValue.value">
              </el-input>
            </div>
            <div class="float-right text-right">
              <el-button
                @click.prevent="handleFieldChange(scope.row.defaultValue)"
                type="success"
                size="mini"
                icon="el-icon-success"
              >
              </el-button>
            </div>
          </div>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <!-- enum -->
          <div
            v-if="scope.row.type.value === 'enum'"
            class="d-inline"
          >
            <el-button
              :disabled="!canEdit"
              plain
              size="mini"
              type="primary"
              @click.prevent="handleAction('enum-enums', scope.row)"
            >
              添加枚举项
            </el-button>
          </div>

          <!-- common -->
          <el-button
            :disabled="!canEdit"
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

    <!-- 添加枚举项弹出框 start -->
    <el-dialog
      :visible.sync="enumDialog"
      :close-on-click-modal=false
      :close-on-press-escape=false
      width="60%"
      custom-class="metadata-dialog"
    >
      <div slot="title">
        <h4 class="text-muted">
          {{ enumDialogTitle }}
        </h4>
      </div>
      <metadata-enum :data="fieldEnums" @apply="handleApplyEnumChange" />
    </el-dialog>
  <!-- 添加枚举项弹出框 end -->
  </div>
</template>

<script>
import MetadataEnum from '../metadata/MetadataEnum'
import FieldTag from '../metadata/FieldTag'
import * as util from '../../metadata/metadataUtil'
import service from '../../metadata/metadataService'
export default {
  name: 'Metadata',
  data () {
    return {
      // metadataFieldArray: [],
      data: [],
      // 元数据是否可以编辑
      canEdit: true,
      types: util.RECORD_TYPE,

      // 添加枚举项
      enumDialog: false,
      enumDialogTitle: '',
      fieldEnums: []
    }
  },
  created () {
    this.fetchMetadata()
  },
  methods: {
    handleAddField (count) {
      const newFields = util.createMetadataFields(count)
      this.data.push(...newFields)
      // this.metadataFieldArray.push(...newFields)
    },
    handleFieldChange (field) {
      field.edit = false
    },
    handleApplyEnumChange (enums) {
      this.toggleEnumDialog()
      this.enumFieldHolder.enums = []
      this.enumFieldHolder.enums.push(...enums.filter(item => item.code.value !== '' && item.name.value !== ''))
    },
    toggleEnumDialog () {
      this.enumDialog = !this.enumDialog
    },

    handleSave () {
      const corpDuplication =
        util.duplicationCheck(this.data.filter(item => item.key.value !== '' && item.name.value !== ''),
          ['key', 'name'])
      if (corpDuplication) {
        this.$message.error('元数据中存在重复的项目')
        return
      }
      this.$confirm('对于没有确定关键字和名称的元数据将不会保存，请检查',
        '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
        let body = {}
        body.field = util.unwarpMetadataFields(this.data)
        // TODO 应该根据参数设置
        body.currentUse = true
        service.updateOrganizationMetadata(body)
          .then(() => {
            this.$message.success('保存成功！')
            this.fetchMetadata()
          })
          .catch((error) => {
            this.$message.error(error.response)
          })
      }).catch(() => {
      })
    },

    // TODO 删除
    handleAction (action, field, rowIndex) {
      switch (action) {
        case 'enum-enums':
          this.enumDialogTitle = `${field.name.value}字段枚举项`
          this.toggleEnumDialog()
          // 先清空，然后在插入
          this.fieldEnums = []
          if (field.enums === undefined) {
            this.$set(field, 'enums', [])
          }
          this.fieldEnums.push(...field.enums)
          // 记录所添加的字段
          this.enumFieldHolder = field
          break

        case 'delete':
          this.$confirm(`此操作将删除字段【${field.name.value}】？`, '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
            closeOnClickModal: false,
            closeOnPressEscape: false
          }).then(() => {
            this.data.splice(rowIndex, 1)
            // this.metadata.splice(rowIndex, 1)
          }).catch(() => {
          })
          break

        default:
          break
      }
    },

    fetchMetadata () {
      this.data = []
      // this.metadataFieldArray = []
      service.getMetadata()
        .then((data) => {
          let oneData = data[0]
          service.setId(oneData.id)
          this.data.push(...util.warpMetadataFields(oneData.field))
        })
        .catch((error) => {
          this.$message.error(error.response)
        })
    },
    handleCellEdit (row, column) {
      if (!this.canEdit) {
        this.$message({
          message: '没有权限执行此操作',
          type: 'warning'
        })
        return
      }
      switch (column.label) {
        case '默认值':
          row.defaultValue.edit = true
          break
        default:
          break
      }
    }

  },
  components: {
    FieldTag,
    MetadataEnum
  }

}
</script>

<style scoped>

</style>
