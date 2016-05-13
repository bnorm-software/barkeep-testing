package com.bnorm.barkeep.net;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

import com.bnorm.barkeep.net.data.Bar;
import com.bnorm.barkeep.net.data.Book;
import com.bnorm.barkeep.net.data.CreateBarResponse;
import com.bnorm.barkeep.net.data.CreateBookResponse;
import com.bnorm.barkeep.net.data.DeleteBarResponse;
import com.bnorm.barkeep.net.data.DeleteBookResponse;
import com.bnorm.barkeep.net.data.GetBarsResponse;
import com.bnorm.barkeep.net.data.GetBooksResponse;
import com.bnorm.barkeep.net.data.Ingredient;
import com.bnorm.barkeep.net.data.LoginResponse;
import com.bnorm.barkeep.net.data.LogoutResponse;
import com.bnorm.barkeep.net.data.MockResponse;
import com.bnorm.barkeep.net.data.Recipe;
import com.bnorm.barkeep.net.data.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.Calls;
import retrofit2.mock.MockRetrofit;

public final class MockBarkeepService implements BarkeepService {
    private final BehaviorDelegate<BarkeepService> delegate;
    private final Map<Class<? extends Enum<? extends MockResponse<?>>>, Enum<? extends MockResponse<?>>> responses;

    @Inject
    MockBarkeepService(MockRetrofit mockRetrofit) {
        this.delegate = mockRetrofit.create(BarkeepService.class);
        this.responses = new LinkedHashMap<>();

        // Initialize mock responses.
        load(LoginResponse.class, LoginResponse.Success);
        load(LogoutResponse.class, LogoutResponse.Success);
        load(GetBooksResponse.class, GetBooksResponse.Success);
        load(CreateBookResponse.class, CreateBookResponse.Success);
        load(DeleteBookResponse.class, DeleteBookResponse.Success);
        load(GetBarsResponse.class, GetBarsResponse.Success);
        load(CreateBarResponse.class, CreateBarResponse.Success);
        load(DeleteBarResponse.class, DeleteBarResponse.Success);
    }

    private <T extends Enum<T> & MockResponse<?>> void load(Class<T> responseClass, T defaultValue) {
        responses.put(responseClass, defaultValue);
    }

    public <T extends Enum<T> & MockResponse<?>> T get(Class<T> responseClass) {
        return responseClass.cast(responses.get(responseClass));
    }

    public <T extends Enum<T> & MockResponse<?>> void set(Class<T> responseClass, T value) {
        responses.put(responseClass, value);
    }

    private <T extends Enum<T> & MockResponse<?>> BarkeepService returning(Class<T> responseClass) {
        return delegate.returning(Calls.response(get(responseClass).response()));
    }

    @Override
    public Call<Void> login(@Body User user) {
        return returning(LoginResponse.class).login(user);
    }

    @Override
    public Call<Void> logout() {
        return returning(LogoutResponse.class).logout();
    }

    @Override
    public Call<List<Book>> getBooks() {
        return returning(GetBooksResponse.class).getBooks();
    }

    @Override
    public Call<Book> createBook(@Body Book book) {
        return returning(CreateBookResponse.class).createBook(book);
    }

    @Override
    public Call<Book> getBook(@Path("id") long id) {
        return null;
    }

    @Override
    public Call<Book> updateBook(@Path("id") long id, @Body Book book) {
        return null;
    }

    @Override
    public Call<Void> deleteBook(@Path("id") long id) {
        return returning(DeleteBookResponse.class).deleteBook(id);
    }

    @Override
    public Call<List<Bar>> getBars() {
        return returning(GetBarsResponse.class).getBars();
    }

    @Override
    public Call<Bar> createBar(@Body Bar bar) {
        return returning(CreateBarResponse.class).createBar(bar);
    }

    @Override
    public Call<Bar> getBar(@Path("id") long id) {
        return null;
    }

    @Override
    public Call<Bar> updateBar(@Path("id") long id, @Body Bar bar) {
        return null;
    }

    @Override
    public Call<Void> deleteBar(@Path("id") long id) {
        return returning(DeleteBarResponse.class).deleteBar(id);
    }

    @Override
    public Call<List<Recipe>> getRecipes(@Path("book") long book) {
        return null;
    }

    @Override
    public Call<Recipe> createRecipe(@Path("book") long book, @Body Recipe recipe) {
        return null;
    }

    @Override
    public Call<Recipe> getRecipe(@Path("book") long book, @Path("id") long id) {
        return null;
    }

    @Override
    public Call<Recipe> updateRecipe(@Path("book") long book, @Path("id") long id, @Body Recipe recipe) {
        return null;
    }

    @Override
    public Call<Void> deleteRecipe(@Path("book") long book, @Path("id") long id) {
        return null;
    }

    @Override
    public Call<List<Ingredient>> getIngredients(@Query("search") String search) {
        return null;
    }

    @Override
    public Call<List<Ingredient>> getIngredients() {
        return null;
    }

    @Override
    public Call<Ingredient> createIngredient(@Body Ingredient ingredient) {
        return null;
    }

    @Override
    public Call<Ingredient> getIngredient(@Path("id") long id) {
        return null;
    }

    @Override
    public Call<Ingredient> updateIngredient(@Path("id") long id, @Body Ingredient ingredient) {
        return null;
    }

    @Override
    public Call<Void> deleteIngredient(@Path("id") long id) {
        return null;
    }
}
