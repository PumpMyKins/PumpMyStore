package fr.pumpmyfstore;

import net.minecraftforge.common.config.Config;


@Config(modid = Main.MODID)
public class StoreConfig {

	public static String host = "localhost";
	
	public static int port = 3306;
	
	public static String username = "root";
	
	public static String password = "root";
	
	public static String database = "root";
	
}
