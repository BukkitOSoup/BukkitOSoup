package com.ogxclaw.bukkitosoup.commands.bans;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ogxclaw.bukkitosoup.commands.BaseCommand;
import com.ogxclaw.bukkitosoup.commands.BaseCommand.Name;
import com.ogxclaw.bukkitosoup.commands.Permissions;
import com.ogxclaw.bukkitosoup.core.BukkitOSoupCommandException;
import com.ogxclaw.bukkitosoup.core.PermissionDeniedException;

@Name("kickall")
public class KickAllCommand extends BaseCommand {
	
	@Override
	public boolean onCommandPlayer(Player player, Command comand, String s, String[] args) throws BukkitOSoupCommandException {
		if (player.hasPermission(Permissions.commandKickAll)) {
			for (World w : plugin.getServer().getWorlds()) {
				for (Player p2 : w.getPlayers()) {
					if (!p2.hasPermission(Permissions.commandKickExempt)) {
						String reason = "";
						for(int i = 1; i < args.length; i++){
							reason = reason + args[i] + " ";
						}
						p2.kickPlayer("Kicked by " + player.getName() + " Reason:" + reason);
						sendServerMessage(player.getName() + " kicked all players!");
						return true;
					}
				}
			}
		}else{
			throw new PermissionDeniedException();
		}
		return false;
	}
	
	@Override
	public boolean onCommandConsole(CommandSender sender, Command comand, String s, String[] args) throws BukkitOSoupCommandException {
		for (World w : plugin.getServer().getWorlds()) {
			for (Player p2 : w.getPlayers()) {
				if (!p2.hasPermission(Permissions.commandKickExempt)) {
					String reason = "";
					for (int i = 1; i < args.length; i++) {
						reason = reason + args[i] + " ";
					}
					p2.kickPlayer("Kicked by CONSOLE. Reason:" + reason);
					sendServerMessage("CONSOLE kicked all players!");
					return true;
				}
			}
		}
		return false;
	}

}
