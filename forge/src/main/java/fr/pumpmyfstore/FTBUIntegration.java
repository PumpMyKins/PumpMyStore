package fr.pumpmyfstore;

import com.feed_the_beast.ftblib.lib.command.CommandUtils;
import com.feed_the_beast.ftblib.lib.data.ForgePlayer;
import com.feed_the_beast.ftblib.lib.util.ServerUtils;
import com.feed_the_beast.ftbutilities.ranks.Rank;
import com.feed_the_beast.ftbutilities.ranks.Ranks;

import fr.pumpmystore.core.FTBUIntegrations;
import fr.pumpmystore.core.MySql;
import net.minecraft.command.CommandException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@EventBusSubscriber(modid = Main.MODID)
public class FTBUIntegration extends FTBUIntegrations{
	
	public FTBUIntegration(MySql mySql) throws Exception {
		super(mySql);
		if (!Ranks.isActive()) {
			throw new Exception("FTBU Ranks disabled");
		}
	}
	
	public void updatePlayerRanks(ForgePlayer player) throws Exception {
		
		String rankName = this.getFTBURank(player.getId().toString());
		
		if (!Ranks.INSTANCE.getRankNames(true).contains(rankName))	{
			
			rankName = "player";
			
		}
		
		Rank rank = Ranks.INSTANCE.getRank(rankName);

		if (Ranks.INSTANCE.setRank(player.getId(), rank))
		{
			Main.LOGGER.info("Rank " + rankName + " successfuly updated on " + player.getName());
		}
		else
		{
			Main.LOGGER.info("Rank " + rankName + " not updated on " + player.getName());
		}
		
		
	}
	
	@SubscribeEvent
	public static void onPlayerJoinServer(PlayerEvent.PlayerLoggedInEvent event) throws CommandException {
		
		if (!(event.player instanceof EntityPlayerMP) || ServerUtils.isFake((EntityPlayerMP) event.player)) {
			return;
		}
		
		ForgePlayer player = CommandUtils.getForgePlayer(event.player);
		Main.EXEC.submit(new Runnable() {
			
			@Override
			public void run() {				
				try {
					Main.FTBUI.updatePlayerRanks(player);
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		});
		
	}

}
