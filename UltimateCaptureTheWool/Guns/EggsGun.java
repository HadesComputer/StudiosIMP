package guns;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import ultimate.main.ConfigManager;
import ultimate.main.Cooldown;
import ultimate.main.PlayerDataManager;
import ultimate.main.Ultimate;
import util.Util;
 
public class EggsGun implements Listener {
	
   private List<Integer> bullets = new ArrayList<>();
   
    
   public EggsGun(Ultimate instance) {}
   
   @EventHandler
   public void Eggs(PlayerInteractEvent e)
   {
     Player p = e.getPlayer();
     FileConfiguration guns = ConfigManager.getInstance().getGuns();
     String name = guns.getString("GunList.EggGun.name");
     ItemStack hand = p.getInventory().getItemInMainHand();
     PlayerDataManager PM = new PlayerDataManager(p.getUniqueId());
     double i = guns.getDouble("GunList.EggGun.velocity");
     if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
       if (hand.hasItemMeta() && hand.getItemMeta().getDisplayName() != null && hand.getItemMeta().getDisplayName().equals(Util.Chat(name)) && 
         hand.getType() != null && hand.getType() == Material.valueOf(guns.getString("GunList.EggGun.material").toUpperCase())) {
         if (PM.CheckAchievement(guns.getString("GunList.EggGun.achievement"))) {
           if (PM.GetPlayerXP() >= guns.getDouble("GunList.EggGun.level"))
           {
             Egg bullet = (Egg)p.launchProjectile(Egg.class, p.getLocation().getDirection());
             
             Vector v = p.getLocation().getDirection().normalize().multiply(i);
             bullet.setVelocity(v);
             this.bullets.add(Integer.valueOf(bullet.getEntityId()));
             p.playSound(p.getLocation(), Sound.valueOf(guns.getString("GunList.EggGun.sound").toUpperCase()), 1.0F, 1.0F);
             p.spawnParticle(Particle.valueOf(guns.getString("GunList.EggGun.particle.type").toUpperCase()), p.getLocation(), guns.getInt("GunList.EggGun.particle.amount"), 
               guns.getDouble("GunList.EggGun.particle.x"), guns.getDouble("GunList.EggGun.particle.y"), guns.getDouble("GunList.EggGun.particle.z"));
             Cooldown.setCooldown(e.getPlayer(), guns.getInt("GunList.EggGun.waittimer"));
           } else {
             p.sendMessage(Util.Chat(guns.getString("DontHaveLevel-ToUse")));
           }
         } else {
           p.sendMessage(Util.Chat(guns.getString("DontHaveAchivement-ToUse")));
         }
       } else {
         e.setCancelled(true);
       }
     }
   }
}
