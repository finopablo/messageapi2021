package edu.utn.mailapi.persistence.mysql;


import edu.utn.mailapi.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserMySqlDaoTest {


    UserMySqlDao userMySqlDao;

    @Mock
    Connection connection;

    @Before
    public void setUp() {
        initMocks(this);
        userMySqlDao = new UserMySqlDao(connection);
    }


    @Test
    public void testGetAllOk() throws SQLException {
        //Set scenario
        Statement st = mock(Statement.class);
        ResultSet rs = mock(ResultSet.class);

        when(connection.createStatement()).thenReturn(st);
        when(st.executeQuery("select * from users")).thenReturn(rs);
        when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(rs.getString("username")).thenReturn("finopablo").thenReturn("ggianotti");
        when(rs.getString("password")).thenReturn("1234").thenReturn("12344");
        when(rs.getString("name")).thenReturn("Pablo").thenReturn("German");
        when(rs.getString("last_name")).thenReturn("Fino").thenReturn("Gianotti");

        //Ejecuto el codigo
        List<User> users = userMySqlDao.getAll();

        //Verifico
        assertEquals(2, users.size() );
        assertEquals(users.get(0).getUserName(), "finopablo");
        assertEquals(users.get(1).getUserName(), "ggianotti");
        verify(rs, times(3)).next();

        verify(rs, times(2)).getString("username");
    }



    @Test
    public void testRemoveOk() throws SQLException {

        User user = new User("finopablo", "1234", "Pablo", "Fino", LocalDate.MIN);
        //Set scenario
        PreparedStatement ps = mock(PreparedStatement.class);
        when(connection.prepareStatement("DELETE FROM users where username = ?")).thenReturn(ps);
        doNothing().when(ps).setString(anyInt(), anyString());
        when(ps.executeUpdate()).thenReturn(1);

        //Ejecuto el codigo
        userMySqlDao.remove(user);

        //Verifico
        verify(ps, times(1)).setString(1, user.getUserName());
        verify(ps, times(1)).executeUpdate();


    }


}
