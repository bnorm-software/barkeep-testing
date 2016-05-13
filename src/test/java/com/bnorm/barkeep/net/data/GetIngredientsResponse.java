package com.bnorm.barkeep.net.data;

import java.util.Collections;
import java.util.List;

import retrofit2.Response;

public interface GetIngredientsResponse extends MockResponse<List<Ingredient>> {
    enum Enum implements GetIngredientsResponse {
        Success {
            @Override
            public Response<List<Ingredient>> response() {
                return Response.success(Collections.singletonList(Ingredient.create(1, "Ingredient1", null)));
            }
        },
        Unauthorized {
            @Override
            public Response<List<Ingredient>> response() {
                return Response.error(401, MockResponse.json("{\"message\":\"Invalid Credentials\"}"));
            }
        }
    }
}
