import axios from 'axios'
import * as constants from '../metadata/constants'
class MetadataService {
  id
  constructor (id) {
    this.id = id
  }
  setId (id) {
    this.id = id
  }
  // 调用后台方法
  async getMetadata () {
    const response = await axios.get(`/analysis/api/metadatas`)
    if (response.status === 200) {
      return response.data.data
    }
    throw new Error(response.statusText)
  }

  async updateOrganizationMetadata (metadata) {
    if (this.id !== undefined) {
      metadata.id = this.id
    }
    const response = await axios.put(
      `analysis/api/metadatas`,
      metadata,
      {
        headers: { 'content-type': constants.CONTENT_TYPE_JSON }
      })
    if (response.status === 200) {
      return response.data
    }
    throw new Error(response.statusText)
  }
}
export default new MetadataService()
