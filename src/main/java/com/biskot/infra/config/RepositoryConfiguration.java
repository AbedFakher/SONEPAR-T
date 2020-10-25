package com.biskot.infra.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@Configuration
@EnableJpaRepositories("com.biskot.infra.repository")
@EntityScan("com.biskot.infra.repository.*") 
public class RepositoryConfiguration {

}