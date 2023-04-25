package cn.edu.xmut.learningplatform.utils;

import cn.edu.xmut.learningplatform.constant.ErrorCode;
import cn.edu.xmut.learningplatform.exception.GlobalException;
import cn.edu.xmut.learningplatform.model.yilian;
import cn.edu.xmut.learningplatform.model.yilianResp;
import okhttp3.*;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.InetAddress;
import java.util.UUID;
import java.util.regex.Pattern;

@Component
public class UploadFileUtil {

    private static final String PORT = PropertiesUtil.getProjectPort();
    private static final String URL_ROOT = "http://";

    private static final Pattern filePattern = Pattern.compile("/([^/]+)$");

    /**
     * 项目路径
     */
    private static final String CONTEXT_PATH = "";

    public static String uploadFile(MultipartFile multipartFile) {
        try {
            if (multipartFile.isEmpty()) {
                throw new GlobalException(ErrorCode.UPLOAD_ERROR);
            }
            // 获取文件的名称
            String originalFilename = multipartFile.getOriginalFilename();
            // 文件后缀 例如：.png
            String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            // uuid 生成文件名
            String uuid = String.valueOf(UUID.randomUUID());
            // 根路径，在 resources/static/upload
            String basePath = ResourceUtils.getURL("classpath:").getPath() + "static/upload/";
            // 新的文件名，使用uuid生成文件名
            String fileName = uuid + fileSuffix;
            // 创建新的文件
            File fileExist = new File(basePath);
            // 文件夹不存在，则新建
            if (!fileExist.exists()) {
                fileExist.mkdirs();
            }
            // 获取文件对象
            File file = new File(basePath, fileName);
            // 完成文件的上传
            multipartFile.transferTo(file);

            //如果是PPT
            if (fileSuffix.equals(".pptx")){
                return getPPTUrl(fileName, file);
            }

            // 返回绝对路径
            return URL_ROOT + InetAddress.getLocalHost().getHostAddress() + ":" + PORT + CONTEXT_PATH + "/upload/" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new GlobalException(ErrorCode.UPLOAD_ERROR);
    }


    private static String getPPTUrl(String fileName, File file) throws Exception {
        yilian yiLian = getYiLian(fileName);
        System.out.println(yiLian);
        return "https://fs-im-kefu.7moor-fs1.com/" + getYiLianResp(fileName, file, yiLian).getKey();
    }

    private static yilian getYiLian(String fileName) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");
        RequestBody body = RequestBody.create(mediaType, "name=" + fileName);
        Request request = new Request.Builder()
                .url("https://up.ly93.cc/")
                .method("POST", body)
                .addHeader("Accept", "application/json, text/javascript, */*; q=0.01")
                .addHeader("Origin", "https://up.ly93.cc")
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Referer", "https://up.ly93.cc/")
                .addHeader("Content-Length", "42")
                .addHeader("Host", "up.ly93.cc")
                .addHeader("Accept-Language", "zh-CN,zh-Hans;q=0.9")
                .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.4.1 Safari/605.1.15")
                .addHeader("Accept-Encoding", "gzip, deflate, br")
                .addHeader("Connection", "keep-alive")
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .build();
        Response response = client.newCall(request).execute();
        return JsonUtils.fromJson(response.body().string(), yilian.class);
    }

    private static yilianResp getYiLianResp(String fileName, File file, yilian yilian) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file", fileName,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                file))
                .addFormDataPart("name", yilian.getData().getName())
                .addFormDataPart("key", yilian.getData().getKey())
                .addFormDataPart("token", yilian.getData().getToken())
                .build();
        Request request = new Request.Builder()
                .url("https://up.qbox.me/")
                .method("POST", body)
                .build();
        Response response = client.newCall(request).execute();
        return JsonUtils.fromJson(response.body().string(), yilianResp.class);
    }
}

