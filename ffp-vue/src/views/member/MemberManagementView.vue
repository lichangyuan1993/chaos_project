<script setup lang="ts" xmlns="http://www.w3.org/1999/html">
import { reactive } from "vue";
import type { Member, MemberPage } from "@/views/member/types/MemberInterface.d.ts";
import { request } from "@/utils/request";
import { fetchData } from "@/utils/fetch";
import { memberDict, memberTableHeaderKeys } from "@/views/member/dictionary/MemberDictionary.ts";
// 定义一个请求后端的接口

//
// const members = reactive({
//   webRequest: {
//     listMember: memberList(),
//     // memberDetail: getMember(),
//     // updateMember: updateMember(),
//     // deleteMember: deleteMember(),
//     // createMember: createMember(),
//   }
// });

// action case: 获取会员列表
const fetchMemberList = reactive({
  request: {
    url: "http://localhost:8080/member/list",
    param: reactive(<MemberPage>{
      pageNum: 1,
      pageSize: 2
    })
  },
  response: reactive(<MemberPage>{
    list: [],
    pageNum: 0,
    pageSize: 0,
    total: 0,
    pages: 0
  }),
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
      this.response = { ...this.response, ...data };
      console.log("clickGetMemberList", this);
    });
  }
});

const fetchMemberDetail = reactive({
  request: reactive({
    url: "http://localhost:8080/member/get",
    param: reactive(<Member>{})
  }),
  response: reactive(<Member>{}),
  onClickGetMemberDetail(param: Member) {
    const requestParam = { recId: param.recId};
    request<Member>("http://localhost:8080/member/get",requestParam).then((data) => {
      this.response = { ...this.response, ...data };
    });
  }
});

import { computed, ref } from 'vue'

interface User {
  date: string
  name: string
  address: string
}

const search = ref('')
const filterTableData = computed(() =>
  tableData.filter(
    (data) =>
      !search.value ||
      data.name.toLowerCase().includes(search.value.toLowerCase())
  )
)
const handleEdit = (index: number, row: User) => {
  console.log(index, row)
}
const handleDelete = (index: number, row: User) => {
  console.log(index, row)
}

const tableData: User[] = [
  {
    date: '2016-05-03',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-02',
    name: 'John',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-04',
    name: 'Morgan',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Jessy',
    address: 'No. 189, Grove St, Los Angeles',
  },
]
</script>

<template>
  <div class="member-view">
    <div class="member-view__header">
      <h1>Member Management View</h1>
      <button @click="fetchMemberList.onClickGetMemberList">Get Member List</button>
    </div>

    <div class="member-view__body">
      <table class="member-view__table">
        <!-- 表头 -->
        <thead>
          <tr>
            <th v-for="(key, index) in memberTableHeaderKeys" :key="index">
              {{ memberDict[key] }}
            </th>
          </tr>
        </thead>

        <!-- 表体 -->
        <tbody>
          <tr v-for="(member, index) in fetchMemberList.response.list" :key="index">
            <td
              v-for="(key, idx) in memberTableHeaderKeys"
              :key="idx"
              @click="fetchMemberDetail.onClickGetMemberDetail(member)"
            >
              {{ member[key] }}
            </td>
          </tr>
        </tbody>
      </table>
      <br/>
      <el-table :data="filterTableData" style="width: 100%">
        <el-table-column label="Date" prop="date" />
        <el-table-column label="Name" prop="name" />
        <el-table-column align="right">
          <template #header>
            <el-input v-model="search" size="small" placeholder="Type to search" />
          </template>
          <template #default="scope">
            <el-button size="small" @click="handleEdit(scope.$index, scope.row)">
              Edit
            </el-button>
            <el-button
              size="small"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
            >
              Delete
            </el-button>
          </template>
        </el-table-column>
      </el-table>
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

  &__body {
    overflow-x: auto;
    width: 100%;
    background-color: #1e5489;
    color: yellow;
  }
}

/*.member-view__body {
  width: 100%;
  border-collapse: collapse;
  table-layout: fixed;
  min-width: 800px;
}*/

.member-view__body th,
.member-view__body td {
  padding: 12px;
  border: 1px solid #eaeaea;
  text-align: left;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.member-view__body th {
  /* background-color: #f9f9f9;*/
  font-weight: bold;
}
</style>
