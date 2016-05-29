package com.bnorm.barkeep.net.data;

import com.bnorm.barkeep.net.Responder;

import retrofit2.Response;

public interface UpdateBarResponder extends Responder<Bar> {
    enum Enum implements UpdateBarResponder {
        Success {
            @Override
            public Response<Bar> response() {
                return Response.success(Bar.create(1, "Private", "Bar1", "Description1"));
            }
        },
        Unauthorized {
            @Override
            public Response<Bar> response() {
                return Response.error(401, Responder.json("{\"message\":\"Invalid Credentials\"}"));
            }
        }
    }
}
