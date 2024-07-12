package com.fengwenyi.component.sample.example;

import okhttp3.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class OkHttpTests {

    @Test
    public void testUpload() {
        // 创建 OkHttpClient 实例
        OkHttpClient client = new OkHttpClient();

        // 创建要上传的文件
        File file = new File("D:\\download\\OIP-C.jpg");
        File file2 = new File("D:\\download\\OIP-C (1).jpg");

        // 设置请求的 URL
        String url = "http://localhost:8080/file/upload";

        // 创建 MediaType 对象
//        MediaType mediaType = MediaType.parse("image/jpg");
        MediaType mediaType = MediaType.parse("multipart/form-data; charset=utf-8");

        // 创建 RequestBody 对象用于文件上传
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("files", file.getName(), RequestBody.create(mediaType, file))
                .addFormDataPart("files", file2.getName(), RequestBody.create(mediaType, file2))
                // 如果需要添加其他表单数据，可以继续调用 addFormDataPart 方法
                .build();

        // 创建 Request 对象
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        // 发送请求并处理响应
        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
