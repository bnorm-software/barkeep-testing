package com.bnorm.barkeep.net.data;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Response;

public interface MockResponse<E> {
    MediaType JSON = MediaType.parse("application/json");

    static ResponseBody json(String content) {
        return ResponseBody.create(MockResponse.JSON, content);
    }

    Response<E> response();
}
