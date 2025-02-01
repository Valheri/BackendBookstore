package exercise5.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import exercise5.bookstore.model.Book;
import exercise5.bookstore.repository.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(BookRepository repository) {
        return (args) -> {
            // Insert example data
            repository.save(new Book("Book Title 1", "Author 1", 2001, "ISBN001", 19.99));
            repository.save(new Book("Book Title 2", "Author 2", 2002, "ISBN002", 29.99));
            repository.save(new Book("Book Title 3", "Author 3", 2003, "ISBN003", 39.99));
        };
    }
}
