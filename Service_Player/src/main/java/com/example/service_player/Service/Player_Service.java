package com.example.service_player.Service;


import com.example.service_player.Entities.Player;
import com.example.service_player.Repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
  public class Player_Service {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
      return playerRepository.findAll();
    }

    public Optional<Player> getPlayerById(int id) {
      return playerRepository.findById(id);
    }

    public Player savePlayer(Player player) {
      return playerRepository.save(player);
    }

    public void deletePlayer(int id) {
      playerRepository.deleteById(id);
    }

  //calcule le butteur de l'aquipe solon le maxx de but a marquer

  //lister les joueur de  maniere decroissante
    public List<Player> getAllPlayersByGoals() {
      return playerRepository.findAll().stream()
        .sorted(Comparator.comparing(Player::getButs).reversed())
        .collect(Collectors.toList());
    }

  //recupere le meilleur butteur
    public Optional<Player> getBestPlayer(Player player) {
      return playerRepository.findAll().stream()
        .filter(player1  -> player.getButs().equals(player))
        .max(Comparator.comparing(Player::getButs));
    }


  //mis a jour le nb de joueur selon il sont quitter l equipe ou non
    public void updatePlayers(List<Player> updatedPlayers) {

      List<Integer> currentPlayerIds = updatedPlayers.stream()
        .map(Player::getId)
        .collect(Collectors.toList());


      List<Player> currentPlayers = playerRepository.findAll(); // players actuelles
      for (Player player : currentPlayers) {
        if (!currentPlayerIds.contains(player.getId())) {  // Quitte
          playerRepository.delete(player);
        }
      }

      for (Player player : updatedPlayers) { // update
        playerRepository.save(player);
      }
    }


//augmenter les buts
  public void augmenterButs(List<Player> players) {
    for (Player player : players) {
      Optional<Player> existingPlayer = playerRepository.findById(player.getId());
      existingPlayer.ifPresent(p -> {
        p.setButs(p.getButs() + player.getButs());
        playerRepository.save(p);
      });
    }
  }
}
