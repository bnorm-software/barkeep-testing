package com.bnorm.barkeep.app;

import com.bnorm.barkeep.net.NetComponent;

import dagger.Component;

@AppScope
@Component(dependencies = NetComponent.class)
public interface AppComponent {

    static AppComponent build(NetComponent netComponent) {
        return DaggerAppComponent.builder().netComponent(netComponent).build();
    }

    Application app();
}
