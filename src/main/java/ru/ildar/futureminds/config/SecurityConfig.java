package ru.ildar.futureminds.config;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.ildar.futureminds.filter.JwtFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http = http.cors().and().csrf().disable();


        http.httpBasic().disable();


        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();


        http = http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                            System.out.println(ex.getMessage());
                        }
                )
                .and();

        http.authorizeHttpRequests()
                // Our public endpoints
                .requestMatchers("/api/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/docs", "/v3/api-docs/**",
                        "/swagger-ui/**", "/swagger-ui.html", "/swagger-ui.html").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/news", "/api/news/**").permitAll()
                // Our private endpoints
                .anyRequest().authenticated();


        http.addFilterBefore(
                jwtFilter,
                UsernamePasswordAuthenticationFilter.class
        );


        return http.build();

//                .addFilterBefore(corsFilter(), SessionManagementFilter.class)
//                .httpBasic().disable()

//                .authorizeHttpRequests(
//                        authz -> {
//                            try {
//                                authz
//                                        .requestMatchers("/api/auth/login",
//                                                "/api/auth/register",
//                                                "/docs",
//                                                "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html",
//                                                "/api/course/",
//                                                "/api/course").permitAll()
//                                        .anyRequest().authenticated()
//                                        .and()
//                                        .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//                            } catch (Exception e) {
//                                throw new RuntimeException(e);
//                            }
//                        }
//                )
//                .build();
//    }
    }

//    public CorsFilter corsFilter() {
//
//        return new ru.ildar.futureminds.filter.CorsFilter();
//    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
