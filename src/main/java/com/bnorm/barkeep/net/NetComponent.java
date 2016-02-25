package com.bnorm.barkeep.net;

import dagger.Component;
import okhttp3.Cache;

@Component(modules = NetModule.class)
public interface NetComponent {

    Cache cache();

    BarkeepService barkeepService();
}
