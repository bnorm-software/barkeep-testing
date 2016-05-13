package com.bnorm.barkeep.net.data;

import java.util.Collections;
import java.util.List;

import retrofit2.Response;

public enum GetBarsResponse implements MockResponse<List<Bar>> {
    Success {
        @Override
        public Response<List<Bar>> response() {
            return Response.success(Collections.singletonList(Bar.create(1, "Private", "Bar1", "Description1")));
        }
    },
    Unauthorized {
        @Override
        public Response<List<Bar>> response() {
            return Response.error(401, MockResponse.json("{\"message\":\"Invalid Credentials\"}"));
        }
    }
}
