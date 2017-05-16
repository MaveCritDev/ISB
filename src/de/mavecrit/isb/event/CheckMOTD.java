package de.mavecrit.isb.event;

import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import de.mavecrit.isb.Main;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class CheckMOTD {

	public static void start() {
		ProxyServer.getInstance().getScheduler().schedule(Main.getInstance(), new Runnable() {	
			public void run() {
				for (Entry<String, ServerInfo> de : ProxyServer.getInstance().getServers().entrySet()) {
					String key = de.getKey();
					ServerInfo info = de.getValue();
					if(info.getMotd().contains("Ending")){
						for(ProxiedPlayer p : info.getPlayers()){
							findNew(p);
						}
					}
				}
			}
		}, 0, 1, TimeUnit.SECONDS);
	}
	
	@SuppressWarnings("deprecation")
	private static void findNew(ProxiedPlayer p){
		ProxyServer.getInstance().getScheduler().schedule(Main.getInstance(), new Runnable() {
            public void run() {
        		for (Entry<String, ServerInfo> de : ProxyServer.getInstance().getServers().entrySet()) {	
        			ServerInfo info = de.getValue();
        			if(info.getMotd().contains("Waiting")){
        				if(!Main.servers.contains(p.getServer())){
        				p.connect(info);
        				p.sendMessage("§cSending you to a new game server..");
        				}
        			}
        		} 
            }
         }, 5, TimeUnit.SECONDS);
	}
}
