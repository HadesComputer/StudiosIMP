package gamemanager;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import ultimate.main.Ultimate;

public class Detector extends BukkitRunnable{
	
	
	private final Arena arena;
	
	public Detector(Arena arena){
		this.arena = arena;
	}
	
	public Arena getArena(){
		return arena;
	}
	
	public void start(){
		this.runTaskTimerAsynchronously(Ultimate.getInstance(), 0L, 20L);
	}
	
	@Override
	public void run() {
		if(arena.getBluesPoints() >= 2){
			arena.getTimmer().cancel();
			for(Player p : arena.getPlayers()){
				p.teleport(arena.getMainLobby());
				arena.setState(ArenaStates.RESTARTING);
				arena.reset();
				this.cancel();
				return;
			}
		}
		if(arena.getRedPoints() >= 2){
			arena.getTimmer().cancel();
			for(Player p : arena.getPlayers()){
				p.teleport(arena.getMainLobby());
				arena.setState(ArenaStates.RESTARTING);
				arena.reset();
				this.cancel();
				return;
			}
		}
		
	}

}
