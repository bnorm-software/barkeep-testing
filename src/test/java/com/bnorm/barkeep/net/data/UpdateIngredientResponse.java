package com.bnorm.barkeep.net.data;

import retrofit2.Response;

public interface UpdateIngredientResponse extends MockResponse<Ingredient> {
    enum Enum implements UpdateIngredientResponse {
        Success {
            @Override
            public Response<Ingredient> response() {
                return Response.success(Ingredient.create(1, "Ingredient1", null));
            }
        },
        Unauthorized {
            @Override
            public Response<Ingredient> response() {
                return Response.error(401, MockResponse.json("{\"message\":\"Invalid Credentials\"}"));
            }
        }
    }
}
