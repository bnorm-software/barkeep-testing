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
        return ImmutableSet.of((type, annotations, moshi) -> {
            if (type.equals(Recipe.class)) {
                return Recipe.jsonAdapter(moshi);
            } else if (type.equals(Bar.class)) {
                return Bar.jsonAdapter(moshi);
            } else if (type.equals(Book.class)) {
                return Book.jsonAdapter(moshi);
            } else if (type.equals(Ingredient.class)) {
                return Ingredient.jsonAdapter(moshi);
            } else if (type.equals(RecipeComponent.class)) {
                return RecipeComponent.jsonAdapter(moshi);
            } else if (type.equals(User.class)) {
                return User.jsonAdapter(moshi);
            } else {
                return null;
            }
        });
    }
}
