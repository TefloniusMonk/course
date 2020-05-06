package com.young.fighter.course.backend.config;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories
//@EnableRepositories("com.young.fighter.course.backend.db.repository")
@EntityScan("com.young.fighter.course.backend.db.entity")
public class DbConfig extends AbstractR2dbcConfiguration {
    @Value("${spring.r2dbc.port}")
    private int port;

    @Value("${spring.r2dbc.username}")
    private String user;

    @Value("${spring.r2dbc.password}")
    private String password;

    @Value("${spring.r2dbc.db}")
    private String db;

    @Value("${spring.r2dbc.host}")
    private String host;


    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                        .username(user)
                        .password(password)
                        .port(port)
                        .database(db)
                        .host(host)
                        .build()
        );
    }
}
