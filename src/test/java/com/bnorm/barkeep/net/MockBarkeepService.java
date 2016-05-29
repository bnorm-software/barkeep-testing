package com.bnorm.barkeep.net;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

import com.bnorm.barkeep.net.data.Bar;
import com.bnorm.barkeep.net.data.Book;
import com.bnorm.barkeep.net.data.CreateBarResponder;
import com.bnorm.barkeep.net.data.CreateBookResponder;
import com.bnorm.barkeep.net.data.CreateIngredientResponder;
import com.bnorm.barkeep.net.data.CreateRecipeResponder;
import com.bnorm.barkeep.net.data.DeleteBarResponder;
import com.bnorm.barkeep.net.data.DeleteBookResponder;
import com.bnorm.barkeep.net.data.DeleteIngredientResponder;
import com.bnorm.barkeep.net.data.DeleteRecipeResponder;
import com.bnorm.barkeep.net.data.GetBarResponder;
import com.bnorm.barkeep.net.data.GetBarsResponder;
import com.bnorm.barkeep.net.data.GetBookResponder;
import com.bnorm.barkeep.net.data.GetBooksResponder;
import com.bnorm.barkeep.net.data.GetIngredientResponder;
import com.bnorm.barkeep.net.data.GetIngredientsResponder;
import com.bnorm.barkeep.net.data.GetRecipeResponder;
import com.bnorm.barkeep.net.data.GetRecipesResponder;
import com.bnorm.barkeep.net.data.Ingredient;
import com.bnorm.barkeep.net.data.LoginResponder;
import com.bnorm.barkeep.net.data.LogoutResponder;
import com.bnorm.barkeep.net.data.Recipe;
import com.bnorm.barkeep.net.data.UpdateBarResponder;
import com.bnorm.barkeep.net.data.UpdateBookResponder;
import com.bnorm.barkeep.net.data.UpdateIngredientResponder;
import com.bnorm.barkeep.net.data.UpdateRecipeResponder;
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
    private final Map<Class<? extends Responder<?>>, Responder<?>> responses;

    @Inject
    MockBarkeepService(MockRetrofit mockRetrofit) {
        this.delegate = mockRetrofit.create(BarkeepService.class);
        this.responses = new LinkedHashMap<>();

        // Initialize mock responses.
        set(LoginResponder.class, LoginResponder.Enum.Success);
        set(LogoutResponder.class, LogoutResponder.Enum.Success);

        set(GetBooksResponder.class, GetBooksResponder.Enum.Success);
        set(CreateBookResponder.class, CreateBookResponder.Enum.Success);
        set(GetBookResponder.class, GetBookResponder.Enum.Success);
        set(UpdateBookResponder.class, UpdateBookResponder.Enum.Success);
        set(DeleteBookResponder.class, DeleteBookResponder.Enum.Success);

        set(GetBarsResponder.class, GetBarsResponder.Enum.Success);
        set(CreateBarResponder.class, CreateBarResponder.Enum.Success);
        set(GetBarResponder.class, GetBarResponder.Enum.Success);
        set(UpdateBarResponder.class, UpdateBarResponder.Enum.Success);
        set(DeleteBarResponder.class, DeleteBarResponder.Enum.Success);

        set(GetRecipesResponder.class, GetRecipesResponder.Enum.Success);
        set(CreateRecipeResponder.class, CreateRecipeResponder.Enum.Success);
        set(GetRecipeResponder.class, GetRecipeResponder.Enum.Success);
        set(UpdateRecipeResponder.class, UpdateRecipeResponder.Enum.Success);
        set(DeleteRecipeResponder.class, DeleteRecipeResponder.Enum.Success);

        set(GetIngredientsResponder.class, GetIngredientsResponder.Enum.Success);
        set(CreateIngredientResponder.class, CreateIngredientResponder.Enum.Success);
        set(GetIngredientResponder.class, GetIngredientResponder.Enum.Success);
        set(UpdateIngredientResponder.class, UpdateIngredientResponder.Enum.Success);
        set(DeleteIngredientResponder.class, DeleteIngredientResponder.Enum.Success);
    }

    public <T extends Responder<?>> void set(Class<T> responseClass, T value) {
        responses.put(responseClass, value);
    }

    private <T extends Responder<?>> BarkeepService returning(Class<T> responseClass) {
        T value = responseClass.cast(responses.get(responseClass));
        Call<?> call = Calls.response(value.response());
        return delegate.returning(call);
    }

    @Override
    public Call<Void> login(@Body User user) {
        return returning(LoginResponder.class).login(user);
    }

    @Override
    public Call<Void> logout() {
        return returning(LogoutResponder.class).logout();
    }

    @Override
    public Call<List<Book>> getBooks() {
        return returning(GetBooksResponder.class).getBooks();
    }

    @Override
    public Call<Book> createBook(@Body Book book) {
        return returning(CreateBookResponder.class).createBook(book);
    }

    @Override
    public Call<Book> getBook(@Path("id") long id) {
        return returning(GetBookResponder.class).getBook(id);
    }

    @Override
    public Call<Book> updateBook(@Path("id") long id, @Body Book book) {
        return returning(UpdateBookResponder.class).updateBook(id, book);
    }

    @Override
    public Call<Void> deleteBook(@Path("id") long id) {
        return returning(DeleteBookResponder.class).deleteBook(id);
    }

    @Override
    public Call<List<Bar>> getBars() {
        return returning(GetBarsResponder.class).getBars();
    }

    @Override
    public Call<Bar> createBar(@Body Bar bar) {
        return returning(CreateBarResponder.class).createBar(bar);
    }

    @Override
    public Call<Bar> getBar(@Path("id") long id) {
        return returning(GetBarResponder.class).getBar(id);
    }

    @Override
    public Call<Bar> updateBar(@Path("id") long id, @Body Bar bar) {
        return returning(UpdateBarResponder.class).updateBar(id, bar);
    }

    @Override
    public Call<Void> deleteBar(@Path("id") long id) {
        return returning(DeleteBarResponder.class).deleteBar(id);
    }

    @Override
    public Call<List<Recipe>> getRecipes(@Path("book") long book) {
        return returning(GetRecipesResponder.class).getRecipes(book);
    }

    @Override
    public Call<Recipe> createRecipe(@Path("book") long book, @Body Recipe recipe) {
        return returning(CreateRecipeResponder.class).createRecipe(book, recipe);
    }

    @Override
    public Call<Recipe> getRecipe(@Path("book") long book, @Path("id") long id) {
        return returning(GetRecipeResponder.class).getRecipe(book, id);
    }

    @Override
    public Call<Recipe> updateRecipe(@Path("book") long book, @Path("id") long id, @Body Recipe recipe) {
        return returning(UpdateRecipeResponder.class).updateRecipe(book, id, recipe);
    }

    @Override
    public Call<Void> deleteRecipe(@Path("book") long book, @Path("id") long id) {
        return returning(DeleteRecipeResponder.class).deleteRecipe(book, id);
    }

    @Override
    public Call<List<Ingredient>> getIngredients(@Query("search") String search) {
        return returning(GetIngredientsResponder.class).getIngredients(search);
    }

    @Override
    public Call<List<Ingredient>> getIngredients() {
        return returning(GetIngredientsResponder.class).getIngredients();
    }

    @Override
    public Call<Ingredient> createIngredient(@Body Ingredient ingredient) {
        return returning(CreateIngredientResponder.class).createIngredient(ingredient);
    }

    @Override
    public Call<Ingredient> getIngredient(@Path("id") long id) {
        return returning(GetIngredientResponder.class).getIngredient(id);
    }

    @Override
    public Call<Ingredient> updateIngredient(@Path("id") long id, @Body Ingredient ingredient) {
        return returning(UpdateIngredientResponder.class).updateIngredient(id, ingredient);
    }

    @Override
    public Call<Void> deleteIngredient(@Path("id") long id) {
        return returning(DeleteIngredientResponder.class).deleteIngredient(id);
    }
}
