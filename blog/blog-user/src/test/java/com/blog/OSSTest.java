// package com.blog;

// import java.io.IOException;
// import java.nio.file.Paths;

// import org.junit.jupiter.api.Test;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.boot.test.context.SpringBootTest;

// import com.google.gson.Gson;
// import com.qiniu.common.QiniuException;
// import com.qiniu.http.Response;
// import com.qiniu.storage.Configuration;
// import com.qiniu.storage.Region;
// import com.qiniu.storage.UploadManager;
// import com.qiniu.storage.model.DefaultPutRet;
// import com.qiniu.storage.persistent.FileRecorder;
// import com.qiniu.util.Auth;

// import lombok.Data;

// @SpringBootTest
// @Data
// @ConfigurationProperties(prefix = "oss")
// public class OSSTest {
    
//     private String accessKey;
//     private String secretKey;
//     private String bucket;

//     @Test
//     public void testOSS() {
//         // 构造一个带指定 Region 对象的配置类
//         Configuration cfg = new Configuration(Region.autoRegion());
//         cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
//         cfg.resumableUploadMaxConcurrentTaskCount = 2; // 设置分片上传并发，1：采用同步上传；大于1：采用并发上传
//         // ...其他参数参考类注释

//         // ...生成上传凭证，然后准备上传
//         // String accessKey = "DbkCk9on0rWQbbEs2jyykrI3ReyVR4tCp-u5P-HP";
//         // String secretKey = "pWIcsLbaGRs-ZhGpsup2l_0BFV-x8075kecXvTMI";
//         // String bucket = "yusuaois-blog";
//         // 如果是Windows情况下，格式是 D:\\qiniu\\test.png
//         String localFilePath = "C:\\Users\\LHX\\Documents\\Code\\JavaWeb\\Blog\\blog-vue\\sg-blog-vue\\static\\img\\tou.png";
//         // 默认不指定key的情况下，以文件内容的hash值作为文件名
//         String key = "null";

//         Auth auth = Auth.create(accessKey, secretKey);
//         String upToken = auth.uploadToken(bucket);

//         String localTempDir = Paths.get(System.getenv("java.io.tmpdir"), bucket).toString();
//         try {
//             // 设置断点续传文件进度保存目录
//             FileRecorder fileRecorder = new FileRecorder(localTempDir);
//             UploadManager uploadManager = new UploadManager(cfg, fileRecorder);
//             try {
//                 Response response = uploadManager.put(localFilePath, key, upToken);


//                 // 解析上传成功的结果
//                 DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//                 System.out.println(putRet.key);
//                 System.out.println(putRet.hash);
//             } catch (QiniuException ex) {
//                 ex.printStackTrace();
//                 if (ex.response != null) {
//                     System.err.println(ex.response);

//                     try {
//                         String body = ex.response.toString();
//                         System.err.println(body);
//                     } catch (Exception ignored) {
//                     }
//                 }
//             }
//         } catch (IOException ex) {
//             ex.printStackTrace();
//         }

//     }
// }
