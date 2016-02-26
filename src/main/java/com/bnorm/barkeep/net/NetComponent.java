package com.bnorm.barkeep.net;

import dagger.Component;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

@NetScope
@Component(modules = NetModule.class)
public interface NetComponent {

    Cache cache();

    CacheInterceptor cacheInterceptor();

    OkHttpClient client();

    BarkeepService barkeepService();
}
