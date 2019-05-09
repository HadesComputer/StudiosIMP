package gamemanager;
import java.text.NumberFormat;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ultimate.main.ConfigManager;
import ultimate.main.Ultimate;
import util.Util;

public class Countdown  extends BukkitRunnable{
	
   private int time;
   private final Arena arena;
   private NumberFormat nf = NumberFormat.getInstance();
   
   public Countdown(Arena arena)   {
     this.arena = arena;
     this.time = 0;
   }
   
   public void start(int time)  {
     this.time = time;
     this.arena.setState(ArenaStates.STARTING);
     this.runTaskTimerAsynchronously(Ultimate.getInstance(), 0L, 20L);
  }
      
   public int getTime() {
     return this.time;
   }
   
   public Arena getArena() {
     return this.arena;
   }
   
@Override 
   public void run(){
     FileConfiguration mess = ConfigManager.getInstance().getMessages();
     if (this.time == 0) {
       this.arena.setState(ArenaStates.INGAME);
       arena.getDetector().start();
       arena.getTimmer().start();
       cancel();
       return;
     }else{
     if (this.time == 10) {
       for (Player p : this.arena.getPlayers()) {
         p.sendMessage(Util.Chat(mess.getString("Start-in").replace("[PREFIX]", this.arena.getPrefix()).replace("%time%", nf.format(this.time))));
         Util.PlaySound(p, Sound.valueOf(mess.getString("Sound-To-Start")));
       }
     }
       
       if (this.time == 60) {
	for (Player p : this.arena.getPlayers()) {
           p.sendMessage(Util.Chat(mess.getString("Start-in").replace("[PREFIX]", this.arena.getPrefix()).replace("%time%", nf.format(this.time))));
         }
       }
       if (this.time <= 5) {
         for (Player p : this.arena.getPlayers()) {
             p.sendMessage(Util.Chat(mess.getString("Start-in").replace("[PREFIX]", this.arena.getPrefix()).replace("%time%", nf.format(this.time))));
           p.sendTitle(Util.Chat(mess.getString("Start").replace("%time%", nf.format(this.time))), Util.Chat(mess.getString("Start-Subtitle").replace("%arena%", arena.getName()).replace("[PREFIX]", arena.getPrefix())), 0, 40, 0);
           Util.PlaySound(p, Sound.valueOf(mess.getString("SoundStart")));
         }
       }
       if (!isRunning()) {
         for (Player pl : arena.getPlayers()) {
           pl.sendMessage(Util.Chat(mess.getString("There-Are-Not-Players-Required")));
          arena.setState(ArenaStates.WAITING);
          cancel();
          return;
      	 }
       }
     }
     this.time--;
   }
   
   public boolean isRunning()
   {
     return this.arena.getPlayers().size() >= this.arena.getRequiredPlayers();
   }
 }
