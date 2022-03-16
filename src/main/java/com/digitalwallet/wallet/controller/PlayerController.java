package com.digitalwallet.wallet.controller;

import com.digitalwallet.wallet.dto.PlayerDTO;
import com.digitalwallet.wallet.model.Player;
import com.digitalwallet.wallet.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/player")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @GetMapping(path = "/getPlayerById/{playerId}")
    public ResponseEntity<Object> getPlayerById(@PathVariable("playerId") String playerId){
        Optional<Player> player = playerService.getPlayerById(Long.parseLong(playerId));
        return player.<ResponseEntity<Object>>map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElse(null);
    }

    @PostMapping(path = "/addPlayer")
    public void addPlayer(@RequestBody PlayerDTO playerDTO){
        playerService.addPlayer(playerDTO);
    }

    @DeleteMapping(path = "/deletePlayerById/{playerId}")
    public void deletePlayerById(@PathVariable("playerId") String playerId){
        playerService.deletePlayerById(Long.parseLong(playerId));
    }
}
