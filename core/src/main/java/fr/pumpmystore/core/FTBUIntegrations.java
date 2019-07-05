package fr.pumpmystore.core;

import java.sql.ResultSet;

public abstract class FTBUIntegrations {

	private MySql mysql;
	
	public FTBUIntegrations(MySql mysql) throws Exception {
		this.mysql = mysql;
		this.initMySql();
	}
	
	private void initMySql() throws Exception {
		
		String createtable = "CREATE TABLE IF NOT EXISTS `ftbuplayerranks` ( `uuid` VARCHAR(50) NOT NULL , `rank` VARCHAR(50) NOT NULL , PRIMARY KEY (`uuid`), UNIQUE (`uuid`)) ENGINE = InnoDB;";
		this.mysql.sendUpdate(createtable);	
		
	}
	
	public void removeFTBURank(String uuid) throws Exception{
		
		this.mysql.sendUpdate("DELETE FROM `ftbuplayerranks` WHERE `uuid`='" + uuid + "';");
		
	}
	
	public void addFTBURank(String uuid, String rankName) throws Exception {
		
		this.mysql.sendUpdate("INSERT INTO `ftbuplayerranks`(`uuid`, `rank`) VALUES ('" + uuid + "','" + rankName.toLowerCase() + "');");
		
	}
	
	public String getFTBURank(String uuid) throws Exception {
		
		ResultSet rs = this.mysql.sendQuery("SELECT `rank` FROM `ftbuplayerranks` WHERE `uuid`='" + uuid + "';");
		
		if(!rs.first()) {
			return new String();
		}
		
		return rs.getString("rank");
		
	}
	
}
