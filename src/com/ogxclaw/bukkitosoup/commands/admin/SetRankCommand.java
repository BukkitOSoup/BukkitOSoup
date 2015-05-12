package com.ogxclaw.bukkitosoup.commands.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ogxclaw.bukkitosoup.BukkitOSoup;
import com.ogxclaw.bukkitosoup.commands.BaseCommand;
import com.ogxclaw.bukkitosoup.commands.BaseCommand.Name;
import com.ogxclaw.bukkitosoup.commands.Permissions;
import com.ogxclaw.bukkitosoup.core.BukkitOSoupCommandException;

@Name("setrank")
public class SetRankCommand extends BaseCommand {
	
	@SuppressWarnings("deprecation")
	@Override //TODO: Change to UUID!
	public boolean onCommandPlayer(Player player, Command command, String s, String[] args) throws BukkitOSoupCommandException {
		Player target = plugin.getServer().getPlayer(args[0]);
		int senderLevel = plugin.getPlayerData().getInt(player.getName() + ".level");
		int targetLevel = plugin.getPlayerData().getInt(target + ".level");
		if(player.hasPermission(Permissions.commandSetrank)){
			if (args[1].equalsIgnoreCase("guest")) {
				if (player.hasPermission("bukkitosoup.admin.ranks.guest") && senderLevel > targetLevel && senderLevel != targetLevel && senderLevel > 10) {
					setRankToGuest(target);
					sendServerMessage(player.getName() + " set rank of "
							+ target.getName() + " to Guest!"); 
					return true;
				} else {
					sendDirectedMessage(player,
							"You cannot set this player to Guest!", '4');
					return true;
				}
			} else if (args[1].equalsIgnoreCase("builder")) {
				if (player.hasPermission("bukkitosoup.admin.ranks.builder") && senderLevel > targetLevel && senderLevel != targetLevel && senderLevel > 20) {
					setRankToBuilder(target);
					sendServerMessage(player.getName() + " set rank of "
							+ target.getName() + " to Builder!");
					return true;
				} else {
					sendDirectedMessage(player,
							"You cannot set this player to Builder!", '4');
					return true;
				}
			} else if (args[1].equalsIgnoreCase("advbuilder")) {
				if (player.hasPermission("bukkitosoup.admin.ranks.advbuilder") && senderLevel > targetLevel && senderLevel != targetLevel && senderLevel > 30) {
					setRankToAdvBuilder(target);
					sendServerMessage(player.getName() + " set rank of "
							+ target.getName() + " to AdvBuilder!");
					return true;
				} else {
					sendDirectedMessage(player,
							"You cannot set this player to AdvBuilder!", '4');
					return true;
				}
			}else if (args[1].equalsIgnoreCase("vip")) {
				if (player.hasPermission("bukkitosoup.admin.ranks.vip") && senderLevel > targetLevel && senderLevel != targetLevel && senderLevel > 40) {
					setRankToVIP(target);
					sendServerMessage(player.getName() + " set rank of "
							+ target.getName() + " to VIP!");
					return true;
				} else {
					sendDirectedMessage(player,
							"You cannot set this player to VIP!", '4');
					return true;
				}
			} else if (args[1].equalsIgnoreCase("mod")) {
				if (player.hasPermission("bukkitosoup.admin.ranks.mod") && senderLevel > targetLevel && senderLevel != targetLevel && senderLevel > 50) {
					setRankToMod(target);
					sendServerMessage(player.getName() + " set rank of "
							+ target.getName() + " to Mod!");
					return true;
				} else {
					sendDirectedMessage(player,
							"You cannot set this player to Mod!", '4');
					return true;
				}
			} else if (args[1].equalsIgnoreCase("admin")) {
				if (player.hasPermission("bukkitosoup.admin.ranks.admin") && senderLevel > targetLevel && senderLevel != targetLevel && senderLevel > 60) {
					setRankToAdmin(target);
					sendServerMessage(player.getName() + " set rank of "
							+ target.getName() + " to Admin!");
					return true;
				} else {
					sendDirectedMessage(player,
							"You cannot set this player to Admin!", '4');
					return true;
				}
			} else if (args[1].equalsIgnoreCase("special")) {
				if (player.hasPermission("bukkitosoup.admin.ranks.special") && senderLevel > targetLevel && senderLevel != targetLevel && senderLevel > 70) {
					setRankToSpecial(target);
					sendServerMessage(player.getName() + " set rank of "
							+ target.getName() + " to Special!");
					return true;
				} else {
					sendDirectedMessage(player,
							"You cannot set this player to Special!", '4');
					return true;
				}
			} else {
				sendDirectedMessage(
						player,
						"Rank not found. Please check that you've entered the rank correctly and/or have the correct syntax.", '4');
				return true;
			}
		}else{
			sendDirectedMessage(player, "Permission Denied!", '4');
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	@Override //TODO: Change to UUID!
	public boolean onCommandConsole(CommandSender sender, Command command, String s, String[] args) throws BukkitOSoupCommandException {
		Player target = sender.getServer().getPlayer(args[0]);
		if(args[1].equalsIgnoreCase("guest")){
			setRankToGuest(target);
			sendServerMessage("CONSOLE set rank of " + target.getName() + " to Guest!");
			return true;
		}else if(args[1].equalsIgnoreCase("builder")){
			setRankToBuilder(target);
			sendServerMessage("CONSOLE set rank of " + target.getName() + " to Builder!");
			return true;
		}else if(args[1].equalsIgnoreCase("advbuilder")){
			setRankToAdvBuilder(target);
			sendServerMessage("CONSOLE set rank of " + target.getName() + " to AdvBuilder!");
			return true;
		}else if(args[1].equalsIgnoreCase("vip")){
			setRankToVIP(target);
			sendServerMessage("CONSOLE set rank of " + target.getName() + " to VIP!");
			return true;
		}else if(args[1].equalsIgnoreCase("mod")){
			setRankToMod(target);
			sendServerMessage("CONSOLE set rank of " + target.getName() + " to Mod!");
			return true;
		}else if(args[1].equalsIgnoreCase("admin")){
			setRankToAdmin(target);
			sendServerMessage("CONSOLE set rank of " + target.getName() + " to Admin!");
			return true;
		}else if(args[1].equalsIgnoreCase("special")){
			setRankToSpecial(target);
			sendServerMessage("CONSOLE set rank of " + target.getName() + " to Special!");
			return true;
		}else{
			sendDirectedMessage(sender, "Rank not found!");
		}
		return false;
	}
	
	public void setRankToGuest(Player player){
		BukkitOSoup.instance.getServer().dispatchCommand(
				BukkitOSoup.instance.getServer().getConsoleSender(),
				"pex user " + player.getName() + " group set guest");
		BukkitOSoup.instance.getServer().dispatchCommand(
				BukkitOSoup.instance.getServer().getConsoleSender(),
				"pex user " + player.getName() + " group set guest");
		BukkitOSoup.instance.getServer().dispatchCommand(
				BukkitOSoup.instance.getServer().getConsoleSender(),
				"pex user " + player.getName() + " group set guest");
		plugin.getPlayerData().set(player.getName() + ".level", 10);	
		plugin.getPlayerData().set(player.getName() + ".rank", "Guest");
		plugin.savePlayerData();
	}
	
	public void setRankToBuilder(Player player){
		BukkitOSoup.instance.getServer().dispatchCommand(
				BukkitOSoup.instance.getServer().getConsoleSender(),
				"pex user " + player.getName() + " group set builder");
		BukkitOSoup.instance.getServer().dispatchCommand(
				BukkitOSoup.instance.getServer().getConsoleSender(),
				"pex user " + player.getName() + " group set builder");
		BukkitOSoup.instance.getServer().dispatchCommand(
				BukkitOSoup.instance.getServer().getConsoleSender(),
				"pex user " + player.getName() + " group set builder");
		plugin.getPlayerData().set(player.getName() + ".level", 20);
		plugin.getPlayerData().set(player.getName() + ".rank", "Builder");
		plugin.savePlayerData();
	}
	
	public void setRankToAdvBuilder(Player player){
		BukkitOSoup.instance.getServer().dispatchCommand(
				BukkitOSoup.instance.getServer().getConsoleSender(),
				"pex user " + player.getName() + " group set advbuilder");
		BukkitOSoup.instance.getServer().dispatchCommand(
				BukkitOSoup.instance.getServer().getConsoleSender(),
				"pex user " + player.getName() + " group set advbuilder");
		BukkitOSoup.instance.getServer().dispatchCommand(
				BukkitOSoup.instance.getServer().getConsoleSender(),
				"pex user " + player.getName() + " group set advbuilder");
		plugin.getPlayerData().set(player.getName() + ".level", 30);
		plugin.getPlayerData().set(player.getName() + ".rank", "AdvBuilder");
		plugin.savePlayerData();
	}
	
	public void setRankToVIP(Player player){
		BukkitOSoup.instance.getServer().dispatchCommand(
				BukkitOSoup.instance.getServer().getConsoleSender(),
				"pex user " + player.getName() + " group set vip");
		BukkitOSoup.instance.getServer().dispatchCommand(
				BukkitOSoup.instance.getServer().getConsoleSender(),
				"pex user " + player.getName() + " group set vip");
		BukkitOSoup.instance.getServer().dispatchCommand(
				BukkitOSoup.instance.getServer().getConsoleSender(),
				"pex user " + player.getName() + " group set vip");
		plugin.getPlayerData().set(player.getName() + ".level", 40);
		plugin.getPlayerData().set(player.getName() + ".rank", "VIP");
		plugin.savePlayerData();
	}
	
	public void setRankToMod(Player player){
		BukkitOSoup.instance.getServer().dispatchCommand(
				BukkitOSoup.instance.getServer().getConsoleSender(),
				"pex user " + player.getName() + " group set mod");
		BukkitOSoup.instance.getServer().dispatchCommand(
				BukkitOSoup.instance.getServer().getConsoleSender(),
				"pex user " + player.getName() + " group set mod");
		BukkitOSoup.instance.getServer().dispatchCommand(
				BukkitOSoup.instance.getServer().getConsoleSender(),
				"pex user " + player.getName() + " group set mod");
		plugin.getPlayerData().set(player.getName() + ".level", 50);
		plugin.getPlayerData().set(player.getName() + ".rank", "Mod");
		plugin.savePlayerData();
	}
	
	public void setRankToAdmin(Player player){
		BukkitOSoup.instance.getServer().dispatchCommand(
				BukkitOSoup.instance.getServer().getConsoleSender(),
				"pex user " + player.getName() + " group set admin");
		BukkitOSoup.instance.getServer().dispatchCommand(
				BukkitOSoup.instance.getServer().getConsoleSender(),
				"pex user " + player.getName() + " group set admin");
		BukkitOSoup.instance.getServer().dispatchCommand(
				BukkitOSoup.instance.getServer().getConsoleSender(),
				"pex user " + player.getName() + " group set admin");
		plugin.getPlayerData().set(player.getName() + ".level", 60);
		plugin.getPlayerData().set(player.getName() + ".rank", "Admin");
		plugin.savePlayerData();
	}
	
	public void setRankToSpecial(Player player){
		BukkitOSoup.instance.getServer().dispatchCommand(
				BukkitOSoup.instance.getServer().getConsoleSender(),
				"pex user " + player.getName() + " group set special");
		BukkitOSoup.instance.getServer().dispatchCommand(
				BukkitOSoup.instance.getServer().getConsoleSender(),
				"pex user " + player.getName() + " group set special");
		BukkitOSoup.instance.getServer().dispatchCommand(
				BukkitOSoup.instance.getServer().getConsoleSender(),
				"pex user " + player.getName() + " group set special");
		plugin.getPlayerData().set(player.getName() + ".level", 71);
		plugin.getPlayerData().set(player.getName() + ".rank", "Special");
		plugin.savePlayerData();
	}

}
