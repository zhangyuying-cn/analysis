import axios from 'axios'

export function getAnalysis (analysisType, successCb) {
  axios.get(`/analysis/api/analysis/${analysisType}`).then((response) => {
    if (response.status === 200) {
      successCb(response.data.data)
    }
  }).catch((error) => {
    // eslint-disable-next-line
    console.log(error);
  })
}
