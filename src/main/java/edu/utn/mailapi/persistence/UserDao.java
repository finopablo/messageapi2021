package edu.utn.mailapi.persistence;

import edu.utn.mailapi.domain.User;

import java.util.List;

public interface UserDao extends Dao<User, String> {
    User get(String username, String password);
}
