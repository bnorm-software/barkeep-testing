package com.bnorm.barkeep.net.data;

import com.bnorm.barkeep.net.Responder;

import retrofit2.Response;

public interface GetRecipeResponder extends Responder<Recipe> {
    enum Enum implements GetRecipeResponder {
        Success {
            @Override
            public Response<Recipe> response() {
                return Response.success(Recipe.create("Recipe1", "Description1"));
            }
        },
        Unauthorized {
            @Override
            public Response<Recipe> response() {
                return Response.error(401, Responder.json("{\"message\":\"Invalid Credentials\"}"));
            }
        }
    }
}
