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

export interface MemberIdentityDocument {
  [key: string]: any
  recId: string
  memberRecId: string
  idType: string
  idNumber: string
  countryOfIssue: string
  issueDate: string
  expireDate: string
  primaryInd: string
  sequenceNumber: number
  verifyDateTime: string
  verifyStatus: string
  versionNumber: number
  createTimestamp: string
  updateTimestamp: string
  createUser: string
  updateUser: string
}

export interface MemberFile{
  [key: string]: any
  recId: string
  memberRecId: string
  relationTable: string
  fileName: string
  fileType: string
  fileSize: number
  fileContent: string
  uploadDateTime: string
  createTimestamp: string
  updateTimestamp: string
  createUser: string
  updateUser: string
  versionNumber: number
  deleted: string
}

export interface MemberRemarkDocumentMap {
  key: string,
  value: string,
}

export interface MemberPage {
  [key: string]: any
  list: Member[]
  pageNum: number
  pageSize: number
  total: number
  pages: number
}
