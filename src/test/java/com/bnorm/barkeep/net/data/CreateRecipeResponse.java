package com.bnorm.barkeep.net.data;

import retrofit2.Response;

public interface CreateRecipeResponse extends MockResponse<Recipe> {
    enum Enum implements CreateRecipeResponse {
        Success {
            @Override
            public Response<Recipe> response() {
                return Response.success(Recipe.create("Recipe1", "Description1"));
            }
        },
        Unauthorized {
            @Override
            public Response<Recipe> response() {
                return Response.error(401, MockResponse.json("{\"message\":\"Invalid Credentials\"}"));
            }
        }
    }
}
