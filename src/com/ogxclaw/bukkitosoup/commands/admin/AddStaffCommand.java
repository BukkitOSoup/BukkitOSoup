package com.ogxclaw.bukkitosoup.commands.admin;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import com.ogxclaw.bukkitosoup.commands.BaseCommand;
import com.ogxclaw.bukkitosoup.commands.BaseCommand.Name;
import com.ogxclaw.bukkitosoup.core.BukkitOSoupCommandException;

@Name("addstaff")
public class AddStaffCommand extends BaseCommand {
	
	@Override
	public boolean onCommandPlayer(Player player, Command command, String s , String[] args) throws BukkitOSoupCommandException {
		@SuppressWarnings("deprecation")
		Player target = player.getServer().getPlayer(args[0]);
		String addedRank = args[1].toLowerCase();
		if(player.hasPermission("bukkitosoup.staff.add")){
			if(args.length >= 2){
				if(target != null){
					if(addedRank == "mods" || addedRank == "admins" || addedRank == "devs"){
						plugin.getConfig().set("Staff." + addedRank, plugin.getConfig().get("Staff." + addedRank) + " " + target.getName() + ",".replaceAll("null", ""));
						plugin.saveConfig();
						return true;
					}else{
						sendDirectedMessage(player, "Available Ranks: Mods, Admins, Devs");
					}
				}else{
					sendDirectedMessage(player, "Player must be online!");
				}
			}else{
				sendDirectedMessage(player, "Incorrect syntax!");
			}
		}else{
			sendDirectedMessage(player, "Permission Denied!");
		}
		return false;
	}

}
