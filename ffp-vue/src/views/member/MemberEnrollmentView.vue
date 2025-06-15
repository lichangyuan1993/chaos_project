<script setup lang="ts" xmlns="http://www.w3.org/1999/html">

import { onMounted, reactive, ref } from "vue";
import type {
  Member, MemberFile,
  MemberIdentityDocument,
  MemberIdentityDocumentMap
} from "@/views/member/types/MemberInterface.d.ts";
import {
  MEMBER,
  MEMBER_IDENTITY_DOCUMENT
} from "@/views/member/dictionary/MemberDictionary.ts";
import type { FormItemProps, FormProps } from "element-plus";
import { request } from "@/utils/request.ts";

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

const memberFormState = reactive({
  data: reactive({ ...props?.member }),
  member: reactive({} as Member),
  memberIdentityDocument: reactive({} as MemberIdentityDocument),
  memberFile: reactive({} as MemberFile),
  memberIdentityDocumentMap: reactive({} as MemberIdentityDocumentMap)
} as Member);


const onSubmit = (event: Event) => {
  event.preventDefault();
  console.log("submit!");
  request("http://localhost:8080/member/create", memberFormState.data, "POST").then(res => {
    console.log(res);
  });
};

const onClear = (event: Event) => {
  event.preventDefault();
  console.log("clear!");

};

</script>

<template>
  <div class="enrollment">
    <form class="enrollment-form">
      <div class="enrollment-form__familyNameCn">
        <label> {{ MEMBER.familyNameCn }}：<input type="text" v-model="memberFormState.data.familyNameCn"></label>
      </div>
      <div class="enrollment-form__givenNameCn">
        <label> {{ MEMBER.givenNameCn }}：<input type="text"
                                                v-model="memberFormState.data.givenNameCn"></label>
      </div>
      <div class="enrollment-form__familyNameEn">
        <label> {{ MEMBER.familyNameEn }}：<input type="text"
                                                 v-model="memberFormState.data.givenNameEn"></label>
      </div>
      <div class="enrollment-form__givenNameEn">
        <label> {{ MEMBER.familyNameEn }}：<input type="text"
                                                 v-model="memberFormState.data.givenNameEn"></label>
      </div>
      <div class="enrollment-form__idType">
        <label> {{ MEMBER_IDENTITY_DOCUMENT.idType }}：<select type="text"
                                                              v-model="memberFormState.data.idType">
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


        </select></label>
      </div>
      <div class="enrollment-form__idNumber">
        <label> {{ MEMBER_IDENTITY_DOCUMENT.idNumber }}：<input type="text"
                                                               v-model="memberFormState.data.membershipNumber"></label>
      </div>
      <div class="enrollment-form__dateOfBirth">
        <label> {{ MEMBER.dateOfBirth }}：<input type="date"
                                                v-model="memberFormState.data.dateOfBirth"></label>
      </div>
      <div class="enrollment-form__gender">
        <label> {{ MEMBER.gender }}：<select type="text" v-model="memberFormState.data.gender">
          <option value="">请选择性别</option>
          <option value="M">男</option>
          <option value="F">女</option>
          <option value="U">未知</option>

        </select></label>
      </div>
      <div class="enrollment-form__nationality">
        <label> {{ MEMBER.nationality }}：<select type="text"
                                                 v-model="memberFormState.data.nationality">
          <option value="">请选择国籍</option>
          <option value="CN">中国</option>
          <option value="US">美国</option>
          <option value="SU">苏联</option>
          <option value="EU">欧洲</option>

        </select></label>
      </div>
      <div class="enrollment-form__membershipTier">
        <label> {{ MEMBER.membershipTier }}：<select type="text"
                                                    v-model="memberFormState.data.membershipTier">
          <option value="">请选择会员等级</option>
          <option value="STANDARD">普卡</option>
        </select>
        </label>
      </div>
      <div class="enrollment-form__file">
        <label>附件：<input type="file" :value="memberFormState.data.membershipTier" multiple>

        </input></label>
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
