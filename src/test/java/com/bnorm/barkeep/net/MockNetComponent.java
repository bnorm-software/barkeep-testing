package com.bnorm.barkeep.net;

import com.bnorm.barkeep.net.data.NetDataModule;

import dagger.Component;

@NetScope
@Component(modules = {NetModule.class, NetDataModule.class, MockNetModule.class})
public interface MockNetComponent extends NetComponent {

    MockBarkeepService mockService();

    default BarkeepService barkeepService() {
        return mockService();
    }
}
