package com.example.ticketreservation.dto;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
public class Requests {
 public record LoginRequest(@NotBlank String username,@NotBlank String password) {}
 public record RegisterRequest(@NotBlank String username,@Size(min=6) String password,@NotBlank String fullName, com.example.ticketreservation.model.Role role) {}
 public record EventRequest(@NotBlank String name,String description,@NotBlank String venue,@Future LocalDateTime eventDate,@Min(1) int totalSeats,@DecimalMin("0.0") double ticketPrice) {}
 public record ReservationRequest(@NotBlank String eventId,@Min(1) @Max(10) int seats) {}
}
