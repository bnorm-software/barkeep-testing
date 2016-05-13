package com.bnorm.barkeep.net;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.bnorm.IntegrationTest;
import com.bnorm.barkeep.net.data.Ingredient;

import retrofit2.Response;

import static com.bnorm.barkeep.net.data.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

@Category(IntegrationTest.class)
public class BarkeepService_IngredientTest extends BarkeepServiceBaseTest {

    @Before
    public void login() throws Exception {
        service.login(TEST_USER).execute();
    }


    // =========================== //
    // ***** POST ingredient ***** //
    // =========================== //

    @Test
    public void createIngredient_successful() throws Exception {
        // given
        Ingredient ingredient = Ingredient.create("Ingredient1");

        // when
        Response<Ingredient> response = service.createIngredient(ingredient).execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
        assertThat(response.body()).has(VALID_ID);
        assertThat(response.body()).hasTitle("Ingredient1");
        assertThat(response.body()).hasBase(null);
    }

    @Test
    public void createIngredient_failure_badId() throws Exception {
        // given
        Ingredient ingredient = Ingredient.create(1, "Ingredient1", null);

        // when
        Response<Ingredient> response = service.createIngredient(ingredient).execute();

        // then
        assertThat(response.isSuccessful()).isFalse();
        assertThat(response.raw().code()).isEqualTo(CODE_FORBIDDEN);
    }


    // ========================== //
    // ***** GET ingredient ***** //
    // ========================== //

    @Test
    public void getIngredient_successful() throws Exception {
        // given
        Ingredient ingredient1 = service.createIngredient(Ingredient.create("Ingredient1")).execute().body();

        // when
        Response<Ingredient> response = service.getIngredient(ingredient1.getId()).execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
        assertThat(response.body()).hasId(ingredient1.getId());
        assertThat(response.body()).hasTitle("Ingredient1");
        assertThat(response.body()).hasBase(null);
    }

    @Test
    public void getIngredient_failure_badId() throws Exception {
        // given

        // when
        Response<Ingredient> response = service.getIngredient(-1).execute();

        // then
        assertThat(response.isSuccessful()).isFalse();
        assertThat(response.raw().code()).isEqualTo(CODE_NOT_FOUND);
    }


    // ============================= //
    // ***** UPDATE ingredient ***** //
    // ============================= //

    @Test
    public void updateIngredient_successful_withValidBodyId() throws Exception {
        // given
        Ingredient ingredient1 = service.createIngredient(Ingredient.create("Ingredient1")).execute().body();

        // when
        Response<Ingredient> response = service.updateIngredient(ingredient1.getId(),
                                                                 ingredient1.withTitle("Ingredient2")).execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
        assertThat(response.body()).hasId(ingredient1.getId());
        assertThat(response.body()).hasTitle("Ingredient2");
        assertThat(response.body()).hasBase(null);
    }

    @Test
    public void updateIngredient_successful_withInvalidBodyId() throws Exception {
        // given
        Ingredient ingredient1 = service.createIngredient(Ingredient.create("Ingredient1")).execute().body();

        // when
        Response<Ingredient> response = service.updateIngredient(ingredient1.getId(), Ingredient.create("Ingredient2"))
                                               .execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
        assertThat(response.body()).hasId(ingredient1.getId());
        assertThat(response.body()).hasTitle("Ingredient2");
        assertThat(response.body()).hasBase(null);
    }

    @Test
    public void updateIngredient_failure_badId() throws Exception {
        // given

        // when
        Response<Ingredient> response = service.updateIngredient(-1, Ingredient.create("Ingredient1")).execute();

        // then
        assertThat(response.isSuccessful()).isFalse();
        assertThat(response.raw().code()).isEqualTo(CODE_NOT_FOUND);
    }


    // ============================= //
    // ***** DELETE ingredient ***** //
    // ============================= //

