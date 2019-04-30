package gamemanager;
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
   
   public Countdown(Arena arena)   {
     this.arena = arena;
     this.time = 0;
   }
   
   public void start(int time)  {
     this.time = time;
     this.arena.setState(ArenaStates.STARTING);
     runTaskTimer(Ultimate.getInstance(), 0L, 20L);
  }
   
   public void cancel()
   {
     cancel();
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
     }
     if (this.time <= 10) {
       for (Player p : this.arena.getPlayers()) {
         p.sendMessage(Util.Chat(mess.getString("Start-in").replace("[PREFIX]", this.arena.getPrefix()).replace("%time%", String.valueOf(this.time))));
         Util.PlaySound(p, Sound.valueOf(mess.getString("Sound-To-Start")));
       }
       
 
       if (this.time != 0) {
         for (Player p : this.arena.getPlayers()) {
           p.sendMessage(Util.Chat(mess.getString("Start-in").replace("[PREFIX]", this.arena.getPrefix()).replace("%time%", String.valueOf(this.time))));
         }
       }
       if (this.time <= 60) {
	for (Player p : this.arena.getPlayers()) {
           p.sendMessage(Util.Chat(mess.getString("Start-in").replace("[PREFIX]", this.arena.getPrefix()).replace("%time%", String.valueOf(this.time))));
         }
       }
       if (this.time <= 5) {
         for (Player p : this.arena.getPlayers()) {
           Util.SendInstantTitle(p, mess.getString("Start").replace("%time%", String.valueOf(this.time)), null, 0);
           Util.PlaySound(p, Sound.valueOf(mess.getString("SoundStart")));
         }
       } else {
         cancel();
       }
     }
     this.time--;
   }
   
   public boolean isRunning()
   {
     return this.arena.getPlayers().size() == this.arena.getRequiredPlayers();
   }
 }
