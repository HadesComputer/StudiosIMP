package murdermistery.managers;

import org.bukkit.scheduler.BukkitRunnable;

import epic.main.MurderMistery;

public class Detector extends BukkitRunnable{
	
	private final Arena arena;
	
	public Detector(Arena arena){
		this.arena = arena;
	}
	
	public void start(){
		this.runTaskTimer(MurderMistery.getInstance(), 0L, 20L);
	}
	
	public Arena getArena(){
		return this.arena;
	}

	@Override
	public void run() {
		if(arena.getInnocents().size() == 0 && arena.getDetectives().size() == 0 && arena.getAssistants().size() == 0 && arena.getAssassins().size() != 0 || arena.getAccomplices().size() != 0){
			
		}
		
		if(arena.getAssassins().size() == 0 && arena.getAccomplices().size() == 0 &&  arena.getInnocents().size() != 0){
			
		}
		
		if(arena.getInnocents().size() == 0 && arena.getAssassins().size() == 0 &&   arena.getDetectives().size() != 0 || arena.getAssistants().size() != 0){
			
		}
		
		if(arena.getInnocents().size() == 0 && arena.getDetectives().size() == 0 && arena.getInnocents().size() == 0 && arena.getAssistants().size() == 0 && arena.getAssassins().size() == 0){
			
		}
		
	}

}
