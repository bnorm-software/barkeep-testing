package com.bnorm.barkeep.net;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public class NetModule {

    private final HttpUrl httpUrl;

    public NetModule(HttpUrl httpUrl) {
        this.httpUrl = httpUrl;
    }

    @NetScope
    @Provides
    Cache provideOkHttpCache() {
        File file = new File("http");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(file, cacheSize);
        return cache;
    }

    @NetScope
    @Provides
    CacheInterceptor provideCacheInterceptor() {
        return new CacheInterceptor();
    }

    @NetScope
    @Provides
    OkHttpClient provideOkHttpClient(Cache cache, CacheInterceptor cacheInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cache(cache);
        builder.addInterceptor(cacheInterceptor);
        builder.addInterceptor(new SessionInterceptor());
        builder.addInterceptor(new TokenInterceptor());
        builder.addInterceptor(new WireTraceInterceptor());
        return builder.build();
    }

    @NetScope
    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.addConverterFactory(MoshiConverterFactory.create());
        builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        builder.baseUrl(httpUrl);
        builder.client(okHttpClient);
        return builder.build();
    }

    @NetScope
    @Provides
    BarkeepService provideBarkeepService(Retrofit retrofit) {
        return retrofit.create(BarkeepService.class);
    }
}
