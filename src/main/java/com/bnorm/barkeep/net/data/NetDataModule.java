package com.bnorm.barkeep.net.data;

import java.util.Set;

import com.bnorm.barkeep.net.NetScope;
import com.google.common.collect.ImmutableSet;
import com.ryanharter.auto.value.moshi.AutoValueMoshiAdapterFactory;
import com.squareup.moshi.JsonAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class NetDataModule {

    @NetScope
    @Provides(type = Provides.Type.SET_VALUES)
    Set<JsonAdapter.Factory> provideJsonAdapterFactories() {
        return ImmutableSet.of(new AutoValueMoshiAdapterFactory());
    }
}
