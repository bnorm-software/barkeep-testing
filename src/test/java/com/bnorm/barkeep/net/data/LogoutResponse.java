package com.bnorm.barkeep.net.data;

import retrofit2.Response;

public enum LogoutResponse implements MockResponse<Void> {
    Success {
        @Override
        public Response<Void> response() {
            return Response.success(null);
        }
    },
    NotLoggedIn {
        @Override
        public Response<Void> response() {
            return Response.error(401, MockResponse.json("{\"message\":\"You are not logged in.\"}"));
        }
    }
}
