package com.example.ticketreservation.dto;
import com.example.ticketreservation.model.Role;
public class Responses {
 public record AuthResponse(String token,String tokenType,long expiresInMs,String username,String fullName,Role role) {}
 public record UserResponse(String id,String username,String fullName,Role role) {}
 public record StatsResponse(long users,long events,long ticketsSold,double revenue) {}
 public record MessageResponse(String message) {}
}
