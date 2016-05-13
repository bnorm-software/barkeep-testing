package com.bnorm.barkeep.net.data;

import java.util.Collections;
import java.util.List;

import retrofit2.Response;

public interface GetRecipesResponse extends MockResponse<List<Recipe>> {
    enum Enum implements GetRecipesResponse {
        Success {
            @Override
            public Response<List<Recipe>> response() {
                return Response.success(Collections.singletonList(Recipe.create("Recipe1", "Description1")));
            }
        },
        Unauthorized {
            @Override
            public Response<List<Recipe>> response() {
                return Response.error(401, MockResponse.json("{\"message\":\"Invalid Credentials\"}"));
            }
        }
    }
}
