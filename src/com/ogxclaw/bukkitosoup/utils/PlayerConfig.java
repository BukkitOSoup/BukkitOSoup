package com.ogxclaw.bukkitosoup.utils;

import com.ogxclaw.bukkitosoup.BukkitOSoup;

public class PlayerConfig {
	
	public BukkitOSoup plugin;
	
	public int getLevel(String name){
		return plugin.getConfig().getInt(name + ".level");
	}
	
	public void setLevel(String name, int level){
		plugin.getConfig().set(name + ".level", level);
	}

}
