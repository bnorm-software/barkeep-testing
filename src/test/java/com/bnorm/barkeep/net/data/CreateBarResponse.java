package com.bnorm.barkeep.net.data;

import retrofit2.Response;

public interface CreateBarResponse extends MockResponse<Bar> {
    enum Enum implements CreateBarResponse {
        Success {
            @Override
            public Response<Bar> response() {
                return Response.success(Bar.create(1, "Private", "Bar1", "Description1"));
            }
        },
        Unauthorized {
            @Override
            public Response<Bar> response() {
                return Response.error(401, MockResponse.json("{\"message\":\"Invalid Credentials\"}"));
            }
        }
    }
}
