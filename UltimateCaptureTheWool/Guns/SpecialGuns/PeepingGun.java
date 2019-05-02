package guns.specialguns;
import java.text.NumberFormat;
import java.util.Collection;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import ultimate.main.ConfigManager;
import ultimate.main.Cooldown;
import ultimate.main.PlayerDataManager;
import ultimate.main.Ultimate;
import util.Util;
 
public class PeepingGun implements Listener{
	
   public PeepingGun(Ultimate ultimate) {}
   
   @EventHandler
   public void Peeping(PlayerInteractEvent e)
   {
     Player p = e.getPlayer();
     FileConfiguration messages = ConfigManager.getInstance().getMessages();
     FileConfiguration guns = ConfigManager.getInstance().getGuns();
     NumberFormat nf = NumberFormat.getInstance();
     String name = guns.getString("GunList.PeepingGun.name");
     ItemStack hand = p.getInventory().getItemInMainHand();
     PlayerDataManager PM = new PlayerDataManager(p.getUniqueId());
     int amount = guns.getInt("GunList.PeepingGun.distance");
     int cq = guns.getInt("GunList.PeepingGun.amount");
     int dmg = guns.getInt("GunList.PeepingGun.damage");
     if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.PHYSICAL)) {
       if (hand.hasItemMeta() && hand.getItemMeta().getDisplayName() != null && hand.getItemMeta().getDisplayName().equals(Util.Chat(name)) && 
         hand.getType() != null && hand.getType() == Material.valueOf(guns.getString("GunList.PeepingGun.material").toUpperCase())) {
         if (PM.CheckAchievement(guns.getString("GunList.PeepingGun.achievement"))) {
           if (PM.CheckXp(guns.getDouble("GunList.PeepingGun.level"))) {
             if (Cooldown.checkCooldown(e.getPlayer())) {
               Location start = p.getLocation();
               Vector direction = start.getDirection();
               for (int i = 1; i < amount; i++) {
                 Collection<Entity> ent = p.getWorld().getNearbyEntities(start.clone().add(direction.clone().multiply(i)), 0.35D, 0.35D, 0.35D);
                 if (ent.isEmpty()) {
                   p.getWorld().spawnParticle(Particle.SNOW_SHOVEL, start.clone().add(direction.clone().multiply(i)), cq);
                   p.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, start.clone().add(direction.clone().multiply(i)), cq);
                   p.playSound(p.getLocation(), Sound.valueOf(guns.getString("GunList.PeepingGun.sound").toUpperCase()), 1.0F, 1.0F);
                   p.spawnParticle(Particle.valueOf(guns.getString("GunList.PeepingGun.particle.type").toUpperCase()), p.getLocation(), guns.getInt("GunList.PeepingGun.particle.amount"), 
                     guns.getDouble("GunList.PeepingGun.particle.x"), guns.getDouble("GunList.PeepingGun.particle.y"), guns.getDouble("GunList.PeepingGun.particle.z"));
                 } else {
                   for (Entity entity : ent) {
                     if ((entity instanceof Player)) {
                       Player plajer = (Player)entity;
                       if (plajer.getPlayer() != null) {
                         plajer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 860000, 40));
                         plajer.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 860000, 40));
                         plajer.setNoDamageTicks(0);
                         plajer.damage(dmg);
                         p.openInventory(plajer.getInventory());
                       }
                     }
                     if ((entity instanceof Monster)) {
                       ((Monster)entity).setNoDamageTicks(0);
                       ((Monster)entity).damage(dmg);
                     }
                     if ((entity instanceof Animals)) {
                       ((Animals)entity).setNoDamageTicks(0);
                       ((Animals)entity).damage(dmg);
                     }
                   }
                 }
               }
               
               Cooldown.setCooldown(e.getPlayer(), guns.getInt("GunList.PeepingGun.waittimer"));
             } else {
               p.sendMessage(Util.Chat(messages.getString("WaitTimerToUseGun").replace("%time%", nf.format(Cooldown.getCooldown(e.getPlayer())))));
             }
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