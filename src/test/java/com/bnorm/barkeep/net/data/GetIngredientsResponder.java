package com.bnorm.barkeep.net.data;

import java.util.Collections;
import java.util.List;

import com.bnorm.barkeep.net.Responder;

import retrofit2.Response;

public interface GetIngredientsResponder extends Responder<List<Ingredient>> {
    enum Enum implements GetIngredientsResponder {
        Success {
            @Override
            public Response<List<Ingredient>> response() {
                return Response.success(Collections.singletonList(Ingredient.create(1, "Ingredient1", null)));
            }
        },
        Unauthorized {
            @Override
            public Response<List<Ingredient>> response() {
                return Response.error(401, Responder.json("{\"message\":\"Invalid Credentials\"}"));
            }
        }
    }
}
