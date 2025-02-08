package exercise5.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import exercise5.bookstore.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
