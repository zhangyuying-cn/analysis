import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import GenerateChart from '@/components/chart/GenerateChart'
import ImportIssue from '@/components/ImportIssue'
import Metadata from '@/components/metadata/Metadata'
import RecordMaintain from '@/components/record/RecordMaintain'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/hello',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/chart',
      name: 'GenerateChart',
      component: GenerateChart
    },
    {
      path: '/import',
      name: 'ImportIssue',
      component: ImportIssue
    },
    {
      path: '/metadata',
      name: 'Metadata',
      component: Metadata
    },
    {
      path: '/record',
      name: 'RecordMaintain',
      component: RecordMaintain
    }
  ]
})
