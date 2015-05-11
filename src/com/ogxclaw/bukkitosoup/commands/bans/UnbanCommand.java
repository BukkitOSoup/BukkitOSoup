package com.ogxclaw.bukkitosoup.commands.bans;

import org.bukkit.BanList;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ogxclaw.bukkitosoup.commands.BaseCommand;
import com.ogxclaw.bukkitosoup.commands.BaseCommand.Name;
import com.ogxclaw.bukkitosoup.commands.Permissions;
import com.ogxclaw.bukkitosoup.core.BukkitOSoupCommandException;
import com.ogxclaw.bukkitosoup.core.PermissionDeniedException;

@Name("unban")
public class UnbanCommand extends BaseCommand {
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommandPlayer(Player player, Command command, String s, String[] args) throws BukkitOSoupCommandException {
		if(player.hasPermission(Permissions.commandUnban)){
			Player target = player.getServer().getPlayer(args[0]);
			if(target != null){
				if(target.isBanned()){
					plugin.getServer().getBanList(BanList.Type.NAME).pardon(target.getName());
					sendServerMessage(player.getName() + " unbanned " + target.getName());
					return true;
				}else{
					throw new BukkitOSoupCommandException("Player is not banned!");
				}
			}else{
				OfflinePlayer offlineTarget = player.getServer().getOfflinePlayer(args[0]);
				if(offlineTarget.isBanned()){
					plugin.getServer().getBanList(BanList.Type.NAME).pardon(offlineTarget.getName());
					sendServerMessage(player.getName() + " unbanned " + offlineTarget.getName());
					return true;
				}else{
					throw new BukkitOSoupCommandException("Player is not banned!");
				}
			}
		}else{
			throw new PermissionDeniedException();
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommandConsole(CommandSender sender, Command command,
			String s, String[] args) throws BukkitOSoupCommandException {
		Player target = sender.getServer().getPlayer(args[0]);
		if (target != null) {
			if (target.isBanned()) {
				plugin.getServer().getBanList(BanList.Type.NAME)
						.pardon(target.getName());
				sendServerMessage("CONSOLE unbanned " + target.getName());
				return true;
			} else {
				throw new BukkitOSoupCommandException("Player is not banned!");
			}
		} else {
			OfflinePlayer offlineTarget = sender.getServer().getOfflinePlayer(
					args[0]);
			if (offlineTarget.isBanned()) {
				plugin.getServer().getBanList(BanList.Type.NAME)
						.pardon(offlineTarget.getName());
				sendServerMessage("CONSOLE unbanned " + offlineTarget.getName());
				return true;
			} else {
				throw new BukkitOSoupCommandException("Player is not banned!");
			}
		}
	}

}
