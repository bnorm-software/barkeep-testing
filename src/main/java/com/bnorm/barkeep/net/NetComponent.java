package com.bnorm.barkeep.net;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.Cache;

@NetScope
@Component(modules = NetModule.class)
public interface NetComponent {

    Cache cache();

    CacheInterceptor cacheInterceptor();

    BarkeepService barkeepService();
}
