package edu.utn.mailapi.persistence;

import edu.utn.mailapi.domain.User;


public interface UserDao extends Dao<User, String> {
    User get(String username, String password);
}
