package edu.miu.cs.cs489.pizzadeliveryapp.config;

import edu.miu.cs.cs489.pizzadeliveryapp.filter.JWTAuthFilter;
import edu.miu.cs.cs489.pizzadeliveryapp.service.impl.AppUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AppWebAPISecurityConfig {

    private AppUserDetailsService appUserDetailsService;
    private JWTAuthFilter jwtAuthFilter;

    public AppWebAPISecurityConfig(AppUserDetailsService appUserDetailsService, JWTAuthFilter jwtAuthFilter) {
        this.appUserDetailsService = appUserDetailsService;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> {
                            auth
                                    .requestMatchers("/pizzamgm/api/v1/public/auth/**").permitAll()
                                    .requestMatchers("/pizzamgm/home").permitAll()
                                    .requestMatchers("/pizzamgm/about").permitAll()
                                    .requestMatchers("/pizzamgm/sysadmin/**").permitAll()
                                    .requestMatchers("/pizzamgm/useradmin/**").permitAll()
                                    .requestMatchers("/pizzamgm/api/v1/users/new").permitAll()
                                    .requestMatchers("/pizzamgm/api/v1/users/list").permitAll()
                                    .requestMatchers("/pizzamgm/api/v1/customers/**").permitAll()
                                    .requestMatchers("/pizzamgm/api/v1/users/update/status/**").hasRole("CUSTOMER")
                                    .requestMatchers("/pizzamgm/api/v1/users/update/**").hasRole("MANAGER")
                                    .requestMatchers("/pizzamgm/api/v1/users/delete/**").hasRole("MANAGER")
                                    .requestMatchers("/pizzamgm/api/v1/orders/**").permitAll()//.hasRole("MANAGER")
                                    .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                                    .requestMatchers("/pizzamgm/api/v1/pizzas/**").permitAll();

                        }
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(appUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
