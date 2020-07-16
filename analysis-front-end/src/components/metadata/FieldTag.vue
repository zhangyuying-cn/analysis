<template>
  <el-tag
    :type="elTagType"
    :size="size">
    {{ message }}
  </el-tag>
</template>

<script>
import {
  FIELD_TYPE_DATE,
  FIELD_TYPE_ENUM,
  FIELD_TYPE_STRING,
  FIELD_TYPE_ORGAN,
  FIELD_TYPES
} from '../../metadata/constants'

export default {
  name: 'FieldTag',

  props: {
    type: {
      type: String,
      required: true,
      validator (value) {
        return FIELD_TYPES.indexOf(value) !== -1
      }
    },

    size: {
      type: String
    }
  },

  computed: {
    elTagType () {
      if (this.type === FIELD_TYPE_STRING) {
        return 'success'
      }
      if (this.type === FIELD_TYPE_DATE) {
        return 'info'
      }
      if (this.type === FIELD_TYPE_ENUM) {
        return ''
      }
      if (this.type === FIELD_TYPE_ORGAN) {
        return 'danger'
      }
      return ''
    },

    message () {
      if (this.type === FIELD_TYPE_STRING) {
        return '字符串'
      }
      if (this.type === FIELD_TYPE_DATE) {
        return '日期'
      }
      if (this.type === FIELD_TYPE_ENUM) {
        return '枚举'
      }
      return '错误的类型！！！'
    }
  }
}
</script>

<style scoped>

</style>
