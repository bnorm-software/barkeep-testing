package com.bnorm.barkeep.net;

import java.util.List;

import com.bnorm.barkeep.net.data.Book;
import com.bnorm.barkeep.net.data.User;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface BarkeepService {

    @POST("login")
    Observable<Void> login(@Body User user);

    @POST("logout")
    Observable<Void> logout();

    @GET("ingredients")
    Observable<Void> getIngredient();

    @GET("books")
    Observable<List<Book>> getBooks();
}