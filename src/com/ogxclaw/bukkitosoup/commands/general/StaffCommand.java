package com.ogxclaw.bukkitosoup.commands.general;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import com.ogxclaw.bukkitosoup.commands.BaseCommand;
import com.ogxclaw.bukkitosoup.commands.BaseCommand.Name;
import com.ogxclaw.bukkitosoup.commands.Permissions;
import com.ogxclaw.bukkitosoup.core.BukkitOSoupCommandException;
import com.ogxclaw.bukkitosoup.core.PermissionDeniedException;

@Name("staff")
public class StaffCommand extends BaseCommand {
	
	@Override
	public boolean onCommandPlayer(Player player, Command command, String s, String[] args) throws BukkitOSoupCommandException {
		if(player.hasPermission(Permissions.commandStaff)){
			String moderators = "Moderators:" + plugin.getConfig().getString("Staff.Mods");
			String administrators = "Administrators:" + plugin.getConfig().getString("Staff.Admins");
			String developers = "Developers:"+  plugin.getConfig().getString("Staff.Devs");
			String owner = "Owner: BowlOSoup";
			sendDirectedMessage(player, "Current Staff:");
			sendDirectedMessage(player, moderators.replaceAll("null", ""));
			sendDirectedMessage(player, administrators.replaceAll("null", ""));
			sendDirectedMessage(player, developers.replaceAll("null", ""));
			sendDirectedMessage(player, owner.replaceAll("null", ""));
		}else{
			throw new PermissionDeniedException();
		}
		return true;
	}

}
