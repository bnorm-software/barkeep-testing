package com.bnorm.barkeep.net;

import java.io.File;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public class NetModule {

    private final HttpUrl httpUrl;
    private final File cacheFile;
    private final int cacheSize;

    public NetModule(HttpUrl httpUrl) {
        this.httpUrl = httpUrl;
        this.cacheFile = new File("http");
        this.cacheSize = 10 * 1024 * 1024; // 10 MiB
    }

    @NetScope
    @Provides
    Cache provideOkHttpCache() {
        return new Cache(cacheFile, cacheSize);
    }

    @NetScope
    @Provides
    CacheInterceptor provideCacheInterceptor() {
        return new CacheInterceptor();
    }

    @NetScope
    @Provides(type = Provides.Type.SET_VALUES)
    Set<Interceptor> provideInterceptors(CacheInterceptor cacheInterceptor) {
        return ImmutableSet.of(cacheInterceptor,
                               new SessionInterceptor(),
                               new TokenInterceptor(),
                               new WireTraceInterceptor());
    }

    @NetScope
    @Provides
    OkHttpClient provideOkHttpClient(Cache cache, Set<Interceptor> interceptors) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cache(cache);
        interceptors.forEach(builder::addInterceptor);
        return builder.build();
    }

    @NetScope
    @Provides(type = Provides.Type.SET_VALUES)
    Set<JsonAdapter.Factory> provideJsonAdapterFactories() {
        return ImmutableSet.of();
    }

    @NetScope
    @Provides
    Moshi provideMoshi(Set<JsonAdapter.Factory> adapterFactories) {
        Moshi.Builder builder = new Moshi.Builder();
        adapterFactories.forEach(builder::add);
        return builder.build();
    }

    @NetScope
    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Moshi moshi) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.addConverterFactory(MoshiConverterFactory.create(moshi));
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
