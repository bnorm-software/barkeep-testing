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
import static com.bnorm.barkeep.net.data.Sources.books;
import static com.bnorm.barkeep.net.data.Sources.descriptions;
import static com.bnorm.barkeep.net.data.Sources.titles;
import static com.bnorm.barkeep.net.data.Sources.types;
import static com.bnorm.qt.Consumers.throwing;
import static org.assertj.core.api.Assertions.assertThat;
import static org.quicktheories.quicktheories.QuickTheory.qt;
import static org.quicktheories.quicktheories.generators.SourceDSL.strings;

@Category(UnitTest.class)
public class BookTest {

    private static final String EXPECTED = "book.expected.json";
    private static final String INPUT = "book.input.json";

    @Rule
    public final ResourcePathsRule paths = new ResourcePathsRule();

    @Test
    public void create() throws Exception {
        qt().forAll(titles(), descriptions())
            .asWithPrecursor(Book::create)
            .checkAssert((title, description, book) -> {
                assertThat(book).hasId(null) //
                                .hasType(null) //
                                .hasTitle(title) //
                                .hasDescription(description);
            });
    }

    @Test
    @ResourcePath(EXPECTED)
    @ResourcePath(INPUT)
    public void typeAdapterFactory() throws Exception {
        JsonAdapter<Book> adapter = Book.jsonAdapter(new Moshi.Builder().build());

        String json = adapter.toJson(Book.create("title", "description"));
        assertThat(json).isEqualTo(paths.string(EXPECTED));

        Book book = adapter.fromJson(paths.string(INPUT));
        assertThat(book).hasId(1L) //
                        .hasType("type") //
                        .hasTitle("title") //
                        .hasDescription("description");

        // ascii is the largest set that passes
        qt().forAll(books(strings().ascii().ofLengthBetween(0, 20))) //
            .checkAssert(throwing(here -> {
                String there = adapter.toJson(here);
                Book back = adapter.fromJson(there);
                assertThat(here).isEqualTo(back);
            }));
    }

    @Test
    public void withType() throws Exception {
        Book book = Book.create("title", "description");
        assertThat(book).hasType(null);

        qt().forAll(types()) //
            .asWithPrecursor(book::withType) //
            .checkAssert((newType, newBook) -> {
                assertThat(newBook).hasId(null) //
                                   .hasType(newType) //
                                   .hasTitle("title") //
                                   .hasDescription("description");
            });
    }

    @Test
    public void withTitle() throws Exception {
        Book book = Book.create("title", "description");
        assertThat(book).hasTitle("title");

        qt().forAll(titles()) //
            .asWithPrecursor(book::withTitle) //
            .checkAssert((newTitle, newBook) -> {
                assertThat(newBook).hasId(null) //
                                   .hasType(null) //
                                   .hasTitle(newTitle) //
                                   .hasDescription("description");
            });
    }

    @Test
    public void withDescription() throws Exception {
        Book book = Book.create("title", "description");
        assertThat(book).hasDescription("description");

        qt().forAll(descriptions()) //
            .asWithPrecursor(book::withDescription) //
            .checkAssert((newDescription, newBook) -> {
                assertThat(newBook).hasId(null) //
                                   .hasType(null) //
                                   .hasTitle("title") //
                                   .hasDescription(newDescription);
            });
    }
}
