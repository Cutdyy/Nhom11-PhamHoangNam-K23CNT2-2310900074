package com.shopquanao.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // 1. NHÓM TỰ DO (Ai cũng xem được): Thêm "/products" vào đây!
                        .requestMatchers("/", "/products", "/danhmuc/**", "/sanpham/**", "/search", "/login", "/register", "/css/**", "/js/**", "/images/**").permitAll()

                        // 2. GIỎ HÀNG: Cho phép khách vãng lai thêm đồ vào giỏ
                        .requestMatchers("/cart/**").permitAll()

                        // 3. THANH TOÁN: Bắt buộc phải đăng nhập mới được mua hàng
                        .requestMatchers("/checkout/**").authenticated()

                        // 4. QUẢN TRỊ: Chỉ tài khoản có quyền ADMIN mới được vào
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // Các đường dẫn khác chưa khai báo thì bắt đăng nhập
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/", true) // Đăng nhập xong về trang chủ
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/") // Đăng xuất xong về trang chủ
                        .permitAll()
                )
                // Tạm thời tắt CSRF để dễ test form (bạn có thể bỏ dòng này nếu đã xử lý csrf token trong form HTML)
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}