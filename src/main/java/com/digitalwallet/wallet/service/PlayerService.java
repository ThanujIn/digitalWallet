package com.digitalwallet.wallet.service;

import com.digitalwallet.wallet.repository.PlayerRepository;
import com.digitalwallet.wallet.dto.PlayerDTO;
import com.digitalwallet.wallet.model.Account;
import com.digitalwallet.wallet.model.Player;
import com.digitalwallet.wallet.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void addPlayer(@RequestBody PlayerDTO player) {
        try{
            Player newPlayer = playerRepository.save(new Player(player));
            accountRepository.save(new Account(newPlayer.getId() * 1000));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void deletePlayerById(Long playerId){
        try{
            playerRepository.deleteById(playerId);
            Account playerAccount = accountRepository.getById(playerId * 1000 + "");
            playerAccount.setActive(false);
            accountRepository.save(playerAccount);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
