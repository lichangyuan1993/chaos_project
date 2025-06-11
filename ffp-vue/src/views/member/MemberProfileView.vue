<script setup lang="ts" xmlns="http://www.w3.org/1999/html">
import { onMounted, reactive } from 'vue'
import type { Member, MemberPage } from '@/views/member/types/MemberInterface.d.ts'
import { request } from '@/utils/request'
import { fetchData } from '@/utils/fetch'
import { MEMBER_DICTIONARY, MEMBER_TABLE_KEYS } from '@/views/member/dictionary/MemberDictionary'
import MemberMainForm from '@/views/member/component/MemberMainForm.vue'
// 引入本地数据集
import MemberDateSet from '@/views/member/datum/member-list.json'

// action case: 获取会员列表
const memberTableState = reactive({
  request: {
    url: 'http://localhost:8080/member/list',
    param: reactive(<MemberPage>{
      pageNum: 1,
      pageSize: 2,
    }),
  },
  response: reactive(<MemberPage>{
    list: [],
    pageNum: 0,
    pageSize: 0,
    total: 0,
    pages: 0,
  }),
  onClickRefreshMemberList() {
    console.log('onClickRefresh', this)
    memberTableState.onClickGetMemberList()
  },
  onClickGetMemberList() {
    fetchData(this.request.url, this.request.param).then((data) => {
      // 一、响应式失效：reactive 不追踪对象整体替换
      /*
       * 这是在替换整个 data 对象的引用，而 Vue 的响应式系统无法检测到这种嵌套属性的整体替换，导致：
       * 1. 响应性丢失：视图不会更新。
       * 2. data 变成普通对象：不再是响应式的。
       * */
      // this.data = data;

      // 二、手动赋值，响应式有效
      // this.data.list = data.list;
      // this.data.pageNum = data.pageNum;
      // this.data.pageSize = data.pageSize;
      // this.data.total = data.total;
      // this.data.pages = data.pages;

      // 三、使用ref报错this.data, 然后展开this.data赋值
      // this.data.value = {...this.data.value, ...data}
      this.response = { ...this.response, ...data }
      console.log('clickGetMemberList', this)
    })
  },
})

const memberDetailState = reactive({
  request: reactive({
    url: 'http://localhost:8080/member/get',
    param: reactive(<Member>{}),
  }),
  response: reactive(<Member>{}),
  onClickGetMemberDetail(param: Member) {
    const requestParam = { recId: param.recId }
    request<Member>('http://localhost:8080/member/get', requestParam).then((data) => {
      this.response = { ...this.response, ...data }
    })
  },
})

const memberEditorState = reactive({
  data: reactive(<Member>{}),
  show: false,
  onClickOpenEditor(index: number, row: Member) {
    memberEditorState.data = { ...this.data, ...row }
    memberEditorState.show = true
    console.log('onClickOpenEditor', index, this)
  },
  onClickCloseEditor() {
    memberEditorState.show = false
  },
})

const onClickOpenEditor = (index: number, row: Member) => {
  console.log(index, row)
  // 传递数据给MemberMainForm组件
}
const onClickOpenDeletor = (index: number, row: Member) => {
  console.log(index, row)
}

onMounted(() => {
  console.log('memberEditorState', memberEditorState)
})
</script>

<template>
  <div class="member-profile-view">
    Member Profile View
  </div>
</template>

<style lang="scss" scoped>
.member-view {
  width: 100%;
  background-color: #282828;
  color: #1a1a1a;

  &__header {
    display: flex;
    justify-content: flex-end;
    align-items: center;
    padding: 16px;
    background-color: #f8f8f8;
    color: greenyellow;
  }

  &__member-table {
    overflow-x: auto;
    width: 100%;
    background-color: #1e5489;
    color: yellow;
  }
}
</style>
