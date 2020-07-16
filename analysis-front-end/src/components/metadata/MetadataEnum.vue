<template>
  <div class="container-fluid">
    <div class="row">
      <div class="col-12">
        <el-table
          style="width: 100%"
          size="small"
          max-height="400"
          :data="data"
          @cell-dblclick="handleCellEdit"
        >
          <el-table-column label="代码" align="center">
            <template slot-scope="scope">
              <!-- 显示 -->
              <span
                v-show="!scope.row.code.edit">{{ scope.row.code.value }}</span>

              <!-- 编辑 -->
              <div v-show="scope.row.code.edit">
                <div class="float-left w-75">
                  <el-input size="small"
                            v-model="scope.row.code.value"></el-input>
                </div>
                <div class="float-right text-right">
                  <el-button
                    @click.prevent="handleFieldChange(scope.row.code, '代码', true)"
                    type="success"
                    size="mini"
                    icon="el-icon-success"
                  >
                  </el-button>
                </div>
              </div>
            </template>
          </el-table-column>

          <el-table-column label="显示名称" align="center">
            <template slot-scope="scope">
              <!-- 显示 -->
              <span
                v-show="!scope.row.name.edit">{{ scope.row.name.value }}</span>

              <!-- 编辑 -->
              <div v-show="scope.row.name.edit">
                <div class="float-left w-75">
                  <el-input size="small"
                            v-model="scope.row.name.value"></el-input>
                </div>
                <div class="float-right text-right">
                  <el-button
                    @click.prevent="handleFieldChange(scope.row.name, '显示名称', true)"
                    type="success"
                    size="mini"
                    icon="el-icon-success"
                  >
                  </el-button>
                </div>
              </div>
            </template>
          </el-table-column>

          <el-table-column label="顺序" align="center">
            <template slot-scope="scope">
              <!-- 显示 -->
              <span
                v-show="!scope.row.order.edit">{{ scope.row.order.value }}</span>

              <!-- 编辑 -->
              <div v-show="scope.row.order.edit">
                <div class="float-left w-75">
                  <el-input size="small"
                            v-model="scope.row.order.value"></el-input>
                </div>
                <div class="float-right text-right">
                  <el-button
                    @click.prevent="handleFieldChange(scope.row.order)"
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
              <el-button
                plain
                size="mini"
                type="danger"
                icon="el-icon-delete"
                @click.prevent="handleEnumItemDelete(scope.$index)"
              ></el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="col-12 text-right mt-3">
        <button
          class="btn btn-sm btn-primary"
          @click.prevent="handleAddEnumItem(1)"
        >
          增加枚举项
        </button>
        <button
          class="btn btn-sm btn-primary"
          @click.prevent="handleSaveChange"
        >
          确定
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import { createFieldEnums, duplicationCheck } from '../../metadata/metadataUtil'

export default {
  name: 'MetadataEnum',

  props: {
    data: {
      type: Array,
      default () {
        return []
      }
    }
  },

  data () {
    return {
      existCode: [],
      existName: []
    }
  },

  methods: {
    handleCellEdit (row, column) {
      if (['代码'].indexOf(column.label) !== -1) {
        // this.$warning(`${column.label}不可修改`);
        // do nothing
      } else {
        switch (column.label) {
          case '显示名称':
            row.name.edit = true
            break

          case '顺序':
            row.order.edit = true
            break

          default:
            break
        }
      }
    },

    handleFieldChange (field, fieldName, once) {
      // 没有任何值的情况
      if (once && field.value === '') {
        this.$warning(`请先输入${fieldName}，然后再确定`)
        return
      }

      // 填的值冲突的情况
      if (fieldName === '代码' &&
          this.existCode.indexOf(field.value) !== -1) {
        this.$error('代码已经存在，请勿重复命名')
        return
      }
      if (fieldName === '显示名称' &&
          this.existName.indexOf(field.value) !== -1) {
        this.$error('显示名称已经存在，请务重复命名')
        return
      }

      field.edit = false
    },

    handleAddEnumItem (count) {
      const newEnums = createFieldEnums(count)
      let index = this.data.length + 1
      newEnums.forEach((item) => {
        item.order.value = index
        index += 1
        // 赋值了，那就默认不进行修改
        item.order.edit = false
      })
      this.data.push(...newEnums)
    },

    handleSaveChange () {
      const isDuplication =
          duplicationCheck(this.data.filter(item => item.code.value !== '' && item.name.value !== ''),
            ['code', 'name'])
      if (isDuplication) {
        this.$error('存在代码重复的枚举项')
      } else {
        this.cleanRecorder()
        this.$emit('apply', this.data)
      }
    },

    handleEnumItemDelete (index) {
      this.data.splice(index, 1)
    },

    cleanRecorder () {
      this.existCode = []
      this.existName = []
    }

  },

  updated () {
    this.cleanRecorder()
    this.data.forEach((element) => {
      // 当枚举项的值不为且不可编辑的时候，进行记录
      if (element.code.value !== '' && element.code.edit === false) {
        this.existCode.push(element.code.value)
      }
      if (element.name.value !== '' && element.name.edit === false) {
        this.existName.push(element.name.value)
      }
    })
  }
}
</script>
