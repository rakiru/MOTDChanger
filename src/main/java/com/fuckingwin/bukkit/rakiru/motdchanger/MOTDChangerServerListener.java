package com.fuckingwin.bukkit.rakiru.motdchanger;

import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.server.ServerListener;

public class MOTDChangerServerListener extends ServerListener {

	private MOTDChangerPlugin plugin;

	public MOTDChangerServerListener(MOTDChangerPlugin plugin) {
        this.plugin = plugin;
    }

	@Override
	public void onServerListPing(ServerListPingEvent event) {
		//If this plugin hasn't been used to change the MOTD yet, grab the one bukkit loaded
		if (plugin.getRealMOTD() == null) {
			plugin.setRealMOTD(event.getMotd());
		}

		//Set the MOTD to our current one, which could be the one bukkit loaded in
		event.setMotd(plugin.getUsableMOTD());
	}
}