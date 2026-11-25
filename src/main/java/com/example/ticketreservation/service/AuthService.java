package com.example.ticketreservation.service;
import com.example.ticketreservation.dto.Requests.*;
import com.example.ticketreservation.dto.Responses.*;
import com.example.ticketreservation.exception.ApiException;
import com.example.ticketreservation.model.*;
import com.example.ticketreservation.repository.UserRepository;
import com.example.ticketreservation.security.JwtService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class AuthService {
 private final UserRepository users; private final PasswordEncoder encoder; private final JwtService jwt;
 public AuthService(UserRepository u,PasswordEncoder e,JwtService j){users=u;encoder=e;jwt=j;}
 public UserResponse register(RegisterRequest r,boolean admin){
  if(users.existsByUsername(r.username()))throw new ApiException(HttpStatus.CONFLICT,"Username already exists");
  Role role=r.role()==null?Role.CUSTOMER:r.role();
  if(role!=Role.CUSTOMER&&!admin)throw new ApiException(HttpStatus.FORBIDDEN,"Only ADMIN can create ADMIN or ORGANIZER accounts");
  AppUser u=users.save(new AppUser(r.username(),encoder.encode(r.password()),r.fullName(),role));return view(u);
 }
 public AuthResponse login(LoginRequest r){AppUser u=users.findByUsername(r.username()).orElseThrow(()->new ApiException(HttpStatus.UNAUTHORIZED,"Invalid credentials"));
  if(!encoder.matches(r.password(),u.getPassword()))throw new ApiException(HttpStatus.UNAUTHORIZED,"Invalid credentials");
  return new AuthResponse(jwt.generate(u.getUsername(),u.getRole().name()),"Bearer",jwt.getExpiration(),u.getUsername(),u.getFullName(),u.getRole());}
 public UserResponse me(String username){return view(users.findByUsername(username).orElseThrow());}
 public List<UserResponse> all(){return users.findAll().stream().map(this::view).toList();}
 private UserResponse view(AppUser u){return new UserResponse(u.getId(),u.getUsername(),u.getFullName(),u.getRole());}
}
