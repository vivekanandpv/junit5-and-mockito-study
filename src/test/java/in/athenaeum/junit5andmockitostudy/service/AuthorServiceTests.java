package in.athenaeum.junit5andmockitostudy.service;

import in.athenaeum.junit5andmockitostudy.model.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("AuthorService should")
class AuthorServiceTests {

    //  return value testing
    @Test
    @DisplayName("return an author when getRandomAuthor() is called")
    public void getRandomAuthorReturnsAuthor() {
        //  Arrange
        AuthorService service = new AuthorService();

        //  Act
        Author author = service.getRandomAuthor();

        //  Assert
        assertNotNull(author);
    }

    //  this is now parameterized
    @ParameterizedTest
    @ValueSource(ints = {12, 14, 144, 16, 18, 24})
    @DisplayName("return an author by id, when id is found")
    public void getAuthorByIdWhenIdIsFound(int id) {
        //  Arrange
        AuthorService service = new AuthorService();

        //  Act
        Author author = service.getById(id);

        //  Multiple asserts in one
        assertAll(
                () -> assertEquals(id, author.getId()),
                () -> assertEquals("India", author.getCountry())
        );
    }


    //  this is now parameterized
    @ParameterizedTest
    @ValueSource(ints = {7, 15, 133, 17, 19, 297})
    @DisplayName("throw RuntimeException when id is not found")
    public void throwsRuntimeExceptionWhenIdIsNotFound(int id) {
        AuthorService service = new AuthorService();
        Throwable exception = assertThrows(RuntimeException.class, () -> service.getById(id));
        assertEquals("Cannot find the author id", exception.getMessage());
    }

    //  side-effect testing
    @Test
    @DisplayName("add an author to the list")
    public void addsAnAuthorToList() {
        AuthorService service = new AuthorService();
        //  initial size should be 0
        assertEquals(0, service.getAuthorList().size());
        //  action causes side-effect (adds to list)
        service.addAuthor(new Author());
        //  size now should be 1
        assertEquals(1, service.getAuthorList().size());
    }
}