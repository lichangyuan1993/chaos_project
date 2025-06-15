<script setup lang="ts">

import { RouterLink } from "vue-router";
</script>

<template>
 <div class="navbar">
   <RouterLink to="/">
     Home
   </RouterLink>

   <RouterLink to="/member-enrollment">
     会员入会
   </RouterLink>

   <RouterLink to="/member-search">
     会员查询
   </RouterLink>
   <RouterLink to="/member-profile">
     信息变更
   </RouterLink>
   <RouterLink to="/member-nominee">
     受益人管理
   </RouterLink>
   <RouterLink to="/user-management">
     用户管理
   </RouterLink>
   <routerLink to="/user-settings">
     个人设置
   </routerLink>
 </div>
</template>

<style lang="scss">
.navbar {
  display: flex;
  flex-direction: column;
  background-color: var(--navbar-bg-color);
  color: var(--navbar-font-color);
  /* 滚动条 */
  overflow: scroll;
  scrollbar-width: none;
  scrollbar-color: var(--navbar-bg-color) transparent;
  /* WebKit 浏览器滚动条定制 */
  & ::-webkit-scrollbar {
    width: 8px;
    height: 8px;
  }

  /* 隐藏滚动条的头尾按钮 */
  & ::-webkit-scrollbar-button {
    display: none !important;
    width: 0 !important;
    height: 0 !important;
  }

  & ::-webkit-scrollbar-track {
    background: transparent;
    border-radius: 4px;
  }

  & ::-webkit-scrollbar-thumb {
    background: transparent;
    border-radius: 4px;
    border: 2px solid transparent;
    background-clip: padding-box;
  }

  & ::-webkit-scrollbar-thumb:hover {
    background: transparent;
  }

  &  a {
    padding: 6px 1rem;
    margin: 4px 0;
    color: var(--navbar-font-color);
  }

  & a:hover {
    box-shadow: 0 4px 8px rgba(93, 143, 149, 0.25);
    border: 2px solid var(--border-color);
    background: linear-gradient(135deg, #7BA7AB, #5D8F95);
    transition: all 0.4s; /* 统一过渡 */
  }


}


</style>
