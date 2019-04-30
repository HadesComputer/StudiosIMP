package guns;
import java.text.NumberFormat;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ultimate.main.ConfigManager;
import ultimate.main.Cooldown;
import ultimate.main.PlayerDataManager;
import ultimate.main.Ultimate;
import util.Util;
 
public class FireworkGun implements Listener
 {
 
   
   public FireworkGun(Ultimate instance) {}
   
   @EventHandler
   public void FireW(PlayerInteractEvent e)
   {
	   Player p = e.getPlayer();
     FileConfiguration messages = ConfigManager.getInstance().getMessages();
     FileConfiguration guns = ConfigManager.getInstance().getGuns();
     NumberFormat nf = NumberFormat.getInstance();
     ItemStack stack = new ItemStack(Material.GOLDEN_APPLE);
     String name = guns.getString("GunList.FireworkGun.name");
     ItemStack hand = p.getInventory().getItemInMainHand();
     PlayerDataManager PM = new PlayerDataManager(p.getUniqueId());
     if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.PHYSICAL)) {
    	 if (hand.hasItemMeta() && hand.getItemMeta().getDisplayName() != null && hand.getItemMeta().getDisplayName().equals(Util.Chat(name)) && 
         hand.getType() != null && hand.getType() == Material.valueOf(guns.getString("GunList.FireworkGun.material").toUpperCase())) {
         if (PM.CheckAchievement(guns.getString("GunList.FireworkGun.achievement"))) {
           if (PM.GetPlayerXP() >= guns.getDouble("GunList.FireworkGun.level"))
           {
             if (Cooldown.checkCooldown(e.getPlayer())) {
               p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
               p.getInventory().addItem(new ItemStack[] { stack });
               for (Player on : Bukkit.getOnlinePlayers()) {
                 on.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 500, 5));
                 p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 400, 10));
                 p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200, 5));
               }
               p.removePotionEffect(PotionEffectType.BLINDNESS);
               p.playSound(p.getLocation(), Sound.valueOf(guns.getString("GunList.FireworkGun.sound").toUpperCase()), 1.0F, 1.0F);
               p.spawnParticle(Particle.valueOf(guns.getString("GunList.FireworkGun.particle.type").toUpperCase()), p.getLocation(), guns.getInt("GunList.FireworkGun.particle.amount"), 
                 guns.getDouble("GunList.FireworkGun.particle.x"), guns.getDouble("GunList.FireworkGun.particle.y"), guns.getDouble("GunList.FireworkGun.particle.z"));
               Cooldown.setCooldown(e.getPlayer(), guns.getInt("GunList.FireworkGun.waittimer"));
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
