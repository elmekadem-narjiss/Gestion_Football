package com.example.service_equipe.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Equipe")
public class Equipe {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int num_serie ;
  private  String code ;
  private  String libelle ;

  @Transient
  private  int id_player ;



}



