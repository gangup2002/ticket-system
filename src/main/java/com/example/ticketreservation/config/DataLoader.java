package com.example.ticketreservation.config;
import com.example.ticketreservation.model.*;import com.example.ticketreservation.repository.*;
import java.time.LocalDateTime;
import org.springframework.boot.CommandLineRunner;import org.springframework.context.annotation.Bean;import org.springframework.context.annotation.Configuration;import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class DataLoader {
 @Bean CommandLineRunner seed(UserRepository users,EventRepository events,PasswordEncoder encoder){return args->{
  if(!users.existsByUsername("admin"))users.save(new AppUser("admin",encoder.encode("admin123"),"System Administrator",Role.ADMIN));
  if(!users.existsByUsername("organizer"))users.save(new AppUser("organizer",encoder.encode("organizer123"),"Event Organizer",Role.ORGANIZER));
  if(!users.existsByUsername("customer"))users.save(new AppUser("customer",encoder.encode("customer123"),"Test Customer",Role.CUSTOMER));
  if(events.count()==0){Event e=new Event();e.setName("Sample Music Concert");e.setDescription("Sample event for Lab 08");e.setVenue("Colombo");e.setEventDate(LocalDateTime.of(2030,12,31,18,0));e.setOrganizer("organizer");e.setTotalSeats(20);e.setAvailableSeats(20);e.setTicketPrice(1000);events.save(e);}
 };}
}
