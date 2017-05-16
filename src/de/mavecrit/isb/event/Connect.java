package de.mavecrit.isb.event;

import java.util.Random;

import de.mavecrit.isb.Main;
import de.mavecrit.isb.util.PingServer;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Connect implements Listener{
	
	@EventHandler
	public void onConnect(net.md_5.bungee.api.event.ServerConnectEvent e) {
		 
		 ProxiedPlayer p = e.getPlayer();
		 String[] address = e.getTarget().getAddress().toString().split(":");
		 int port = e.getTarget().getAddress().getPort();
		 PingServer ping = new PingServer("127.0.0.1", port);
	     ping.fetchData();
	     int current = e.getTarget().getPlayers().size();
	     int max = ping.getMaxPlayers();
	     int percentage = (current * 100) / max; 

	     if(e.getTarget().getName().contains("lobby_")) {
	     if(percentage >= 80){	
	    	 p.connect(ProxyServer.getInstance().getServerInfo(Main.servers.get(getRandom(0, Main.servers.size()))));
	        }
	     }
	}
	
	public int getRandom(int lower, int upper) {
		Random random = new Random();
		return random.nextInt((upper - lower) + 1) + lower;
	}
}

