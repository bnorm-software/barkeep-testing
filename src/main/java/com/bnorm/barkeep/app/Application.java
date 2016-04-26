package com.bnorm.barkeep.app;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.inject.Inject;

import com.bnorm.barkeep.net.BarkeepService;
import com.bnorm.barkeep.net.DaggerNetComponent;
import com.bnorm.barkeep.net.NetComponent;
import com.bnorm.barkeep.net.NetModule;
import com.bnorm.barkeep.net.NetworkController;
import com.bnorm.barkeep.net.data.Book;
import com.bnorm.barkeep.net.data.User;

import okhttp3.HttpUrl;
import retrofit2.Response;

public class Application {

    private final NetworkController controller;
    private final BarkeepService service;

    @Inject
    Application(NetworkController controller, BarkeepService service) {
        this.controller = controller;
        this.service = service;
    }

    public void run() throws IOException {
        User authentication = User.create("bnorm", "nohomohug");
        Book book = Book.create("Magic Recipes", "A book filled with magically delicious drink recipes.");

        Response<Void> response = service.login(authentication).execute();
        if (response.isSuccessful()) {
            System.out.println("Successfully logged in with user [" + authentication.getUsername() + "]");
            service.getBooks().execute();
            Book created = service.createBook(book).execute().body();
            System.out.println(created.toString());
            service.getBooks().execute();
            service.deleteBook(created.getId()).execute();
            service.getBooks().execute();
            service.logout().execute();
        } else {
            System.out.println("Unable to login! Error: [" + response.errorBody().string() + "]");
        }

        System.out.println();
        controller.logCacheStats();
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder();
        urlBuilder.scheme("https");
        urlBuilder.host("barkeep.beefyhost.com");
        urlBuilder.port(8443);
        urlBuilder.addPathSegment("api");
        urlBuilder.addPathSegment("rest");
        urlBuilder.addPathSegment("v1");
        // This last, empty segment adds a trailing '/' which is required for relative paths in the annotations
        urlBuilder.addPathSegment("");

        NetModule netModule = new NetModule(urlBuilder.build());
        NetComponent netComponent = DaggerNetComponent.builder().netModule(netModule).build();
        AppComponent appComponent = DaggerAppComponent.builder().netComponent(netComponent).build();

        appComponent.app().run();
    }
}
