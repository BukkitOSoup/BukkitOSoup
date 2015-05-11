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

@Name("ban")
public class BanCommand extends BaseCommand {
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommandPlayer(Player player, Command command, String s, String[] args) throws BukkitOSoupCommandException {
		if(player.hasPermission(Permissions.commandBan)){
			Player target = player.getServer().getPlayer(args[0]);
			String message = "";
			for(int i = 1; i < args.length; i++){
				message = message + args[i] + " ";
			}
			if(target != null){
				target.kickPlayer("Banned by " + player.getName() + ". Reason: " + message);
				player.getServer().getBanList(BanList.Type.NAME).addBan(target.getName(), message, null, player.getName());
				if(message != ""){
					sendServerMessage(player.getName() + " kickbanned " + target.getName() + " ((" + message + "))");
				}else{
					sendServerMessage(player.getName() + " kickbanned " + target.getName());
				}
				return true;
			}else{
				OfflinePlayer offlineTarget = player.getServer().getOfflinePlayer(args[0]);
				player.getServer().getBanList(BanList.Type.NAME).addBan(offlineTarget.getName(), message, null, player.getName());
				if(message != ""){
					sendServerMessage(player.getName() + " banned " + offlineTarget.getName() + " ((" + message + "))");
				}else{
					sendServerMessage(player.getName() + " banned " + offlineTarget.getName());
				}
				if(offlineTarget.isBanned()){
					sendDirectedMessage(player, "Player is already banned!");
				}
				return true;
			}
		}else{
			throw new PermissionDeniedException();
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommandConsole(CommandSender sender, Command command, String s, String[] args) throws BukkitOSoupCommandException {
		Player target = sender.getServer().getPlayer(args[0]);
		String reason = "";
		for(int i = 1; i < args.length; i++){
			reason = reason + args[i] + " ";
		}
		if(target != null){
			target.kickPlayer("Banned by CONSOLE. Reason: " + reason);
			sender.getServer().getBanList(BanList.Type.NAME).addBan(target.getName(), reason, null, "CONSOLE");
			if(reason != ""){
				sendServerMessage("CONSOLE kickbanned " + target.getName() + " ((" + reason + "))");
			}else{
				sendServerMessage("CONSOLE kickbanned " + target.getName());
			}
			return true;
		}else{
			OfflinePlayer offlineTarget = sender.getServer().getOfflinePlayer(args[0]);
			sender.getServer().getBanList(BanList.Type.NAME).addBan(offlineTarget.getName(), reason, null, "CONSOLE");
			if(reason != ""){
				sendServerMessage("CONSOLE banned " + offlineTarget.getName() + " ((" + reason + "))");
			}else{
				sendServerMessage("CONSOLE banned " + offlineTarget.getName());
			}
		}
		return false;
	}

}
