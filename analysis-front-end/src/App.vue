<template>
  <div id="app">
    <!--<img src="./assets/logo.png">-->
    <!-- 组织机构管理 -->
    <header>
      <div>
        <div class="d-inline-block float-left ml-5">
          <router-link  class="" to="/">
            <!-- 模块链接 -->
            <span class="">回到首页</span>
          </router-link>
        </div>
        <div class="d-inline-block float-left ml-5">
          <router-link  class="" to="/metadata">
            <!-- 模块链接 -->
            <span class="">元数据定义</span>
          </router-link>
        </div>
        <div class="d-inline-block float-left ml-5">
          <router-link  class="" to="/record">
            <!-- 模块链接 -->
            <span class="">记录维护</span>
          </router-link>
        </div>
        <div class="d-inline-block float-left ml-5">
          <router-link  class="" to="/chart">
            <!-- 模块链接 -->
            <span class="">统计图生成</span>
          </router-link>
        </div>
        <div class="d-inline-block ml-5">
          <router-link  class="" to="/import">
          <!-- 模块链接 -->
          <span class="">数据导入</span>
        </router-link>
        </div>
      </div>
    </header>
    <main class="mt-5">
      <!---路由-->
      <router-view/>
    </main>
  </div>
</template>

<script>
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
