
export function createRecordFields (count, metadataFields) {
  const fields = []
  for (let index = 0; index < count; index += 1) {
    fields.push(createRecordField(metadataFields))
  }
  return fields
}
/**
 * 创建单个包装元数据字段
 */
function createRecordField (metadataFields) {
  const record = {}
  // 类型默认是 string
  // field.type.value = 'string'
  // TODO 需要设置上默认值
  metadataFields.forEach(field => {
    record[field.name] = {
      value: '',
      edit: true
    }
  })
  return record
}

/**
 * 元数据包装
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
  return wrapedFields
}
function warpBaseField (fields, typeInfo) {
  fields.forEach((element) => {
    // add type
    element.type = typeInfo
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
  // enumsFileds.forEach((element) => {
  //   if (element.enums === undefined || element.enums.length === 0) {
  //     // do nothing
  //   } else {
  //     wrapFieldAttrEnums(element.enums)
  //   }
  // })
}
// function wrapFieldAttrEnums (enums) {
//   enums.forEach((element) => {
//     ENUM_BASE_ATTR.forEach((item) => {
//       element[item] = {
//         value: element[item],
//         edit: false
//       }
//     })
//   })
// }

/**
 * 查询出来的records，进行包装
 */
export function warpRecords (records, metadataFields) {
  let wrapedRecords = []
  records.forEach(record => {
    let wrapedRecord = {}
    metadataFields.forEach(field => {
      wrapedRecord[field.name] = {
        value: record.recordData[field.name],
        edit: false
      }
    })
    wrapedRecord.sourceId = {value: record.id, edit: false}
    wrapedRecords.push(wrapedRecord)
  })
  return wrapedRecords
}

/**
 * 保存records之前先解包装
 */
export function unwarpRecords (wrapedFields, metadataFields) {
  let unwarpRecords = []
  wrapedFields.forEach((element) => {
    let recordData = {}
    // 全是空的数据应该不保存，需要做个判断
    let valueNotNull = false
    metadataFields.forEach(field => {
      if (!valueNotNull && (element[field.name].value && element[field.name].value !== '')) {
        valueNotNull = true
      }
      recordData[field.name] = element[field.name].value
    })
    if (valueNotNull) {
      let record = {}
      record.recordData = recordData
      if (element.sourceId && element.sourceId.value) {
        record.id = element.sourceId.value
      }
      unwarpRecords.push(record)
    }
  })

  return unwarpRecords
}
