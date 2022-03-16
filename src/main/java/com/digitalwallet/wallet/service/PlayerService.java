package com.digitalwallet.wallet.service;

import com.digitalwallet.wallet.repository.PlayerRepository;
import com.digitalwallet.wallet.dto.PlayerDTO;
import com.digitalwallet.wallet.model.Account;
import com.digitalwallet.wallet.model.Player;
import com.digitalwallet.wallet.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    AccountRepository accountRepository;

    public Optional<Player> getPlayerById(Long playerId){
        return playerRepository.findById(playerId);
    }

    public ResponseEntity<Object> addPlayer(@RequestBody PlayerDTO player) {
        try{
            Player newPlayer = playerRepository.save(new Player(player));
            accountRepository.save(new Account(newPlayer.getId() * 1000));
            return new ResponseEntity<>("Player registration successful and a new account created", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Player registration failed", HttpStatus.OK);
        }

    }

    public ResponseEntity<Object> deletePlayerById(Long playerId){
        try{
            playerRepository.deleteById(playerId);
            Account playerAccount = accountRepository.getById(playerId * 1000 + "");
            playerAccount.setActive(false);
            accountRepository.save(playerAccount);
            return new ResponseEntity<>("Player deactivation successful", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Player deactivation failed", HttpStatus.OK);
        }
    }
}
