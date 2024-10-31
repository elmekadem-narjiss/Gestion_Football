package com.example.service_player.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "players")
@NoArgsConstructor
public class Player {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
   private  int id ;
   private String nom ;
   private String position ;
   private String salaire ;
   private String buts ;

   //meme equipe on n a pas besoin d'un id equipe

}
