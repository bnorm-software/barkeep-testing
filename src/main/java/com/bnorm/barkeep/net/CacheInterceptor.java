package com.bnorm.barkeep.net;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;

public class CacheInterceptor implements Interceptor {

    public boolean refresh = false;

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (refresh) {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build();
        } else {
            request = request.newBuilder()
                             .cacheControl(new CacheControl.Builder().maxStale(Integer.MAX_VALUE, TimeUnit.SECONDS)
                                                                     .build())
                             .build();
        }
        return chain.proceed(request);
    }
}
