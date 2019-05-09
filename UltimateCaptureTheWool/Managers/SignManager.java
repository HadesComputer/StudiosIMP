package gamemanager;
import java.text.NumberFormat;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.yaml.snakeyaml.error.YAMLException;

import ultimate.main.ConfigManager;
import ultimate.main.Ultimate;
import util.Util;

public class SignManager implements Listener{
	
	private ArenaManager am = new ArenaManager();

	public SignManager(Ultimate ultimate) {
	}


	@EventHandler
	public void placeSings(SignChangeEvent e){
		Player p = e.getPlayer();
		Location loc = e.getBlock().getLocation();
		Arena arena = am.getArena(e.getLine(1));
		if(e.getLine(0).equalsIgnoreCase("UCTW") && e.getLine(1) != null){
			Sign sign = (Sign) e.getBlock().getState();
					FileConfiguration mes = ConfigManager.getInstance().getMessages();
					NumberFormat nf = NumberFormat.getInstance();
					FileConfiguration data = ConfigManager.getInstance().getArenas();
					ConfigurationSection sec = data.getConfigurationSection("arenas");
					if(!e.getLine(0).equalsIgnoreCase("UCTW")){
						return;
					}
					if(!am.exists(e.getLine(1))){
						p.sendMessage(Util.Chat(mes.getString("Not-Exists")));
						e.setCancelled(true);
					}else{
										
					ConfigurationSection sec2 = sec.getConfigurationSection(arena.getName());
					if(sec2 !=null){
						sec2.set("sign.x", Double.valueOf(loc.getX()));
						sec2.set("sign.y", Double.valueOf(loc.getY()));
						sec2.set("sign.z", Double.valueOf(loc.getZ()));
						sec2.set("sign.world", String.valueOf(loc.getWorld().getName()));
						ConfigManager.getInstance().SaveArenas();
						
						p.sendMessage(Util.Chat(mes.getString("Sign-Created-Succefully").replace("%arena%", arena.getName())));
					
					new BukkitRunnable(){

						@Override
						public void run() {
							sign.setLine(0, Util.Chat(mes.getString("Sign-Title")));
							sign.setLine(1, Util.Chat(mes.getString("Sign-Arena").replace("%arena%", arena.getName()).replace("[PREFIX]", arena.getPrefix())));
							sign.setLine(2, Util.Chat(mes.getString("Sign-Players").replace("%players%", nf.format(arena.getPlayers().size())).replace("%maxplayers%", nf.format(arena.getMaxPlayers()))));
						    sign.update(true);
							arena.setSign(loc);
							if(arena.getState().equals(ArenaStates.WAITING)){
								sign.setLine(3, Util.Chat(mes.getString("WAITING")));
							    sign.update(true);
							}
							
							if(arena.getState().equals(ArenaStates.STARTING)){
								sign.setLine(3, Util.Chat(mes.getString("STARTING")));
							    sign.update(true);
							}
							
							if(arena.getState().equals(ArenaStates.INGAME)){
								sign.setLine(3, Util.Chat(mes.getString("INGAME")));
							    sign.update(true);
							}
							
							if(arena.getState().equals(ArenaStates.ENDING)){
								sign.setLine(3, Util.Chat(mes.getString("ENDING")));
							    sign.update(true);
							}
							
							if(arena.getState().equals(ArenaStates.RESTARTING)){
								sign.setLine(3, Util.Chat(mes.getString("RESTARTING")));
							    sign.update(true);
							}
						    arena.getState();
						    sign.update(true);
						    sign.update();
							
						}
					}.runTaskTimerAsynchronously(Ultimate.getInstance(), 0L, 20L);
			}
					}

					}
}
	
	
	public void loadSing(){
		if(ConfigManager.getInstance().getArenasFile().exists()){
			try{
			FileConfiguration base  = ConfigManager.getInstance().getArenas();
			
			for(String key : base.getConfigurationSection("arenas").getKeys(false)){
				
				String world = base.getString("arenas." + key + ".sign.world");
				if(world != null){
				double signx = base.getDouble("arenas." + key + ".sign.x");
				
				double signy = base.getDouble("arenas." + key + ".sign.y");
				
				double signz = base.getDouble("arenas." + key + ".sign.z");
				
				String name = base.getString("arenas." + key + ".name");
				
				Location sign = new Location(Bukkit.getWorld(world), signx, signy, signz);
				Block block = sign.getBlock();
				BlockState state = block.getState();
					if(state instanceof Sign){
						Sign show =  (Sign) state;
					FileConfiguration mes = ConfigManager.getInstance().getMessages();
					NumberFormat nf = NumberFormat.getInstance();
					Arena arena = am.getArena(show.getLine(1));
					
				
					
					if(!show.getLine(0).equalsIgnoreCase("UCTW")){
						return;
					}
					if(show.getLine(0).equalsIgnoreCase("UCTW")){
					show.setLine(0, Util.Chat(mes.getString("Sign-Title")));
					show.setLine(1, Util.Chat(mes.getString("Sign-Arena").replace("%arena%", arena.getName()).replace("[PREFIX]", arena.getPrefix())));
					show.setLine(2, Util.Chat(mes.getString("Sign-Players").replace("%players%", nf.format(arena.getPlayers())).replace("%maxplayers%", nf.format(arena.getMaxPlayers()))));
					show.update(true);
					Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA+"Sign Loaded Succefully for " + key);
					
					new BukkitRunnable(){

						@Override
						public void run() {
						    arena.getState();
							show.setLine(0, Util.Chat(mes.getString("Sign-Title")));
							show.setLine(1, Util.Chat(mes.getString("Sign-Arena").replace("%arena%", arena.getName()).replace("[PREFIX]", arena.getPrefix())));
							show.setLine(2, Util.Chat(mes.getString("Sign-Players").replace("%players%", nf.format(arena.getPlayers())).replace("%maxplayers%", nf.format(arena.getMaxPlayers()))));
						    show.update(true);
							
							if(arena.getState().equals(ArenaStates.WAITING)){
								show.setLine(3, Util.Chat(mes.getString("WAITING")));
							    show.update(true);
							}
							
							if(arena.getState().equals(ArenaStates.STARTING)){
								show.setLine(3, Util.Chat(mes.getString("STARTING")));
							    show.update(true);
							}
							
							if(arena.getState().equals(ArenaStates.INGAME)){
								show.setLine(3, Util.Chat(mes.getString("INGAME")));
							    show.update(true);
							}
							
							if(arena.getState().equals(ArenaStates.ENDING)){
								show.setLine(3, Util.Chat(mes.getString("ENDING")));
							    show.update(true);
							}
							
							if(arena.getState().equals(ArenaStates.RESTARTING)){
								show.setLine(3, Util.Chat(mes.getString("RESTARTING")));
							    show.update(true);
							}
							
						    show.update(true);
						    show.update();
							
						}
					}.runTaskTimerAsynchronously(Ultimate.getInstance(), 0L, 20L);
			}
					}else{
						Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE+"Block not Sign Found " + key + " Skiped This Sign");
					}
			}
			}
			}catch(YAMLException ex){
				ex.printStackTrace();
			}
		}
	}
		
			
}

