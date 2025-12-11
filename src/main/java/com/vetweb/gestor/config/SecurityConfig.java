package com.vetweb.gestor.config;

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

    // Define cómo se protegerán las URLs y cómo se manejará el login/logout
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                // Permite acceso sin autenticar a recursos estáticos, página de inicio y páginas de usuarios públicas
                .requestMatchers("/css/**", "/js/**", "/").permitAll()
                .requestMatchers("/usuarios/login", "/usuarios/registro").permitAll()
                // Permite acceso al H2 Console
                .requestMatchers("/h2-console/**").permitAll()
                
                // Requiere el rol 'VETERINARIO' para acceder a paths de la agenda/pacientes/etc. del veterinario
                .requestMatchers("/veterinario/**").hasRole("vet")
                
                // Requiere el rol 'DUENO' para acceder a paths de gestión de citas/mascotas/etc. del dueño
                .requestMatchers("/tutor/**").hasRole("tutor")
                
                // Cualquier otra URL requiere autenticación
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/usuarios/login")
                .loginProcessingUrl("/authenticate")
                .defaultSuccessUrl("/default", true)
                .permitAll()
            )
            .logout(logout -> logout
                // URL para el logout
                .logoutUrl("/logout")
                // Redirige al inicio después del logout
                .logoutSuccessUrl("/") 
                .permitAll()
            );
        // Configuración necesaria para que H2 console funcione dentro de frames y sin CSRF
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"));
        http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));
        return http.build();
    }

    // Define el codificador de contraseñas. ¡BCrypt es el estándar recomendado!
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}