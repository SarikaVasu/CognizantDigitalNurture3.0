package com.empManagement.EmpManSys.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
public class AuditingConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
//        return () -> Optional.ofNullable("System");
        //this would be curr authenticated user
        return () -> Optional.of("System");
    }

}

//add auditing field to entities
