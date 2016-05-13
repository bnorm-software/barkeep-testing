package com.bnorm.barkeep.net;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

import com.bnorm.barkeep.net.data.Bar;
import com.bnorm.barkeep.net.data.Book;
import com.bnorm.barkeep.net.data.CreateBarResponse;
import com.bnorm.barkeep.net.data.CreateBookResponse;
import com.bnorm.barkeep.net.data.CreateIngredientResponse;
import com.bnorm.barkeep.net.data.CreateRecipeResponse;
import com.bnorm.barkeep.net.data.DeleteBarResponse;
import com.bnorm.barkeep.net.data.DeleteBookResponse;
import com.bnorm.barkeep.net.data.DeleteIngredientResponse;
import com.bnorm.barkeep.net.data.DeleteRecipeResponse;
import com.bnorm.barkeep.net.data.GetBarResponse;
import com.bnorm.barkeep.net.data.GetBarsResponse;
import com.bnorm.barkeep.net.data.GetBookResponse;
import com.bnorm.barkeep.net.data.GetBooksResponse;
import com.bnorm.barkeep.net.data.GetIngredientResponse;
import com.bnorm.barkeep.net.data.GetIngredientsResponse;
import com.bnorm.barkeep.net.data.GetRecipeResponse;
import com.bnorm.barkeep.net.data.GetRecipesResponse;
import com.bnorm.barkeep.net.data.Ingredient;
import com.bnorm.barkeep.net.data.LoginResponse;
import com.bnorm.barkeep.net.data.LogoutResponse;
import com.bnorm.barkeep.net.data.MockResponse;
import com.bnorm.barkeep.net.data.Recipe;
import com.bnorm.barkeep.net.data.UpdateBarResponse;
import com.bnorm.barkeep.net.data.UpdateBookResponse;
import com.bnorm.barkeep.net.data.UpdateIngredientResponse;
import com.bnorm.barkeep.net.data.UpdateRecipeResponse;
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
    private final Map<Class<? extends MockResponse<?>>, MockResponse<?>> responses;

    @Inject
    MockBarkeepService(MockRetrofit mockRetrofit) {
        this.delegate = mockRetrofit.create(BarkeepService.class);
        this.responses = new LinkedHashMap<>();

        // Initialize mock responses.
        set(LoginResponse.class, LoginResponse.Enum.Success);
        set(LogoutResponse.class, LogoutResponse.Enum.Success);

        set(GetBooksResponse.class, GetBooksResponse.Enum.Success);
        set(CreateBookResponse.class, CreateBookResponse.Enum.Success);
        set(GetBookResponse.class, GetBookResponse.Enum.Success);
        set(UpdateBookResponse.class, UpdateBookResponse.Enum.Success);
        set(DeleteBookResponse.class, DeleteBookResponse.Enum.Success);

        set(GetBarsResponse.class, GetBarsResponse.Enum.Success);
        set(CreateBarResponse.class, CreateBarResponse.Enum.Success);
        set(GetBarResponse.class, GetBarResponse.Enum.Success);
        set(UpdateBarResponse.class, UpdateBarResponse.Enum.Success);
        set(DeleteBarResponse.class, DeleteBarResponse.Enum.Success);

        set(GetRecipesResponse.class, GetRecipesResponse.Enum.Success);
        set(CreateRecipeResponse.class, CreateRecipeResponse.Enum.Success);
        set(GetRecipeResponse.class, GetRecipeResponse.Enum.Success);
        set(UpdateRecipeResponse.class, UpdateRecipeResponse.Enum.Success);
        set(DeleteRecipeResponse.class, DeleteRecipeResponse.Enum.Success);

        set(GetIngredientsResponse.class, GetIngredientsResponse.Enum.Success);
        set(CreateIngredientResponse.class, CreateIngredientResponse.Enum.Success);
        set(GetIngredientResponse.class, GetIngredientResponse.Enum.Success);
        set(UpdateIngredientResponse.class, UpdateIngredientResponse.Enum.Success);
        set(DeleteIngredientResponse.class, DeleteIngredientResponse.Enum.Success);
    }

    public <T extends MockResponse<?>> void set(Class<T> responseClass, T value) {
        responses.put(responseClass, value);
    }

    private <T extends MockResponse<?>> BarkeepService returning(Class<T> responseClass) {
        T value = responseClass.cast(responses.get(responseClass));
        Call<?> call = Calls.response(value.response());
        return delegate.returning(call);
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
        return returning(GetBookResponse.class).getBook(id);
    }

    @Override
    public Call<Book> updateBook(@Path("id") long id, @Body Book book) {
        return returning(UpdateBookResponse.class).updateBook(id, book);
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
        return returning(GetBarResponse.class).getBar(id);
    }

    @Override
    public Call<Bar> updateBar(@Path("id") long id, @Body Bar bar) {
        return returning(UpdateBarResponse.class).updateBar(id, bar);
    }

    @Override
    public Call<Void> deleteBar(@Path("id") long id) {
        return returning(DeleteBarResponse.class).deleteBar(id);
    }

    @Override
    public Call<List<Recipe>> getRecipes(@Path("book") long book) {
        return returning(GetRecipesResponse.class).getRecipes(book);
    }

    @Override
    public Call<Recipe> createRecipe(@Path("book") long book, @Body Recipe recipe) {
        return returning(CreateRecipeResponse.class).createRecipe(book, recipe);
    }

    @Override
    public Call<Recipe> getRecipe(@Path("book") long book, @Path("id") long id) {
        return returning(GetRecipeResponse.class).getRecipe(book, id);
    }

    @Override
    public Call<Recipe> updateRecipe(@Path("book") long book, @Path("id") long id, @Body Recipe recipe) {
        return returning(UpdateRecipeResponse.class).updateRecipe(book, id, recipe);
    }

    @Override
    public Call<Void> deleteRecipe(@Path("book") long book, @Path("id") long id) {
        return returning(DeleteRecipeResponse.class).deleteRecipe(book, id);
    }

    @Override
    public Call<List<Ingredient>> getIngredients(@Query("search") String search) {
        return returning(GetIngredientsResponse.class).getIngredients(search);
    }

    @Override
    public Call<List<Ingredient>> getIngredients() {
        return returning(GetIngredientsResponse.class).getIngredients();
    }

    @Override
    public Call<Ingredient> createIngredient(@Body Ingredient ingredient) {
        return returning(CreateIngredientResponse.class).createIngredient(ingredient);
    }

    @Override
    public Call<Ingredient> getIngredient(@Path("id") long id) {
        return returning(GetIngredientResponse.class).getIngredient(id);
    }

    @Override
    public Call<Ingredient> updateIngredient(@Path("id") long id, @Body Ingredient ingredient) {
        return returning(UpdateIngredientResponse.class).updateIngredient(id, ingredient);
    }

    @Override
    public Call<Void> deleteIngredient(@Path("id") long id) {
        return returning(DeleteIngredientResponse.class).deleteIngredient(id);
    }
}
