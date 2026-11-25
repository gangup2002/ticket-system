package com.example.ticketreservation.model;
import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document("reservations")
public class Reservation {
 @Id private String id; private String reservationCode,customerUsername,eventId; private int seats; private double amount;
 private ReservationStatus status; private LocalDateTime createdAt;
 public Reservation(){}
 public String getId(){return id;} public void setId(String v){id=v;}
 public String getReservationCode(){return reservationCode;} public void setReservationCode(String v){reservationCode=v;}
 public String getCustomerUsername(){return customerUsername;} public void setCustomerUsername(String v){customerUsername=v;}
 public String getEventId(){return eventId;} public void setEventId(String v){eventId=v;}
 public int getSeats(){return seats;} public void setSeats(int v){seats=v;}
 public double getAmount(){return amount;} public void setAmount(double v){amount=v;}
 public ReservationStatus getStatus(){return status;} public void setStatus(ReservationStatus v){status=v;}
 public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
}
