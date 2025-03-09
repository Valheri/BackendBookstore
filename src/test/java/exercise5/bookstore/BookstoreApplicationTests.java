package exercise5.bookstore;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import exercise5.bookstore.model.AppUser;
import exercise5.bookstore.model.Book;
import exercise5.bookstore.model.Category;
import exercise5.bookstore.repository.BookRepository;
import exercise5.bookstore.repository.CategoryRepository;
import exercise5.bookstore.repository.UserRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookstoreApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testGetBooks() {
        // Ensure there is at least one book in the repository
        Category category = categoryRepository.save(new Category("Test Category"));
        Book book = new Book("Book 1", "Author 1", 2001, "ISBN001", 19.99, category);
        bookRepository.save(book);

        ResponseEntity<String> response = restTemplate.getForEntity("/api/books", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("Book 1");
    }

    @Test
    void testGetBookById() {
        // Ensure there is a book with ID 1 in the repository
        Category category = categoryRepository.save(new Category("Test Category"));
        Book book = new Book("Book 1", "Author 1", 2001, "ISBN001", 19.99, category);
        book = bookRepository.save(book);

        ResponseEntity<String> response = restTemplate.getForEntity("/api/books/" + book.getId(), String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("Book 1");
    }


    @Test
    @Transactional
    void testCreateAndDeleteBook() {
        Category category = categoryRepository.save(new Category("Test Category"));
        Book book = new Book("Test Book", "Test Author", 2021, "TESTISBN", 19.99, category);
        book = bookRepository.save(book);
        assertThat(bookRepository.findById(book.getId())).isPresent();

        bookRepository.delete(book);
        assertThat(bookRepository.findById(book.getId())).isNotPresent();
    }

    @Test
    @Transactional
    void testCreateAndDeleteCategory() {
        Category category = new Category("Test Category");
        category = categoryRepository.save(category);
        assertThat(categoryRepository.findById(category.getId())).isPresent();

        categoryRepository.delete(category);
        assertThat(categoryRepository.findById(category.getId())).isNotPresent();
    }

    @Test
    @Transactional
    void testCreateAndDeleteUser() {
        AppUser user = new AppUser("testuser", "password", "testuser@example.com", "USER");
        user = userRepository.save(user);
        assertThat(userRepository.findById(user.getId())).isPresent();

        userRepository.delete(user);
        assertThat(userRepository.findById(user.getId())).isNotPresent();
    }

    @Test
    @Transactional
    void testFindBookByTitle() {
        Category category = categoryRepository.save(new Category("Test Category"));
        Book book = new Book("Unique Title", "Test Author", 2021, "TESTISBN", 19.99, category);
        bookRepository.save(book);

        assertThat(bookRepository.findByTitle("Unique Title")).isNotEmpty();
    }
}
