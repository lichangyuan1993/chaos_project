<script setup lang="ts" xmlns="http://www.w3.org/1999/html">

import { onMounted, reactive, ref } from "vue";
import type {
  Member, MemberFile,
  MemberIdentityDocument
} from "@/views/member/types/MemberInterface.d.ts";
import {
  MEMBER,
  MEMBER_IDENTITY_DOCUMENT
} from "@/views/member/dictionary/MemberDictionary.ts";
import type { FormItemProps, FormProps } from "element-plus";
import { request } from "@/utils/request.ts";
import { getFileExtension } from "@/utils/StringUtils.ts"
import {fileToBase64} from "@/utils/FileUtils.ts"

const labelPosition = ref<FormProps["labelPosition"]>("left");
const itemLabelPosition = ref<FormItemProps["labelPosition"]>("");
const formLabelAlign = reactive({
  name: "",
  region: "",
  type: ""
});

const props = defineProps({
  member: {} as Member
});

const enrollmentFormState = reactive({
  data: reactive({ ...props?.member }),
  member: reactive({} as Member),
  memberIdentityDocument: reactive({} as MemberIdentityDocument),
  memberFileList: reactive([] as MemberFile[] ),
  fileList: reactive([] as File[] )

});

const handleFileChange = async (event: Event) => {

  const input = event.target as HTMLInputElement
  if (!input.files) return
  // 清空现有文件列表
  // enrollmentFormState.memberFileList = []
  // enrollmentFormState.fileList = []

  // 处理每个文件
  for (const file of Array.from(input.files)) {
    try {
      enrollmentFormState.memberFileList.push({
        fileContent: await fileToBase64(file), // 存储Base64字符串
        fileName: file.name,
        fileType: getFileExtension(file.name),
        originalFile: file // 保留原始文件引用（可选）
      })
    } catch (error) {
      console.error('文件转换失败:', error)
    }
  }
  console.log("file change", enrollmentFormState.fileList);
  console.log("file change", enrollmentFormState.memberFileList);
}


/**
 * 表单状态赋值
 * @param event
 */
const onSubmit = (event: Event) => {
  event.preventDefault();
  console.log("submit!");
  const formData = new FormData();
  formData.append("member", new Blob([JSON.stringify(enrollmentFormState.member)],
    { type: 'application/json' }))
  formData.append("memberIdentityDocument", new Blob([JSON.stringify(enrollmentFormState.memberIdentityDocument)],
    { type: 'application/json' }))
  formData.append("memberFileList", new Blob([JSON.stringify(enrollmentFormState.memberFileList)],
    { type: 'application/json' }))
  request("http://localhost:8080/member/create", formData, "POST","multipart").then(res => {
    console.log(res);
  });
};

/**
 * 清除表单状态
 */
const onClear = (event: Event) => {
  event.preventDefault();
  console.log("clear!");
  Object.assign(enrollmentFormState.member,{})
  Object.assign(enrollmentFormState.memberIdentityDocument,{})
  Object.assign(enrollmentFormState.memberFileList,{})
  Object.assign(enrollmentFormState.fileList,{})

};

</script>

