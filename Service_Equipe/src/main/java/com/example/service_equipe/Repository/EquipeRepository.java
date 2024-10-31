package com.example.service_equipe.Repository;


import com.example.service_equipe.Entities.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Integer> {

  //Chercheur findChercheurByEmail(String email); // utiliser en login


}
