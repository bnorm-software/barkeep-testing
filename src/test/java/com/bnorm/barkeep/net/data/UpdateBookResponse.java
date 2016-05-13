package com.bnorm.barkeep.net.data;

import retrofit2.Response;

public interface UpdateBookResponse extends MockResponse<Book> {
    enum Enum implements UpdateBookResponse {
        Success {
            @Override
            public Response<Book> response() {
                return Response.success(Book.create(1, "Private", "Book1", "Description1"));
            }
        },
        Unauthorized {
            @Override
            public Response<Book> response() {
                return Response.error(401, MockResponse.json("{\"message\":\"Invalid Credentials\"}"));
            }
        }
    }
}
