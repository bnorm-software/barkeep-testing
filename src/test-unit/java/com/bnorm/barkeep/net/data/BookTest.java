package com.bnorm.barkeep.net.data;

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.bnorm.ResourcePath;
import com.bnorm.ResourcePathsRule;
import com.bnorm.UnitTest;
import com.bnorm.barkeep.net.data.Book;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import static com.bnorm.barkeep.net.data.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

@Category(UnitTest.class)
public class BookTest {

    private static final String EXPECTED = "book.expected.json";
    private static final String INPUT = "book.input.json";

    @Rule
    public final ResourcePathsRule paths = new ResourcePathsRule();

    @Test
    public void create() throws Exception {
        Book book = Book.create("title", "description");
        assertThat(book).hasId(-1).hasType(null).hasTitle("title").hasDescription("description");
    }

    @Test
    @ResourcePath(EXPECTED)
    @ResourcePath(INPUT)
    public void typeAdapterFactory() throws Exception {
        JsonAdapter<Book> adapter = Book.jsonAdapter(new Moshi.Builder().build());

        String json = adapter.toJson(Book.create("title", "description"));
        assertThat(json).isEqualTo(paths.string(EXPECTED));

        Book book = adapter.fromJson(paths.string(INPUT));
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
