package in.athenaeum.junit5andmockitostudy.service;

import in.athenaeum.junit5andmockitostudy.model.Author;
import org.junit.jupiter.api.*;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("AuthorService should")
class AuthorServiceTests {

    private static Logger logger = Logger.getLogger("AuthorServiceTests");

    @BeforeEach
    private void setUp() {
        logger.info("beforeEach()");
    }

    @AfterEach
    private void tearDown() {
        logger.info("afterEach()");
    }

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

        logger.info("getRandomAuthorReturnsAuthor()");
    }

    @Test
    @DisplayName("return an author by id, when id is found")
    public void getAuthorByIdWhenIdIsFound() {
        //  Arrange
        AuthorService service = new AuthorService();
        int id = 34;    //  make sure it is even

        //  Act
        Author author = service.getById(id);

        //  Multiple asserts in one
        assertAll(
                () -> assertEquals(id, author.getId()),
                () -> assertEquals("India", author.getCountry())
        );

        logger.info("getAuthorByIdWhenIdIsFound()");
    }

    //  exception testing
    @Test
    @DisplayName("throw RuntimeException when id is not found")
    public void throwsRuntimeExceptionWhenIdIsNotFound() {
        AuthorService service = new AuthorService();
        Throwable exception = assertThrows(RuntimeException.class, () -> service.getById(35));
        assertEquals("Cannot find the author id", exception.getMessage());
        logger.info("throwsRuntimeExceptionWhenIdIsNotFound()");
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
        logger.info("addsAnAuthorToList()");

    }
}