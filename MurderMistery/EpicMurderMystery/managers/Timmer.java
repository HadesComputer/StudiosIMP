package murdermistery.managers;

import org.bukkit.scheduler.BukkitRunnable;

import epic.main.MurderMistery;

public class Timmer extends BukkitRunnable{

	private int minutes;
	private int seconds;
	private final Arena arena;
	
	
	public Timmer(Arena arena){
		this.arena = arena;
	}
	
	public void start(int minutes, int seconds){
		this.minutes = minutes;
		this.seconds = seconds;
		this.runTaskTimer(MurderMistery.getInstance(), 0L, 20L);
	}
	
	public Arena getArena(){
		return this.arena;
	}
	
	
	
	@Override
	public void run() {
		if(seconds == 0){
			minutes--;
		}
		if(minutes == 0 && seconds == 0){
			arena.setStates(ArenaStates.RESTARTING);
		}
		--seconds;
	}
	
}
