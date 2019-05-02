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
import org.bukkit.util.Vector;
import ultimate.main.ConfigManager;
import ultimate.main.Cooldown;
import ultimate.main.PlayerDataManager;
import ultimate.main.Ultimate;
import util.Util;
 
public class HonestGun implements Listener{
	
   public HonestGun(Ultimate ultimate) {}
   
   @EventHandler
   public void Honest(PlayerInteractEvent e)
   {
     Player p = e.getPlayer();
     FileConfiguration messages = ConfigManager.getInstance().getMessages();
     FileConfiguration guns = ConfigManager.getInstance().getGuns();
     NumberFormat nf = NumberFormat.getInstance();
     String name = guns.getString("GunList.HonestGun.name");
     ItemStack hand = p.getInventory().getItemInMainHand();
     PlayerDataManager PM = new PlayerDataManager(p.getUniqueId());
     int amount = guns.getInt("GunList.HonestGun.distance");
     int cq = guns.getInt("GunList.HonestGun.amount");
     int dmg = guns.getInt("GunList.HonestGun.damage");
     if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.PHYSICAL)) {
       if (hand.hasItemMeta() && hand.getItemMeta().getDisplayName() != null && hand.getItemMeta().getDisplayName().equals(Util.Chat(name)) && 
         hand.getType() != null && hand.getType() == Material.valueOf(guns.getString("GunList.HonestGun.material").toUpperCase())) {
         if (PM.CheckAchievement(guns.getString("GunList.HonestGun.achievement"))) {
           if (PM.CheckXp(guns.getDouble("GunList.HonestGun.level"))) {
             if (Cooldown.checkCooldown(e.getPlayer())) {
               Location start = p.getLocation();
               Vector direction = start.getDirection();
               for (int i = 1; i < amount; i++) {
                 Collection<Entity> ent = p.getWorld().getNearbyEntities(start.clone().add(direction.clone().multiply(i)), 0.35D, 0.35D, 0.35D);
                 if (ent.isEmpty()) {
                   p.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, start.clone().add(direction.clone().multiply(i)), cq);
                   p.playSound(p.getLocation(), Sound.valueOf(guns.getString("GunList.HonestGun.sound").toUpperCase()), 1.0F, 1.0F);
                   p.spawnParticle(Particle.valueOf(guns.getString("GunList.HonestGun.particle.type").toUpperCase()), p.getLocation(), guns.getInt("GunList.HonestGun.particle.amount"), 
                     guns.getDouble("GunList.HonestGun.particle.x"), guns.getDouble("GunList.HonestGun.particle.y"), guns.getDouble("GunList.HonestGun.particle.z"));
                 } else {
                   for (Entity entity : ent) {
                     if ((entity instanceof Player)) {
                       Player plajer = (Player)entity;
                       if (plajer.getPlayer() != null) {
                         plajer.getInventory().setItemInMainHand(null);
                         plajer.setNoDamageTicks(0);
                         plajer.damage(dmg);
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
               
               Cooldown.setCooldown(e.getPlayer(), guns.getInt("GunList.HonestGun.waittimer"));
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