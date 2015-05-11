package com.ogxclaw.bukkitosoup.commands.bans;

import java.awt.Desktop;
import java.net.URI;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import com.ogxclaw.bukkitosoup.commands.BaseCommand;
import com.ogxclaw.bukkitosoup.commands.BaseCommand.Name;
import com.ogxclaw.bukkitosoup.commands.Permissions;
import com.ogxclaw.bukkitosoup.core.BukkitOSoupCommandException;
import com.ogxclaw.bukkitosoup.core.PermissionDeniedException;

@Name("lookup")
public class LookupCommand extends BaseCommand {

	@Override
	public boolean onCommandPlayer(Player player, Command command, String s, String[] args) throws BukkitOSoupCommandException {
		if(player.hasPermission(Permissions.commandLookup)){
			@SuppressWarnings("deprecation")
			Player target = player.getServer().getPlayer(args[0]);
			if(args.length >= 1){
				sendDirectedMessage(player, "Looking up " + target.getName() + "...");
				if(Desktop.isDesktopSupported()){
					try {
						Desktop.getDesktop().browse(new URI("http://www.fishbans.com/u/" + target.getName()));
						return true;
					}catch(Exception e){
						throw new BukkitOSoupCommandException("Error with connecting to servers...");
					}
				}
			}else{
				throw new BukkitOSoupCommandException(this.getUsage());
			}
		}else{
			throw new PermissionDeniedException();
		}
		return false;
	}
	
	public String getUsage(){
		return "Usage: /lookup <playername>";
	}
}
