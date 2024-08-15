package com.EmployeeManagementSystem.EMS.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@EnableJpaRepositories(basePackages = "com.example.EmployeeManagementSystem.repository")
public class AuditingConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        // This is just a placeholder; you should provide your own implementation
        return () -> Optional.of("system"); // Replace with actual user information
    }

    @Bean
    public AuditingEntityListener auditingEntityListener() {
        return new AuditingEntityListener();
    }
}
