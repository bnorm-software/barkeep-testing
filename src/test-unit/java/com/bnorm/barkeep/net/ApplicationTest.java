package com.bnorm.barkeep.net;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Ignore;
import org.junit.Test;

import com.bnorm.barkeep.app.AppComponent;

import okhttp3.HttpUrl;
import retrofit2.mock.NetworkBehavior;

@Ignore
public class ApplicationTest {
    @Test
    public void test() throws Exception {
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder();
        urlBuilder.scheme("http");
        urlBuilder.host("localhost");
        // This last, empty segment adds a trailing '/' which is required for relative paths in the annotations
        urlBuilder.addPathSegment("");

        NetworkBehavior behavior = NetworkBehavior.create();
        behavior.setDelay(0, TimeUnit.SECONDS);
        behavior.setFailurePercent(0);
        behavior.setVariancePercent(0);
        ExecutorService executor = Executors.newCachedThreadPool();
        MockNetComponent mockNetComponent = MockNetComponent.build(urlBuilder, behavior, executor);
        AppComponent appComponent = AppComponent.build(mockNetComponent);
        appComponent.app().run();
        executor.shutdown();
    }
}
