package com.bnorm.barkeep.net.data;

import org.junit.Test;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import static com.bnorm.barkeep.net.data.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class BarTest {

    @Test
    public void create() throws Exception {
        Bar bar = Bar.create("title", "description");
        assertThat(bar).hasId(-1).hasType(null).hasTitle("title").hasDescription("description");
    }

    @Test
    public void typeAdapterFactory() throws Exception {
        JsonAdapter<Bar> adapter = new Moshi.Builder().add(Bar.typeAdapterFactory()).build().adapter(Bar.class);

        String json = adapter.toJson(Bar.create("title", "description"));
        assertThat(json).isEqualTo("{\"id\":-1,\"title\":\"title\",\"description\":\"description\"}");

        Bar bar = adapter.fromJson("{\"id\":1,\"type\":\"type\",\"title\":\"title\",\"description\":\"description\"}");
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