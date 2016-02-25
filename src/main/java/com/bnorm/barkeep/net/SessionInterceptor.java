package com.bnorm.barkeep.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;

public class SessionInterceptor implements Interceptor {

    private String sessionId = null;

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        if (sessionId != null) {
            builder.addHeader("Cookie", sessionId);
        }

        okhttp3.Response response = chain.proceed(builder.build());

        String setCookie = response.header("Set-Cookie");
        if (setCookie != null) {
            sessionId = setCookie.split(";")[0];
        }

        return response;
    }
}
