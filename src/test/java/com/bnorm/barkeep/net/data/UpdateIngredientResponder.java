package com.bnorm.barkeep.net.data;

import com.bnorm.barkeep.net.Responder;

import retrofit2.Response;

public interface UpdateIngredientResponder extends Responder<Ingredient> {
    enum Enum implements UpdateIngredientResponder {
        Success {
            @Override
            public Response<Ingredient> response() {
                return Response.success(Ingredient.create(1, "Ingredient1", null));
            }
        },
        Unauthorized {
            @Override
            public Response<Ingredient> response() {
                return Response.error(401, Responder.json("{\"message\":\"Invalid Credentials\"}"));
            }
        }
    }
}
