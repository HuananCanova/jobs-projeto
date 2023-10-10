package br.ufsm.csi.jobs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthFilter authFilter;

    public SecurityConfig(AuthFilter authFilter) {
        this.authFilter = authFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf->csrf.disable())
                .sessionManagement(sm-> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.
                                // PERMIT ALL
                                requestMatchers(HttpMethod.POST, "/login").permitAll()
                                .requestMatchers(HttpMethod.POST, "/register/empresa").permitAll()
                                .requestMatchers(HttpMethod.POST, "/register/candidato").permitAll()
                                // - /EMPRESA
                                .requestMatchers(HttpMethod.POST, "/empresa").hasAuthority("ROLE_EMPRESA")
                                .requestMatchers(HttpMethod.PUT, "/empresa").hasAuthority("ROLE_EMPRESA")
                                .requestMatchers(HttpMethod.DELETE, "/empresa").hasAuthority("ROLE_EMPRESA")
                                // - /VAGA
                                .requestMatchers(HttpMethod.GET, "/vaga").permitAll()
                                .requestMatchers(HttpMethod.GET, "/vaga/{id}").hasAnyAuthority("ROLE_USER", "ROLE_EMPRESA")
                                .requestMatchers(HttpMethod.POST, "/vaga").hasAuthority("ROLE_EMPRESA")
                                .requestMatchers(HttpMethod.PUT, "/vaga").hasAuthority("ROLE_EMPRESA")
                                .requestMatchers(HttpMethod.DELETE, "/vaga").hasAuthority("ROLE_EMPRESA")
                                // - /CANDIDATURA
                                .requestMatchers(HttpMethod.GET, "/candidatura").hasAnyAuthority("ROLE_USER", "ROLE_EMPRESA")
                                .requestMatchers(HttpMethod.GET, "/candidatura/{id}").hasAnyAuthority("ROLE_USER", "ROLE_EMPRESA")
                                .requestMatchers(HttpMethod.POST, "/candidatura").hasAuthority("ROLE_USER")
                                // - /USERS
                                .requestMatchers(HttpMethod.GET, "/user").hasAnyAuthority("ROLE_USER", "ROLE_EMPRESA")
                                .anyRequest().authenticated())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
