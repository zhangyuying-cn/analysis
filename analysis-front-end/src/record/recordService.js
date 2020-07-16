import axios from 'axios'
import * as constants from '../metadata/constants'
class RecordService {
  id
  constructor (id) {
    this.id = id
  }
  setId (id) {
    this.id = id
  }
  // 调用后台方法
  async getAllRecord (limit, page) {
    let url = `/analysis/api/records`
    if (limit && page) {
      url += '?limit=' + limit + '&page=' + page
    }
    const response = await axios.get(url)
    if (response.status === 200) {
      return response.data.data
    }
    throw new Error(response.statusText)
  }

  async addOrUpdateRecord (record) {
    const response = await axios.put(
      `analysis/api/records`,
      record,
      {
        headers: { 'content-type': constants.CONTENT_TYPE_JSON }
      })
    if (response.status === 200) {
      return response.data
    }
    throw new Error(response.statusText)
  }
  async deleteRecord (recordId) {
    const response = await axios.delete(
      'analysis/api/records/' + recordId,
      {
        headers: { 'content-type': constants.CONTENT_TYPE_JSON }
      })
    if (response.status === 204) {
      return response.data
    }
    throw new Error(response.statusText)
  }
}
export default new RecordService()
