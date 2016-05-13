package com.bnorm.barkeep.net.data;

import retrofit2.Response;

public interface LoginResponse extends MockResponse<Void> {
    enum Enum implements LoginResponse {
        Success {
            @Override
            public Response<Void> response() {
                return Response.success(null);
            }
        },
        Unauthorized {
            @Override
            public Response<Void> response() {
                return Response.error(401, MockResponse.json("{\"message\":\"Invalid Credentials\"}"));
            }
        }
    }
}
