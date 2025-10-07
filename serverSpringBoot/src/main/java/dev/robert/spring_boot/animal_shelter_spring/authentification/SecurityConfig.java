package dev.robert.spring_boot.animal_shelter_spring.authentification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Value("${app.admin.ADMIN_USER}")
    private String adminUserName;

    @Value("${app.admin.ADMIN_PASSWORD}")
    private String adminPassword;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder encoder) {
        var admin = User.builder()
                .username(adminUserName)
                .password(encoder.encode(adminPassword))
                .roles("ADMIN")
                .build();

        // Only admin is stored — regular visitors don't need an account
        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/api/v1/animal/admin/**").hasRole("ADMIN")    // admin-only
                                .requestMatchers("/api/v1/adoption/admin/**").hasRole("ADMIN")  // admin-only
                                .requestMatchers("/api/v1/visit/admin/**").hasRole("ADMIN")     // admin-only
                                .requestMatchers("/api/v1/animal/public/**").permitAll()        // everyone else can access without login
                                .requestMatchers("/api/v1/adoption/public/**").permitAll()      // everyone else can access without login
                                .requestMatchers("/api/v1/visit/public/**").permitAll()         // everyone else can access without login
                )
                .httpBasic(withDefaults()); // still enable Basic Auth for admin paths

        return http.build();
    }
}
