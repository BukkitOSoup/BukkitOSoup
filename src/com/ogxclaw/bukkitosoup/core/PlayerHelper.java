package com.ogxclaw.bukkitosoup.core;

import org.bukkit.entity.Player;

import com.ogxclaw.bukkitosoup.BukkitOSoup;

public class PlayerHelper {
	
	public static BukkitOSoup plugin;
	
	//TODO: Use for AdminChat
	/*for(World w : plugin.getServer().getWorlds()){
	for(Player p : w.getPlayers()){
		if(p.hasPermission(Permissions.commandAdminChat)){
			String message = "";
			for(int i = 0; i < args.length; i++){
				message = message + args[i] + " ";
			}
			p.sendMessage("\u00a7e[OPCHAT] \u00a74" + player.getDisplayName() + "\u00a77: " + message);
		}
	}
}*/
	
	public static boolean canUseCommand(Player player, String s){
		if(player.hasPermission(s)){
			return true;
		}else{
			return false;
		}
	}
	
	public static void setRankToGuest(Player player){
		BukkitOSoup.instance.getServer().dispatchCommand(
				BukkitOSoup.instance.getServer().getConsoleSender(),
				"pex user " + player.getName() + " group set guest");
		plugin.getConfig().set(player.getName() + ".level", 10);
		plugin.getConfig().set(player.getName() + ".rank", "Guest");
		plugin.saveConfig();
	}

}
