package com.example.ticketreservation.controller;
import com.example.ticketreservation.dto.Responses.*;import com.example.ticketreservation.service.*;
import java.util.List;import org.springframework.security.access.prepost.PreAuthorize;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/admin") @PreAuthorize("hasRole('ADMIN')")
public class AdminController {
 private final AuthService auth;private final AdminService admin;
 public AdminController(AuthService a,AdminService s){auth=a;admin=s;}
 @GetMapping("/users") public List<UserResponse> users(){return auth.all();}
 @GetMapping("/stats") public StatsResponse stats(){return admin.stats();}
 @PostMapping("/users") @org.springframework.web.bind.annotation.ResponseStatus(org.springframework.http.HttpStatus.CREATED)
 public UserResponse createUser(@jakarta.validation.Valid @RequestBody com.example.ticketreservation.dto.Requests.RegisterRequest r){return auth.register(r,true);}
}
