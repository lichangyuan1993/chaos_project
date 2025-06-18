// 文件转Base64的通用方法
// 文件转Base64的通用方法（异步）
export const fileToBase64 = (file: File): Promise<string> => {
  return new Promise((resolve, reject) => {
    const reader = new FileReader()

    reader.onload = () => {
      // 确保结果是字符串
      if (typeof reader.result === 'string') {
        // 分割字符串并取第二部分（纯Base64）
        const base64Data = reader.result.split(',')[1];
        resolve(base64Data)
      } else {
        reject(new Error('Failed to read file as Base64'))
      }
    }

    reader.onerror = () => {
      reject(reader.error || new Error('Unknown file read error'))
    }

    reader.readAsDataURL(file)
  })
}
