package com.example.ticketreservation.service;
import com.example.ticketreservation.dto.Responses.StatsResponse;import com.example.ticketreservation.model.*;
import com.example.ticketreservation.repository.*;
import org.springframework.stereotype.Service;
@Service
public class AdminService {
 private final UserRepository users;private final EventRepository events;private final ReservationRepository reservations;
 public AdminService(UserRepository u,EventRepository e,ReservationRepository r){users=u;events=e;reservations=r;}
 public StatsResponse stats(){var confirmed=reservations.findAll().stream().filter(r->r.getStatus()==ReservationStatus.CONFIRMED).toList();return new StatsResponse(users.count(),events.count(),confirmed.stream().mapToLong(Reservation::getSeats).sum(),confirmed.stream().mapToDouble(Reservation::getAmount).sum());}
}
