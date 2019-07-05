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

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION, acceptableRemoteVersions = "*", dependencies = Main.DEP)
public class Main
{
    public static final String MODID = "pumpmyfstore";
    public static final String NAME = "PumpMyFStore";
    public static final String VERSION = "1.0";
    public static final String DEP = FTBLib.THIS_DEP + ";required-after:ftbutilities";

    private static Logger logger;
    public static SimpleNetworkWrapper network;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        network = NetworkRegistry.INSTANCE.newSimpleChannel("FTBU-RANKS");
        network.registerMessage(MyMessage.Handler.class, MyMessage.class, 0, Side.SERVER);
        
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}
