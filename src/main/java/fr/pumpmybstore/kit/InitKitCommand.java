package fr.pumpmybstore.kit;

import java.util.ArrayList;
import java.util.List;

import fr.pumpmybstore.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

public class InitKitCommand extends Command implements TabExecutor{

	private Main main;

	public InitKitCommand(Main main) {
		super("pumpmybstore-kit-init", "pumpmybstore.command.rank.set");
		this.main = main;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		// TODO Auto-generated method stub
		
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
