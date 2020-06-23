package com.epam.esm.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("com.epam.esm")
@EnableTransactionManagement
@EnableWebMvc
public class SpringConfiguration implements WebMvcConfigurer {
    private static final String PATH_TO_DATASOURCE_PROPERTIES = "/datasource.properties";

    @Bean
    public HikariDataSource hikariDataSource(){
        HikariConfig config = new HikariConfig(PATH_TO_DATASOURCE_PROPERTIES);
        return new HikariDataSource(config);
    }

    @Bean
    public JdbcTemplate JdbcTemplate(){
        return new JdbcTemplate(hikariDataSource());
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(hikariDataSource());
    }
}
