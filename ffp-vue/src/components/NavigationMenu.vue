<template>
  <el-menu
    default-active="2"
    class="el-menu-vertical-demo"
  >
    <el-menu-item index="0" @click="updateMenuTitle('首页')">
      <RouterLink to="/">Home</RouterLink>
    </el-menu-item>
    <el-sub-menu index="1">
      <template #title>
        <span>会员管理</span>
      </template>
        <el-menu-item index="1-1" @click="updateMenuTitle('会员入会')">
<!--        <el-menu-item index="1-1" >-->
          <RouterLink to="/member-enrollment">会员入会</RouterLink>
        </el-menu-item>
        <el-menu-item index="1-2" @click="updateMenuTitle('会员查询')">
<!--        <el-menu-item index="1-2">-->
          <RouterLink to="/member-search">会员查询</RouterLink>
        </el-menu-item>
        <el-menu-item index="1-3" @click="updateMenuTitle('信息变更')">
<!--        <el-menu-item index="1-3" >-->
          <RouterLink to="/member-profile">信息变更</RouterLink>
          </el-menu-item>
        <el-menu-item index="1-4" @click="updateMenuTitle('受益人管理')">
<!--        <el-menu-item index="1-4" >-->
          <RouterLink to="/member-nominee">受益人管理</RouterLink>
        </el-menu-item>
    </el-sub-menu>

    <el-menu-item index="2"  @click="updateMenuTitle('用户管理')">
        <RouterLink to="/user-management">用户管理</RouterLink>
    </el-menu-item>

    <el-menu-item index="3" disabled>
      <el-icon><document /></el-icon>
      <template #title>审计日志</template>
    </el-menu-item>

    <el-menu-item index="4" @click="updateMenuTitle('个人设置')">
      <el-icon><setting /></el-icon>
      <template #title>个人设置</template>
    </el-menu-item>
  </el-menu>
</template>

<script lang="ts" setup>
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'
import { RouterLink } from "vue-router";
import { inject, provide } from "vue";


const {menuTitle,updateMenuTitle } = inject('menuTitle');


//
</script>

<style>
.el-menu-vertical-demo {
  width: 100%;
  min-height: 100%;
  border-radius: inherit;
  border: 0;
}

</style>
