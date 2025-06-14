<script setup lang="ts">
import { RouterLink, RouterView } from "vue-router";
import NavigationMenu from "@/components/NavigationMenu.vue";
import { inject, provide, reactive, ref } from "vue";
import { Document, Setting } from "@element-plus/icons-vue";


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
  <div class="ui">
    <div class="ui__nav-menu">
      <NavigationMenu />

    </div>
    <!--  内容区域   -->
    <div class="ui__header">
      <div class="ui__title">
        <h1>{{ menuTitle }}</h1>
      </div>
      <div class="ui__search">
        <input type="text" placeholder="Search...">
        <input type="button" value="Search">
      </div>
    </div>
    <!--   主内容区   -->
    <div class="ui__body">
      <RouterView />
    </div>
    <div class="ui__footer">
      footer
    </div>
  </div>

</template>

<style lang="scss">
.ui {
  display: grid;
  grid-template-areas:
    "nav-menu header"
    "nav-menu body"
    "nav-menu footer";
  gap: 0.2rem;
  grid-template-columns:repeat(100, 1fr);
  grid-template-rows: 3rem 1fr 1.5rem;
  min-height: 100vh;

  &__nav-menu {
    grid-area: nav-menu;
    /*    background: #1e5489;*/
    grid-column: span 15;
    background-color: #3A6A70;

  }

  &__header {
    grid-area: header;
    grid-column: span 85;
    display: flex;
    background-color: #2c3e50;
    justify-content: space-between;
    align-items: center;
    padding: 1rem;
  }

  &__body {
    grid-area: body;
    background: #f9f9f9;
    grid-column: span 85;
  }

  &__footer {
    grid-area: footer;
    background: skyblue;
    grid-column: span 85;
  }

}

.ui > * {
  background-color: rgba(255, 255, 255, 0.2);
  border: 0 solid rgba(255, 255, 255, 0.8);
  border-radius: 8px;
  box-shadow: 0.5rem 0.5rem 1rem rgba(0, 0, 0, 0.2);
  transition: border-radius 0.3s ease;
}

</style>
