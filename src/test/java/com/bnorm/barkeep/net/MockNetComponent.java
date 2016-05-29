package com.bnorm.barkeep.net;

import java.util.concurrent.ExecutorService;

import com.bnorm.barkeep.net.data.NetDataModule;

import dagger.Component;
import okhttp3.HttpUrl;
import retrofit2.mock.NetworkBehavior;

@NetScope
@Component(modules = {NetModule.class, NetDataModule.class, MockNetModule.class})
public interface MockNetComponent extends NetComponent {

    static MockNetComponent build(HttpUrl.Builder urlBuilder, NetworkBehavior behavior, ExecutorService executor) {
        return DaggerMockNetComponent.builder()
                                     .netModule(new NetModule(urlBuilder.build()))
                                     .mockNetModule(new MockNetModule(behavior, executor))
                                     .build();
    }

    MockBarkeepService mockService();

    default BarkeepService barkeepService() {
        return mockService();
    }
}
