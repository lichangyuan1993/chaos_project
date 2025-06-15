<script setup lang="ts">
import { RouterView } from "vue-router";
import { provide, reactive, ref } from "vue";
import Navbar from "@/components/Navbar.vue";
import "@/assets/base.css";


const appState = reactive({
  title: "",
  onClickMenu(title: string) {
    appState.title = title;
    console.log("click menu", title);
  }
});


const menuTitle = ref("");

function updateMenuTitle(title: string) {
  console.log("updateMenuTitle", title);
  menuTitle.value = title;
}

provide("menuTitle", { menuTitle, updateMenuTitle });

</script>

<template>
  <div class="app">
    <div class="app__navbar">
      <Navbar />
    </div>
    <!--  内容区域   -->
    <div class="app__header">
      <div class="app__title">
        <!--        <h1>{{ menuTitle }}</h1>-->
        <h1>首页</h1>
      </div>
      <div class="app__search">
        <input type="text" placeholder="Search...">
        <input type="button" value="Search">
      </div>
    </div>
    <!--   主内容区   -->
    <div class="app__main">
      <RouterView />
    </div>
    <div class="app__footer">
      footer
    </div>
  </div>

</template>

<style lang="scss">
.app {
  display: grid;
  margin: 0;
  padding: 3px;
  width: 100vw;
  height: 100vh;
  grid-template-areas:
    "navbar  header "
    "navbar  main"
    "navbar footer";
  grid-gap: 3px 3px;
  grid-template-columns:200px 1fr;
  grid-template-rows: 3rem auto 1.5rem;
  background-color: var(--bg-color);
  color: var(--font-color);
  /* 直接子元素边框样式 */
  > * {
    border-radius: var(--button-radius);
    border: var(--button-border);
  }
  /* 导航栏样式 */
  &__navbar {
    grid-area: navbar;
    display: flex;
    padding: 1rem;
    flex-direction: column;
    background-color: var(--navbar-bg-color);
    color: var(--navbar-font-color);
    //background: linear-gradient(to bottom, var(--main-bg-deep-color), var(--main-bg-pale-color));
    /* 滚动条 */
    overflow: scroll;
    scrollbar-width: none;
    scrollbar-color: var(--navbar-bg-color) transparent;
  }


  /* 头部栏样式 */
  &__header {
    grid-area: header;
    background-color: var(--header-bg-color);
    color: var(--header-font-color);
  }

  &__header h1, h2, h3, h4, h5, h6 {
    margin: 4px 1rem 4px 2rem;
  }

  &__main {
    grid-area: main;
    background-color: var(--main-bg-color);
    //background: linear-gradient(to bottom, var(--main-bg-deep-color), var(--main-bg-pale-color));
    transition: background 1.2s ease;
    color: var(--main-font-color);
    padding: 1rem;
    overflow: auto;
  }

  &__footer {
    grid-area: footer;
    background-color: var(--footer-bg-color);
    color: var(--footer-font-color);
    padding: 0 2rem;
  }

}


</style>
