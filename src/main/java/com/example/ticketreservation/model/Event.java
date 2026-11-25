package com.example.ticketreservation.model;
import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document("events")
public class Event {
 @Id private String id; private String name,description,venue,organizer; private LocalDateTime eventDate;
 private int totalSeats,availableSeats; private double ticketPrice;
 public Event(){}
 public String getId(){return id;} public void setId(String v){id=v;}
 public String getName(){return name;} public void setName(String v){name=v;}
 public String getDescription(){return description;} public void setDescription(String v){description=v;}
 public String getVenue(){return venue;} public void setVenue(String v){venue=v;}
 public String getOrganizer(){return organizer;} public void setOrganizer(String v){organizer=v;}
 public LocalDateTime getEventDate(){return eventDate;} public void setEventDate(LocalDateTime v){eventDate=v;}
 public int getTotalSeats(){return totalSeats;} public void setTotalSeats(int v){totalSeats=v;}
 public int getAvailableSeats(){return availableSeats;} public void setAvailableSeats(int v){availableSeats=v;}
 public double getTicketPrice(){return ticketPrice;} public void setTicketPrice(double v){ticketPrice=v;}
}
