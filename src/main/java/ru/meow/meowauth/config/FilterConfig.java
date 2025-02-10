package ru.meow.meowauth.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import ru.meow.meowauth.filter.AuthorizationHeaderExistFilter;

@Configuration
@EnableWebSecurity
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<AuthorizationHeaderExistFilter> loggingFilter() {
        FilterRegistrationBean<AuthorizationHeaderExistFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AuthorizationHeaderExistFilter());
        registrationBean.addUrlPatterns("/sign-in", "/sign-up");

        return registrationBean;
    }
}