    @Test
    public void deleteIngredient_successful() throws Exception {
        // given
        Ingredient ingredient1 = service.createIngredient(Ingredient.create("Ingredient1")).execute().body();

        // when
        Response<Void> response = service.deleteIngredient(ingredient1.getId()).execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
    }

    @Test
    public void deleteIngredient_failure_badId() throws Exception {
        // given

        // when
        Response<Void> response = service.deleteIngredient(-1).execute();

        // then
        assertThat(response.isSuccessful()).isFalse();
        assertThat(response.raw().code()).isEqualTo(CODE_NOT_FOUND);
    }


    // ==================== //
    // ***** GET list ***** //
    // ==================== //

    @Test
    public void getIngredients_successful_empty() throws Exception {
        // given

        // when
        Response<List<Ingredient>> response = service.getIngredients().execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
        assertThat(response.body()).isEmpty();
    }

    @Test
    public void getIngredients_successful_emptyAfterDelete() throws Exception {
        // given
        Ingredient ingredient1 = service.createIngredient(Ingredient.create("Ingredient1")).execute().body();
        service.deleteIngredient(ingredient1.getId()).execute();

        // when
        Response<List<Ingredient>> response = service.getIngredients().execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
        assertThat(response.body()).isEmpty();
    }

    @Test
    public void getIngredients_successful_single() throws Exception {
        // given
        Ingredient ingredient1 = service.createIngredient(Ingredient.create("Ingredient1")).execute().body();

        // when
        Response<List<Ingredient>> response = service.getIngredients().execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
        assertThat(response.body()).hasSize(1);
        assertThat(response.body()).containsExactly(ingredient1);
    }

    @Test
    public void getIngredients_successful_singleAfterDelete() throws Exception {
        // given
        Ingredient ingredient1 = service.createIngredient(Ingredient.create("Ingredient1")).execute().body();
        Ingredient ingredient2 = service.createIngredient(Ingredient.create("Ingredient2")).execute().body();
        Ingredient ingredient3 = service.createIngredient(Ingredient.create("Ingredient3")).execute().body();
        service.deleteIngredient(ingredient1.getId()).execute();
        service.deleteIngredient(ingredient3.getId()).execute();

        // when
        Response<List<Ingredient>> response = service.getIngredients().execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
        assertThat(response.body()).hasSize(1);
        assertThat(response.body()).containsExactly(ingredient2);
    }

    @Test
    public void getIngredients_successful_multiple() throws Exception {
        // given
        Ingredient ingredient1 = service.createIngredient(Ingredient.create("Ingredient1")).execute().body();
        Ingredient ingredient3 = service.createIngredient(Ingredient.create("Ingredient3")).execute().body();
        Ingredient ingredient2 = service.createIngredient(Ingredient.create("Ingredient2")).execute().body();

        // when
        Response<List<Ingredient>> response = service.getIngredients().execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
        assertThat(response.body()).hasSize(3);
        assertThat(response.body()).containsExactly(ingredient1, ingredient3, ingredient2);
    }

    @Test
    public void getIngredients_successful_multipleAfterDelete() throws Exception {
        // given
        Ingredient ingredient5 = service.createIngredient(Ingredient.create("Ingredient5")).execute().body();
        Ingredient ingredient2 = service.createIngredient(Ingredient.create("Ingredient2")).execute().body();
        Ingredient ingredient3 = service.createIngredient(Ingredient.create("Ingredient3")).execute().body();
        Ingredient ingredient4 = service.createIngredient(Ingredient.create("Ingredient4")).execute().body();
        Ingredient ingredient1 = service.createIngredient(Ingredient.create("Ingredient1")).execute().body();
        service.deleteIngredient(ingredient4.getId()).execute();
        service.deleteIngredient(ingredient2.getId()).execute();

        // when
        Response<List<Ingredient>> response = service.getIngredients().execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
        assertThat(response.body()).hasSize(3);
        assertThat(response.body()).containsExactly(ingredient5, ingredient3, ingredient1);
    }
}
