package murdermistery.managers;

import org.bukkit.scheduler.BukkitRunnable;

import epic.main.MurderMistery;

public class Countdown extends BukkitRunnable{
	
	private int time;
	private final Arena arena;
	public Countdown(Arena arena){
		this.arena = arena;
	}
	
	public void start(int time){
		this.time = time;
		this.runTaskTimer(MurderMistery.getInstance(), 0L, 20L);
	}
	
	public int getTime(){
		return this.time;
	}
	
	public Arena getArena(){
		return this.arena;
	}

	@Override
	public void run() {
	   if(time == 0){
		   arena.setStates(ArenaStates.INGAME);
		   arena.getTimmer().start(arena.getMinutes(), arena.getSeconds());
		   arena.getDetector().start();
	   }
	   
	   if(time != 0){
		   
	   }
	   
	   if(time <= 60){
		   
	   }
	   
	   if(time <=30){
		   
	   }
	   
	   if(time <= 5){
		   
	   }
		
	}

	public boolean isRunning(){
	return	arena.getPlayers().size() == arena.getRequiredPlayers();
	}
}
