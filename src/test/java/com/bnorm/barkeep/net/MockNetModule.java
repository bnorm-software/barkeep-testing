package com.bnorm.barkeep.net;

import java.util.concurrent.ExecutorService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

@Module
public class MockNetModule {

    private final NetworkBehavior behavior;
    private final ExecutorService executor;

    public MockNetModule(NetworkBehavior behavior, ExecutorService executor) {
        this.behavior = behavior;
        this.executor = executor;
    }

    @NetScope
    @Provides
    MockRetrofit mockRetrofit(Retrofit retrofit) {
        return new MockRetrofit.Builder(retrofit).networkBehavior(behavior).backgroundExecutor(executor).build();
    }

    @NetScope
    @Provides
    MockBarkeepService mockBarkeepService(MockRetrofit mockRetrofit) {
        return new MockBarkeepService(mockRetrofit);
    }
}
