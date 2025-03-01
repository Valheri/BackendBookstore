package exercise5.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import exercise5.bookstore.model.AppUser;

public interface UserRepository extends CrudRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}