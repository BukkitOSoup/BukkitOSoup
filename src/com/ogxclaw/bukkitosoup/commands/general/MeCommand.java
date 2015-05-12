package com.ogxclaw.bukkitosoup.commands.general;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import com.ogxclaw.bukkitosoup.commands.BaseCommand;
import com.ogxclaw.bukkitosoup.commands.BaseCommand.Name;
import com.ogxclaw.bukkitosoup.commands.Permissions;
import com.ogxclaw.bukkitosoup.core.BukkitOSoupCommandException;
import com.ogxclaw.bukkitosoup.core.PermissionDeniedException;

@Name("me")
public class MeCommand extends BaseCommand {
	
	@Override
	public boolean onCommandPlayer(Player player, Command command, String s, String[] args) throws BukkitOSoupCommandException {
		if(player.hasPermission(Permissions.commandMe)){
			String message = "";
			for(int i = 0; i < args.length; i++){
				message = message + args[i] + " ";
			}
			player.getServer().broadcastMessage("\u00a75*\u00a7f " + player.getDisplayName() + " \u00a77" + message);
			return true;
		}else{
			throw new PermissionDeniedException();
		}
	}

}
