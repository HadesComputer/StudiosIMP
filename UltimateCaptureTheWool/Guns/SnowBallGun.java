package guns;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import ultimate.main.ConfigManager;
import ultimate.main.PlayerDataManager;
import ultimate.main.Ultimate;
import util.Util;
 
public class SnowBallGun implements Listener{
	
   private List<Integer> bullets = new ArrayList<>();
   
   
 
   public SnowBallGun(Ultimate instance) {}
   
   @EventHandler
   public void Snow(PlayerInteractEvent e)
   {
     Player p = e.getPlayer();
     FileConfiguration guns = ConfigManager.getInstance().getGuns();
     String name = guns.getString("GunList.SnowBallGun.name");
     ItemStack hand = p.getInventory().getItemInMainHand();
     PlayerDataManager PM = new PlayerDataManager(p.getUniqueId());
     double i = guns.getDouble("GunList.SnowBallGun.velocity");
     if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
       if (hand.hasItemMeta() && hand.getItemMeta().getDisplayName() != null && hand.getItemMeta().getDisplayName().equals(Util.Chat(name)) && 
         hand.getType() != null && hand.getType() == Material.valueOf(guns.getString("GunList.SnowBallGun.material").toUpperCase())) {
         if (PM.CheckAchievement(guns.getString("GunList.SnowBallGun.achievement"))) {
           if (PM.GetPlayerXP() >= guns.getDouble("GunList.SnowBallGun.level"))
           {
             Snowball bullet = (Snowball)p.launchProjectile(Snowball.class, p.getLocation().getDirection());
             
             Vector v = p.getLocation().getDirection().normalize().multiply(i);
             bullet.setVelocity(v);
             this.bullets.add(Integer.valueOf(bullet.getEntityId()));
             p.playSound(p.getLocation(), Sound.valueOf(guns.getString("GunList.SnowBallGun.sound").toUpperCase()), 1.0F, 1.0F);
             p.spawnParticle(Particle.valueOf(guns.getString("GunList.SnowBallGun.particle.type").toUpperCase()), p.getLocation(), guns.getInt("GunList.SnowBallGun.particle.amount"), 
               guns.getDouble("GunList.SnowBallGun.particle.x"), guns.getDouble("GunList.SnowBallGun.particle.y"), guns.getDouble("GunList.SnowBallGun.particle.z"));
           } else {
             p.sendMessage(Util.Chat(guns.getString("DontHaveLevel-ToUse")));
           }
         } else {
           p.sendMessage(Util.Chat(guns.getString("DontHaveAchivement-ToUse")));
         }
        }  else {
         e.setCancelled(true);
       }
     }
   }
 }