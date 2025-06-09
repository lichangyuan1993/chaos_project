import { fetchData } from './fetch'

export async function request<T>(
  url: string,
  param?: Record<string, any>,
  method?: 'POST' | 'GET',
  contentType?: 'json' | 'form' | 'multipart',
  headers: Record<string, string> = {},
  body: string | FormData | undefined = undefined,
): Promise<T> {
  // 请求前拦截（如添加 token）
  const token: string | null = localStorage.getItem('token')
  if (token) {
    // 可以扩展 fetchRemoteData 的 headers 参数来支持
    headers['Authorization'] = token
  }

  let response = await fetchData(url, param, method, contentType, headers);

  // 这是 JavaScript 的 空值合并运算符（Nullish Coalescing Operator）。
  return  response ?? {} as T
}



// const response = await fetch(url, {
//   method: "POST",
//   headers: {
//     "Content-Type": "application/x-www-form-urlencoded"
//   },
//   body: new URLSearchParams({ ...param })
// });
// console.log('fetch', response)
// const data = await response.json()
