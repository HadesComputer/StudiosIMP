package guns.specialguns; 
import java.text.NumberFormat;
import java.util.Collection;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import ultimate.main.ConfigManager;
import ultimate.main.Cooldown;
import ultimate.main.PlayerDataManager;
import ultimate.main.Ultimate;
import util.Util;

public class JokerGun implements Listener{
	
   public JokerGun(Ultimate ultimate) {}
   
   @EventHandler
   public void Joker(PlayerInteractEvent e)
   {
     Player p = e.getPlayer();
     FileConfiguration messages = ConfigManager.getInstance().getMessages();
     FileConfiguration guns = ConfigManager.getInstance().getGuns();
     NumberFormat nf = NumberFormat.getInstance();
     String name = guns.getString("GunList.JokerGun.name");
     ItemStack hand = p.getInventory().getItemInMainHand();
     PlayerDataManager PM = new PlayerDataManager(p.getUniqueId());
     int amount = guns.getInt("GunList.JokerGun.distance");
     int cq = guns.getInt("GunList.JokerGun.amount");
     int dmg = guns.getInt("GunList.JokerGun.damage");
     if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.PHYSICAL)) {
       if (hand.hasItemMeta() && hand.getItemMeta().getDisplayName() != null && hand.getItemMeta().getDisplayName().equals(Util.Chat(name)) && 
         hand.getType() != null && hand.getType() == Material.valueOf(guns.getString("GunList.JokerGun.material").toUpperCase())) {
         if (PM.CheckAchievement(guns.getString("GunList.JokerGun.achievement"))) {
           if (PM.CheckXp(guns.getDouble("GunList.JokerGun.level"))) {
             if (Cooldown.checkCooldown(e.getPlayer())) {
               Location start = p.getLocation();
               Vector direction = start.getDirection();
               for (int i = 1; i < amount; i++) {
                 Collection<Entity> ent = p.getWorld().getNearbyEntities(start.clone().add(direction.clone().multiply(i)), 0.35D, 0.35D, 0.35D);
                 if (ent.isEmpty()) {
                   p.getWorld().spawnParticle(Particle.DRAGON_BREATH, start.clone().add(direction.clone().multiply(i)), cq);
                   p.playSound(p.getLocation(), Sound.valueOf(guns.getString("GunList.JokerGun.sound").toUpperCase()), 1.0F, 1.0F);
                   p.spawnParticle(Particle.valueOf(guns.getString("GunList.JokerGun.particle.type").toUpperCase()), p.getLocation(), guns.getInt("GunList.JokerGun.particle.amount"), 
                     guns.getDouble("GunList.JokerGun.particle.x"), guns.getDouble("GunList.JokerGun.particle.y"), guns.getDouble("GunList.JokerGun.particle.z"));
                 } else {
                   for (Entity entity : ent) {
                     if ((entity instanceof Player)) {
                       Player plajer = (Player)entity;
                       if (plajer.getPlayer() != null) {
                         plajer.setNoDamageTicks(0);
                         plajer.damage(dmg);
                         ItemStack thack = plajer.getInventory().getItemInMainHand();
                         ItemStack boots = plajer.getInventory().getBoots();
                         ItemStack helmet = plajer.getInventory().getHelmet();
                         ItemStack legins = plajer.getInventory().getLeggings();
                         ItemStack chesplate = plajer.getInventory().getChestplate();
                         ItemStack offhand = plajer.getInventory().getItemInOffHand();
                         if (((thack != null) && (thack.getType() == Material.DIAMOND_AXE)) || (thack.getType() == Material.DIAMOND_SWORD) || 
                           (thack.getType() == Material.DIAMOND_HOE) || (thack.getType() == Material.DIAMOND_PICKAXE)) {
                           plajer.getInventory().setItemInMainHand(new ItemStack(Material.SUGAR));
                         }
                         if (((thack != null) && (thack.getType() == Material.GOLD_AXE)) || (thack.getType() == Material.GOLD_SWORD) || 
                           (thack.getType() == Material.GOLD_HOE) || (thack.getType() == Material.GOLD_PICKAXE)) {
                           plajer.getInventory().setItemInMainHand(new ItemStack(Material.YELLOW_FLOWER));
                         }
                         if (((thack != null) && (thack.getType() == Material.IRON_AXE)) || (thack.getType() == Material.IRON_SWORD) || 
                           (thack.getType() == Material.IRON_HOE) || (thack.getType() == Material.IRON_PICKAXE)) {
                           plajer.getInventory().setItemInMainHand(new ItemStack(Material.BAKED_POTATO));
                         }
                         if (((thack != null) && (thack.getType() == Material.WOOD_AXE)) || (thack.getType() == Material.WOOD_SWORD) || 
                           (thack.getType() == Material.WOOD_HOE) || (thack.getType() == Material.WOOD_PICKAXE)) {
                           plajer.getInventory().setItemInMainHand(new ItemStack(Material.BONE));
                         }
                         if (((thack != null) && (thack.getType() == Material.STONE_AXE)) || (thack.getType() == Material.STONE_SWORD) || 
                           (thack.getType() == Material.STONE_HOE) || (thack.getType() == Material.STONE_PICKAXE)) {
                           plajer.getInventory().setItemInMainHand(new ItemStack(Material.STICK));
                         }
                         if ((thack != null) && (thack.getType() == Material.BOW)) {
                           plajer.getInventory().setItemInMainHand(new ItemStack(Material.CARROT_ITEM));
                         }
                         if ((offhand != null) && (offhand.getType() == Material.SHIELD)) {
                           plajer.getInventory().setItemInOffHand(new ItemStack(Material.POTATO_ITEM));
                         }
                         if (helmet != null) {
                           plajer.getInventory().setHelmet(new ItemStack(Material.WOOL));
                         }
                         
 
                         if (boots != null) {
                           plajer.getInventory().setBoots(null);
                         }
                         if (legins != null) {
                           plajer.getInventory().setLeggings(null);
                         }
                         if (chesplate != null) {
                           plajer.getInventory().setChestplate(null);
                         }
                         double red = 0.0D;
                         double green = 0.4980392156862745D;
                         double blue = 1.0D;
                         plajer.spawnParticle(Particle.SPELL_MOB, plajer.getLocation(), 0, red, green, blue, 50.0D);
                          Sheep vehicle = (Sheep)plajer.getWorld().spawn(plajer.getLocation(), Sheep.class);
                         vehicle.addPassenger(plajer);
                         vehicle.setCustomNameVisible(false);
                         new BukkitRunnable()
                         {
                           int dyeIndex = 0;
                           @Override
                           public void run()
                           {
                             vehicle.setColor(DyeColor.values()[(this.dyeIndex++)]);
                             if (this.dyeIndex == DyeColor.values().length) { this.dyeIndex = 0;
                             }
                           }
                         }.runTaskTimer(Ultimate.getInstance(), 20L, 20L);
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
               
               Cooldown.setCooldown(e.getPlayer(), guns.getInt("GunList.JokerGun.waittimer"));
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