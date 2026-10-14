<template>
  <div class="manager-container">
    <div class="manager-header">
      <div class="manager-header-left">
        <img src="@/assets/imgs/logo.png" alt="">
        <div class="title">高校教材管理系统</div>
      </div>
      <div class="manager-header-center">
        <el-breadcrumb separator="/">
          <el-breadcrumb-item :to="{ path: '/manager/home' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>{{ router.currentRoute.value.meta.name }}</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <div class="manager-header-right">
        <el-dropdown style="cursor: pointer">
          <div style="padding-right: 20px; display: flex; align-items: center">
            <img style="width: 40px; height: 40px; border-radius: 50%;" :src="data.user.avatar" alt="">
            <span style="margin-left: 5px; color: white">
              {{ data.user.name }}
              (<span v-if="data.user.role === 'STUDENT'">学生</span>
                <span v-if="data.user.role === 'TEACHER'">教务员</span>
                <span v-if="data.user.role === 'ADMIN'">管理员</span>)
            </span><el-icon color="#fff"><arrow-down /></el-icon>
          </div>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="router.push('/manager/person')">个人资料</el-dropdown-item>
              <el-dropdown-item @click="router.push('/manager/password')">修改密码</el-dropdown-item>
              <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>
    <!-- 下面部分开始 -->
    <div style="display: flex">
      <div class="manager-main-left">
        <el-menu :default-active="router.currentRoute.value.path"
                 :default-openeds="['1', '2', '3', '4']"
                 router
        >
          <el-menu-item index="/manager/home">
            <el-icon><HomeFilled /></el-icon>
            <span>系统首页</span>
          </el-menu-item>
          <el-menu-item index="/manager/dataAnalysis"  v-if="data.user.role === 'ADMIN'">
            <el-icon><Histogram /></el-icon>
            <span>数据统计</span>
          </el-menu-item>
          <el-menu-item index="/manager/outStock"  v-if="data.user.role !== 'ADMIN'">
            <el-icon><Notebook /></el-icon>
            <span>教材发放</span>
          </el-menu-item>
          <el-menu-item index="/manager/feedback">
            <el-icon><ChatLineSquare /></el-icon>
            <span>反馈信息</span>
          </el-menu-item>
          <el-menu-item index="/manager/student" v-if="data.user.role === 'TEACHER'">
            <el-icon><User /></el-icon>
            <span>学生信息</span>
          </el-menu-item>
          <el-menu-item index="/manager/classInfo" v-if="data.user.role === 'TEACHER'">
            <el-icon><Grid /></el-icon>
            <span>班级信息</span>
          </el-menu-item>
          <el-sub-menu index="1" v-if="data.user.role === 'ADMIN'">
            <template #title>
              <el-icon><Notebook /></el-icon>
              <span>教材管理</span>
            </template>
            <el-menu-item index="/manager/textbookType">教材类型信息</el-menu-item>
            <el-menu-item index="/manager/textbook">教材信息</el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="2" v-if="data.user.role === 'ADMIN'">
            <template #title>
              <el-icon><Notebook /></el-icon>
              <span>库存管理</span>
            </template>
            <el-menu-item index="/manager/inventory">库存信息</el-menu-item>
            <el-menu-item index="/manager/inStock">入库信息</el-menu-item>
            <el-menu-item index="/manager/outStock">教材发放</el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="3" v-if="data.user.role === 'ADMIN'">
            <template #title>
              <el-icon><Tools /></el-icon>
              <span>系统管理</span>
            </template>
            <el-menu-item index="/manager/classInfo">班级信息</el-menu-item>
            <el-menu-item index="/manager/notice">系统公告</el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="4" v-if="data.user.role === 'ADMIN'">
            <template #title>
              <el-icon><User /></el-icon>
              <span>用户管理</span>
            </template>
            <el-menu-item index="/manager/student">学生信息</el-menu-item>
            <el-menu-item index="/manager/teacher">教务员信息</el-menu-item>
            <el-menu-item index="/manager/admin">管理员信息</el-menu-item>
          </el-sub-menu>
        </el-menu>
      </div>
      <div class="manager-main-right">
        <RouterView @updateUser="updateUser" />
      </div>
    </div>
    <!-- 下面部分结束 -->


  </div>
</template>

<script setup>
import { reactive } from "vue";
import router from "@/router/index.js";
import {ElMessage} from "element-plus";

const data = reactive({
  user: JSON.parse(localStorage.getItem('xm-user') || '{}')
})

const logout = () => {
  localStorage.removeItem('xm-user')
  router.push('/login')
}

const updateUser = () => {
  data.user =  JSON.parse(localStorage.getItem('xm-user') || '{}')
}

if (!data.user.id) {
  logout()
  ElMessage.error('请登录！')
}
</script>

<style scoped>
@import "@/assets/css/manager.css";
</style>