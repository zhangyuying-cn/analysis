import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import GenerateChart from '@/components/GenerateChart'
import ImportIssue from '@/components/ImportIssue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/hello',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/pie',
      name: 'GenerateChart',
      component: GenerateChart
    },
    {
      path: '/import',
      name: 'ImportIssue',
      component: ImportIssue
    }
  ]
})
