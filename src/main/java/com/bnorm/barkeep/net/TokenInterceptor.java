package com.bnorm.barkeep.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;

public class TokenInterceptor implements Interceptor {

    private String token = null;

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        if (token != null) {
            builder.addHeader("Authorization", token);
        }

        okhttp3.Response response = chain.proceed(builder.build());

        String setToken = response.header("Set-Authorization");
        if (setToken != null) {
            token = setToken;
        }

        String clearToken = response.header("Clear-Authorization");
        if (clearToken != null) {
            token = null;
        }

        return response;
    }
}
