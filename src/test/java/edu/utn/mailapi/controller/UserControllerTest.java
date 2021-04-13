package edu.utn.mailapi.controller;

import edu.utn.mailapi.domain.User;
import edu.utn.mailapi.exceptions.InvalidUserPasswordException;
import edu.utn.mailapi.persistence.UserDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserControllerTest {


    @Mock
    UserDao userDao;

    UserController userController;

    @Before
    public void setUp() {
        initMocks(this);
         userController = new UserController(userDao);
    }


    @Test
    public void testIfLoginIsOk() {
        when(userDao.get("finopablo", "1234")).thenReturn(new User("finopablo", "1234", "Pablo", "Fino", LocalDate.MIN));
        try {
            User user = userController.login("finopablo", "1234");
            Assert.assertNotNull(user);
            Assert.assertEquals(user.getUserName(), "finopablo");
            Assert.assertEquals(user.getName(), "Pablo");
        } catch (InvalidUserPasswordException e) {
            Assert.fail("This test shouldn't throw an exception");
        }
    }


    @Test(expected = InvalidUserPasswordException.class)
    public void testIfUsernameOrPasswordNotExists() throws InvalidUserPasswordException {
        when(userDao.get("finopablo", "1234")).thenReturn(null);
        User user = userController.login("finopablo", "1234");
        Assert.fail("Username does not exists so should trhow an exception");
    }

}
