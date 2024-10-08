package com.empManagement.EmpManSys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//enables auditing
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@SpringBootApplication
public class EmpManSysApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpManSysApplication.class, args);
	}

}
