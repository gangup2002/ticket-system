package com.example.ticketreservation.service;
import com.example.ticketreservation.dto.Requests.ReservationRequest;
import com.example.ticketreservation.exception.ApiException;
import com.example.ticketreservation.model.*;
import com.example.ticketreservation.repository.ReservationRepository;
import java.time.LocalDateTime;import java.util.*;
import org.springframework.http.HttpStatus;import org.springframework.stereotype.Service;
@Service
public class ReservationService {
 private final ReservationRepository reservations;private final EventService events;
 public ReservationService(ReservationRepository r,EventService e){reservations=r;events=e;}
 public Reservation book(ReservationRequest r,String username){Event e=events.get(r.eventId());if(!events.takeSeats(e.getId(),r.seats()))throw new ApiException(HttpStatus.CONFLICT,"Not enough available seats");
  Reservation b=new Reservation();b.setReservationCode("TKT-"+UUID.randomUUID().toString().substring(0,8).toUpperCase());b.setCustomerUsername(username);b.setEventId(e.getId());b.setSeats(r.seats());b.setAmount(e.getTicketPrice()*r.seats());b.setStatus(ReservationStatus.CONFIRMED);b.setCreatedAt(LocalDateTime.now());return reservations.save(b);}
 public List<Reservation> mine(String u){return reservations.findByCustomerUsername(u);}
 public List<Reservation> all(){return reservations.findAll();}
 public List<Reservation> byEventOrganizer(String username){return events.all().stream().filter(e->e.getOrganizer().equals(username)).flatMap(e->reservations.findByEventId(e.getId()).stream()).toList();}
 public void cancel(String id,String username,boolean admin){Reservation r=reservations.findById(id).orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,"Reservation not found"));if(!admin&&!r.getCustomerUsername().equals(username))throw new ApiException(HttpStatus.FORBIDDEN,"You can cancel only your own reservations");if(r.getStatus()==ReservationStatus.CONFIRMED){r.setStatus(ReservationStatus.CANCELLED);reservations.save(r);events.releaseSeats(r.getEventId(),r.getSeats());}}
}
