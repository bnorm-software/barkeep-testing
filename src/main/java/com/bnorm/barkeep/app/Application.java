package com.bnorm.barkeep.app;

import java.io.IOException;

import javax.inject.Inject;

import com.bnorm.barkeep.net.BarkeepService;

import okhttp3.Cache;

public class Application {

    private final BarkeepService service;
    private final Cache cache;

    @Inject
    Application(BarkeepService service, Cache cache) {
        this.service = service;
        this.cache = cache;
    }

    public void run() throws IOException {
        BarkeepService.User authentication = new BarkeepService.User();
        authentication.setUsername("bnorm");
        authentication.setPassword("nohomohug");

        service.login(authentication).subscribe(System.out::println);
        service.getIngredient().subscribe(System.out::println);
        service.logout().subscribe(System.out::println);
        service.getIngredient().subscribe(System.out::println);

        System.out.println();
        System.out.println("cache.requestCount() = " + cache.requestCount());
        System.out.println("cache.networkCount() = " + cache.networkCount());
        System.out.println("cache.hitCount() = " + cache.hitCount());
    }
}
