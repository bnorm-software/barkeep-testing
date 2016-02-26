package com.bnorm.barkeep.app;

import java.io.IOException;

import javax.inject.Inject;

import com.bnorm.barkeep.net.BarkeepService;
import com.bnorm.barkeep.net.data.Book;
import com.bnorm.barkeep.net.data.User;

import okhttp3.Cache;
import retrofit2.Response;

public class Application {

    private final BarkeepService service;
    private final Cache cache;

    @Inject
    Application(BarkeepService service, Cache cache) {
        this.service = service;
        this.cache = cache;
    }

    public void run() throws IOException {
        User authentication = new User("bnorm", "nohomohug");
        Book book = new Book("Magic Recipes", "A book filled with magically delicious drink recipes.");

        service.login(authentication).subscribe(System.out::println);
        service.getBooks().execute();
        Response<BarkeepService.BookCreate> created = service.createBook(book).execute();
        service.getBooks().execute();
        service.deleteBook(created.body().getBook().getId()).execute();
        service.getBooks().execute();
        service.logout().subscribe(System.out::println);
        service.getIngredient().subscribe(System.out::println);

        System.out.println();
        System.out.println("cache.requestCount() = " + cache.requestCount());
        System.out.println("cache.networkCount() = " + cache.networkCount());
        System.out.println("cache.hitCount() = " + cache.hitCount());
    }
}
