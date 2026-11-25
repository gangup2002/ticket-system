package com.example.ticketreservation.security;
import com.example.ticketreservation.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
@Service
public class CustomUserDetailsService implements UserDetailsService {
 private final UserRepository users;
 public CustomUserDetailsService(UserRepository users){this.users=users;}
 public UserDetails loadUserByUsername(String username){var u=users.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
  return User.withUsername(u.getUsername()).password(u.getPassword()).roles(u.getRole().name()).build();}
}
