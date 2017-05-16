package de.mavecrit.isb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import de.mavecrit.isb.event.CheckMOTD;
import de.mavecrit.isb.event.Connect;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin implements Listener {
	public static Main plugin;
	static Main instance;
	public static List<String> servers = new ArrayList<>();
	
	
	
	public void onEnable() {
		instance = this;
		ProxyServer.getInstance().getPluginManager().registerListener(this, this);
		ProxyServer.getInstance().getPluginManager().registerListener(this, new Connect());
		for (Entry<String, ServerInfo> de : ProxyServer.getInstance().getServers().entrySet()) {
			String key = de.getKey();
			for(int i = 1; i < 100; i++){
				if(key.equals("lobby_" + i)){
					servers.add(key);
				}
			}	
		}
		CheckMOTD.start();
	}


	public static Main getInstance() {
		return instance;
	}

}
