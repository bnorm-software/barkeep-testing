package com.bnorm.barkeep.net;

import java.util.List;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.bnorm.IntegrationTest;
import com.bnorm.barkeep.net.data.Book;

import retrofit2.Response;

import static com.bnorm.barkeep.net.data.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

@Category(IntegrationTest.class)
public class BarkeepService_BookTest extends BarkeepServiceBaseTest {

    @Before
    public void login() throws Exception {
        service.login(TEST_USER).execute();
    }


    // ===================== //
    // ***** POST book ***** //
    // ===================== //

    @Test
    public void createBook_successful() throws Exception {
        // given
        Book book = Book.create("Book1", "Description1");

        // when
        Response<Book> response = service.createBook(book).execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
        assertThat(response.body()).has(VALID_ID);
        assertThat(response.body()).hasTitle("Book1");
        assertThat(response.body()).hasType("Private");
        assertThat(response.body()).hasDescription("Description1");
    }

    @Test
    public void createBook_failure_badId() throws Exception {
        // given
        Book book = Book.create(1L, "Public", "Book1", "Description1");

        // when
        Response<Book> response = service.createBook(book).execute();

        // then
        assertThat(response.isSuccessful()).isFalse();
        assertThat(response.raw().code()).isEqualTo(CODE_FORBIDDEN);
    }


    // ==================== //
    // ***** GET book ***** //
    // ==================== //

    @Test
    public void getBook_successful() throws Exception {
        // given
        Book book1 = service.createBook(Book.create("Book1", "Description1")).execute().body();

        // when
        Response<Book> response = service.getBook(Objects.requireNonNull(book1.getId())).execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
        assertThat(response.body()).hasId(book1.getId());
        assertThat(response.body()).hasTitle("Book1");
        assertThat(response.body()).hasType("Private");
        assertThat(response.body()).hasDescription("Description1");
    }

    @Test
    public void getBook_failure_badId() throws Exception {
        // given

        // when
        Response<Book> response = service.getBook(-1).execute();

        // then
        assertThat(response.isSuccessful()).isFalse();
        assertThat(response.raw().code()).isEqualTo(CODE_NOT_FOUND);
    }


    // ======================= //
    // ***** UPDATE book ***** //
    // ======================= //

    @Test
    public void updateBook_successful_withValidBodyId() throws Exception {
        // given
        Book book1 = service.createBook(Book.create("Book1", "Description1")).execute().body();

        // when
        Response<Book> response = service.updateBook(Objects.requireNonNull(book1.getId()), book1.withTitle("Book2"))
                                         .execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
        assertThat(response.body()).hasId(book1.getId());
        assertThat(response.body()).hasTitle("Book2");
        assertThat(response.body()).hasType("Private");
        assertThat(response.body()).hasDescription("Description1");
    }

    @Test
    public void updateBook_successful_withInvalidBodyId() throws Exception {
        // given
        Book book1 = service.createBook(Book.create("Book1", "Description1")).execute().body();

        // when
        Response<Book> response = service.updateBook(Objects.requireNonNull(book1.getId()),
                                                     Book.create("Book2", "Description2")).execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
        assertThat(response.body()).hasId(book1.getId());
        assertThat(response.body()).hasTitle("Book2");
        assertThat(response.body()).hasType("Private");
        assertThat(response.body()).hasDescription("Description2");
    }

    @Test
    public void updateBook_failure_badId() throws Exception {
        // given

        // when
        Response<Book> response = service.updateBook(-1, Book.create("Book1", "Description1")).execute();

        // then
        assertThat(response.isSuccessful()).isFalse();
        assertThat(response.raw().code()).isEqualTo(CODE_NOT_FOUND);
    }


    // ======================= //
    // ***** DELETE book ***** //
    // ======================= //

    @Test
    public void deleteBook_successful() throws Exception {
        // given
        Book book1 = service.createBook(Book.create("Book1", "Description1")).execute().body();

        // when
        Response<Void> response = service.deleteBook(Objects.requireNonNull(book1.getId())).execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
    }

    @Test
    public void deleteBook_failure_badId() throws Exception {
        // given

        // when
        Response<Void> response = service.deleteBook(-1).execute();

        // then
        assertThat(response.isSuccessful()).isFalse();
        assertThat(response.raw().code()).isEqualTo(CODE_NOT_FOUND);
    }


    // ==================== //
    // ***** GET list ***** //
    // ==================== //

    @Test
    public void getBooks_successful_empty() throws Exception {
        // given

        // when
        Response<List<Book>> response = service.getBooks().execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
        assertThat(response.body()).isEmpty();
    }

    @Test
    public void getBooks_successful_emptyAfterDelete() throws Exception {
        // given
        Book book1 = service.createBook(Book.create("Book1", "Description1")).execute().body();
        service.deleteBook(Objects.requireNonNull(book1.getId())).execute();

        // when
        Response<List<Book>> response = service.getBooks().execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
        assertThat(response.body()).isEmpty();
    }

    @Test
    public void getBooks_successful_single() throws Exception {
        // given
        Book book1 = service.createBook(Book.create("Book1", "Description1")).execute().body();

        // when
        Response<List<Book>> response = service.getBooks().execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
        assertThat(response.body()).hasSize(1);
        assertThat(response.body()).containsExactly(book1);
    }

    @Test
    public void getBooks_successful_singleAfterDelete() throws Exception {
        // given
        Book book1 = service.createBook(Book.create("Book1", "Description1")).execute().body();
        Book book2 = service.createBook(Book.create("Book2", "Description2")).execute().body();
        Book book3 = service.createBook(Book.create("Book3", "Description3")).execute().body();
        service.deleteBook(Objects.requireNonNull(book1.getId())).execute();
        service.deleteBook(Objects.requireNonNull(book3.getId())).execute();

        // when
        Response<List<Book>> response = service.getBooks().execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
        assertThat(response.body()).hasSize(1);
        assertThat(response.body()).containsExactly(book2);
    }

    @Test
    public void getBooks_successful_multiple() throws Exception {
        // given
        Book book1 = service.createBook(Book.create("Book1", "Description1")).execute().body();
        Book book3 = service.createBook(Book.create("Book3", "Description3")).execute().body();
        Book book2 = service.createBook(Book.create("Book2", "Description2")).execute().body();

        // when
        Response<List<Book>> response = service.getBooks().execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
        assertThat(response.body()).hasSize(3);
        assertThat(response.body()).containsExactly(book1, book3, book2);
    }

    @Test
    public void getBooks_successful_multipleAfterDelete() throws Exception {
        // given
        Book book5 = service.createBook(Book.create("Book5", "Description5")).execute().body();
        Book book2 = service.createBook(Book.create("Book2", "Description2")).execute().body();
        Book book3 = service.createBook(Book.create("Book3", "Description3")).execute().body();
        Book book4 = service.createBook(Book.create("Book4", "Description4")).execute().body();
        Book book1 = service.createBook(Book.create("Book1", "Description1")).execute().body();
        service.deleteBook(Objects.requireNonNull(book4.getId())).execute();
        service.deleteBook(Objects.requireNonNull(book2.getId())).execute();

        // when
        Response<List<Book>> response = service.getBooks().execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
        assertThat(response.body()).hasSize(3);
        assertThat(response.body()).containsExactly(book5, book3, book1);
    }
}
