export const MEMBER = {
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

export const MEMBER_IDENTITY_DOCUMENT = {
  recId: '数据主键',
  memberRecId: '会员数据主键',
  idType: '证件类型',
  idNumber: '证件号码',
  countryOfIssue: '证件签发国家',
  issueDate: '证件签发日期',
  expireDate: '证件到期日',
  primaryInd: '是否主证件',
  verifyStatus: '证件验证状态',
  verifyDateTime: '证件验证时间',
  sequenceNumber: '序号',
  createTimestamp: '创建时间',
  updateTimestamp: '更新时间',
  createUser: '创建人',
  updateUser: '更新人',
  versionNumber: '版本号',
  deleted: '删除标识'
}
export const MEMBER_FILE = {
  recId: '数据主键',
  memberRecId: '会员数据主键',
  relationTable: '关联表',
  fileName: '文件名',
  fileType: '文件类型',
  fileSize: '文件大小',
  fileContent: '文件内容',
  uploadDateTime: '上传时间',
  createTimestamp: '创建时间',
  updateTimestamp: '更新时间',
  createUser: '创建人',
  updateUser: '更新人',
  versionNumber: '版本号',
  deleted: '删除标识'
}


export const MEMBER_ENTRIES = Object.entries(MEMBER)

export const MEMBER_KEYS = Object.keys(MEMBER)

export const MEMBER_TABLE_KEYS = [
  'memberId',
  'membershipNumber',
  'gender',
  'familyNameCn',
  'givenNameCn',
  'familyNameEn',
  'givenNameEn',
  'nationality',
  'enrollmentDate',
  'membershipTier',
  'membershipStatus',
];


