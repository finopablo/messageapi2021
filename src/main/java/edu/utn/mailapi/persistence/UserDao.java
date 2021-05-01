package edu.utn.mailapi.persistence;

import edu.utn.mailapi.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, String> {
    User findByUsernameAndPassword(String username, String password);
}
