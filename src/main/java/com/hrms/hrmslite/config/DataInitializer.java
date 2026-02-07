package com.hrms.hrmslite.config;

import com.hrms.hrmslite.entity.Role;
import com.hrms.hrmslite.entity.User;
import com.hrms.hrmslite.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initUsers(UserRepository repo) {
        return args -> {

            if (repo.findByUsername("admin").isEmpty()) {
                repo.save(new User(
                        null,
                        "admin",
                        "admin123",
                        Role.ADMIN
                ));
            }

            if (repo.findByUsername("hr").isEmpty()) {
                repo.save(new User(
                        null,
                        "hr",
                        "hr123",
                        Role.HR
                ));
            }
        };
    }
}
