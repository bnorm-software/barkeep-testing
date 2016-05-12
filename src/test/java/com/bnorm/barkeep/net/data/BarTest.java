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
import static org.assertj.core.api.Assertions.assertThat;

@Category(UnitTest.class)
public class BarTest {

    private static final String EXPECTED = "bar.expected.json";
    private static final String INPUT = "bar.input.json";

    @Rule
    public final ResourcePathsRule paths = new ResourcePathsRule();

    @Test
    public void create() throws Exception {
        Bar bar = Bar.create("title", "description");
        assertThat(bar).hasId(-1).hasType(null).hasTitle("title").hasDescription("description");
    }

    @Test
    @ResourcePath(EXPECTED)
    @ResourcePath(INPUT)
    public void typeAdapterFactory() throws Exception {
        JsonAdapter<Bar> adapter = Bar.jsonAdapter(new Moshi.Builder().build());

        String json = adapter.toJson(Bar.create("title", "description"));
        assertThat(json).isEqualTo(paths.string(EXPECTED));

        Bar bar = adapter.fromJson(paths.string(INPUT));
        assertThat(bar).hasId(1).hasType("type").hasTitle("title").hasDescription("description");
    }

    @Test
    public void withType() throws Exception {
        Bar bar = Bar.create("title", "description");
        assertThat(bar).hasType(null);

        bar = bar.withType("type");
        assertThat(bar).hasType("type");
    }

    @Test
    public void withTitle() throws Exception {
        Bar bar = Bar.create("title", "description");
        assertThat(bar).hasTitle("title");

        bar = bar.withTitle("title1");
        assertThat(bar).hasTitle("title1");
    }

    @Test
    public void withDescription() throws Exception {
        Bar bar = Bar.create("title", "description");
        assertThat(bar).hasDescription("description");

        bar = bar.withDescription("description1");
        assertThat(bar).hasDescription("description1");
    }
}
