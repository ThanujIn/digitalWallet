package com.digitalwallet.wallet.repository;

import com.digitalwallet.wallet.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findByFirstNameAndLastName(String firstName, String lastName);
}
