package edu.utn.mailapi.configuration;


import edu.utn.mailapi.exceptions.DatabaseConnectionException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

@Configuration
public class Config {

    @Bean
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/mailapi2021?user=root&password=a");
        } catch (Exception e) {
            e.printStackTrace(
            );
            throw new DatabaseConnectionException();
        }
    }
}
