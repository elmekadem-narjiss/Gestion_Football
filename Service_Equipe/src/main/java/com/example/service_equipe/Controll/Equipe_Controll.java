package com.example.service_equipe.Controll;

import com.example.service_equipe.Entities.Equipe;
import com.example.service_equipe.Service.Equipe_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/equipes/")
public class Equipe_Controll {

  @Autowired
  private Equipe_Service equipeService;

  @GetMapping
  public List<Equipe> getAllEquipes() {
    return equipeService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Equipe> getEquipeById(@PathVariable int id) {
    return equipeService.findById(id)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public Equipe createEquipe(@RequestBody Equipe equipe) {
    return equipeService.save(equipe);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEquipe(@PathVariable int id) {
    equipeService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
