package com.bnorm.barkeep.net;

import java.util.List;

import com.bnorm.barkeep.net.data.Bar;
import com.bnorm.barkeep.net.data.Book;
import com.bnorm.barkeep.net.data.Ingredient;
import com.bnorm.barkeep.net.data.Recipe;
import com.bnorm.barkeep.net.data.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BarkeepService {

    @POST("login")
    Call<Void> login(@Body User user);

    @POST("logout")
    Call<Void> logout();


    // ================= //
    // ***** Books ***** //
    // ================= //

    @GET("books")
    Call<List<Book>> getBooks();

    @POST("books")
    Call<Book> createBook(@Body Book book);

    @GET("books/{id}")
    Call<Book> getBook(@Path("id") long id);

    @PUT("books/{id}")
    Call<Book> updateBook(@Path("id") long id, @Body Book book);

    @DELETE("books/{id}")
    Call<Void> deleteBook(@Path("id") long id);


    // ================= //
    // ***** Bars ***** //
    // ================= //

    @GET("bars")
    Call<List<Bar>> getBars();

    @POST("bars")
    Call<Bar> createBar(@Body Bar bar);

    @GET("bars/{id}")
    Call<Bar> getBar(@Path("id") long id);

    @PUT("bars/{id}")
    Call<Bar> updateBar(@Path("id") long id, @Body Bar bar);

    @DELETE("bars/{id}")
    Call<Void> deleteBar(@Path("id") long id);


    // =================== //
    // ***** Recipes ***** //
    // =================== //

    @GET("books/{book}/recipes")
    Call<List<Recipe>> getRecipes(@Path("book") long book);

    @POST("books/{book}/recipes")
    Call<Recipe> createRecipe(@Path("book") long book, @Body Recipe recipe);

    @GET("books/{book}/recipes/{id}")
    Call<Recipe> getRecipe(@Path("book") long book, @Path("id") long id);

    @PUT("books/{book}/recipes/{id}")
    Call<Recipe> updateRecipe(@Path("book") long book, @Path("id") long id, @Body Recipe recipe);

    @DELETE("books/{book}/recipes/{id}")
    Call<Void> deleteRecipe(@Path("book") long book, @Path("id") long id);


    // ======================= //
    // ***** Ingredients ***** //
    // ======================= //

    @GET("ingredients")
    Call<List<Ingredient>> getIngredients(@Query("search") String search);

    @GET("ingredients")
    Call<List<Ingredient>> getIngredients();

    @POST("ingredients")
    Call<Ingredient> createIngredient(@Body Ingredient ingredient);

    @GET("ingredients/{id}")
    Call<Ingredient> getIngredient(@Path("id") long id);

    @PUT("ingredients/{id}")
    Call<Ingredient> updateIngredient(@Path("id") long id, @Body Ingredient ingredient);

    @DELETE("ingredients/{id}")
    Call<Void> deleteIngredient(@Path("id") long id);
}
