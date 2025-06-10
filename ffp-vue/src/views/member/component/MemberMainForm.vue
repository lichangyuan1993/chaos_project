<template>
  <div>
    <el-form :label-position="labelPosition"
             label-width="auto"
             :model="memberFormState.data"
             style="max-width: 600px"
             class="demo-form-inline">
      <!--   中文姓   -->
      <el-form-item :label="MEMBER_DICTIONARY.familyNameCn">
        <el-input v-model="memberFormState.data.familyNameCn" placeholder="" clearable />
      </el-form-item>
      <!--   中文名   -->
      <el-form-item :label="MEMBER_DICTIONARY.givenNameCn">
        <el-input v-model="memberFormState.data.givenNameCn" placeholder="" clearable/>
      </el-form-item>
      <!--   英文姓   -->
      <el-form-item :label="MEMBER_DICTIONARY.familyNameEn">
        <el-input v-model="memberFormState.data.familyNameEn" placeholder="" clearable />
      </el-form-item>
      <!--   英文名   -->
      <el-form-item :label="MEMBER_DICTIONARY.givenNameEn">
        <el-input v-model="memberFormState.data.givenNameEn" placeholder="" clearable />
      </el-form-item>
      <!--   性别    -->
      <el-form-item :label="MEMBER_DICTIONARY.gender">
        <el-select v-model="memberFormState.data.gender" placeholder="" clearable>
          <el-option label="男" value="M" />
          <el-option label="女" value="F" />
          <el-option label="未知" value="U" />
          <el-option label="" value="" />
        </el-select>
      </el-form-item>

      <!--   国籍    -->
      <el-form-item :label="MEMBER_DICTIONARY.nationality">
        <el-select v-model="memberFormState.data.nationality" placeholder="" clearable>
          <el-option label="中国" value="cn" />
          <el-option label="美国" value="us" />
          <el-option label="苏联" value="su" />
          <el-option label="" value="" />
        </el-select>
      </el-form-item>

      <!--   入会时间    -->
      <el-form-item :label="MEMBER_DICTIONARY.enrollmentDate">
        <el-date-picker
          v-model="memberFormState.data.enrollmentDate"
          type="date"
          placeholder=""
          clearable
        />
      </el-form-item>

      <!--   会员等级    -->
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

      <!--   会员状态    -->
      <el-form-item :label="MEMBER_DICTIONARY.membershipStatus">
        <el-select v-model="memberFormState.data.membershipStatus" placeholder="" clearable>
          <el-option label="活跃" value="ACTIVE" />
          <el-option label="黑名单" value="BLACK" />
          <el-option label="被取消" value="CANCLE" />
          <el-option label="死亡" value="DEAD" />
          <el-option label="冻结" value="FREEZE" />
          <el-option label="" value="su" />
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">保存</el-button>
        <el-button type="primary" @click="onClose">关闭</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts" setup>
import { onMounted, reactive, ref } from "vue";
import type { Member } from '@/views/member/types/MemberInterface.d.ts'
import {
  MEMBER_KEYS,
  MEMBER_TABLE_KEYS,
  MEMBER_DICTIONARY,
} from '@/views/member/dictionary/MemberDictionary.ts'
import type { FormItemProps, FormProps } from "element-plus";
import { request } from "@/utils/request.ts";

const labelPosition = ref<FormProps['labelPosition']>('right')
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

onMounted(() => {
  console.log(props)

})



//  定义事件
const emit = defineEmits(['close']);

const onSubmit = () => {
  console.log('submit!')
  request("http://localhost:8080/member/update",  memberFormState.data, 'POST').then(res => {
    console.log(res)
    // 卸载当前组件
    emit('close');
  })
}
const onClose = () => {
  console.log('close!')
  // 卸载当前组件
  emit('close');

}
</script>

<style>
.demo-form-inline .el-input {
  --el-input-width: 220px;
}

.demo-form-inline .el-select {
  --el-select-width: 220px;
}
</style>
