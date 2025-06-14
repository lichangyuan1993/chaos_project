<template>
  <el-menu
    default-active="2"
    class="el-menu-vertical-demo"
  >

    <RouterLink to="/">
      <el-menu-item index="0" @click="updateMenuTitle('首页')">Home</el-menu-item>
    </RouterLink>

    <el-sub-menu index="1">
      <template #title>
        <span>会员管理</span>
      </template>

      <RouterLink to="/member-enrollment">
        <el-menu-item index="1-1" @click="updateMenuTitle('会员入会')"> 会员入会</el-menu-item>
      </RouterLink>

      <RouterLink to="/member-search">
        <el-menu-item index="1-2" @click="updateMenuTitle('会员查询')">
          会员查询
        </el-menu-item>
      </RouterLink>


      <RouterLink to="/member-profile">
        <el-menu-item index="1-3" @click="updateMenuTitle('信息变更')">信息变更</el-menu-item>
      </RouterLink>


      <RouterLink to="/member-nominee">
        <el-menu-item index="1-4" @click="updateMenuTitle('受益人管理')">受益人管理</el-menu-item>
      </RouterLink>


      <RouterLink to="/user-management">
        <el-menu-item index="2" @click="updateMenuTitle('用户管理')">用户管理</el-menu-item>
      </RouterLink>
    </el-sub-menu>

    <el-menu-item index="3" disabled>
      <el-icon>
        <document />
      </el-icon>
      <template #title>审计日志</template>
    </el-menu-item>

    <el-menu-item index="4" @click="updateMenuTitle('个人设置')">
      <el-icon>
        <setting />
      </el-icon>
      <template #title>个人设置</template>
    </el-menu-item>
  </el-menu>
</template>

<script lang="ts" setup>
import {
  Document,
  Menu as IconMenu,
  Setting
} from "@element-plus/icons-vue";
import { RouterLink } from "vue-router";
import { inject, provide } from "vue";


const { menuTitle, updateMenuTitle } = inject("menuTitle");


//
</script>

<style>
.el-menu-vertical-demo {
  width: 100%;
  min-height: 100%;
  border-radius: inherit;
  border: 0;
  background-color: #C4D1C9;
  ul{
    background-color: #C4D1C9;
  }
}

</style>
