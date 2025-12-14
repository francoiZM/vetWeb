package com.vetweb.gestor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import com.vetweb.gestor.service.impl.UsuarioServiceImpl;



@Configuration
@EnableWebSecurity

public class SecurityConfig {

    private final UsuarioServiceImpl userDetailsService;

    public SecurityConfig(UsuarioServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
            // Permitir frames para H2 Console
            .headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin())
            )

            // Deshabilitar CSRF solo para H2 Console
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**")
            )

            .authorizeHttpRequests(auth -> auth

                .requestMatchers(
                    "/css/**",
                    "/js/**",
                    "/images/**",
                    "/webjars/**",
                    "/favicon.ico",
                    "/error"
                   
                ).permitAll()

                // Rutas públicas
                .requestMatchers(
                    "/login",
                    "/usuarios/crear",
                    "/usuarios/guardar",
                    "/h2-console",
                    "/h2-console/**",
                    "/usuarios/registro"
                ).permitAll()

                // Rutas administrativas
                .requestMatchers("/usuarios/**").hasRole("ADMIN")

                // rutas tutor y admin pueden ver mascotas
                .requestMatchers("/mascotas/**").hasAnyRole("TUTOR", "ADMIN", "VETERINARIO")

                // Rutas de citas: TUTOR y VETERINARIO
                .requestMatchers("/citas/**").hasAnyRole("TUTOR", "VETERINARIO", "ADMIN")

                // Cualquier otra solicitud requiere autenticación
                .anyRequest().authenticated()
                    
            )

            //definir formulario de login
            .formLogin(login -> login
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")

                .defaultSuccessUrl("/mascotas/listar", true)

                .failureUrl("/login?error=true")
                .permitAll()
            )

            // configuración de logout
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )

            .build();



            
    }


    
}
