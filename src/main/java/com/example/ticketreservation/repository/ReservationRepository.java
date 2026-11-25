package com.example.ticketreservation.repository;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.ticketreservation.model.Reservation;
public interface ReservationRepository extends MongoRepository<Reservation,String> {
 List<Reservation> findByCustomerUsername(String username); List<Reservation> findByEventId(String eventId);
 boolean existsByEventIdAndStatus(String eventId, com.example.ticketreservation.model.ReservationStatus status);
}
