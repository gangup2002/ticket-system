package com.example.ticketreservation.controller;
import com.example.ticketreservation.dto.Requests.*;import com.example.ticketreservation.dto.Responses.*;
import com.example.ticketreservation.service.AuthService;
import jakarta.validation.Valid;import java.security.Principal;
import org.springframework.http.HttpStatus;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/auth")
public class AuthController {
 private final AuthService auth;public AuthController(AuthService a){auth=a;}
 @PostMapping("/register") @ResponseStatus(HttpStatus.CREATED) public UserResponse register(@Valid @RequestBody RegisterRequest r){return auth.register(r,false);}
 @PostMapping("/login") public AuthResponse login(@Valid @RequestBody LoginRequest r){return auth.login(r);}
 @GetMapping("/me") public UserResponse me(Principal p){return auth.me(p.getName());}
}
