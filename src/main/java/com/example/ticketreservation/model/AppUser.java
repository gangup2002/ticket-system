package com.example.ticketreservation.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document("users")
public class AppUser {
 @Id private String id; private String username; private String password; private String fullName; private Role role;
 public AppUser() {}
 public AppUser(String username,String password,String fullName,Role role){this.username=username;this.password=password;this.fullName=fullName;this.role=role;}
 public String getId(){return id;} public void setId(String v){id=v;}
 public String getUsername(){return username;} public void setUsername(String v){username=v;}
 public String getPassword(){return password;} public void setPassword(String v){password=v;}
 public String getFullName(){return fullName;} public void setFullName(String v){fullName=v;}
 public Role getRole(){return role;} public void setRole(Role v){role=v;}
}
