package com.ogxclaw.bukkitosoup.listeners;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.ogxclaw.bukkitosoup.BukkitOSoup;
import com.ogxclaw.bukkitosoup.commands.BaseCommand;

public class BukkitOSoupPlayerListener implements Listener {
	
	public BukkitOSoup plugin;
	
	public final HashMap<String, ArrayList<Block>> mutedPlayers = new HashMap<String, ArrayList<Block>>();
	
	public BukkitOSoupPlayerListener(BukkitOSoup plugin){
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerChat(AsyncPlayerChatEvent event){
		Player player = event.getPlayer();
		if(mutedPlayers.containsKey(player.getName())){
			try {
				event.setCancelled(true);
				BaseCommand.sendDirectedMessage(player, "You are muted!");
			}catch(Exception e){
				event.setMessage("");
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		/*String colorCode;
		String rank = plugin.getConfig().getString(player.getName() + ".rank");
		int level = plugin.getConfig().getInt(player.getName() + ".level");
		if(rank == null){
			plugin.getConfig().set(player.getName() + ".rank", "Guest");
		}
		if(level == 0){
			plugin.getConfig().set(player.getName() + ".level", 10);
		}
		plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "pex user " + player.getName() + " group set " + rank);
		if(rank == "Guest"){
			colorCode = "7";
		}else if(rank == "Builder"){
			colorCode = "a";
		}else if(rank == "AdvBuilder"){
			colorCode = "c";
		}else if(rank == "Moderator"){
			colorCode = "b";
		}else if(rank == "Admin"){
			colorCode = "2";
		}else if(rank == "Owner"){
			colorCode = "5";
		}else{
			colorCode = "7";
		}*/
		BaseCommand.sendDirectedMessage(player, "Welcome to \u00a7eBowlOCraft\u00a7f.");
		BaseCommand.sendDirectedMessage(player, "Use \u00a73/rules \u00a7ffor rules and \u00a73/help\u00a7f for help!");
		BaseCommand.sendDirectedMessage(player, "Use \u00a73/staff\u00a7f for a list of staff!");
		plugin.saveConfig();
		event.setJoinMessage("\u00a72[+] \u00a7ePlayer \u00a77" + player.getDisplayName() + " \u00a7econnected");
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerLeave(PlayerQuitEvent event){
		Player player = event.getPlayer();
		/*String colorCode;
		String rank = plugin.getConfig().getString(player.getName() + ".rank");
		if(rank == "Guest"){
			colorCode = "7";
		}else if(rank == "Builder"){
			colorCode = "a";
		}else if(rank == "AdvBuilder"){
			colorCode = "c";
		}else if(rank == "Moderator"){
			colorCode = "b";
		}else if(rank == "Admin"){
			colorCode = "2";
		}else if(rank == "Owner"){
			colorCode = "5";
		}else{
			colorCode = "7";
		}*/
		plugin.saveConfig();
		if(player.isBanned()){
			event.setQuitMessage("\u00a74[-] \u00a7ePlayer \u00a70" + player.getDisplayName() + " \u00a7edisconnected");
		}else{
			event.setQuitMessage("\u00a74[-] \u00a7ePlayer \u00a77" + player.getDisplayName() + " \u00a7edisconnected");
		}
	}

}
