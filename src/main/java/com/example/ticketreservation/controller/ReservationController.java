package com.example.ticketreservation.controller;
import com.example.ticketreservation.dto.Requests.ReservationRequest;import com.example.ticketreservation.dto.Responses.MessageResponse;import com.example.ticketreservation.model.Reservation;import com.example.ticketreservation.service.ReservationService;
import jakarta.validation.Valid;import java.security.Principal;import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;import org.springframework.security.core.Authentication;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/reservations")
public class ReservationController {
 private final ReservationService service;public ReservationController(ReservationService s){service=s;}
 @PostMapping @PreAuthorize("hasRole('CUSTOMER')") public Reservation book(@Valid @RequestBody ReservationRequest r,Principal p){return service.book(r,p.getName());}
 @GetMapping("/my") @PreAuthorize("hasRole('CUSTOMER')") public List<Reservation> mine(Principal p){return service.mine(p.getName());}
 @GetMapping @PreAuthorize("hasAnyRole('ADMIN','ORGANIZER')") public List<Reservation> all(Principal p,Authentication a){return a.getAuthorities().stream().anyMatch(x->x.getAuthority().equals("ROLE_ADMIN"))?service.all():service.byEventOrganizer(p.getName());}
 @DeleteMapping("/{id}") @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')") public MessageResponse cancel(@PathVariable String id,Principal p,Authentication a){boolean admin=a.getAuthorities().stream().anyMatch(x->x.getAuthority().equals("ROLE_ADMIN"));service.cancel(id,p.getName(),admin);return new MessageResponse("Reservation cancelled");}
}
