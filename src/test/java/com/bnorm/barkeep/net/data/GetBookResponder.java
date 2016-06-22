package com.bnorm.barkeep.net.data;

import com.bnorm.barkeep.net.Responder;

import retrofit2.Response;

public interface GetBookResponder extends Responder<Book> {
    enum Enum implements GetBookResponder {
        Success {
            @Override
            public Response<Book> response() {
                return Response.success(Book.create(1L, "Private", "Book1", "Description1"));
            }
        },
        Unauthorized {
            @Override
            public Response<Book> response() {
                return Response.error(401, Responder.json("{\"message\":\"Invalid Credentials\"}"));
            }
        }
    }
}
