package fr.pumpmybstore.rank;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

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
	
	public void sendMessagingUpdate(ProxiedPlayer player) {
		
		  ByteArrayDataOutput out = ByteStreams.newDataOutput();
		  out.writeUTF(player.getName());
		  
		  player.getServer().sendData("FTBU-RANKS", out.toByteArray());
		
	}

}
