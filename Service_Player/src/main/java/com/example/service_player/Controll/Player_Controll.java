package com.example.service_player.Controll;

import com.example.service_player.Entities.Player;
import com.example.service_player.Service.Player_Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(value = "Player Management System", tags = {"Players"})
@RestController
@RequestMapping("/api/players")
public class Player_Controll {

  @Autowired
  private Player_Service playerService;

  @ApiOperation(value = "Creeate a new playeer", response = Player.class)
  @ApiResponses(value = {
    @ApiResponse(code = 201, message = "Player created succcessfully"),
    @ApiResponse(code = 400, message = "Invalid input data")
  })
  @PostMapping
  public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
    Player savedPlayer = playerService.savePlayer(player);
    return new ResponseEntity<>(savedPlayer, HttpStatus.CREATED);
  }

  @ApiOperation(value = "Save a player", response = Player.class)
  @ApiResponses(value = {
    @ApiResponse(code = 201, message = "Player saved successsfully"),
    @ApiResponse(code = 400, message = "Invalid input data")
  })
  @PostMapping("/save")
  public ResponseEntity<Player> savePlayer(@RequestBody Player player) {
    Player savedPlayer = playerService.savePlayer(player);
    return new ResponseEntity<>(savedPlayer, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Player>> getAllPlayers() {
    List<Player> players = playerService.getAllPlayers();
    return new ResponseEntity<>(players, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Player> getPlayerById(@PathVariable int id) {
    Optional<Player> player = playerService.getPlayerById(id);
    return player.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePlayer(@PathVariable int id) {
    playerService.deletePlayer(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/player/{playerid}/best-player")
  public ResponseEntity<Player> getBestPlayer(@PathVariable int playerid) {
    Player player = new Player();
    player.setId(player.getId());

    Optional<Player> bestScorer = playerService.getBestPlayer(player);
    return bestScorer.map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  // Scope admin
  @GetMapping("/lister-buts")
  public ResponseEntity<List<Player>> getAllPlayersByGoals() {
    List<Player> players = playerService.getAllPlayersByGoals();
    return new ResponseEntity<>(players, HttpStatus.OK);
  }

  // Pour PUT, scope admin
  @PutMapping
  public ResponseEntity<Void> updatePlayers(@RequestBody List<Player> updatedPlayers) {
    playerService.updatePlayers(updatedPlayers);
    return ResponseEntity.noContent().build();
  }

  // Pour PATCH, scope admin
  @PatchMapping("/augmenter-buts")
  public ResponseEntity<Void> AugmenteButs(@RequestBody List<Player> players) {
    playerService.augmenterButs(players);
    return ResponseEntity.noContent().build();
  }
}


