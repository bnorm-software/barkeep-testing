package com.bnorm.barkeep.net.data;

import com.bnorm.barkeep.net.Responder;

import retrofit2.Response;

public interface LogoutResponder extends Responder<Void> {
    enum Enum implements LogoutResponder {
        Success {
            @Override
            public Response<Void> response() {
                return Response.success(null);
            }
        },
        NotLoggedIn {
            @Override
            public Response<Void> response() {
                return Response.error(401, Responder.json("{\"message\":\"You are not logged in.\"}"));
            }
        }
    }
}
