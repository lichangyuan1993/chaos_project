export const memberDict: Record<string, string> = {
  recId: '数据主键',
  memberId: '会员编号',
  membershipNumber: '会员卡号',
  gender: '性别',
  dateOfBirth: '生日',
  familyNameCn: '中文姓',
  givenNameCn: '中文名',
  familyNameEn: '英文姓',
  givenNameEn: '英文名',
  nationality: '国籍',
  enrollmentDate: '注册日期',
  sourceChannel: '来源渠道',
  membershipTier: '会员等级',
  membershipStatus: '会员状态',
  tierEffectiveDate: '等级生效日',
  tierExpiryDate: '等级过期日',
  lifetimeEliteInd: '终身精英标识',
  currentNauticalMile: '当前航程里程',
  eligibleNauticalMile: '可用航程里程',
  lifetimeNauticalMile: '累计航程里程',
  nextNauticalMileExpiry: '下一次里程过期数',
  currentTierQualMile: '当前等级达标里程',
  currentTierQualSegment: '当前等级达标航段',
  tierQualStartDate: '等级达标开始日',
  tierQualEndDate: '等级达标结束日',
  pinCode: '安全码',
  lastActivityDate: '最后活动时间',
  createTimestamp: '创建时间',
  updateTimestamp: '更新时间',
  createUser: '创建人',
  updateUser: '更新人',
}


export const memberEntries = Object.entries(memberDict)

export const memberKeys = Object.keys(memberDict)

export const memberTableHeaderKeys = Object.keys(memberDict).filter((key, index) => 0 < index && index < 15  )

