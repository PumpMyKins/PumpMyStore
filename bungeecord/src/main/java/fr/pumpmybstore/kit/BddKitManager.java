package fr.pumpmybstore.kit;

import fr.pumpmybstore.rank.Rank;
import fr.pumpmystore.core.MySql;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class BddKitManager {

	private MySql mySql;

	public BddKitManager(MySql mySql) throws Exception {
		this.mySql = mySql;
		initMySql();
	}

	private void initMySql() throws Exception {

		String createtable = "CREATE TABLE IF NOT EXISTS `playerskit` ( `uuid` VARCHAR(50) NOT NULL , `global_random` INT NOT NULL , `per_server_random` INT NOT NULL DEFAULT '0' ,`global_select` INT NOT NULL DEFAULT '0' , `per_server_select` INT NOT NULL , `init_date` BIGINT , PRIMARY KEY (`uuid`), UNIQUE (`uuid`)) ENGINE = InnoDB;";
		this.mySql.sendUpdate(createtable);	

	}

	public void addPlayerInPlayersKitList(ProxiedPlayer player, Rank rank) throws Exception {

		this.mySql.sendUpdate("INSERT INTO `playerskit`(`uuid`, `global_random`, `per_server_select`,`init_date`) VALUES ('" + player.getUniqueId().toString() + "','" + rank.getKitGlobalRandom() + "','" + rank.getKitPerServerChoice() + "'," + System.currentTimeMillis() + ")");

	}
	
	public void updatePlayerInPlayersKitList(ProxiedPlayer player, Rank rank) throws Exception {

		this.mySql.sendUpdate("UPDATE `playerskit` SET `global_random`='" + rank.getKitGlobalRandom() + "' ,`per_server_select`='" + rank.getKitPerServerChoice() + "',`init_date`=" + System.currentTimeMillis() + " WHERE `uuid`='" + player.getUniqueId().toString() + "';");

	}

	public void removePlayerFromPlayersKitList(ProxiedPlayer player) throws Exception {

		this.mySql.sendUpdate("DELETE FROM `playerskit` WHERE `uuid`='" + player.getUniqueId().toString() + "';");

	}

}
