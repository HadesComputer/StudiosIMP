package guns;
import java.text.NumberFormat;
import org.bukkit.Location;
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
import org.bukkit.util.Vector;
import ultimate.main.ConfigManager;
import ultimate.main.Cooldown;
import ultimate.main.PlayerDataManager;
import ultimate.main.Ultimate;
import util.Util;

public class FangEvokerGun implements Listener{
	

   
   public FangEvokerGun(Ultimate instance) {}
   
   @EventHandler
   public void Fang(PlayerInteractEvent e)
   {
     Player p = e.getPlayer();
     FileConfiguration messages = ConfigManager.getInstance().getMessages();
     FileConfiguration guns = ConfigManager.getInstance().getGuns();
     NumberFormat nf = NumberFormat.getInstance();
     int amount = guns.getInt("GunList.FangEvokerGun.amount");
     String name = guns.getString("GunList.FangEvokerGun.name");
     ItemStack hand = p.getInventory().getItemInMainHand();
     PlayerDataManager PM = new PlayerDataManager(p.getUniqueId());
     if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.PHYSICAL)) {
       if (hand.hasItemMeta() && hand.getItemMeta().getDisplayName() != null && hand.getItemMeta().getDisplayName().equals(Util.Chat(name)) && 
         hand.getType() != null && hand.getType() == Material.valueOf(guns.getString("GunList.FangEvokerGun.material").toUpperCase())) {
         if (PM.CheckAchievement(guns.getString("GunList.FangEvokerGun.achievement"))) {
           if (PM.GetPlayerXP() >= guns.getDouble("GunList.FangEvokerGun.level")) {
             if (Cooldown.checkCooldown(e.getPlayer())) {
               Location start = p.getLocation();
               Vector direction = start.getDirection();
                   for (int i = 1; i <amount; i++) {
                 p.getWorld().spawnEntity(start.clone().add(direction.clone().multiply(i)), EntityType.EVOKER_FANGS);
                 p.playSound(p.getLocation(), Sound.valueOf(guns.getString("GunList.FangEvokerGun.sound").toUpperCase()), 1.0F, 1.0F);
                 p.spawnParticle(Particle.valueOf(guns.getString("GunList.FangEvokerGun.particle.type").toUpperCase()), p.getLocation(), guns.getInt("GunList.FangEvokerGun.particle.amount"), 
                   guns.getDouble("GunList.FangEvokerGun.particle.x"), guns.getDouble("GunList.FangEvokerGun.particle.y"), guns.getDouble("GunList.FangEvokerGun.particle.z"));
               }
              Cooldown.setCooldown(e.getPlayer(), guns.getInt("GunList.FangEvokerGun.waittimer"));
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