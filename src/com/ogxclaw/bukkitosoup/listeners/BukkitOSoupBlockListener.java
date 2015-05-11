package com.ogxclaw.bukkitosoup.listeners;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import com.ogxclaw.bukkitosoup.BukkitOSoup;
import com.ogxclaw.bukkitosoup.commands.BaseCommand;

public class BukkitOSoupBlockListener implements Listener {
	
	public BukkitOSoup plugin;
	public static Material[] blacklist = {
		Material.TNT,
		Material.LAVA,
		Material.BEDROCK,
		Material.LAVA,
		Material.LAVA_BUCKET
	};
	public static Material[] alertList = {
		Material.DIAMOND_BLOCK,
		Material.IRON_BLOCK,
		Material.GOLD_BLOCK,
		Material.LAPIS_BLOCK,
	};
	
	public BukkitOSoupBlockListener(BukkitOSoup plugin){
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockPlace(BlockPlaceEvent event){
		Material block = event.getBlock().getType();
		Player player = event.getPlayer();
		
		for(Material blocked : blacklist){
			if(blocked == block){
				if(!player.isOp()){ //TODO: Change this to individual permissions for each block so that life is easier
					event.getBlock().setType(Material.AIR);
					BaseCommand.sendServerMessage(player.getName() + " tried to spawn illegal block " + blocked + ".");
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockBreak(BlockBreakEvent event){
		Material block = event.getBlock().getType();
		Player player = event.getPlayer();
		
		for(Material alerted : alertList){
			for(World w : plugin.getServer().getWorlds()){
				if(alerted == block){
					for(Player p2 : w.getPlayers()){
						if(p2.isOp()){//TODO: Same as above
							BaseCommand.sendDirectedMessage(p2, player.getName() + " destroyed block \u00a7e" + alerted + "\u00a7f.");
						}
					}
				}
			}
		}
	}

}
