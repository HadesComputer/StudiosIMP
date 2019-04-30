package gamemanager;

import org.bukkit.scheduler.BukkitRunnable;

import ultimate.main.Ultimate;

public class Timmer extends BukkitRunnable{

		
	private int seconds = 0;
	private int minutes = 0;
	private final Arena arena;
	
	public Timmer(Arena arena){
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
		if(seconds == 60){
			seconds = 00;
			++minutes;
			if(minutes == 60){
				minutes = 0;
			}
		}
		++seconds;
  }
	
	public int getMinutes(){
		return this.minutes;
	}
	
	public int getSeconds(){
		return this.seconds;
	}
}