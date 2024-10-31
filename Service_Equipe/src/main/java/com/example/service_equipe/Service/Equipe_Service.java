package com.example.service_equipe.Service;

import com.example.service_equipe.Entities.Equipe;
import com.example.service_equipe.Repository.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Equipe_Service {

  @Autowired
  private EquipeRepository equipeRepository;

  public List<Equipe> findAll() {
    return equipeRepository.findAll();
  }

  public Optional<Equipe> findById(int id) {
    return equipeRepository.findById(id);
  }

  public Equipe save(Equipe equipe) {
    return equipeRepository.save(equipe);
  }

  public void deleteById(int id) {
    equipeRepository.deleteById(id);
  }
}
