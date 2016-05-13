package com.bnorm.barkeep.net;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.bnorm.IntegrationTest;
import com.bnorm.barkeep.net.data.Bar;

import retrofit2.Response;

import static com.bnorm.barkeep.net.data.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

@Category(IntegrationTest.class)
public class BarkeepService_BarTest extends BarkeepServiceBaseTest {

    @Before
    public void login() throws Exception {
        service.login(TEST_USER).execute();
    }


    // ==================== //
    // ***** POST bar ***** //
    // ==================== //

    @Test
    public void createBar_successful() throws Exception {
        // given
        Bar bar = Bar.create("Bar1", "Description1");

        // when
        Response<Bar> response = service.createBar(bar).execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
        assertThat(response.body()).has(VALID_ID);
        assertThat(response.body()).hasTitle("Bar1");
        assertThat(response.body()).hasType("Private");
        assertThat(response.body()).hasDescription("Description1");
    }

    @Test
    public void createBar_failure_badId() throws Exception {
        // given
        Bar bar = Bar.create(1, "Public", "Bar1", "Description1");

        // when
        Response<Bar> response = service.createBar(bar).execute();

        // then
        assertThat(response.isSuccessful()).isFalse();
        assertThat(response.raw().code()).isEqualTo(CODE_FORBIDDEN);
    }


    // =================== //
    // ***** GET bar ***** //
    // =================== //

    @Test
    public void getBar_successful() throws Exception {
        // given
        Bar bar1 = service.createBar(Bar.create("Bar1", "Description1")).execute().body();

        // when
        Response<Bar> response = service.getBar(bar1.getId()).execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
        assertThat(response.body()).hasId(bar1.getId());
        assertThat(response.body()).hasTitle("Bar1");
        assertThat(response.body()).hasType("Private");
        assertThat(response.body()).hasDescription("Description1");
    }

    @Test
    public void getBar_failure_badId() throws Exception {
        // given

        // when
        Response<Bar> response = service.getBar(-1).execute();

        // then
        assertThat(response.isSuccessful()).isFalse();
        assertThat(response.raw().code()).isEqualTo(CODE_NOT_FOUND);
    }


    // ====================== //
    // ***** UPDATE bar ***** //
    // ====================== //

    @Test
    public void updateBar_successful_withValidBodyId() throws Exception {
        // given
        Bar bar1 = service.createBar(Bar.create("Bar1", "Description1")).execute().body();

        // when
        Response<Bar> response = service.updateBar(bar1.getId(), bar1.withTitle("Bar2")).execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
        assertThat(response.body()).hasId(bar1.getId());
        assertThat(response.body()).hasTitle("Bar2");
        assertThat(response.body()).hasType("Private");
        assertThat(response.body()).hasDescription("Description1");
    }

    @Test
    public void updateBar_successful_withInvalidBodyId() throws Exception {
        // given
        Bar bar1 = service.createBar(Bar.create("Bar1", "Description1")).execute().body();

        // when
        Response<Bar> response = service.updateBar(bar1.getId(), Bar.create("Bar2", "Description2")).execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
        assertThat(response.body()).hasId(bar1.getId());
        assertThat(response.body()).hasTitle("Bar2");
        assertThat(response.body()).hasType("Private");
        assertThat(response.body()).hasDescription("Description2");
    }

    @Test
    public void updateBar_failure_badId() throws Exception {
        // given

        // when
        Response<Bar> response = service.updateBar(-1, Bar.create("Bar1", "Description1")).execute();

        // then
        assertThat(response.isSuccessful()).isFalse();
        assertThat(response.raw().code()).isEqualTo(CODE_NOT_FOUND);
    }


    // ====================== //
    // ***** DELETE bar ***** //
    // ====================== //

    @Test
    public void deleteBar_successful() throws Exception {
        // given
        Bar bar1 = service.createBar(Bar.create("Bar1", "Description1")).execute().body();

        // when
        Response<Void> response = service.deleteBar(bar1.getId()).execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
    }

    @Test
    public void deleteBar_failure_badId() throws Exception {
        // given

        // when
        Response<Void> response = service.deleteBar(-1).execute();

        // then
        assertThat(response.isSuccessful()).isFalse();
        assertThat(response.raw().code()).isEqualTo(CODE_NOT_FOUND);
    }


    // ==================== //
    // ***** GET list ***** //
    // ==================== //

    @Test
    public void getBars_successful_empty() throws Exception {
        // given

        // when
        Response<List<Bar>> response = service.getBars().execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
        assertThat(response.body()).isEmpty();
    }

    @Test
    public void getBars_successful_emptyAfterDelete() throws Exception {
        // given
        Bar bar1 = service.createBar(Bar.create("Bar1", "Description1")).execute().body();
        service.deleteBar(bar1.getId()).execute();

        // when
        Response<List<Bar>> response = service.getBars().execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
        assertThat(response.body()).isEmpty();
    }

    @Test
    public void getBars_successful_single() throws Exception {
        // given
        Bar bar1 = service.createBar(Bar.create("Bar1", "Description1")).execute().body();

        // when
        Response<List<Bar>> response = service.getBars().execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
        assertThat(response.body()).hasSize(1);
        assertThat(response.body()).containsExactly(bar1);
    }

    @Test
    public void getBars_successful_singleAfterDelete() throws Exception {
        // given
        Bar bar1 = service.createBar(Bar.create("Bar1", "Description1")).execute().body();
        Bar bar2 = service.createBar(Bar.create("Bar2", "Description2")).execute().body();
        Bar bar3 = service.createBar(Bar.create("Bar3", "Description3")).execute().body();
        service.deleteBar(bar1.getId()).execute();
        service.deleteBar(bar3.getId()).execute();

        // when
        Response<List<Bar>> response = service.getBars().execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
        assertThat(response.body()).hasSize(1);
        assertThat(response.body()).containsExactly(bar2);
    }

    @Test
    public void getBars_successful_multiple() throws Exception {
        // given
        Bar bar1 = service.createBar(Bar.create("Bar1", "Description1")).execute().body();
        Bar bar3 = service.createBar(Bar.create("Bar3", "Description3")).execute().body();
        Bar bar2 = service.createBar(Bar.create("Bar2", "Description2")).execute().body();

        // when
        Response<List<Bar>> response = service.getBars().execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
        assertThat(response.body()).hasSize(3);
        assertThat(response.body()).containsExactly(bar1, bar3, bar2);
    }

    @Test
    public void getBars_successful_multipleAfterDelete() throws Exception {
        // given
        Bar bar5 = service.createBar(Bar.create("Bar5", "Description5")).execute().body();
        Bar bar2 = service.createBar(Bar.create("Bar2", "Description2")).execute().body();
        Bar bar3 = service.createBar(Bar.create("Bar3", "Description3")).execute().body();
        Bar bar4 = service.createBar(Bar.create("Bar4", "Description4")).execute().body();
        Bar bar1 = service.createBar(Bar.create("Bar1", "Description1")).execute().body();
        service.deleteBar(bar4.getId()).execute();
        service.deleteBar(bar2.getId()).execute();

        // when
        Response<List<Bar>> response = service.getBars().execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
        assertThat(response.body()).hasSize(3);
        assertThat(response.body()).containsExactly(bar5, bar3, bar1);
    }
}
