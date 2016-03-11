package com.bnorm.barkeep.net;

import javax.inject.Inject;

import okhttp3.Cache;

public class NetworkController {

    private final Cache cache;
    private final CacheInterceptor cacheInterceptor;

    @Inject
    public NetworkController(Cache cache, CacheInterceptor cacheInterceptor) {
        this.cache = cache;
        this.cacheInterceptor = cacheInterceptor;
    }

    public void logCacheStats() {
        System.out.println("cache.requestCount() = " + cache.requestCount());
        System.out.println("cache.networkCount() = " + cache.networkCount());
        System.out.println("cache.hitCount() = " + cache.hitCount());
    }
}
