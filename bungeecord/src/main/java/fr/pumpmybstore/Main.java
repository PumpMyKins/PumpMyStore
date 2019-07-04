package fr.pumpmybstore;


import java.sql.SQLException;

import fr.pumpmybstore.kit.BddKitManager;
import fr.pumpmybstore.rank.FTBURanks;
import fr.pumpmybstore.rank.SetRankCommand;
import fr.pumpmybstore.rank.UnsetRankCommand;
import fr.pumpmystore.core.MySql;
import fr.pumpmystore.core.MySql.MySQLCredentials;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class Main extends Plugin{

	public final static String PLUGIN_PREFIX = "§l§6[§2Pump§eMy§5BStore§6]§r§f ";

	private ConfigManager configManager;
	private BddKitManager bddKitManager;
	private FTBURanks ftbuRanks;

	public ConfigManager getConfigManager() {return configManager;}
	public BddKitManager getBddKitManager() {return bddKitManager;}
	public FTBURanks getFtbuRanks() {return ftbuRanks;}

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
		
		
		MySql kitMySql;
		try {
			MySQLCredentials credentials = new MySQLCredentials(this.configManager.getKitHost(),this.configManager.getKitPort(), this.configManager.getKitUser(), this.configManager.getKitPassword(), this.configManager.getKitDatabase());
			kitMySql = new MySql(credentials);
			kitMySql.openConnection();
			this.getLogger().info("MySQL OK");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			this.getLogger().severe("JDBC error, plugin disabled !");
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			this.getLogger().severe("MySQL connection error, plugin disabled !");
			return;
		}
		
		try {
			this.bddKitManager = new BddKitManager(kitMySql);
		} catch (Exception e) {
			e.printStackTrace();
			this.getLogger().severe("MySQL error, plugin disabled !");
			return;
		}
		
		
		MySql rankMySql;
		try {
			MySQLCredentials credentials = new MySQLCredentials(this.configManager.getRankHost(),this.configManager.getRankPort(), this.configManager.getRankUser(), this.configManager.getRankPassword(), this.configManager.getRankDatabase());
			rankMySql = new MySql(credentials);
			rankMySql.openConnection();
			this.getLogger().info("MySQL OK");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			this.getLogger().severe("JDBC error, plugin disabled !");
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			this.getLogger().severe("MySQL connection error, plugin disabled !");
			return;
		}
		
		try {
			this.ftbuRanks = new FTBURanks(rankMySql);
		} catch (Exception e) {
			e.printStackTrace();
			this.getLogger().severe("MySQL error, plugin disabled !");
			return;
		}
		

		PluginManager pm = this.getProxy().getPluginManager();
		pm.registerCommand(this, new SetRankCommand(this));
		pm.registerCommand(this, new UnsetRankCommand(this));



	}

}
