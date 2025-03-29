package exercise5.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import exercise5.bookstore.model.Book;
import exercise5.bookstore.model.Category;
import exercise5.bookstore.repository.BookRepository;
import exercise5.bookstore.repository.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(BookRepository repository, CategoryRepository categoryRepository) {
        return (args) -> {
            if (categoryRepository.count() == 0) {
                Category fiction = categoryRepository.save(new Category("Fiction"));
                Category nonFiction = categoryRepository.save(new Category("Non-Fiction"));
                Category science = categoryRepository.save(new Category("Science"));
                Category technology = categoryRepository.save(new Category("Technology"));

                if (repository.count() == 0) {
                    repository.save(new Book("Book 1", "Author 1", 2001, "ISBN001", 19.99, fiction));
                    repository.save(new Book("Book 2", "Author 2", 2002, "ISBN002", 29.99, science));
                    repository.save(new Book("Book 3", "Author 3", 2003, "ISBN003", 39.99, technology));
                    repository.save(new Book("Book 4", "Author 1", 2004, "ISBN004", 49.99, nonFiction));
                }
            }
        };
    }
}
