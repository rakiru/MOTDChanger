package com.fuckingwin.bukkit.rakiru.motdchanger;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * MCBans 4 main plugin
 *
 * @author Sean Gordon (rakiru)
 */
public class MOTDChangerPlugin extends JavaPlugin {

	private String tempMotd;
	private String realMotd;
	private long startTime;
	private int duration;
	private boolean isTempMotd;
	private final MOTDChangerServerListener serverListener = new MOTDChangerServerListener(this);

    public void onDisable() {
        // Output to console that plugin is disabled
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println(pdfFile.getName() + " version " + pdfFile.getVersion() + " disabled!");
    }

    public void onEnable() {
        // Get plugin info from plugin.yml
        PluginDescriptionFile pdfFile = this.getDescription();
		
		// Get the plugin manager to register events etc
		PluginManager pm = this.getServer().getPluginManager();
		
		// Register events
		pm.registerEvent(Event.Type.SERVER_LIST_PING, serverListener, Event.Priority.Normal, this);

        // Register commands
        getCommand("motd").setExecutor(new MOTDCommand(this));

        // Output to console that plugin is enabled
        System.out.println(pdfFile.getName() + " version " + pdfFile.getVersion() + " enabled!");
    }
	
	public String getUsableMOTD() {
		if (isTempMotd()) {
			if (getRemainingTime() > 0) {
				return tempMotd;
			}
		}
		return realMotd;
	}

	public String getTempMOTD() {
		return tempMotd;
	}
	
	public void setTempMOTD(String newMotd) {
		tempMotd = newMotd;
		isTempMotd = true;
	}

	public String getRealMOTD() {
		return realMotd;
	}

	public void setRealMOTD(String newMotd) {
		realMotd = newMotd;
	}

	public boolean isTempMotd() {
		return isTempMotd;
	}

	public int getRemainingTime() {
		long currentTime = System.currentTimeMillis();
		int timeGone = (int)(currentTime - startTime);
		int timeLeft = (duration * 1000) - timeGone;
		if (timeLeft < 0) {
			timeLeft = 0;
			isTempMotd = false;
		}
		return timeLeft;
	}

	public void setStartTime() {
		startTime = System.currentTimeMillis();
	}

	public void setDuration(int newDuration) {
		duration = newDuration;
	}
}
