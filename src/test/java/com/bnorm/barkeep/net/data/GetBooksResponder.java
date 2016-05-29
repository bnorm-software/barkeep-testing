package com.bnorm.barkeep.net.data;

import java.util.Collections;
import java.util.List;

import com.bnorm.barkeep.net.Responder;

import retrofit2.Response;

public interface GetBooksResponder extends Responder<List<Book>> {
    enum Enum implements GetBooksResponder {
        Success {
            @Override
            public Response<List<Book>> response() {
                return Response.success(Collections.singletonList(Book.create(1, "Private", "Book1", "Description1")));
            }
        },
        Unauthorized {
            @Override
            public Response<List<Book>> response() {
                return Response.error(401, Responder.json("{\"message\":\"Invalid Credentials\"}"));
            }
        }
    }
}
