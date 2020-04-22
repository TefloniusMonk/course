package com.young.fighter.course.backend.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.young.fighter.course.backend.db.repository")
@EntityScan("com.young.fighter.course.backend.db.entity")
public class DbConfig {
}
