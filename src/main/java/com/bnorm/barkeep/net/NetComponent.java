package com.bnorm.barkeep.net;

import dagger.Component;

@NetScope
@Component(modules = NetModule.class)
public interface NetComponent {

    NetworkController controller();

    BarkeepService barkeepService();
}