<template>
  <div class="enrollment">
    <form class="enrollment-form">
      <div class="enrollment-form__familyNameCn">
        <label> {{ MEMBER.familyNameCn }}：</label>
        <input type="text" v-model="enrollmentFormState.member.familyNameCn">
      </div>
      <div class="enrollment-form__givenNameCn">
        <label> {{ MEMBER.givenNameCn }}：</label>
        <input type="text" v-model="enrollmentFormState.member.givenNameCn">
      </div>
      <div class="enrollment-form__familyNameEn">
        <label> {{ MEMBER.familyNameEn }}：</label>
        <input type="text" v-model="enrollmentFormState.member.givenNameEn">
      </div>
      <div class="enrollment-form__givenNameEn">
        <label> {{ MEMBER.familyNameEn }}：</label>
        <input type="text" v-model="enrollmentFormState.member.givenNameEn">
      </div>
      <div class="enrollment-form__idType">
        <label> {{ MEMBER_IDENTITY_DOCUMENT.idType }}：</label>
        <select type="text" v-model="enrollmentFormState.memberIdentityDocument.idType">
          <option value="">请选择证件类型</option>
          <option value="ID">身份证</option>
          <option value="PASSPORT">护照</option>
          <option value="OTHER">其他</option>
          <option value="NATIONAL_ID">国家身份证</option>
          <option value="HONGKONG_ID">港澳身份证</option>
          <option value="TAIWAN_ID">台湾身份证</option>
          <option value="MILITARY_ID">军人身份证</option>
          <option value="POLICE_ID">警察身份证</option>
          <option value="SOLDIER_ID">军人身份证</option>
          <option value="OFFICIAL_ID"> Official ID</option>
        </select>
      </div>
      <div class="enrollment-form__idNumber">
        <label> {{ MEMBER_IDENTITY_DOCUMENT.idNumber }}：</label>
        <input type="text" v-model="enrollmentFormState.memberIdentityDocument.membershipNumber">
      </div>
      <div class="enrollment-form__dateOfBirth">
        <label> {{ MEMBER.dateOfBirth }}：</label>
        <input type="date" v-model="enrollmentFormState.member.dateOfBirth">
      </div>
      <div class="enrollment-form__gender">
        <label> {{ MEMBER.gender }}：</label>
        <select type="text" v-model="enrollmentFormState.member.gender">
          <option value="">请选择性别</option>
          <option value="M">男</option>
          <option value="F">女</option>
          <option value="U">未知</option>
        </select>
      </div>
      <div class="enrollment-form__nationality">
        <label> {{ MEMBER.nationality }}：</label>
        <select type="text" v-model="enrollmentFormState.member.nationality">
          <option value="">请选择国籍</option>
          <option value="CN">中国</option>
          <option value="US">美国</option>
          <option value="SU">苏联</option>
          <option value="EU">欧洲</option>
        </select>
      </div>
      <div class="enrollment-form__membershipTier">
        <label> {{ MEMBER.membershipTier }}：</label>
        <select type="text" v-model="enrollmentFormState.member.membershipTier">
          <option value="">请选择会员等级</option>
          <option value="STANDARD">普卡</option>
        </select>
      </div>
      <div class="enrollment-form__file">
        <label>附件：</label>
        <input type="file" @change="handleFileChange"  multiple>
        </input>
      </div>
      <div class="enrollment-form__button">
<!--        <div class="enrollment-form__submit-button">-->
          <button @click="onSubmit">
            入会
          </button>

<!--        </div>-->
<!--        <div class="enrollment-form__clear-button">-->
          <button @click="onClear">
            清空
          </button>
<!--        </div>-->

      </div>

    </form>
  </div>
</template>

<style lang="scss">
.enrollment {
  padding: 0;
  input, select {
    width: 220px;
  }
  button {
    width: 100px;
  }
}
.enrollment-form{
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-template-rows: auto auto auto auto;
  grid-template-areas:
  "familyNameCn givenNameCn familyNameEn"
   "givenNameEn idType idNumber"
  "dateOfBirth gender nationality"
  "membershipTier file ."
  ". . button"
;
}

.enrollment-form__familyNameCn {grid-area: familyNameCn;}
.enrollment-form__givenNameCn {grid-area: givenNameCn;}
.enrollment-form__familyNameEn {grid-area: familyNameEn;}
.enrollment-form__givenNameEn {grid-area: givenNameEn;}
.enrollment-form__idType {grid-area: idType;}
.enrollment-form__idNumber {grid-area: idNumber;}
.enrollment-form__dateOfBirth {grid-area: dateOfBirth;}
.enrollment-form__gender {grid-area: gender;}
.enrollment-form__nationality {grid-area: nationality;}
.enrollment-form__membershipTier {grid-area: membershipTier;}
.enrollment-form__file {grid-area: file;}
.enrollment-form__button {grid-area: button;}
.enrollment-form__submit-button {grid-area: submit-button;}
.enrollment-form__clear-button {grid-area: clear-button;}

.enrollment-form {
  padding: 0;
  div {
    display: flex;
    align-items: center;
  }
  label {
    width: 5.5rem;
    white-space: nowrap; /* 防止标签换行 */
  }
}

.demo-form-inline {
  .el-select {
    width: 220px;
  }

  .el-input {
    width: 220px;
  }

  .el-date-picker {
    width: 220px;
  }
}

</style>
