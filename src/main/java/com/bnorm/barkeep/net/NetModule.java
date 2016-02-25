package com.bnorm.barkeep.net;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

    private final HttpUrl httpUrl;

    public NetModule(HttpUrl httpUrl) {
        this.httpUrl = httpUrl;
    }

    @Provides
    Cache provideOkHttpCache() {
        File file = new File("http");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(file, cacheSize);
        return cache;
    }

    @Provides
    OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cache(cache);
        builder.addInterceptor(new CacheInterceptor());
        builder.addInterceptor(new SessionInterceptor());
        builder.addInterceptor(new TokenInterceptor());
        builder.addInterceptor(new WireTraceInterceptor());
        return builder.build();
    }

    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        builder.baseUrl(httpUrl);
        builder.client(okHttpClient);
        return builder.build();
    }

    @Provides
    BarkeepService provideBarkeepService(Retrofit retrofit) {
        return retrofit.create(BarkeepService.class);
    }


    private static class CacheInterceptor implements Interceptor {

        public boolean refresh = false;

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (refresh) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build();
            } else {
                request = request.newBuilder().cacheControl(new CacheControl.Builder().onlyIfCached().build()).build();
            }
            return chain.proceed(request);
        }
    }


    private static class SessionInterceptor implements Interceptor {

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


    private static class TokenInterceptor implements Interceptor {

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


    public static class WireTraceInterceptor implements Interceptor {

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            RequestBody requestBody = request.body();
            if (requestBody != null) {
                if (requestBody.contentType() != null && requestBody.contentType().charset() != null) {
                    Buffer buffer = new Buffer();
                    requestBody.writeTo(buffer);
                    String rawBody = buffer.readByteString().utf8();
                    System.out.println(String.format("Sending [%s %s] with body [%s]",
                                                     request.method(),
                                                     request.url(),
                                                     rawBody));
                } else {
                    System.out.println(String.format("Sending [%s %s] with non-text body",
                                                     request.method(),
                                                     request.url()));
                }
            } else {
                System.out.println(String.format("Sending [%s %s]", request.method(), request.url()));
            }

            Instant start = Instant.now();
            okhttp3.Response response = chain.proceed(request);

            Duration duration = Duration.between(start, Instant.now());
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                if (responseBody.contentType() != null && responseBody.contentType().charset() != null) {
                    String rawBody = responseBody.string();
                    System.out.println(String.format("Received response for [%s %s] in %sms with body [%s]",
                                                     response.request().method(),
                                                     response.request().url(),
                                                     duration.toMillis(),
                                                     rawBody));

                    // Reading the body as a string closes the stream so create a new response body
                    response = response.newBuilder()
                                       .body(ResponseBody.create(responseBody.contentType(), rawBody))
                                       .build();
                } else {
                    System.out.println(String.format("Received response for [%s %s] in %sms with non-text body",
                                                     response.request().method(),
                                                     response.request().url(),
                                                     duration.toMillis()));
                }
            } else {
                System.out.println(String.format("Received response for [%s %s] in %sms",
                                                 response.request().method(),
                                                 response.request().url(),
                                                 duration.toMillis()));
            }

            return response;
        }
    }
}
