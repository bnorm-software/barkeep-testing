package com.bnorm.barkeep.net.data;

import org.junit.Test;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import static com.bnorm.barkeep.net.data.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class BookTest {

    @Test
    public void create() throws Exception {
        Book book = Book.create("title", "description");
        assertThat(book).hasId(-1).hasType(null).hasTitle("title").hasDescription("description");
    }

    @Test
    public void typeAdapterFactory() throws Exception {
        JsonAdapter<Book> adapter = new Moshi.Builder().add(Book.typeAdapterFactory()).build().adapter(Book.class);

        String json = adapter.toJson(Book.create("title", "description"));
        assertThat(json).isEqualTo("{\"id\":-1,\"title\":\"title\",\"description\":\"description\"}");

        Book book = adapter.fromJson("{\"id\":1,\"type\":\"type\",\"title\":\"title\",\"description\":\"description\"}");
        assertThat(book).hasId(1).hasType("type").hasTitle("title").hasDescription("description");
    }

    @Test
    public void withType() throws Exception {
        Book book = Book.create("title", "description");
        assertThat(book).hasType(null);

        book = book.withType("type");
        assertThat(book).hasType("type");
    }

    @Test
    public void withTitle() throws Exception {
        Book book = Book.create("title", "description");
        assertThat(book).hasTitle("title");

        book = book.withTitle("title1");
        assertThat(book).hasTitle("title1");
    }

    @Test
    public void withDescription() throws Exception {
        Book book = Book.create("title", "description");
        assertThat(book).hasDescription("description");

        book = book.withDescription("description1");
        assertThat(book).hasDescription("description1");
    }
}