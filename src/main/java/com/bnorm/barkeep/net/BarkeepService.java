package com.bnorm.barkeep.net;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface BarkeepService {

    @POST("Login")
    Observable<Message> login(@Body User user);

    @POST("Logout")
    Observable<Void> logout();

    @GET("ingredients")
    Observable<Void> getIngredient();


    class User {

        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }


    class BaseResponse {

        public boolean success;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }
    }


    class Message extends BaseResponse {

        public String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}