// 更健壮的处理方式，防止没有后缀名的情况
export const getFileExtension = (filename: string) => {
  const lastDotIndex = filename.lastIndexOf('.');
  return lastDotIndex > 0 ? filename.slice(lastDotIndex + 1).toLowerCase() : '';
}
