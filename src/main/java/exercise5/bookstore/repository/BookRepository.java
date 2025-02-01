package exercise5.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import exercise5.bookstore.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
}
