package com.example.ticketreservation.exception;
import java.util.Map;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
@RestControllerAdvice
public class GlobalExceptionHandler {
 @ExceptionHandler(ApiException.class) public ResponseEntity<?> api(ApiException e){return ResponseEntity.status(e.getStatus()).body(Map.of("error",e.getMessage()));}
 @ExceptionHandler(MethodArgumentNotValidException.class) public ResponseEntity<?> validation(MethodArgumentNotValidException e){return ResponseEntity.badRequest().body(Map.of("error","Invalid request","details",e.getBindingResult().getFieldErrors().stream().map(x->x.getField()+": "+x.getDefaultMessage()).toList()));}
 @ExceptionHandler(Exception.class) public ResponseEntity<?> other(Exception e){return ResponseEntity.status(500).body(Map.of("error","Internal server error"));}
}
