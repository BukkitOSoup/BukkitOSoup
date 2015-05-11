package com.ogxclaw.bukkitosoup.commands.bans;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ogxclaw.bukkitosoup.commands.BaseCommand;
import com.ogxclaw.bukkitosoup.commands.BaseCommand.Name;
import com.ogxclaw.bukkitosoup.commands.Permissions;
import com.ogxclaw.bukkitosoup.core.BukkitOSoupCommandException;
import com.ogxclaw.bukkitosoup.core.PermissionDeniedException;

@Name("kick")
public class KickCommand extends BaseCommand {
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommandPlayer(Player player, Command command, String s, String[] args) throws BukkitOSoupCommandException {
		if(player.hasPermission(Permissions.commandKick)){
			Player target = player.getServer().getPlayer(args[0]);
			String reason = "";
			for(int i = 1; i < args.length; i++){
				reason = reason + args[i] + " ";
			}
			target.kickPlayer("Kicked by " + player.getName() + ". Reason: " + reason);
			sendServerMessage(player.getName() + " kicked " + target.getName() + " ((" + reason + "))");
			return true;
		}else{
			throw new PermissionDeniedException();
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommandConsole(CommandSender sender, Command command,
			String s, String[] args) throws BukkitOSoupCommandException {
		Player target = sender.getServer().getPlayer(args[0]);
		String reason = "";
		for (int i = 1; i < args.length; i++) {
			reason = reason + args[i] + " ";
		}
		target.kickPlayer("Kicked by CONSOLE. Reason: " + reason);
		sendServerMessage("CONSOLE kicked " + target.getName() + " ((" + reason + "))");
		return true;
	}

}
