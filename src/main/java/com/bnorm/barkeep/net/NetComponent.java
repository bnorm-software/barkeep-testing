package com.bnorm.barkeep.net;

import com.bnorm.barkeep.net.data.NetDataModule;

import dagger.Component;

@NetScope
@Component(modules = {NetModule.class, NetDataModule.class})
public interface NetComponent {

    NetworkController controller();

    BarkeepService barkeepService();
}
