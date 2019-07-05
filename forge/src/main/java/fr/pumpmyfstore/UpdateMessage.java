package fr.pumpmyfstore;

import com.feed_the_beast.ftblib.lib.command.CommandUtils;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class UpdateMessage implements IMessage{

	private String text;

	public UpdateMessage() { }

	public UpdateMessage(String text) {
		this.text = text;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		text = ByteBufUtils.readUTF8String(buf); // this class is very useful in general for writing more complex objects
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, text);
	}

	public static class Handler implements IMessageHandler<UpdateMessage, IMessage> {

		// or in 1.8:
		@Override
		public IMessage onMessage(UpdateMessage message, MessageContext ctx) {
			
			Main.LOGGER.info(String.format("Received update for %s", message.text));            	
			try {
				Main.FTBUI.updatePlayerRanks(CommandUtils.getForgePlayer(ctx.getServerHandler().player, message.text));
			} catch (Exception e) {
				e.printStackTrace();
			}


			return null; // no response in this case
		}
	}

}
