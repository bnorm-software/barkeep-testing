package com.bnorm.barkeep.net.data;

import java.util.Set;

import com.bnorm.barkeep.net.NetScope;
import com.google.common.collect.ImmutableSet;
import com.squareup.moshi.JsonAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class NetDataModule {

    @NetScope
    @Provides(type = Provides.Type.SET_VALUES)
    Set<JsonAdapter.Factory> provideJsonAdapterFactories() {
        return ImmutableSet.of(Bar.typeAdapterFactory(),
                               Book.typeAdapterFactory(),
                               Ingredient.typeAdapterFactory(),
                               Recipe.typeAdapterFactory(),
                               RecipeComponent.typeAdapterFactory(),
                               User.typeAdapterFactory());
    }
}
