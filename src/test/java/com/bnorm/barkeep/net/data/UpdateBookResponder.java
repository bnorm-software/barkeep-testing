package com.bnorm.barkeep.net.data;

import com.bnorm.barkeep.net.Responder;

import retrofit2.Response;

public interface UpdateBookResponder extends Responder<Book> {
    enum Enum implements UpdateBookResponder {
        Success {
            @Override
            public Response<Book> response() {
                return Response.success(Book.create(1, "Private", "Book1", "Description1"));
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
