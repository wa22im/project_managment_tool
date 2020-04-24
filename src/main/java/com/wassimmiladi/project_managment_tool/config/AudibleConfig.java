package com.wassimmiladi.project_managment_tool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AudibleConfig {

    @Bean
    public AuditorAware<String> auditorAware(){
        return ()-> {
            String Name = SecurityContextHolder.getContext().getAuthentication().getName();
            //   System.out.println(Name);

            return Optional.ofNullable(Name);
        };
    }
}