frontend：jdk21、gradle、springboot、mybaits、dm

backend：typtescript、sass、vue3、vite、element plus、


下一步问题：
1. 前端传递事件格式，后端解析时间格式，需要有弹性匹配
2. 入会上传附件
3. 会员信息修改，混合操作（新增证件，删除证件，以及包含文件上传），考虑使用`multipart/form-data`编码请求参数，把会员基础信息，会员证件信息，证件附件，分成不同表单项。
   这样方便增量编码，避免后端设计过于复杂的映射类，直接把会员信息摊平，降低编码和测试复杂度，也方便前端按照`基础信息`，`证件信息`做数据展示时，仅仅请求当前需要的数据，避免请求会员所有数据。

   2025年6月12日考虑使用嵌套的multipart/form-data构造请求参数，有两种比较适合的思路：

```typescript
interface  Customer {
  id: string;
  name: string;
  age: string;
}
interface IdDocument {
  id: string;
  fileName: string;
  fileType: string;
  originalName: string;
  file: File;
}

interface CustomerUpdate {
  customer: Customer;
  idDocuments: Array<IdDocument>;
}
```
#### 思路一：
   前端
```javascript
const combinedBlob = new Blob([
  JSON.stringify(customer),
  '\r\n',
  file
], { type: 'application/octet-stream' });

formData.append('combined', combinedBlob);
```
后端
```java
@PostMapping("/combined")
public ResponseEntity<?> handleCombinedUpload(@RequestParam("combined") MultipartFile combined) throws IOException {
    InputStream inputStream = combined.getInputStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

    String jsonDataLine = reader.readLine(); // 第一行是 JSON
    Customer customer = new ObjectMapper().readValue(jsonDataLine, Customer.class);

    // 剩下的内容是文件内容（或重新构造文件）
    // 这里只是示例，实际可能需要更复杂的处理
}
```
#### 思路二
使用FormData构造简单的multipart/form-data，不支持嵌套的FormData,不支持嵌套的multipart/form-data,仅仅支持String，Number，Blob，File

```javascript
  const formData = new FormData();

  // 添加 JSON 数据
  formData.append('customer', JSON.stringify(customer));
  formData.append('idDocuments', JSON.stringify(idDocument1));
  formData.append('idDocuments', JSON.stringify(idDocument2));
  formData.append('idDocumentFiles',file1,'file1Id');
  formData.append('idDocumentFiles',file2,'file2Id');
// 在IdDocument类型中添加唯一标识的ID与数据库表的ID区分开来，后端拿到数据后根据文件的唯一标识传递绑定到指定的IdDocument对象
// 这种方式后端会丢失文件的原始名字，需要请求参数保存文件的原始文件名
```
   
下一个小案例：

 - 部门管理：重点在后端数据处理上面，递归查询或者先查部门，按照部门查询人员（分页批次为10000条）

下下一个小案例：

- 银行方面的项目
- 规则引擎
- 流程引擎
