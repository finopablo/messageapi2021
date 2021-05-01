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

}
