package com.bnorm.barkeep.net;

import com.bnorm.barkeep.net.data.NetDataModule;

import dagger.Component;

@NetScope
@Component(modules = {NetModule.class, NetDataModule.class})
public interface NetComponent {

    static NetComponent build(NetModule netModule) {
        return DaggerNetComponent.builder().netModule(netModule).build();
    }

    NetworkController controller();

    BarkeepService barkeepService();
}
