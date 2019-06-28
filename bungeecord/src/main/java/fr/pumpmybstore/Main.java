package fr.pumpmybstore;


import fr.pumpmybstore.rank.SetRankCommand;
import fr.pumpmybstore.rank.UnsetRankCommand;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class Main extends Plugin{

	public final static String PLUGIN_PREFIX = "§l§6[§2Pump§eMy§5BStore§6]§r§f ";

	private ConfigManager configManager;
	
	public ConfigManager getConfigManager() {return configManager;}

	@Override
	public void onEnable() {
		
		try {

			configManager = new ConfigManager(this);
			this.getLogger().info("Configuration OK");

		} catch (Exception e) {

			e.printStackTrace();
			this.getLogger().severe("Configuration error, plugin disabled !");
			return;

		}
		
		PluginManager pm = this.getProxy().getPluginManager();
		pm.registerCommand(this, new SetRankCommand(this));
		pm.registerCommand(this, new UnsetRankCommand(this));
		
		
		
	}
	
}
