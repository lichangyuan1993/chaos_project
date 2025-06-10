<script setup lang="ts" xmlns="http://www.w3.org/1999/html">
import { onMounted, reactive } from 'vue'
import type { Member, MemberPage } from '@/views/member/types/MemberInterface.d.ts'
import { request } from '@/utils/request'
import { fetchData } from '@/utils/fetch'
import { MEMBER_DICTIONARY, MEMBER_TABLE_KEYS } from '@/views/member/dictionary/MemberDictionary.ts'
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
  <div class="member-view">
    <div class="member-view__header">
      <h1>Member Management View</h1>
      <button @click="memberTableState.onClickGetMemberList">Get Member List</button>
      <button @click="memberTableState.onClickRefreshMemberList">Refresh Member List</button>
    </div>
    <div class="member-view__body">
      <!-- 会员列表表格数据 -->
      <div class="member-table">
        <!-- 会员列表 -->
        <el-table :data="memberTableState?.response?.list" style="width: 100%">
          <!--数据行-->
          <el-table-column
            v-for="(key, index) in MEMBER_TABLE_KEYS"
            :key="index"
            :label="MEMBER_DICTIONARY[key]"
            :prop="key"
          />

          <el-table-column align="right">
            <template #default="scope">
              <el-button
                size="small"
                @click="memberEditorState.onClickOpenEditor(scope.$index, scope.row)"
              >
                Edit
              </el-button>
              <el-button
                size="small"
                type="danger"
                @click="onClickOpenDeletor(scope.$index, scope.row)"
              >
                Delete
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>

      <!-- 会员主数据表格 -->
      <div class="member-form" v-if="memberEditorState.show">
        <MemberMainForm :member="memberEditorState.data"
                        @close="memberEditorState.onClickCloseEditor"
        />
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.member-view {
  width: 100%;
  background-color: #282828;
  color: #ffffff;

  &__header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 16px;
    background-color: #2c3e50;
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
