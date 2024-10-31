package com.example.equipesecurite.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomUserDetailServices implements UserDetailsService {
  private final RestTemplate restTemplate;

  public CustomUserDetailServices() {
    this.restTemplate = new RestTemplate();
  }

  @Override
  public UserDetails loadUserByUsername(String combinedUsername) throws UsernameNotFoundException {
    String[] parts = combinedUsername.split(":");
    if (parts.length != 2) {
      throw new UsernameNotFoundException("Invalid username format. Expected format: name:type");
    }

    String name = parts[0]; // Change this to "name"
    String userType = parts[1];
    System.out.println("Searching for user...");

    Map<String, String> user_infos = new HashMap<>();

    if (userType.equals("Player")) {
      System.out.println("Searching for Player...");
      // Assuming the REST service allows searching by name
      user_infos = restTemplate.getForObject("http://localhost:8888/Service_Player/players/name/" + name, HashMap.class);
    }

    if (userType.equals("Equipe")) {
      // Optionally, you can add searching for Equipe by name here.
      System.out.println("Searching for Equipe...");
      user_infos = restTemplate.getForObject("http://localhost:8888/Service_Equipe/equipes/name/" + name, HashMap.class);
    }

    if (user_infos == null || user_infos.isEmpty()) {
      throw new UsernameNotFoundException("User not found with name: " + name);
    }

    Collection<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(user_infos.get("scope")));

    return new User(user_infos.get("email"), "{noop}" + user_infos.get("password"), authorities);
  }
}
