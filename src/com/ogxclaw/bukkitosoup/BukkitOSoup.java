package com.ogxclaw.bukkitosoup;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
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
	
	FileConfiguration config;
	File configFile;
	
	FileConfiguration player;
	File playerFile;
	
	public BukkitOSoup(){
		instance = this;
	}
	
	@Override
	public void onDisable(){
		//saveBasicConfig();
		savePlayerData();
		this.log("MCAdmin Disabled");
	}
	
	@Override
	public void onEnable(){
		PluginManager pm = getServer().getPluginManager();
		config = getConfig();
		//config.options().copyDefaults(true);
		//saveConfig();
		configFile = new File(getDataFolder(), "config.yml");
		
		if(!getDataFolder().exists()){
			try {
				getDataFolder().createNewFile();
			}catch(IOException e){
				log(Level.SEVERE, "Could not create plugin folder!");
			}
		}
		
		playerFile = new File(getDataFolder(), "players.yml");
		
		if(!playerFile.exists()){
			try {
				playerFile.createNewFile();
			}catch(IOException e){
				log(Level.SEVERE, "Could not create player file!");
			}
		}
		
		player = YamlConfiguration.loadConfiguration(playerFile);
		
		//instance.getConfig().options().copyDefaults(true);
		BaseCommand.registerCommands();
		pm.registerEvents(blockListener, this);
		pm.registerEvents(playerListener, this);
		this.log("MCAdmin Enabled!");
	}
	
	public FileConfiguration getPlayerData(){
		return player;
	}
	
	public void savePlayerData(){
		try {
			player.save(playerFile);
		}catch(IOException e){
			log(Level.SEVERE, "players.yml file could not be saved!");
		}
	}
	
	public void reloadPlayerData(){
		player = YamlConfiguration.loadConfiguration(playerFile);
	}
	
	public FileConfiguration getConfig(){
		return config;
	}
	
	public void saveBasicConfig(){
		try {
			config.save(configFile);
		}catch(IOException e){
			log(Level.SEVERE, "config.yml file could nto be saved!");
		}
	}
	
	public void reloadConfig(){
		config = YamlConfiguration.loadConfiguration(configFile);
	}
	
	public void log(String s){
		this.log(Level.INFO, s);
	}
	
	public void log(Level level, String s){
		instance.getLogger().log(level, s);
	}

}
