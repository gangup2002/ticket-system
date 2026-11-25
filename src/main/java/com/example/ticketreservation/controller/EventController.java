package com.example.ticketreservation.controller;
import com.example.ticketreservation.dto.Requests.EventRequest;import com.example.ticketreservation.model.Event;import com.example.ticketreservation.service.EventService;
import jakarta.validation.Valid;import java.security.Principal;import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/events")
public class EventController {
 private final EventService service;public EventController(EventService s){service=s;}
 @GetMapping public List<Event> all(){return service.all();}
 @GetMapping("/{id}") public Event get(@PathVariable String id){return service.get(id);}
 @PostMapping @PreAuthorize("hasAnyRole('ADMIN','ORGANIZER')") public Event create(@Valid @RequestBody EventRequest r,Principal p){return service.create(r,p.getName());}
 @PutMapping("/{id}") @PreAuthorize("hasAnyRole('ADMIN','ORGANIZER')") public Event update(@PathVariable String id,@Valid @RequestBody EventRequest r,Principal p,org.springframework.security.core.Authentication a){return service.update(id,r,p.getName(),a.getAuthorities().stream().anyMatch(x->x.getAuthority().equals("ROLE_ADMIN")));}
 @DeleteMapping("/{id}") @PreAuthorize("hasRole('ADMIN')") public void delete(@PathVariable String id){service.delete(id);}
}
