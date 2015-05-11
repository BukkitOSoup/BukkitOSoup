package com.ogxclaw.bukkitosoup.commands.general;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import com.ogxclaw.bukkitosoup.commands.BaseCommand;
import com.ogxclaw.bukkitosoup.commands.BaseCommand.Name;
import com.ogxclaw.bukkitosoup.commands.Permissions;
import com.ogxclaw.bukkitosoup.core.BukkitOSoupCommandException;
import com.ogxclaw.bukkitosoup.core.PermissionDeniedException;

@Name("who")
public class WhoCommand extends BaseCommand {
	
	@Override
	public boolean onCommandPlayer(Player player, Command command, String s, String[] args) throws BukkitOSoupCommandException {
		if(player.hasPermission(Permissions.commandWho)){
			@SuppressWarnings("deprecation")
			Player[] players = plugin.getServer().getOnlinePlayers();
			String ret = "";
			if(players.length > 0){
				for(Player ply : players){
					String name = ply.getName();
					ret += ", " + name;
				}
				ret = ret.substring(2).trim();
			}
			ret = "Connected players: " + ret;
			sendDirectedMessage(player, ret);
			return true;
		}else{
			throw new PermissionDeniedException();
		}
	}
}
