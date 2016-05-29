package com.bnorm.barkeep.net.data;

import com.bnorm.barkeep.net.Responder;

import retrofit2.Response;

public interface DeleteBarResponder extends Responder<Void> {
    enum Enum implements DeleteBarResponder {
        Success {
            @Override
            public Response<Void> response() {
                return Response.success(null);
            }
        },
        Unauthorized {
            @Override
            public Response<Void> response() {
                return Response.error(401, Responder.json("{\"message\":\"Invalid Credentials\"}"));
            }
        }
    }
}
