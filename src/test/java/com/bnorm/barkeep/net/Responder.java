package com.bnorm.barkeep.net;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Response;

public interface Responder<E> {
    MediaType JSON = MediaType.parse("application/json");

    static ResponseBody json(String content) {
        return ResponseBody.create(Responder.JSON, content);
    }

    Response<E> response();
}
