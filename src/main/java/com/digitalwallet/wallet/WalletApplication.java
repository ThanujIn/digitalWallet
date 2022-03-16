package com.digitalwallet.wallet;

import com.digitalwallet.wallet.model.Player;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WalletApplication {

	public static void main(String[] args) {
		//ConfigurableApplicationContext configurableApplicationContext =
				SpringApplication.run(WalletApplication.class, args);
		/*PlayerRepository playerRepository =
				configurableApplicationContext.getBean(PlayerRepository.class);
		Player player = new Player();
		player.setFirstName("Thanuja");
		player.setLastName("Gunasekara");
		playerRepository.save(player);*/
	}

}
