package guns;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
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
 
public class TNTGun implements Listener{
	
   private List<Integer> bullets = new ArrayList<>();
   
   
 
   public TNTGun(Ultimate instance) {}
   
  @EventHandler
  public void TNTG(PlayerInteractEvent e)
   {
     Player p = e.getPlayer();
     FileConfiguration messages = ConfigManager.getInstance().getMessages();
     FileConfiguration guns = ConfigManager.getInstance().getGuns();
     NumberFormat nf = NumberFormat.getInstance();
     String name = guns.getString("GunList.WitherSkullGun.name");
     ItemStack hand = p.getInventory().getItemInMainHand();
     PlayerDataManager PM = new PlayerDataManager(p.getUniqueId());
     double i = guns.getDouble("GunList.TNTGun.velocity");
     if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.PHYSICAL)) {
       if (hand.hasItemMeta() && hand.getItemMeta().getDisplayName() != null && hand.getItemMeta().getDisplayName().equals(Util.Chat(name)) && 
         hand.getType() != null && hand.getType() == Material.valueOf(guns.getString("GunList.TNTGun.material").toUpperCase())) {
         if (PM.CheckAchievement(guns.getString("GunList.TNTGun.achievement"))) {
           if (PM.GetPlayerXP() >= guns.getDouble("GunList.TNTGun.level"))
           {
             if (Cooldown.checkCooldown(e.getPlayer())) {
               Entity bullet = p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.PRIMED_TNT);
               
               Vector v = p.getLocation().getDirection().normalize().multiply(i);
               bullet.setVelocity(v);
               this.bullets.add(Integer.valueOf(bullet.getEntityId()));
               p.playSound(p.getLocation(), Sound.valueOf(guns.getString("GunList.TNTGun.sound").toUpperCase()), 1.0F, 1.0F);
               p.spawnParticle(Particle.valueOf(guns.getString("GunList.TNTGun.particle.type").toUpperCase()), p.getLocation(), 10, 15.0D, 20.0D, 0.0D);
               Cooldown.setCooldown(e.getPlayer(), guns.getInt("GunList.TNTGun.waittimer"));
             } else {
               p.sendMessage(Util.Chat(messages.getString("WaitTimerToUseGun").replace("%time%", nf.format(Cooldown.getCooldown(e.getPlayer())))));
             }
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