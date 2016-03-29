package com.bnorm.barkeep.net;

import java.util.List;

import com.bnorm.barkeep.net.data.Bar;
import com.bnorm.barkeep.net.data.Book;
import com.bnorm.barkeep.net.data.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

public interface BarkeepService {

    @POST("login")
    Observable<Void> login(@Body User user);

    @POST("logout")
    Observable<Void> logout();


    // ================= //
    // ***** Books ***** //
    // ================= //

    @GET("books")
    Observable<List<Book>> getBooks();

    @POST("books")
    Observable<Book> createBook(@Body Book book);

    @GET("books/{id}")
    Observable<Book> getBook(@Path("id") long id);

    @PUT("books/{id}")
    Observable<Book> updateBook(@Path("id") long id);

    @DELETE("books/{id}")
    Observable<Void> deleteBook(@Path("id") long id);


    // ================= //
    // ***** Bars ***** //
    // ================= //

    @GET("bars")
    Observable<List<Bar>> getBars();

    @POST("bars")
    Observable<Bar> createBar(@Body Bar book);

    @GET("bars/{id}")
    Observable<Bar> getBar(@Path("id") long id);

    @PUT("bars/{id}")
    Observable<Bar> updateBar(@Path("id") long id);

    @DELETE("bars/{id}")
    Observable<Void> deleteBar(@Path("id") long id);
}