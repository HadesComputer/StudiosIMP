package guns;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.ShulkerBullet;
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
 
public class ShulkerBulletGun implements Listener{
	
   private List<Integer> bullets = new ArrayList<>();
   
   
 
   public ShulkerBulletGun(Ultimate instance) {}
   
   @EventHandler
   public void Shulker(PlayerInteractEvent e)
   {
     Player p = e.getPlayer();
     FileConfiguration messages = ConfigManager.getInstance().getMessages();
     FileConfiguration guns = ConfigManager.getInstance().getGuns();
     NumberFormat nf = NumberFormat.getInstance();
     String name = guns.getString("GunList.ShulkerBulletGun.name");
     ItemStack hand = p.getInventory().getItemInMainHand();
     PlayerDataManager PM = new PlayerDataManager(p.getUniqueId());
     double i = guns.getDouble("GunList.ShulkerBulletGun.velocity");
     if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.PHYSICAL))
     {
       if (hand.hasItemMeta() && hand.getItemMeta().getDisplayName() != null && hand.getItemMeta().getDisplayName().equals(Util.Chat(name)) && 
         hand.getType() != null && hand.getType() == Material.valueOf(guns.getString("GunList.ShulkerBulletGun.material").toUpperCase())) {
         if (PM.CheckAchievement(guns.getString("GunList.ShulkerBulletGun.achievement"))) {
           if (PM.GetPlayerXP() >= guns.getDouble("GunList.ShulkerBulletGun.level")) {
             if (Cooldown.checkCooldown(e.getPlayer())) {
               ShulkerBullet bullet = (ShulkerBullet)p.launchProjectile(ShulkerBullet.class, p.getLocation().getDirection());
               
               Vector v = p.getLocation().getDirection().normalize().multiply(i);
              bullet.setVelocity(v);
               this.bullets.add(Integer.valueOf(bullet.getEntityId()));
             p.playSound(p.getLocation(), Sound.valueOf(guns.getString("GunList.ShulkerBulletGun.sound").toUpperCase()), 1.0F, 1.0F);
               p.spawnParticle(Particle.valueOf(guns.getString("GunList.ShulkerBulletGun.particle.type").toUpperCase()), p.getLocation(), guns.getInt("GunList.ShulkerBulletGun.particle.amount"), 
                 guns.getDouble("GunList.ShulkerBulletGun.particle.x"), guns.getDouble("GunList.ShulkerBulletGun.particle.y"), guns.getDouble("GunList.ShulkerBulletGun.particle.z"));
               Cooldown.setCooldown(e.getPlayer(), guns.getInt("GunList.ShulkerBulletGun.waittimer"));

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