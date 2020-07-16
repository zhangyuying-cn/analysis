export const BASE_FIELD_ATTR = ['key', 'name', 'defaultValue']
export const ENUM_BASE_ATTR = ['code', 'name', 'order']
export const ORGAN_BASE_ATTR = ['check']
const FIELD_ATTRIBUTES_STRING_TYPE = BASE_FIELD_ATTR.concat(['type'])
// const FIELD_ATTRIBUTES_BOOLEAN_TYPE = ['require', 'unique', 'valid']
// const FIELD_ATTRIBUTE_CZQX = 'czqx'
// const FIELD_ATTRIBUTE_ENCRYPTDATA = 'encryptData'

export const RECORD_TYPE = [
  {
    value: 'string',
    label: '字符串'
  }, {
    value: 'date',
    label: '日期'
  }, {
    value: 'enum',
    label: '枚举'
  }, {
    value: 'organ',
    label: '组织机构'
  }
]

export function createMetadataFields (count) {
  const fields = []
  for (let index = 0; index < count; index += 1) {
    fields.push(createMetadataField())
  }
  return fields
}
/**
 * 创建单个包装元数据字段
 */
function createMetadataField () {
  const field = {}
  FIELD_ATTRIBUTES_STRING_TYPE.forEach((attr) => {
    field[attr] = {
      value: '',
      edit: true
    }
  })
  // 类型默认是 string
  field.type.value = 'string'
  return field
}
/**
 * 包装元数据（将后台查询过来的元数据拼成前台格式）
 * @param {*} fields 服务器接口返回的元数据
 */
export function warpMetadataFields (fields) {
  const wrapedFields = []
  if (fields.stringField !== undefined && fields.stringField !== null) {
    warpStringField(fields.stringField)
    wrapedFields.push(...fields.stringField)
  }
  if (fields.enumField !== undefined && fields.enumField !== null) {
    warpEnumField(fields.enumField)
    wrapedFields.push(...fields.enumField)
  }
  if (fields.dateField !== undefined && fields.dateField !== null) {
    warpDateField(fields.dateField)
    wrapedFields.push(...fields.dateField)
  }
  // 无效的永远显示在后面
  // wrapedFields.sort((a, b) => {
  //   if (a.valid && !b.valid) {
  //     return -1
  //   }
  //   if (!a.valid && b.valid) {
  //     return 1
  //   }
  //   return 0
  // })
  return wrapedFields
}
function warpBaseField (fields, typeInfo) {
  fields.forEach((element) => {
    BASE_FIELD_ATTR.forEach((attr) => {
      element[attr] = {
        value: element[attr],
        edit: false
      }
    })

    // add type
    element.type = {
      value: typeInfo,
      edit: false
    }
  })
}

function warpStringField (stringFields) {
  warpBaseField(stringFields, 'string')
}

function warpDateField (dateFields) {
  warpBaseField(dateFields, 'date')
}

function warpEnumField (enumsFileds) {
  warpBaseField(enumsFileds, 'enum')
  enumsFileds.forEach((element) => {
    if (element.enums === undefined || element.enums.length === 0) {
      // do nothing
    } else {
      wrapFieldAttrEnums(element.enums)
    }
  })
}
function wrapFieldAttrEnums (enums) {
  enums.forEach((element) => {
    ENUM_BASE_ATTR.forEach((item) => {
      element[item] = {
        value: element[item],
        edit: false
      }
    })
  })
}
// 保存的时候 前台数据变成后台格式
export function unwarpMetadataFields (wrapedFields) {
  const fields = {
    stringField: [],
    enumField: [],
    dateField: []
  }
  wrapedFields.forEach((element) => {
    if (element.key.value !== '' && element.name.value !== '') {
      const field = {}
      BASE_FIELD_ATTR.forEach((attr) => {
        field[attr] = element[attr].value
      })
      if (element.type.value === 'string') {
        fields.stringField.push(field)
      }

      if (element.type.value === 'date') {
        fields.dateField.push(field)
      }

      if (element.type.value === 'enum') {
        if (element.enums !== undefined) {
          field.enums = unwarpFieldAttrEnums(element.enums)
        }
        fields.enumField.push(field)
      }
    } else {
      // do nothing
    }
  })

  return fields
}
function unwarpFieldAttrEnums (wrapedEnums) {
  const enums = []
  wrapedEnums.forEach((enumItem) => {
    const e = {}
    ENUM_BASE_ATTR.forEach((attr) => {
      e[attr] = enumItem[attr].value
    })
    enums.push(e)
  })
  return enums
}

// ---------- 枚举 start
export function createFieldEnums (count) {
  const enumItems = []
  for (let index = 0; index < count; index += 1) {
    enumItems.push(createFieldEnum())
  }
  return enumItems
}

export function createFieldEnum () {
  const enumItem = {}
  ENUM_BASE_ATTR.forEach((field) => {
    enumItem[field] = {
      value: '',
      edit: true
    }
  })

  return enumItem
}

/**
 * 检测包装后的字段中是否有重复的字段关键字和字段名称
 * @param {*} wrapedFields 需要检查的对象数组
 * @param checkAttrs 需要检查重复的对象的属性
 * @return {boolean} true 代表存在重复
 */
export function duplicationCheck (wrapedFields, checkAttrs) {
  let flag = false
  // fixme: n2 复杂度，后续想想办法能后减少复杂度
  wrapedFields.forEach((elementFirst, indexFirst) => {
    wrapedFields.forEach((elementSecond, indexSecond) => {
      if (indexFirst !== indexSecond) {
        checkAttrs.forEach((attr) => {
          if (elementFirst[attr].value === elementSecond[attr].value) {
            flag = true
          }
        })
      }
    })
  })
  return flag
}
// ---------- 枚举 end
