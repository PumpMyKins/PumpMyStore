package fr.pumpmybstore;

import fr.pumpmybstore.kit.InitKitCommand;
import fr.pumpmybstore.rank.SetRankCommand;
import fr.pumpmybstore.rank.UnsetRankCommand;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class Main extends Plugin{

	public final static String PLUGIN_PREFIX = "§l§6[§2Pump§eMy§5BStore§6]§r§f ";
	
	@Override
	public void onEnable() {
		
		PluginManager pm = this.getProxy().getPluginManager();
		pm.registerCommand(this, new SetRankCommand(this));
		pm.registerCommand(this, new UnsetRankCommand(this));
		
		pm.registerCommand(this, new InitKitCommand(this));
		
	}
	
}
