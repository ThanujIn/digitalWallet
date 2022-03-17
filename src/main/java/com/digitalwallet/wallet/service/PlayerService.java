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
            Player existingPlayer = findByFirstNameAndLastName(player.getFirstName(), player.getLastName());
            if(existingPlayer != null && !existingPlayer.getActive()){
                existingPlayer.setActive(true);
                playerRepository.save(existingPlayer);
                Account acc = existingPlayer.getAccount();
                acc.setActive(true);
                accountRepository.save(acc);
                return new ResponseEntity<>("Activated already existed player", HttpStatus.OK);
            }else{
                Player newPlayer = playerRepository.save(new Player(player));
                Account playerDefaultAccount = new Account(newPlayer.getId() * 1000L);
                playerDefaultAccount.setPlayer(newPlayer);
                playerDefaultAccount.setActive(true);
                accountRepository.save(playerDefaultAccount);
                newPlayer.setAccount(playerDefaultAccount);
                playerRepository.save(newPlayer);
                return new ResponseEntity<>("Player registration successful and a new account created", HttpStatus.OK);
            }

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Player registration failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Player findByFirstNameAndLastName(String firstName, String lastName){
        return playerRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public ResponseEntity<Object> deletePlayerById(Long playerId){
        try{
            Account playerAccount = accountRepository.getById(playerId * 1000L);
            playerAccount.setActive(false);
            accountRepository.save(playerAccount);
            Player player = playerRepository.getById(playerId);
            player.setActive(false);
            playerRepository.save(player);
            return new ResponseEntity<>("Player deactivation successful", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Player deactivation failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
