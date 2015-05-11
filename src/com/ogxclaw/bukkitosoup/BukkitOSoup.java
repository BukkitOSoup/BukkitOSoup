package com.ogxclaw.bukkitosoup;

import java.util.logging.Level;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.ogxclaw.bukkitosoup.commands.BaseCommand;
import com.ogxclaw.bukkitosoup.listeners.BukkitOSoupBlockListener;
import com.ogxclaw.bukkitosoup.listeners.BukkitOSoupPlayerListener;

public class BukkitOSoup extends JavaPlugin {
	
	public static BukkitOSoup instance;
	
	//Listeners
	public final BukkitOSoupBlockListener blockListener = new BukkitOSoupBlockListener(this);
	public final BukkitOSoupPlayerListener playerListener = new BukkitOSoupPlayerListener(this);
	
	public BukkitOSoup(){
		instance = this;
	}
	
	@Override
	public void onDisable(){
		instance.saveConfig();
		this.log("MCAdmin Disabled");
	}
	
	@Override
	public void onEnable(){
		PluginManager pm = getServer().getPluginManager();
		//instance.getConfig().options().copyDefaults(true);
		BaseCommand.registerCommands();
		pm.registerEvents(blockListener, this);
		pm.registerEvents(playerListener, this);
		this.log("MCAdmin Enabled!");
	}
	
	public void log(String s){
		this.log(Level.INFO, s);
	}
	
	public void log(Level level, String s){
		instance.getLogger().log(level, s);
	}

}
