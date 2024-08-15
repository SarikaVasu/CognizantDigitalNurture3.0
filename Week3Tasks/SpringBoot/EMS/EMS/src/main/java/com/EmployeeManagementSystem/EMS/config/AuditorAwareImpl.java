package com.EmployeeManagementSystem.EMS.config;
//to supply the current user who is performing audit action

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // For simplicity, returning a fixed user. In a real application, this could be fetched from the security context.
        return Optional.of("system"); // You might want to return the currently authenticated user
    }
}
