package cn.edu.xmut.learningplatform;

import cn.edu.xmut.learningplatform.model.yilian;
import cn.edu.xmut.learningplatform.model.yilianResp;
import cn.edu.xmut.learningplatform.utils.JsonUtils;
import com.mysql.cj.log.Log;
import okhttp3.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class OkhttpTest {
    @Test
    public void test() throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");
        RequestBody body = RequestBody.create(mediaType, "name=e9bcc2bcbf6043c29f9fe9ed35ed8a98.pptx");
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
        yilian yilian = JsonUtils.fromJson(response.body().string(), yilian.class);
        System.out.println(yilian.getData().toString());
    }
    @Test
    public void test1() throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file","e9bcc2bcbf6043c29f9fe9ed35ed8a98.pptx",
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File("/Users/zwj/Downloads/e9bcc2bcbf6043c29f9fe9ed35ed8a98.pptx")))
                .addFormDataPart("name","e9bcc2bcbf6043c29f9fe9ed35ed8a98.pptx")
                .addFormDataPart("key","29397395/4d2c3f00-7d4c-11e5-af15-41bf63ae4ea0/1682169622706/e9bcc2bcbf6043c29f9fe9ed35ed8a98.pptx")
                .addFormDataPart("token","IoZIhomb90eKJFQSKSB7Eda7rMhezU_gaSxDXnS0:peQ3u2XFQyAT8_tZ6ax9OQu16Ho=:eyJtaW1lTGltaXQiOiIhdmlkZW8vbXAydDthcHBsaWNhdGlvbi9vY3RldC1zdHJlYW0iLCJmc2l6ZU1pbiI6MSwic2NvcGUiOiJtNy1pbS1rZWZ1OjI5Mzk3Mzk1LzRkMmMzZjAwLTdkNGMtMTFlNS1hZjE1LTQxYmY2M2FlNGVhMC8xNjgyMTY5NjIyNzA2L2U5YmNjMmJjYmY2MDQzYzI5ZjlmZTllZDM1ZWQ4YTk4LnBwdHgiLCJkZWFkbGluZSI6MTY4MjE3MzIyMn0=")
                .build();
        Request request = new Request.Builder()
                .url("https://up.qbox.me/")
                .method("POST", body)
                .build();
        Response response = client.newCall(request).execute();
        yilianResp yilianResp = JsonUtils.fromJson(response.body().string(), yilianResp.class);
        System.out.println(yilianResp.getKey());
    }
}
