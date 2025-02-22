package exercise5.bookstore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import exercise5.bookstore.model.Book;

@RepositoryRestResource
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByTitle(String title);
}
