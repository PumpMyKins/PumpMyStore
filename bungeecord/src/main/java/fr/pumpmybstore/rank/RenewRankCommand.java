package fr.pumpmybstore.rank;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import fr.pumpmybstore.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.api.plugin.TabExecutor;

public class RenewRankCommand extends Command implements TabExecutor{

	private Main main;

	public RenewRankCommand(Main main) {
		super("pumpmybstore-renewrank", "pumpmybstore.command.rank.set");
		this.main = main;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		
		if(sender instanceof ProxiedPlayer) {			
			return;			
		}
		
		if(args.length != 2) {
			return;
		}
		
		String playerName = args[0];
		
		if(!Lists.newArrayList(this.onTabComplete(sender, args)).contains(playerName)) {
			return;
		}
		
		String rankName = args[1];
		Rank rank = null;
		
		for (Rank r : Rank.values()) {
			
			if(r.name().equals(rankName)) {
				rank = r;
				break;
			}
			
		}
		
		if(rank == null) {
			return;
		}
		
		PluginManager pm = this.main.getProxy().getPluginManager();
		ProxiedPlayer player = this.main.getProxy().getPlayer(playerName);
		
		pm.dispatchCommand(sender, "bprefix-admin renew " + playerName + " " + rank.getModification()); // init prefix
		
		try {
			this.main.getBddKitManager().updatePlayerInPlayersKitList(player, rank);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TextComponent txt = new TextComponent(Main.PLUGIN_PREFIX);
		TextComponent txt1 = new TextComponent("Renouvellement des avantages " + rank.name() + " effectué");
		txt1.setColor(ChatColor.AQUA);
		txt.addExtra(txt1);			
		player.sendMessage(txt);

	}
	
	@Override
	public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
		
		List<String> l = new ArrayList<>();
		
		for (ProxiedPlayer player : this.main.getProxy().getPlayers()) {
			
			l.add(player.getName());
			
		}
		
		return l;
		
	}

}
