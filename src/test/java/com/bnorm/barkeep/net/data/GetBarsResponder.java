package com.bnorm.barkeep.net.data;

import java.util.Collections;
import java.util.List;

import com.bnorm.barkeep.net.Responder;

import retrofit2.Response;

public interface GetBarsResponder extends Responder<List<Bar>> {
    enum Enum implements GetBarsResponder {
        Success {
            @Override
            public Response<List<Bar>> response() {
                return Response.success(Collections.singletonList(Bar.create(1, "Private", "Bar1", "Description1")));
            }
        },
        Unauthorized {
            @Override
            public Response<List<Bar>> response() {
                return Response.error(401, Responder.json("{\"message\":\"Invalid Credentials\"}"));
            }
        }
    }
}
