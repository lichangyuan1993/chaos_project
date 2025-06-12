<script setup lang="ts" xmlns="http://www.w3.org/1999/html">

import { onMounted, reactive, ref } from "vue";
import type { Member } from '@/views/member/types/MemberInterface.d.ts'
import { MEMBER_KEYS, MEMBER_TABLE_KEYS, MEMBER_DICTIONARY, } from '@/views/member/dictionary/MemberDictionary.ts'
import type { FormItemProps, FormProps } from "element-plus";
import { request } from "@/utils/request.ts";

const labelPosition = ref<FormProps['labelPosition']>('left')
const itemLabelPosition = ref<FormItemProps['labelPosition']>('')
const formLabelAlign = reactive({
  name: '',
  region: '',
  type: '',
})

const props = defineProps({
  member: {} as Member,
})

const memberFormState = reactive({
  data: reactive({ ...props?.member }),
} as Member)



const onSubmit = () => {
  console.log('submit!')
  request("http://localhost:8080/member/create",  memberFormState.data, 'POST').then(res => {
    console.log(res)
  })
}
</script>

<template>
  <div class="enrollment">
    <div class="enrollment-form">
      <el-form
        :label-position="labelPosition"
        label-width="auto"
        :model="memberFormState.data"
        style="max-width: 100%"
        class="demo-form-inline"
      >
        <el-row :gutter="20">
          <!-- 中文姓 -->
          <el-col :span="8">
            <el-form-item :label="MEMBER_DICTIONARY.familyNameCn">
              <el-input v-model="memberFormState.data.familyNameCn" placeholder="" clearable />
            </el-form-item>
          </el-col>

          <!-- 中文名 -->
          <el-col :span="8">
            <el-form-item :label="MEMBER_DICTIONARY.givenNameCn">
              <el-input v-model="memberFormState.data.givenNameCn" placeholder="" clearable />
            </el-form-item>
          </el-col>

          <!-- 英文姓 -->
          <el-col :span="8">
            <el-form-item :label="MEMBER_DICTIONARY.familyNameEn">
              <el-input v-model="memberFormState.data.familyNameEn" placeholder="" clearable />
            </el-form-item>
          </el-col>

          <!-- 英文名 -->
          <el-col :span="8">
            <el-form-item :label="MEMBER_DICTIONARY.givenNameEn">
              <el-input v-model="memberFormState.data.givenNameEn" placeholder="" clearable />
            </el-form-item>
          </el-col>

          <!-- 入会时间 -->
          <el-col :span="8">
            <el-form-item :label="MEMBER_DICTIONARY.dateOfBirth">
              <el-date-picker
                v-model="memberFormState.data.dateOfBirth"
                type="date"
                placeholder=""
                clearable
              />
            </el-form-item>
          </el-col>

          <!-- 性别 -->
          <el-col :span="8">
            <el-form-item :label="MEMBER_DICTIONARY.gender">
              <el-select v-model="memberFormState.data.gender" placeholder="" clearable>
                <el-option label="男" value="M" />
                <el-option label="女" value="F" />
                <el-option label="未知" value="U" />
                <el-option label="" value="" />
              </el-select>
            </el-form-item>
          </el-col>

          <!-- 国籍 -->
          <el-col :span="8">
            <el-form-item :label="MEMBER_DICTIONARY.nationality">
              <el-select v-model="memberFormState.data.nationality" placeholder="" clearable>
                <el-option label="中国" value="cn" />
                <el-option label="美国" value="us" />
                <el-option label="苏联" value="su" />
                <el-option label="" value="" />
              </el-select>
            </el-form-item>
          </el-col>

          <!-- 会员等级 -->
          <el-col :span="8">
            <el-form-item :label="MEMBER_DICTIONARY.membershipTier">
              <el-select v-model="memberFormState.data.membershipTier" placeholder="" clearable>
                <el-option label="小飞人卡" value="cn" />
                <el-option label="普卡" value="STANDARD" />
                <el-option label="银卡" value="us" />
                <el-option label="金卡" value="su" />
                <el-option label="白金卡" value="su" />
                <el-option label="成就白金" value="su" />
                <el-option label="尊享银卡" value="su" />
                <el-option label="尊享金卡" value="su" />
                <el-option label="终身白金" value="su" />
                <el-option label="" value="" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <!-- 提交按钮 -->
        <el-row :gutter="20">
          <el-col :span="24">
            <el-form-item>
              <el-button type="primary" @click="onSubmit">保存</el-button>
              <el-button type="primary" >清除</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>

  </div>
</template>

<style lang="scss">

.enrollment-form {
  padding: 2rem;
}

.demo-form-inline {
  .el-select{
    width: 220px;
  }
  .el-input{
    width: 220px;
  }
  .el-date-picker{
    width: 220px;
  }
}

</style>
