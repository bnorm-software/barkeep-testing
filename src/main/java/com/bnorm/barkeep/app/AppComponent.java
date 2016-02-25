package com.bnorm.barkeep.app;

import javax.inject.Singleton;

import com.bnorm.barkeep.net.NetComponent;

import dagger.Component;

@Singleton
@Component(dependencies = NetComponent.class)
public interface AppComponent {

    Application app();
}