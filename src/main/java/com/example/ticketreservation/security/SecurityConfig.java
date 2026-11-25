package com.example.ticketreservation.security;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration @EnableMethodSecurity
public class SecurityConfig {
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean SecurityFilterChain filterChain(HttpSecurity http,JwtAuthenticationFilter jwt)throws Exception{
  return http.csrf(c->c.disable()).sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
   .authorizeHttpRequests(a->a.requestMatchers("/api/auth/register","/api/auth/login").permitAll().anyRequest().authenticated())
   .exceptionHandling(e->e.authenticationEntryPoint((r,s,x)->{s.setStatus(401);s.setContentType("application/json");s.getWriter().write("{\"error\":\"Unauthorized\"}");})
   .accessDeniedHandler((r,s,x)->{s.setStatus(403);s.setContentType("application/json");s.getWriter().write("{\"error\":\"Forbidden\"}");}))
   .addFilterBefore(jwt,UsernamePasswordAuthenticationFilter.class).build();
 }
}
