package com.bnorm.barkeep.app;

import com.bnorm.barkeep.net.NetComponent;

import dagger.Component;

@AppScope
@Component(dependencies = NetComponent.class)
public interface AppComponent {

    Application app();
}