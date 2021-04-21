package edu.utn.mailapi.persistence.memory;

import edu.utn.mailapi.domain.User;
import edu.utn.mailapi.persistence.UserDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("userMemoryDao")
public class UserMemoryDao implements UserDao {

    List<User> users = new ArrayList<>();

    @Override
    public User get(String username, String password) {
        Optional<User> optUser =  users.stream().filter(u -> u.getUserName().equals(username) && u.getPassword().equals(password)).findFirst();
        return optUser.orElse(null);
    }

    @Override
    public User save(User o) {
        Optional<User> optUser = users.stream().filter( u -> u.equals(o)).findFirst();
        if (optUser.isPresent()) {
            users.remove(optUser.get());
        }
        users.add(o);
        return o;
    }

    @Override
    public void remove(User o) {
        users.remove(o);
    }

    @Override
    public User get(String s) {
        Optional<User> optUser =  users.stream().filter(u -> u.getUserName().equals(s)).findFirst();
        return optUser.orElseGet(null);
    }

    @Override
    public List<User> getAll() {
        return users;
    }
}
