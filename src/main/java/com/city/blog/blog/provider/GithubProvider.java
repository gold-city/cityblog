package com.city.blog.blog.provider;


import com.alibaba.fastjson.JSON;
import com.city.blog.blog.dto.AccessTokenDTO;
import com.city.blog.blog.dto.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/3/4
 * Time: 15:12
 * Description: No Description
 *
 * 使用nohttpjava内访问地址拿到返回的数据
 */
@Component
public class GithubProvider {
    public String getAccessTokenDTO(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(60000, TimeUnit.MILLISECONDS)
                .readTimeout(60000, TimeUnit.MILLISECONDS)
                .build();
        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            //通过split方法获取字符串&的左右部分
            String[] split = string.split("&");
            String s = split[0];
            String split1 = s.split("=")[1];
            return split1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public GitHubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(60000, TimeUnit.MILLISECONDS)
                .readTimeout(60000, TimeUnit.MILLISECONDS)
                .build();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try{
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GitHubUser gitHubUser = JSON.parseObject(string, GitHubUser.class);
            return gitHubUser;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
