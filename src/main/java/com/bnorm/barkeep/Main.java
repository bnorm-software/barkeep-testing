package com.bnorm.barkeep;

import java.io.IOException;
import java.net.URISyntaxException;

import com.bnorm.barkeep.app.AppComponent;
import com.bnorm.barkeep.app.DaggerAppComponent;
import com.bnorm.barkeep.net.DaggerNetComponent;
import com.bnorm.barkeep.net.NetComponent;
import com.bnorm.barkeep.net.NetModule;

import okhttp3.HttpUrl;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder();
        urlBuilder.scheme("https");
        urlBuilder.host("barkeep.beefyhost.com");
        urlBuilder.addPathSegment("rest");
        urlBuilder.addPathSegment("api");
        urlBuilder.addPathSegment("v1");
        // This last, empty segment adds a trailing '/' which is required for relative paths in the annotations
        urlBuilder.addPathSegment("");

        NetModule netModule = new NetModule(urlBuilder.build());
        NetComponent netComponent = DaggerNetComponent.builder().netModule(netModule).build();
        AppComponent appComponent = DaggerAppComponent.builder().netComponent(netComponent).build();

        appComponent.app().run();
    }
}
