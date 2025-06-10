import {formatLocalISO} from '@/utils/datetime.ts'
// 创建一个获取会员列表的函数
export async function fetchData(
  url: string,
  param: Record<string, any> = {},
  method: "POST" | "GET" = "POST",
  contentType: "json" | "form" | "multipart" = "form",
  headers: Record<string, string> = {},
  body: string | FormData | undefined = undefined
): Promise<any> {
  console.log("fetch", method, url, param);

  // 判断Date对象，格式化为字符串 yyyy-MM-dd'T'HH:mm:ss.sssZ
  for(const key in param){
    if (param[key] instanceof Date) {
      param[key] = formatLocalISO(param[key]);
      console.log("Date", key, param[key]);
    }
  }

  // 根据contentType设置请求头和请求体
  if (contentType === "json") {
    headers["Content-Type"] = "application/json";
    body = JSON.stringify(param);
  } else if (contentType === "form") {
    headers["Content-Type"] = "application/x-www-form-urlencoded";
    const filteredParam = Object.keys(param).reduce((acc, key) => {
      const value = param[key];
      if (value !== null && value !== undefined) {
        acc[key] = value;
      }
      return acc;
    }, {} as Record<string, any>);
    body = new URLSearchParams(filteredParam).toString();
  } else if (contentType === "multipart") {
    headers["Content-Type"] = "multipart/form-data";
    const formData = new FormData();
    for (const key in param) {
      if (param.hasOwnProperty(key)) {
        if (param[key] === null || param[key] === undefined) {
          continue
        }
        const value = param[key];
        if (value instanceof File) {
          formData.append(key, value);
        } else {
          formData.append(key, String(value));
        }
      }
    }
    body = formData;
    delete headers["Content-Type"];
  } else {
    throw new Error(`Unsupported content type: ${contentType}`);
  }

  // 发送http请求
  const response: Response = await fetch(url, { method, headers, body });

  // 解析响应
  let responseData;
  try {
    responseData = await response.json();
  } catch (e) {
    // 当后端未遵守规范时兜底处理
    if (response.ok) {
      responseData = {}; // 将成功请求的空响应视为null
    } else {
      throw new Error(`解析失败: ${response.status}`);
    }
  }
  // 返回响应体
  return responseData ?? {};

}
