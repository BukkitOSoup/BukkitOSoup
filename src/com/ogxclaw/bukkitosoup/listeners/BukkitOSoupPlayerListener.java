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
		String colorCode;
		String rank = plugin.getPlayerData().getString(player.getName() + ".rank");
		int level = plugin.getPlayerData().getInt(player.getName() + ".level");
		if(rank == ""){
			plugin.getPlayerData().set(player.getName() + ".rank", "Guest");
		}
		if(level == 0){
			plugin.getPlayerData().set(player.getName() + ".level", 10);
		}
		colorCode = getPlayerColor(player);
		//plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "pex user " + player.getName() + " group set " + rank);
		plugin.savePlayerData();
		BaseCommand.sendDirectedMessage(player, "Welcome to \u00a7eBowlOCraft\u00a7f.");
		BaseCommand.sendDirectedMessage(player, "Use \u00a73/rules \u00a7ffor rules and \u00a73/help\u00a7f for help!");
		BaseCommand.sendDirectedMessage(player, "Please report all bugs to \u00a73Ogxclaw\u00a7f!");
		//plugin.saveConfig();
		event.setJoinMessage("\u00a72[+] \u00a7ePlayer \u00a7" + colorCode + player.getDisplayName() + " \u00a7econnected");
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerLeave(PlayerQuitEvent event){
		Player player = event.getPlayer();
		String colorCode;
		//String rank = plugin.getPlayerData().getString(player.getName() + ".rank");
		colorCode = getPlayerColor(player);
		plugin.savePlayerData(); 
		if(player.isBanned()){
			event.setQuitMessage("\u00a74[-] \u00a7ePlayer \u00a70" + player.getDisplayName() + " \u00a7edisconnected");
		}else{
			event.setQuitMessage("\u00a74[-] \u00a7ePlayer \u00a7" + colorCode + player.getDisplayName() + " \u00a7edisconnected");
		}
	}
	
	private String getPlayerColor(Player player){
		String rank = plugin.getPlayerData().getString(player.getName() + ".rank");
		if(rank == "Guest"){
			return "7";
		}else if(rank == "Builder"){
			return "a";
		}else if(rank == "AdvBuilder"){
			return "c";
		}else if(rank == "Moderator"){
			return "b";
		}else if(rank == "Admin"){
			return "2";
		}else if(rank == "Owner"){
			return "5";
		}else{
			return "7";
		}
	}

}
