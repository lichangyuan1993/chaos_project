<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import SidebarMenu from "@/components/SidebarMenu.vue";
import { inject, provide, reactive, ref } from "vue";


const appState = reactive({
  title: '',
  onClickMenu(title: string){
    appState.title = title
    console.log('click menu',title)
  }
})


const menuTitle = ref('')
function updateMenuTitle(title: string) {
  console.log('updateMenuTitle', title);
  menuTitle.value = title
}
provide('menuTitle', { menuTitle, updateMenuTitle })

</script>

<template>
  <div class="window">
    <div class="sidebar">
      <SidebarMenu />
    </div>
<!--  内容区域   -->
    <div class="content">
      <div class="content__header">
        <div class="content__title">
          <h1>{{ menuTitle }}</h1>
        </div>
        <div class="content__search">
          <input type="text" placeholder="Search...">
          <input type="button" value="Search">
        </div>
      </div>
<!--   主内容区   -->
      <div class="content__main">
        <RouterView />
      </div>
    </div>






  </div>



</template>

<style lang="scss">
.window{
  display: grid;
  grid-template-columns: 1fr 11fr;
  grid-template-rows: auto 11fr;
  grid-template-areas:
    "sidebar header"
    "sidebar content"
    "sidebar footer";
  height: 100vh;
  max-height: 100vh;
  width: 100vw;
  max-width: 100vw;
  .sidebar {
    grid-area: sidebar;
    background: #1e5489;
  }

  .content {
    grid-area: content;
    background: #f9f9f9;
  }
  .footer {
    grid-area: footer;
    background: skyblue;
  }

}


.content__header {
  grid-area: header;
  display: flex;
  background-color: #2c3e50;
  color: greenyellow;
  justify-content: space-between;
  align-items: flex-end;
  padding: 0.5rem;
}

.content__main {
  margin: 0.5rem 0 0 0;
}

</style>
