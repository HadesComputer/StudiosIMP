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
 
public class CatharsisGun implements Listener{
	
   public CatharsisGun(Ultimate ultimate) {}
   
   @EventHandler
   public void Catharsis(PlayerInteractEvent e)
   {
	 Player p = e.getPlayer();
     FileConfiguration messages = ConfigManager.getInstance().getMessages();
     FileConfiguration guns = ConfigManager.getInstance().getGuns();
     NumberFormat nf = NumberFormat.getInstance();
     String name = guns.getString("GunList.CatharsisGun.name");
     ItemStack hand = p.getInventory().getItemInMainHand();
     PlayerDataManager PM = new PlayerDataManager(p.getUniqueId());
     int amount = guns.getInt("GunList.CatharsisGun.distance");
     int cq = guns.getInt("GunList.CatharsisGun.amount");
     int dmg = guns.getInt("GunList.CatharsisGun.damage");
     if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.PHYSICAL)) {
       if (hand.hasItemMeta() && hand.getItemMeta().getDisplayName() != null && hand.getItemMeta().getDisplayName().equals(Util.Chat(name)) && 
         hand.getType() != null && hand.getType() == Material.valueOf(guns.getString("GunList.CatharsisGun.material").toUpperCase())) {
         if (PM.CheckAchievement(guns.getString("GunList.CatharsisGun.achievement"))) {
           if (PM.CheckXp(guns.getDouble("GunList.CatharsisGun.level"))) {
             if (Cooldown.checkCooldown(e.getPlayer())) {
               Location start = p.getLocation();
               Vector direction = start.getDirection();
               for (int i = 1; i < amount; i++) {
                 Collection<Entity> ent = p.getWorld().getNearbyEntities(start.clone().add(direction.clone().multiply(i)), 0.35D, 0.35D, 0.35D);
                 if (ent.isEmpty()) {
                   p.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, start.clone().add(direction.clone().multiply(i)), cq);
                   p.playSound(p.getLocation(), Sound.valueOf(guns.getString("GunList.CatharsisGun.sound").toUpperCase()), 1.0F, 1.0F);
                   p.spawnParticle(Particle.valueOf(guns.getString("GunList.CatharsisGun.particle.type").toUpperCase()), p.getLocation(), guns.getInt("GunList.CatharsisGun.particle.amount"), 
                     guns.getDouble("GunList.CatharsisGun.particle.x"), guns.getDouble("GunList.CatharsisGun.particle.y"), guns.getDouble("GunList.CatharsisGun.particle.z"));
                 } else {
                   for (Entity entity : ent) {
                     if ((entity instanceof Player)) {
                       Player plajer = (Player)entity;
                       if (plajer.getPlayer() != null) {
                         plajer.getInventory().clear();
                         plajer.removePotionEffect(PotionEffectType.ABSORPTION);
                         plajer.removePotionEffect(PotionEffectType.HEAL);
                         plajer.removePotionEffect(PotionEffectType.HEALTH_BOOST);
                         plajer.removePotionEffect(PotionEffectType.SPEED);
                         plajer.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                         plajer.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
                         plajer.removePotionEffect(PotionEffectType.SATURATION);
                         plajer.removePotionEffect(PotionEffectType.REGENERATION);
                         plajer.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                         plajer.removePotionEffect(PotionEffectType.INVISIBILITY);
                         plajer.removePotionEffect(PotionEffectType.JUMP);
                         plajer.removePotionEffect(PotionEffectType.LUCK);
                         plajer.removePotionEffect(PotionEffectType.NIGHT_VISION);
                         plajer.removePotionEffect(PotionEffectType.WATER_BREATHING);
                         plajer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 18000, 40));
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
               
               Cooldown.setCooldown(e.getPlayer(), guns.getInt("GunList.CatharsisGun.waittimer"));
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