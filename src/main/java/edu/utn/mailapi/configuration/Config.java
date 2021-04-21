package edu.utn.mailapi.configuration;


import edu.utn.mailapi.exceptions.DatabaseConnectionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.sql.Connection;
import java.sql.DriverManager;

@Configuration
@PropertySource("classpath:app.properties")
public class Config {

    @Bean("Conexion")
    public Connection getConnection(
            @Value("${database.url}") String url,
            @Value("${database.port}") String port,
            @Value("${database.name}") String databaseName,
            @Value("${database.user}") String user,
            @Value("${database.password}") String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            return DriverManager.getConnection("jdbc:mysql://" + url + ":" + port + "/" + databaseName, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseConnectionException();
        }
    }
}
