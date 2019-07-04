package fr.pumpmybstore.rank;

import fr.pumpmystore.core.FTBUIntegrations;
import fr.pumpmystore.core.MySql;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class FTBURanks extends FTBUIntegrations {

	public FTBURanks(MySql mysql) throws Exception {
		super(mysql);
	}
	
	public void setProxiedPlayerFTBURank(ProxiedPlayer player, Rank rank) throws Exception {
		
		this.addFTBURank(player.getUniqueId().toString(), rank.name());
		
	}
	
	public void unsetProxiedPlayerFTBURank(ProxiedPlayer player) throws Exception {
		
		this.removeFTBURank(player.getUniqueId().toString());
		
	}

}
