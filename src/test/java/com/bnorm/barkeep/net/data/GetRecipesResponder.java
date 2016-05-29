package com.bnorm.barkeep.net.data;

import java.util.Collections;
import java.util.List;

import com.bnorm.barkeep.net.Responder;

import retrofit2.Response;

public interface GetRecipesResponder extends Responder<List<Recipe>> {
    enum Enum implements GetRecipesResponder {
        Success {
            @Override
            public Response<List<Recipe>> response() {
                return Response.success(Collections.singletonList(Recipe.create("Recipe1", "Description1")));
            }
        },
        Unauthorized {
            @Override
            public Response<List<Recipe>> response() {
                return Response.error(401, Responder.json("{\"message\":\"Invalid Credentials\"}"));
            }
        }
    }
}
