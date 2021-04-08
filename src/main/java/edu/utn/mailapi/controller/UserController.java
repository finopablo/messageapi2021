package edu.utn.mailapi.controller;

import edu.utn.mailapi.domain.User;
import edu.utn.mailapi.exceptions.InvalidUserPasswordException;
import edu.utn.mailapi.persistence.UserDao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class UserController {

    UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }


    public List<User> getAll() {
            return userDao.getAll();
    }

    public User login(String username, String password) throws InvalidUserPasswordException {
        return Optional.ofNullable(userDao.get(username, password)).orElseThrow(InvalidUserPasswordException::new);
    }

    public User register(String username, String password, String name, String lastName, LocalDate birthDate) {
        return userDao.save(new User(username, password, name, lastName, birthDate));
    }

    public void remove(User user) {
        userDao.remove(user);
    }

    public void changePassword(String username, String oldPassword, String newPassword) throws InvalidUserPasswordException {
        User u = userDao.get(username, oldPassword);
        if (u != null) {
            u.setPassword(newPassword);
        } else {
            throw new InvalidUserPasswordException();
        }
    }

    public User get(String username) {
        return userDao.get(username);
    }

    void logout(User user) {

    }


}
