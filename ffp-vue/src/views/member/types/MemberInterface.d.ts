/***/
export interface Member {
  /**
   *  {{ member[key] }} 报错,TS 不允许任意字符串访问对象属性
   */
  [key: string]: any //✅ 添加这一行 在接口中添加 [key: string]: any 索引签名
  recId: string
  memberId: string
  membershipNumber: string
  gender: string
  dateOfBirth: string
  familyNameCn: string
  givenNameCn: string
  familyNameEn: string
  givenNameEn: string
  nationality: string
  enrollmentDate: string
  sourceChannel: string
  membershipTier: string
  membershipStatus: string
  tierEffectiveDate: string
  tierExpiryDate: string
  lifetimeEliteInd: string
  currentNauticalMile: number
  eligibleNauticalMile: number
  lifetimeNauticalMile: number
  nextNauticalMileExpiry: number
  currentTierQualMile: number
  currentTierQualSegment: number
  tierQualStartDate: string
  tierQualEndDate: string
  pinCode: string
  lastActivityDate: string
  createTimestamp: string
  updateTimestamp: string
  createUser: string
  updateUser: string
}

export interface MemberPage {
  list: Member[]
  pageNum: number
  pageSize: number
  total: number
  pages: number
}
