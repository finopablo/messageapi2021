package edu.utn.mailapi.persistence.mysql;

import edu.utn.mailapi.domain.User;
import edu.utn.mailapi.exceptions.DatabaseConnectionException;
import edu.utn.mailapi.persistence.UserDao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserMySqlDao implements UserDao {


    Connection conn;


    public UserMySqlDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mailapi2021?user=root&password=a");
        } catch (Exception e) {
            throw new DatabaseConnectionException();
        }
    }

    @Override
    public User get(String username, String password) {
        return null;
    }

    @Override
    public User save(User o) {
        try {
            User existingUser =  this.get(o.getUserName());
            if (existingUser!=null) {
                //User exists , we have to update it
                PreparedStatement ps = conn.prepareStatement("UPDATE users set password = ? , name = ?, last_name = ? where username = ?");
                ps.setString(1,o.getPassword());
                ps.setString(2,o.getName());
                ps.setString(3,o.getLastName());
                ps.setString(4,o.getUserName());
                ps.execute();
            } else {
                //User does not exists, we will create it
                PreparedStatement ps = conn.prepareStatement("INSERT INTO users(username,password,name,last_name) values (?,?,?,?)");
                ps.setString(1,o.getUserName());
                ps.setString(2,o.getPassword());
                ps.setString(3,o.getName());
                ps.setString(4,o.getLastName());
                ps.execute();

            }
            return o;
        } catch (SQLException sqlExcetion) {
            throw new DatabaseConnectionException();
        }


    }

    @Override
    public void remove(User o) {

    }

    @Override
    public User get(String s) {

        try {
            PreparedStatement ps = conn.prepareStatement("select * from users where username = ?");

            ps.setString(1, s);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return createUser(rs) ;
            }
        } catch (SQLException sqlException) {
            throw new DatabaseConnectionException();
        }
        return null;

    }
    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from users");
            while (rs.next()) {
                    users.add(createUser(rs));
            }

        } catch (SQLException sqlException) {
            throw new DatabaseConnectionException();
        }
        return users;
    }
    private User createUser(ResultSet rs) throws SQLException {
        return  new User(rs.getString("username"), rs.getString("password"), rs.getString("name"), rs.getString("last_name"), LocalDate.MIN);
    }
}
