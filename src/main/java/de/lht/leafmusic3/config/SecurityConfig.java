package de.lht.leafmusic3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Tắt CSRF nếu không cần thiết
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/public/**").permitAll()  // Cho phép truy cập không cần xác thực
//                        .requestMatchers("/api/user/**").hasRole("USER")  // Chỉ cho phép USER truy cập
//                        .requestMatchers("/api/admin/**").hasRole("ADMIN") // Chỉ cho phép ADMIN truy cập
//                        .requestMatchers("/api/users").authenticated() // Yêu cầu xác thực cho /api/users
//                        .anyRequest().authenticated()  // Các yêu cầu khác yêu cầu đăng nhập
                        .anyRequest().permitAll()  // Các yêu cầu khác yêu cầu đăng nhập
                )
                .formLogin(withDefaults()) // Sử dụng trang đăng nhập mặc định của Spring Security
                .logout(logout -> logout.permitAll()); // Cho phép logout

        return http.build();
    }
}
