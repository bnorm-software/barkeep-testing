package com.bnorm.barkeep.net;

import java.util.List;

import com.bnorm.barkeep.net.data.Book;
import com.bnorm.barkeep.net.data.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public interface BarkeepService {

    @POST("login")
    Observable<Void> login(@Body User user);

    @POST("logout")
    Observable<Void> logout();

    @GET("ingredients")
    Observable<Void> getIngredient();


    // ================= //
    // ***** Books ***** //
    // ================= //


    @GET("books")
    Call<List<Book>> getBooks();

    // todo return just a plan book
    @POST("books")
    Call<BookCreate> createBook(@Body Book book);

    @DELETE("books/{id}")
    Call<Void> deleteBook(@Path("id") long id);


    class BookCreate {

        private boolean success;
        private Book book;

        public boolean isSuccess() {
            return success;
        }
        public void setSuccess(boolean success) {
            this.success = success;
        }
        public Book getBook() {
            return book;
        }
        public void setBook(Book book) {
            this.book = book;
        }
    }
}