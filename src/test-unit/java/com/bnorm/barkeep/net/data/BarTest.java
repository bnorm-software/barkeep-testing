package com.bnorm.barkeep.net.data;

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.bnorm.ResourcePath;
import com.bnorm.ResourcePathsRule;
import com.bnorm.UnitTest;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import static com.bnorm.barkeep.net.data.Assertions.assertThat;
import static com.bnorm.barkeep.net.data.Sources.bars;
import static com.bnorm.barkeep.net.data.Sources.descriptions;
import static com.bnorm.barkeep.net.data.Sources.titles;
import static com.bnorm.barkeep.net.data.Sources.types;
import static com.bnorm.qt.Consumers.throwing;
import static org.assertj.core.api.Assertions.assertThat;
import static org.quicktheories.quicktheories.QuickTheory.qt;
import static org.quicktheories.quicktheories.generators.SourceDSL.strings;

@Category(UnitTest.class)
public class BarTest {

    private static final String EXPECTED = "bar.expected.json";
    private static final String INPUT = "bar.input.json";

    @Rule
    public final ResourcePathsRule paths = new ResourcePathsRule();

    @Test
    public void create() throws Exception {
        qt().forAll(titles(), descriptions()) //
            .asWithPrecursor(Bar::create) //
            .checkAssert((title, description, bar) -> {
                assertThat(bar).hasId(null) //
                               .hasType(null) //
                               .hasTitle(title) //
                               .hasDescription(description);
            });
    }

    @Test
    @ResourcePath(EXPECTED)
    @ResourcePath(INPUT)
    public void typeAdapterFactory() throws Exception {
        JsonAdapter<Bar> adapter = Bar.jsonAdapter(new Moshi.Builder().build());

        String json = adapter.toJson(Bar.create("title", "description"));
        assertThat(json).isEqualTo(paths.string(EXPECTED));

        Bar bar = adapter.fromJson(paths.string(INPUT));
        assertThat(bar).hasId(1L) //
                       .hasType("type") //
                       .hasTitle("title") //
                       .hasDescription("description");

        // ascii is the largest set that passes
        qt().forAll(bars(strings().ascii().ofLengthBetween(0, 20))) //
            .checkAssert(throwing(here -> {
                String there = adapter.toJson(here);
                Bar back = adapter.fromJson(there);
                assertThat(here).isEqualTo(back);
            }));
    }

    @Test
    public void withType() throws Exception {
        Bar bar = Bar.create("title", "description");
        assertThat(bar).hasType(null);

        qt().forAll(types()) //
            .asWithPrecursor(bar::withType) //
            .checkAssert((newType, newBar) -> {
                assertThat(newBar).hasId(null) //
                                  .hasType(newType) //
                                  .hasTitle("title") //
                                  .hasDescription("description");
            });
    }

    @Test
    public void withTitle() throws Exception {
        Bar bar = Bar.create("title", "description");
        assertThat(bar).hasTitle("title");

        qt().forAll(titles()) //
            .asWithPrecursor(bar::withTitle) //
            .checkAssert((newTitle, newBar) -> {
                assertThat(newBar).hasId(null) //
                                  .hasType(null) //
                                  .hasTitle(newTitle) //
                                  .hasDescription("description");
            });
    }

    @Test
    public void withDescription() throws Exception {
        Bar bar = Bar.create("title", "description");
        assertThat(bar).hasDescription("description");

        qt().forAll(descriptions()) //
            .asWithPrecursor(bar::withDescription) //
            .checkAssert((newDescription, newBar) -> {
                assertThat(newBar).hasId(null) //
                                  .hasType(null) //
                                  .hasTitle("title") //
                                  .hasDescription(newDescription);
            });
    }
}
