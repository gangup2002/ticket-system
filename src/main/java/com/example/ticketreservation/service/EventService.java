package com.example.ticketreservation.service;
import com.example.ticketreservation.dto.Requests.EventRequest;
import com.example.ticketreservation.exception.ApiException;
import com.example.ticketreservation.model.Event;
import com.example.ticketreservation.repository.*;
import java.util.List;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
@Service
public class EventService {
 private final EventRepository events; private final ReservationRepository reservations; private final MongoTemplate mongo;
 public EventService(EventRepository e,ReservationRepository r,MongoTemplate m){events=e;reservations=r;mongo=m;}
 public List<Event> all(){return events.findAll();}
 public Event get(String id){return events.findById(id).orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,"Event not found"));}
 public Event create(EventRequest r,String owner){Event e=new Event();apply(e,r);e.setOrganizer(owner);e.setAvailableSeats(r.totalSeats());return events.save(e);}
 public Event update(String id,EventRequest r,String owner,boolean admin){Event e=get(id);if(!admin&&!e.getOrganizer().equals(owner))throw new ApiException(HttpStatus.FORBIDDEN,"You can edit only your own events");int booked=e.getTotalSeats()-e.getAvailableSeats();if(r.totalSeats()<booked)throw new ApiException(HttpStatus.CONFLICT,"Total seats cannot be below already reserved seats");apply(e,r);e.setAvailableSeats(r.totalSeats()-booked);return events.save(e);}
 public void delete(String id){if(reservations.existsByEventIdAndStatus(id,com.example.ticketreservation.model.ReservationStatus.CONFIRMED))throw new ApiException(HttpStatus.CONFLICT,"Cannot delete an event with confirmed reservations");events.delete(get(id));}
 public boolean takeSeats(String id,int seats){Query q=new Query(Criteria.where("_id").is(id).and("availableSeats").gte(seats));Update u=new Update().inc("availableSeats",-seats);return mongo.updateFirst(q,u,Event.class).getModifiedCount()==1;}
 public void releaseSeats(String id,int seats){mongo.updateFirst(new Query(Criteria.where("_id").is(id)),new Update().inc("availableSeats",seats),Event.class);}
 private void apply(Event e,EventRequest r){e.setName(r.name());e.setDescription(r.description());e.setVenue(r.venue());e.setEventDate(r.eventDate());e.setTotalSeats(r.totalSeats());e.setTicketPrice(r.ticketPrice());}
}
