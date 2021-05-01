package edu.utn.mailapi.controller;

import edu.utn.mailapi.domain.User;
import edu.utn.mailapi.exceptions.InvalidUserPasswordException;
import edu.utn.mailapi.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        for ( User u : userDao.findAll() ) {
            users.add(u);
        }
        return users;
    }

    public User login(String username, String password) throws InvalidUserPasswordException {
        return Optional.ofNullable(userDao.findByUsernameAndPassword(username, password)).orElseThrow(InvalidUserPasswordException::new);
    }

    public User register(String username, String password, String name, String lastName, LocalDate birthDate) {
        return userDao.save(new User(username, password, name, lastName, birthDate));
    }

    public void remove(User user) {
        userDao.delete(user);
    }

    public void changePassword(String username, String oldPassword, String newPassword) throws InvalidUserPasswordException {
        User u = userDao.findByUsernameAndPassword(username, oldPassword);
        if (u != null) {
           u.setPassword(newPassword);
        } else {
            throw new InvalidUserPasswordException();
        }
    }

    public User get(String username) {
        return userDao.findById(username).get();
    }

    void logout(User user) {

    }


}
