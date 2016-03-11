package com.bnorm.barkeep.net;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;

public class CacheInterceptor implements Interceptor {

    public static final CacheControl DEFAULT = new CacheControl.Builder().maxStale(Integer.MAX_VALUE, TimeUnit.SECONDS)
                                                                         .build();

    public boolean refresh = false;

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (refresh) {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build();
        } else {
            request = request.newBuilder().cacheControl(DEFAULT).build();
        }
        return chain.proceed(request);
    }
}
