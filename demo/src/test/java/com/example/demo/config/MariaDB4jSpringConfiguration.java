package com.example.demo.config;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import ch.vorburger.mariadb4j.springframework.MariaDB4jSpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.UUID;

@Configuration
public class MariaDB4jSpringConfiguration {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Bean
    public MariaDB4jSpringService mariaDB4j() throws ManagedProcessException {
        DB db = DB.newEmbeddedDB(0);
        db.start();
        return new MariaDB4jSpringService();
    }

    @Bean
    @Primary
    public DataSource dataSource() throws ManagedProcessException {
        String dbname = UUID.randomUUID().toString().substring(0, 8);
        mariaDB4j().getDB().createDB(dbname);
        return DataSourceBuilder.create().driverClassName(dataSourceProperties.getDriverClassName()).url(mariaDB4j().getConfiguration().getURL(dbname)).username(dataSourceProperties.getUsername()).password(dataSourceProperties.getPassword()).build();
    }
}
