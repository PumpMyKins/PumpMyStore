package fr.pumpmyfstore;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.Logger;

import com.feed_the_beast.ftblib.FTBLib;

import fr.pumpmystore.core.MySql;
import fr.pumpmystore.core.MySql.MySQLCredentials;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION, acceptableRemoteVersions = "*", dependencies = Main.DEP, serverSideOnly = true)
public class Main
{
    public static final String MODID = "pumpmyfstore";
    public static final String NAME = "PumpMyFStore";
    public static final String VERSION = "1.0";
    public static final String DEP = FTBLib.THIS_DEP + ";required-after:ftbutilities";

    public static Logger LOGGER;
    public static SimpleNetworkWrapper NETWORK;
    public static FTBUIntegration FTBUI;
    public static ExecutorService EXEC;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        LOGGER = event.getModLog();
        EXEC = Executors.newSingleThreadScheduledExecutor();
        NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel("FTBU-RANKS");
        NETWORK.registerMessage(UpdateMessage.Handler.class, UpdateMessage.class, 0, Side.SERVER);
        
    }
    
    @EventHandler
    public void onServerStarted(FMLServerStartedEvent event)
    {
        
        try {
            MySQLCredentials credentials = new MySQLCredentials(StoreConfig.host, StoreConfig.port, StoreConfig.username, StoreConfig.password, StoreConfig.database);
            MySql mySql = new MySql(credentials);
			mySql.openConnection();
	        FTBUI = new FTBUIntegration(mySql);
	        return;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }

}
