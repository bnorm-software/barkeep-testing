package com.bnorm.barkeep.net.data;

import java.util.Collections;
import java.util.List;

import retrofit2.Response;

public interface GetBooksResponse extends MockResponse<List<Book>> {
    enum Enum implements GetBooksResponse {
        Success {
            @Override
            public Response<List<Book>> response() {
                return Response.success(Collections.singletonList(Book.create(1, "Private", "Book1", "Description1")));
            }
        },
        Unauthorized {
            @Override
            public Response<List<Book>> response() {
                return Response.error(401, MockResponse.json("{\"message\":\"Invalid Credentials\"}"));
            }
        }
    }
}
