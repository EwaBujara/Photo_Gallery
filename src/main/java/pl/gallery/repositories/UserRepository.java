package pl.gallery.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.gallery.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByName(String name);
    User findById(String id);
    
}
