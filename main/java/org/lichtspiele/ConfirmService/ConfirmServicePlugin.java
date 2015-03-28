package org.lichtspiele.ConfirmService;

import org.bukkit.plugin.java.JavaPlugin;

public class ConfirmServicePlugin extends JavaPlugin {

	private static ConfirmServicePlugin instance;
	
	public void onEnable() {
		instance = this;
		
		this.getDataFolder().mkdirs();
		this.saveDefaultConfig();
	}
	
	public static ConfirmServicePlugin getInstance() {
		return instance;
	}
	
}