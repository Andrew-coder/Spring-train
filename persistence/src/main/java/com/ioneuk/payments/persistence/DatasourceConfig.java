package com.ioneuk.payments.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource(value = { "classpath:application.properties" })
public class DatasourceConfig {
    @Autowired
    private Environment env;

    @Bean
    @Profile("dev")
    public DataSource h2DataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("datasource.h2.driver"));
        dataSource.setUrl(env.getProperty("datasource.h2.url"));
        dataSource.setUsername(env.getProperty("datasource.mysql.username"));
        dataSource.setPassword(env.getProperty("datasource.mysql.password"));
        return dataSource;
    }

    @Bean
    @Profile("prod")
    public DriverManagerDataSource mysqlDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("datasource.mysql.driver"));
        dataSource.setUrl(env.getProperty("datasource.mysql.url"));
        dataSource.setUsername(env.getProperty("datasource.mysql.username"));
        dataSource.setPassword(env.getProperty("datasource.mysql.password"));
        return dataSource;
    }
}