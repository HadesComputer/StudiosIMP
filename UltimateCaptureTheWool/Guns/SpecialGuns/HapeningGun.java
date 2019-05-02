package guns.specialguns;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import ultimate.main.ConfigManager;
import ultimate.main.Cooldown;
import ultimate.main.PlayerDataManager;
import ultimate.main.Ultimate;
import util.Util;
 
public class HapeningGun implements Listener{
	
public HapeningGun(Ultimate ultimate) {}
   
   @EventHandler
   public void Hapening(PlayerInteractEvent e)
   {
     Player p = e.getPlayer();
     FileConfiguration messages = ConfigManager.getInstance().getMessages();
     FileConfiguration guns = ConfigManager.getInstance().getGuns();
     NumberFormat nf = NumberFormat.getInstance();
     String name = guns.getString("GunList.HapeningGun.name");
     ItemStack hand = p.getInventory().getItemInMainHand();
     PlayerDataManager PM = new PlayerDataManager(p.getUniqueId());
     int amount = guns.getInt("GunList.HapeningGun.distance");
     int cq = guns.getInt("GunList.HapeningGun.amount");
     Random rand = new Random();
     int cha = rand.nextInt(305);
     int to = rand.nextInt(100000);
     if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.PHYSICAL)) {
       if (hand.hasItemMeta() && hand.getItemMeta().getDisplayName() != null && hand.getItemMeta().getDisplayName().equals(Util.Chat(name)) && 
         hand.getType() != null && hand.getType() == Material.valueOf(guns.getString("GunList.HapeningGun.material").toUpperCase()))
       {
         if (PM.CheckAchievement(guns.getString("GunList.HapeningGun.achievement")))
         {
           if (PM.CheckXp(guns.getDouble("GunList.HapeningGun.level")))
           {
             if (Cooldown.checkCooldown(e.getPlayer()))
             {
               Location start = p.getLocation();
               Vector direction = start.getDirection();
               for (int i = 1; i < amount; i++)
               {
                 Collection<Entity> ent = p.getWorld().getNearbyEntities(start.clone().add(direction.clone().multiply(i)), 0.35D, 0.35D, 0.35D);
                 if (ent.isEmpty())
                 {
                   p.getWorld().spawnParticle(Particle.END_ROD, start.clone().add(direction.clone().multiply(i)), cq);
                   p.playSound(p.getLocation(), Sound.valueOf(guns.getString("GunList.HapeningGun.sound").toUpperCase()), 1.0F, 1.0F);
                   p.spawnParticle(Particle.valueOf(guns.getString("GunList.HapeningGun.particle.type").toUpperCase()), p.getLocation(), guns.getInt("GunList.HapeningGun.particle.amount"), 
                     guns.getDouble("GunList.HapeningGun.particle.x"), guns.getDouble("GunList.HapeningGun.particle.y"), guns.getDouble("GunList.HapeningGun.particle.z"));
                 } else {
                   for (Entity entity : ent)
                   {
                     if ((entity instanceof Player))
                     {
                       final Player plajer = (Player)entity;
                       if (plajer.getPlayer() != null)
                       {
                         ItemStack plahand = plajer.getInventory().getItemInMainHand();
                         ItemStack boots = plajer.getInventory().getBoots();
                         ItemStack helmet = plajer.getInventory().getHelmet();
                         ItemStack legins = plajer.getInventory().getLeggings();
                         ItemStack ches = plajer.getInventory().getChestplate();
                         ItemStack off = plajer.getInventory().getItemInOffHand();
                         ItemStack phand = p.getInventory().getItemInMainHand();
                         ItemStack pboots = p.getInventory().getBoots();
                         ItemStack phelmet = p.getInventory().getHelmet();
                         ItemStack plegins = p.getInventory().getLeggings();
                         ItemStack pches = p.getInventory().getChestplate();
                        ItemStack poff = p.getInventory().getItemInOffHand();
                         if (cha == 2) {
                           plajer.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, to, to));
                         }
                         if (cha == 4) {
                           plajer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, to, to));
                         }
                         if (cha == 6) {
                           plajer.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, to, to));
                         }
                         if (cha == 8) {
                           plajer.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, to, to));
                         }
                         if (cha == 10) {
                           plajer.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, to, to));
                         }
                         if (cha == 12) {
                           plajer.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, to, to));
                         }
                         if (cha == 14) {
                           plajer.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, to, to));
                         }
                         if (cha == 16) {
                           plajer.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, to, to));
                         }
                         if (cha == 18) {
                           plajer.addPotionEffect(new PotionEffect(PotionEffectType.HARM, to, to));
                         }
                         if (cha == 20) {
                           plajer.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, to, to));
                         }
                         if (cha == 22) {
                           plajer.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, to, to));
                         }
                         if (cha == 24) {
                           plajer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, to, to));
                         }
                         if (cha == 26) {
                           plajer.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, to, to));
                         }
                         if (cha == 28) {
                           plajer.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, to, to));
                         }
                         if (cha == 30) {
                           plajer.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, to, to));
                         }
                         if (cha == 32) {
                           plajer.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, to, to));
                         }
                         if (cha == 34) {
                           plajer.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, to, to));
                         }
                         if (cha == 36) {
                           plajer.addPotionEffect(new PotionEffect(PotionEffectType.POISON, to, to));
                         }
                         if (cha == 38) {
                           plajer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, to, to));
                         }
                         if (cha == 40) {
                           plajer.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, to, to));
                         }
                         if (cha == 42) {
                           plajer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, to, to));
                         }
                         if (cha == 44) {
                           plajer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, to, to));
                         }
                         if (cha == 46) {
                           plajer.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK, to, to));
                         }
                         if (cha == 48) {
                           plajer.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, to, to));
                         }
                         if (cha == 48) {
                           plajer.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, to, to));
                         }
                         if (cha == 48) {
                           plajer.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, to, to));
                         }
                         if (cha == 50) {
                           p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, to, to));
                         }
                         if (cha == 52) {
                           p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, to, to));
                         }
                         if (cha == 54) {
                           p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, to, to));
                         }
                         if (cha == 56) {
                           p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, to, to));
                         }
                         if (cha == 58) {
                           p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, to, to));
                         }
                         if (cha == 60) {
                           p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, to, to));
                         }
                         if (cha == 62) {
                           plajer.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, to, to));
                         }
                         if (cha == 64) {
                           p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, to, to));
                         }
                         if (cha == 66) {
                           p.addPotionEffect(new PotionEffect(PotionEffectType.HARM, to, to));
                         }
                         if (cha == 68) {
                           p.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, to, to));
                         }
                         if (cha == 70) {
                           p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, to, to));
                         }
                         if (cha == 72) {
                           p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, to, to));
                         }
                         if (cha == 74) {
                           p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, to, to));
                         }
                         if (cha == 76) {
                           p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, to, to));
                         }
                         if (cha == 78) {
                           p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, to, to));
                         }
                         if (cha == 80) {
                           p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, to, to));
                         }
                         if (cha == 82) {
                           p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, to, to));
                         }
                         if (cha == 84) {
                           p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, to, to));
                         }
                         if (cha == 86) {
                           p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, to, to));
                               }
                        if (cha == 88) {
                           p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, to, to));
                         }
                         if (cha == 90) {
                           p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, to, to));
                         }
                         if (cha == 92) {
                           p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, to, to));
                         }
                         if (cha == 94) {
                           p.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK, to, to));
                         }
                         if (cha == 96) {
                           p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, to, to));
                         }
                         if (cha == 98) {
                           p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, to, to));
                         }
                         if (cha == 100) {
                           p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, to, to));
                         }
                         if (cha == 102) {
                           for (Player pl : Bukkit.getOnlinePlayers()) {
                             pl.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, to, to));
                           }
                         }
                         if (cha == 104) {
                           for (Player pl : Bukkit.getOnlinePlayers()) {
                             pl.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, to, to));
                           }
                         }
                         if (cha == 106) {
                           for (Player pl : Bukkit.getOnlinePlayers()) {
                             pl.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, to, to));
                           }
                         }
                         if (cha == 108) {
                           for (Player pl : Bukkit.getOnlinePlayers()) {
                             pl.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, to, to));
                           }
                         }
                         if (cha == 112) {
                           for (Player pl : Bukkit.getOnlinePlayers()) {
                             pl.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, to, to));
                           }
                         }
                         if (cha == 114) {
                           for (Player pl : Bukkit.getOnlinePlayers()) {
                             pl.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, to, to));
                           }
                         }
                         if (cha == 118) {
                           for (Player pl : Bukkit.getOnlinePlayers()) {
                             pl.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, to, to));
                           }
                         }
                         if (cha == 120) {
                           for (Player pl : Bukkit.getOnlinePlayers()) {
                             pl.addPotionEffect(new PotionEffect(PotionEffectType.HARM, to, to));
                           }
                         }
                          if (cha == 122) {
                            for (Player pl : Bukkit.getOnlinePlayers()) {
                             pl.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, to, to));
                             }
                           }
                         if (cha == 124) {
                           for (Player pl : Bukkit.getOnlinePlayers()) {
                             pl.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, to, to));
                             }
                           }
/*  309 */                         if (cha == 126) {
/*  310 */                           for (Player pl : Bukkit.getOnlinePlayers()) {
/*  311 */                             pl.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, to, to));
                             }
                           }
/*  314 */                         if (cha == 128) {
/*  315 */                           for (Player pl : Bukkit.getOnlinePlayers()) {
/*  316 */                             pl.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, to, to));
                             }
                           }
/*  319 */                         if (cha == 130) {
/*  320 */                           for (Player pl : Bukkit.getOnlinePlayers()) {
/*  321 */                             pl.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, to, to));
                             }
                           }
/*  324 */                         if (cha == 132) {
/*  325 */                           for (Player pl : Bukkit.getOnlinePlayers()) {
/*  326 */                             pl.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, to, to));
                             }
                           }
/*  329 */                         if (cha == 134) {
/*  330 */                           for (Player pl : Bukkit.getOnlinePlayers()) {
/*  331 */                             pl.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, to, to));
                             }
                           }
/*  334 */                         if (cha == 136) {
/*  335 */                           for (Player pl : Bukkit.getOnlinePlayers()) {
/*  336 */                             pl.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, to, to));
                             }
                           }
/*  339 */                         if (cha == 138) {
/*  340 */                           for (Player pl : Bukkit.getOnlinePlayers()) {
/*  341 */                             pl.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, to, to));
                             }
                           }
/*  344 */                         if (cha == 140) {
/*  345 */                           for (Player pl : Bukkit.getOnlinePlayers()) {
/*  346 */                             pl.addPotionEffect(new PotionEffect(PotionEffectType.POISON, to, to));
                             }
                           }
/*  349 */                         if (cha == 142) {
/*  350 */                           for (Player pl : Bukkit.getOnlinePlayers()) {
/*  351 */                             pl.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, to, to));
                             }
                           }
/*  354 */                         if (cha == 144) {
/*  355 */                           for (Player pl : Bukkit.getOnlinePlayers()) {
/*  356 */                             pl.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, to, to));
                             }
                           }
/*  359 */                         if (cha == 146) {
/*  360 */                           for (Player pl : Bukkit.getOnlinePlayers()) {
/*  361 */                             pl.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, to, to));
                             }
                           }
/*  364 */                         if (cha == 148) {
/*  365 */                           for (Player pl : Bukkit.getOnlinePlayers()) {
/*  366 */                             pl.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, to, to));
                             }
                           }
/*  369 */                         if (cha == 150) {
/*  370 */                           for (Player pl : Bukkit.getOnlinePlayers()) {
/*  371 */                             pl.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, to, to));
                             }
                           }
/*  374 */                         if (cha == 152) {
/*  375 */                           for (Player pl : Bukkit.getOnlinePlayers()) {
/*  376 */                             pl.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK, to, to));
                             }
                           }
/*  379 */                         if (cha == 154) {
/*  380 */                           for (Player pl : Bukkit.getOnlinePlayers()) {
/*  381 */                             pl.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, to, to));
                             }
                           }
/*  384 */                         if (cha == 156) {
/*  385 */                           for (Player pl : Bukkit.getOnlinePlayers()) {
/*  386 */                             pl.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, to, to));
                             }
                           }
/*  389 */                         if (cha == 158) {
/*  390 */                           for (Player pl : Bukkit.getOnlinePlayers()) {
/*  391 */                             pl.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, to, to));
                             }
                           }
/*  394 */                         if ((cha == 1) && 
/*  395 */                           (plahand != null)) {
/*  396 */                           plahand.setType(Material.DIAMOND_SWORD);
                           }
                           
/*  399 */                         if ((cha == 3) && 
/*  400 */                           (plahand != null) && (plahand.getType() == Material.SUGAR_CANE)) {
/*  401 */                           plahand.setType(Material.CLAY_BALL);
                           }
                           
/*  404 */                         if ((cha == 5) && 
/*  405 */                           (plahand != null) && (plahand.getType() == Material.WOOD_AXE)) {
/*  406 */                           plahand.setType(Material.ACACIA_DOOR);
                           }
                           
/*  409 */                         if ((cha == 7) && 
/*  410 */                           (plahand != null) && (plahand.getType() == Material.BOW)) {
/*  411 */                           plahand.setType(Material.APPLE);
                           }
                           
/*  414 */                         if ((cha == 9) && 
/*  415 */                           (plahand != null) && (plahand.getType() == Material.WOOD_SWORD)) {
/*  416 */                           plahand.setType(Material.ARMOR_STAND);
                           }
                           
/*  419 */                         if ((cha == 11) && 
/*  420 */                           (plahand != null) && (plahand.getType() == Material.WOOD_HOE)) {
/*  421 */                           plahand.setType(Material.DIAMOND_SWORD);
                           }
                           
/*  424 */                         if ((cha == 13) && 
/*  425 */                           (plahand != null) && (plahand.getType() == Material.WOOD_PICKAXE)) {
/*  426 */                           plahand.setType(Material.DIAMOND_SWORD);
                           }
                           
/*  429 */                         if ((cha == 15) && 
/*  430 */                           (plahand != null) && (plahand.getType() == Material.WOOD_PICKAXE)) {
/*  431 */                           plahand.setType(Material.WOOD);
                           }
                           
/*  434 */                         if ((cha == 17) && 
/*  435 */                           (plahand != null) && (plahand.getType() == Material.STONE_AXE)) {
/*  436 */                           plahand.setType(Material.STONE);
                           }
                           
/*  439 */                         if ((cha == 19) && 
/*  440 */                           (plahand != null) && (plahand.getType() == Material.STONE_SPADE)) {
/*  441 */                           plahand.setType(Material.STONE);
                           }
                           
/*  444 */                         if ((cha == 21) && 
/*  445 */                           (plahand != null) && (plahand.getType() == Material.STONE_HOE)) {
/*  446 */                           plahand.setType(Material.BED);
                           }
                           
/*  449 */                         if ((cha == 23) && 
/*  450 */                           (plahand != null) && (plahand.getType() == Material.STONE_PICKAXE)) {
/*  451 */                           plahand.setType(Material.ANVIL);
                           }
                           
/*  454 */                         if ((cha == 25) && 
/*  455 */                           (plahand != null) && (plahand.getType() == Material.STONE_SWORD)) {
/*  456 */                           plahand.setType(Material.TORCH);
                           }
                           
/*  459 */                         if ((cha == 27) && 
/*  460 */                           (plahand != null) && (plahand.getType() == Material.IRON_AXE)) {
/*  461 */                           plahand.setType(Material.STICK);
                           }
                           
/*  464 */                         if ((cha == 29) && 
/*  465 */                           (plahand != null) && (plahand.getType() == Material.IRON_SWORD)) {
/*  466 */                           plahand.setType(Material.SEEDS);
                           }
                           
/*  469 */                         if ((cha == 31) && 
/*  470 */                           (plahand != null) && (plahand.getType() == Material.IRON_HOE)) {
/*  471 */                           plahand.setType(Material.BREAD);
                           }
                           
/*  474 */                         if ((cha == 33) && 
/*  475 */                           (plahand != null) && (plahand.getType() == Material.IRON_PICKAXE)) {
/*  476 */                           plahand.setType(Material.BOAT);
                           }
                           
/*  479 */                         if ((cha == 35) && 
/*  480 */                           (plahand != null) && (plahand.getType() == Material.IRON_SPADE)) {
/*  481 */                           plahand.setType(Material.BONE);
                           }
                           
/*  484 */                         if ((cha == 37) && 
/*  485 */                           (plahand != null) && (plahand.getType() == Material.GOLD_AXE)) {
/*  486 */                           plahand.setType(Material.STONE);
                           }
                           
/*  489 */                         if ((cha == 39) && 
/*  490 */                           (plahand != null) && (plahand.getType() == Material.GOLD_SWORD)) {
/*  491 */                           plahand.setType(Material.YELLOW_FLOWER);
                           }
                           
/*  494 */                         if ((cha == 41) && 
/*  495 */                           (plahand != null) && (plahand.getType() == Material.GOLD_HOE)) {
/*  496 */                           plahand.setType(Material.YELLOW_GLAZED_TERRACOTTA);
                           }
                           
/*  499 */                         if ((cha == 43) && 
/*  500 */                           (plahand != null) && (plahand.getType() == Material.GOLD_SPADE)) {
/*  501 */                           plahand.setType(Material.BIRCH_FENCE);
                           }
                           
/*  504 */                         if ((cha == 45) && 
/*  505 */                           (plahand != null) && (plahand.getType() == Material.GOLD_PICKAXE)) {
/*  506 */                           plahand.setType(Material.SPONGE);
                           }
                           
/*  509 */                         if ((cha == 47) && 
/*  510 */                           (plahand != null) && (plahand.getType() == Material.DIAMOND_AXE)) {
/*  511 */                           plahand.setType(Material.BEACON);
                           }
                           
/*  514 */                         if ((cha == 49) && 
/*  515 */                           (plahand != null) && (plahand.getType() == Material.DIAMOND_SWORD)) {
/*  516 */                           plahand.setType(Material.WATER_BUCKET);
                           }
                           
/*  519 */                         if ((cha == 51) && 
/*  520 */                           (plahand != null) && (plahand.getType() == Material.DIAMOND_HOE)) {
/*  521 */                           plahand.setType(Material.SULPHUR);
                           }
                           
/*  524 */                         if ((cha == 53) && 
/*  525 */                           (plahand != null) && (plahand.getType() == Material.DIAMOND_PICKAXE)) {
/*  526 */                           plahand.setType(Material.TRIPWIRE_HOOK);
                           }
                           
/*  529 */                         if ((cha == 55) && 
/*  530 */                           (plahand != null) && (plahand.getType() == Material.DIAMOND_SPADE)) {
/*  531 */                           plahand.setType(Material.BOOK);
                           }
                           
/*  534 */                         if ((cha == 57) && 
/*  535 */                           (phand != null)) {
/*  536 */                           phand.setType(Material.DIAMOND_SWORD);
                           }
                           
/*  539 */                         if ((cha == 59) && 
/*  540 */                           (phand != null) && (phand.getType() == Material.SUGAR_CANE)) {
/*  541 */                           phand.setType(Material.CLAY_BALL);
                           }
                           
/*  544 */                         if ((cha == 61) && 
/*  545 */                           (plahand != null) && (phand.getType() == Material.WOOD_AXE)) {
/*  546 */                           plahand.setType(Material.ACACIA_DOOR);
                           }
                           
/*  549 */                         if ((cha == 63) && 
/*  550 */                           (phand != null) && (phand.getType() == Material.BOW)) {
/*  551 */                           phand.setType(Material.APPLE);
                           }
                           
/*  554 */                         if ((cha == 65) && 
/*  555 */                           (phand != null) && (phand.getType() == Material.WOOD_SWORD)) {
/*  556 */                           plahand.setType(Material.ARMOR_STAND);
                           }
                           
/*  559 */                         if ((cha == 67) && 
/*  560 */                           (phand != null) && (phand.getType() == Material.WOOD_HOE)) {
/*  561 */                           phand.setType(Material.DIAMOND_SWORD);
                           }
                           
/*  564 */                         if ((cha == 69) && 
/*  565 */                           (phand != null) && (phand.getType() == Material.WOOD_PICKAXE)) {
/*  566 */                           phand.setType(Material.DIAMOND_SWORD);
                           }
                           
/*  569 */                         if ((cha == 71) && 
/*  570 */                           (phand != null) && (phand.getType() == Material.WOOD_PICKAXE)) {
/*  571 */                           plahand.setType(Material.WOOD);
                           }
                           
/*  574 */                         if ((cha == 73) && 
/*  575 */                           (phand != null) && (phand.getType() == Material.STONE_AXE)) {
/*  576 */                           plahand.setType(Material.STONE);
                           }
                           
/*  579 */                         if ((cha == 75) && 
/*  580 */                           (phand != null) && (phand.getType() == Material.STONE_SPADE)) {
/*  581 */                           phand.setType(Material.STONE);
                           }
                           
/*  584 */                         if ((cha == 77) && 
/*  585 */                           (phand != null) && (phand.getType() == Material.STONE_HOE)) {
/*  586 */                           phand.setType(Material.BED);
                           }
                           
/*  589 */                         if ((cha == 79) && 
/*  590 */                           (phand != null) && (phand.getType() == Material.STONE_PICKAXE)) {
/*  591 */                           phand.setType(Material.ANVIL);
                           }
                           
/*  594 */                         if ((cha == 81) && 
/*  595 */                           (phand != null) && (phand.getType() == Material.STONE_SWORD)) {
/*  596 */                           phand.setType(Material.TORCH);
                           }
                           
/*  599 */                         if ((cha == 83) && 
/*  600 */                           (phand != null) && (phand.getType() == Material.IRON_AXE)) {
/*  601 */                           phand.setType(Material.STICK);
                           }
                           
/*  604 */                         if ((cha == 85) && 
/*  605 */                           (phand != null) && (phand.getType() == Material.IRON_SWORD)) {
/*  606 */                           phand.setType(Material.SEEDS);
                           }
                           
/*  609 */                         if ((cha == 87) && 
/*  610 */                           (phand != null) && (phand.getType() == Material.IRON_HOE)) {
/*  611 */                           phand.setType(Material.BREAD);
                           }
                           
/*  614 */                         if ((cha == 89) && 
/*  615 */                           (phand != null) && (phand.getType() == Material.IRON_PICKAXE)) {
/*  616 */                           phand.setType(Material.BOAT);
                           }
                           
/*  619 */                         if ((cha == 91) && 
/*  620 */                           (phand != null) && (phand.getType() == Material.IRON_SPADE)) {
/*  621 */                           phand.setType(Material.BONE);
                           }
                           
/*  624 */                         if ((cha == 93) && 
/*  625 */                           (phand != null) && (phand.getType() == Material.GOLD_AXE)) {
/*  626 */                           phand.setType(Material.STONE);
                           }
                           
/*  629 */                         if ((cha == 95) && 
/*  630 */                           (phand != null) && (phand.getType() == Material.GOLD_SWORD)) {
/*  631 */                           phand.setType(Material.YELLOW_FLOWER);
                           }
                           
/*  634 */                         if ((cha == 97) && 
/*  635 */                           (phand != null) && (phand.getType() == Material.GOLD_HOE)) {
/*  636 */                           phand.setType(Material.YELLOW_GLAZED_TERRACOTTA);
                           }
                           
/*  639 */                         if ((cha == 99) && 
/*  640 */                           (phand != null) && (phand.getType() == Material.GOLD_SPADE)) {
/*  641 */                           phand.setType(Material.BIRCH_FENCE);
                           }
                           
/*  644 */                         if ((cha == 101) && 
/*  645 */                           (phand != null) && (phand.getType() == Material.GOLD_PICKAXE)) {
/*  646 */                           phand.setType(Material.SPONGE);
                           }
                           
/*  649 */                         if ((cha == 103) && 
/*  650 */                           (phand != null) && (phand.getType() == Material.DIAMOND_AXE)) {
/*  651 */                           phand.setType(Material.BEACON);
                           }
                           
/*  654 */                         if ((cha == 105) && 
/*  655 */                           (phand != null) && (phand.getType() == Material.DIAMOND_SWORD)) {
/*  656 */                           phand.setType(Material.WATER_BUCKET);
                           }
                           
/*  659 */                         if ((cha == 153) && 
/*  660 */                           (phand != null) && (phand.getType() == Material.DIAMOND_HOE)) {
/*  661 */                           phand.setType(Material.SULPHUR);
                           }
                           
/*  664 */                         if ((cha == 155) && 
/*  665 */                           (phand != null) && (phand.getType() == Material.DIAMOND_PICKAXE)) {
/*  666 */                           phand.setType(Material.TRIPWIRE_HOOK);
                           }
                           
/*  669 */                         if ((cha == 157) && 
/*  670 */                           (phand != null) && (phand.getType() == Material.DIAMOND_SPADE)) {
/*  671 */                           phand.setType(Material.BOOK);
                           }
                           
/*  674 */                         if (cha == 159) {
/*  675 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  677 */                             ItemStack ohand = pl.getInventory().getItemInMainHand();
/*  678 */                             if (ohand != null) {
/*  679 */                               ohand.setType(Material.DIAMOND_SWORD);
                               }
                             }
                           }
/*  683 */                         if (cha == 161) {
/*  684 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  686 */                             ItemStack ohand = pl.getInventory().getItemInMainHand();
/*  687 */                             if ((ohand != null) && (ohand.getType() == Material.SUGAR_CANE)) {
/*  688 */                               ohand.setType(Material.CLAY_BALL);
                               }
                             }
                           }
/*  692 */                         if (cha == 163) {
/*  693 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  695 */                             ItemStack ohand = pl.getInventory().getItemInMainHand();
/*  696 */                             if ((ohand != null) && (ohand.getType() == Material.WOOD_AXE)) {
/*  697 */                               ohand.setType(Material.ACACIA_DOOR);
                               }
                             }
                           }
/*  701 */                         if (cha == 165) {
/*  702 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  704 */                             ItemStack ohand = pl.getInventory().getItemInMainHand();
/*  705 */                             if ((ohand != null) && (ohand.getType() == Material.BOW)) {
/*  706 */                               ohand.setType(Material.APPLE);
                               }
                             }
                           }
/*  710 */                         if (cha == 167) {
/*  711 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  713 */                             ItemStack ohand = pl.getInventory().getItemInOffHand();
/*  714 */                             if ((ohand != null) && (ohand.getType() == Material.WOOD_SWORD)) {
/*  715 */                               ohand.setType(Material.ARMOR_STAND);
                               }
                             }
                           }
/*  719 */                         if (cha == 169) {
/*  720 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  722 */                             ItemStack ohand = pl.getInventory().getItemInMainHand();
/*  723 */                             if ((ohand != null) && (ohand.getType() == Material.WOOD_HOE)) {
/*  724 */                               ohand.setType(Material.DIAMOND_SWORD);
                               }
                             }
                           }
/*  728 */                         if (cha == 171) {
/*  729 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  731 */                             ItemStack ohand = pl.getInventory().getItemInMainHand();
/*  732 */                             if ((ohand != null) && (ohand.getType() == Material.WOOD_PICKAXE)) {
/*  733 */                               ohand.setType(Material.DIAMOND_SWORD);
                               }
                             }
                           }
/*  737 */                         if (cha == 173) {
/*  738 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  740 */                             ItemStack ohand = pl.getInventory().getItemInOffHand();
/*  741 */                             if ((ohand != null) && (ohand.getType() == Material.WOOD_PICKAXE)) {
/*  742 */                               ohand.setType(Material.WOOD);
                               }
                             }
                           }
/*  746 */                         if (cha == 175) {
/*  747 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  749 */                             ItemStack ohand = pl.getInventory().getItemInMainHand();
/*  750 */                             if ((ohand != null) && (ohand.getType() == Material.STONE_AXE)) {
/*  751 */                               ohand.setType(Material.STONE);
                               }
                             }
                           }
/*  755 */                         if (cha == 177) {
/*  756 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  758 */                             ItemStack ohand = pl.getInventory().getItemInMainHand();
/*  759 */                             if ((ohand != null) && (ohand.getType() == Material.STONE_SPADE)) {
/*  760 */                               ohand.setType(Material.STONE);
                               }
                             }
                           }
/*  764 */                         if (cha == 179) {
/*  765 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  767 */                             ItemStack ohand = pl.getInventory().getItemInMainHand();
/*  768 */                             if ((ohand != null) && (ohand.getType() == Material.STONE_HOE)) {
/*  769 */                               ohand.setType(Material.BED);
                               }
                             }
                           }
/*  773 */                         if (cha == 181) {
/*  774 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  776 */                             ItemStack ohand = pl.getInventory().getItemInMainHand();
/*  777 */                             if ((ohand != null) && (ohand.getType() == Material.STONE_PICKAXE)) {
/*  778 */                               ohand.setType(Material.ANVIL);
                               }
                             }
                           }
/*  782 */                         if (cha == 183) {
/*  783 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  785 */                             ItemStack ohand = pl.getInventory().getItemInMainHand();
/*  786 */                             if ((ohand != null) && (ohand.getType() == Material.STONE_SWORD)) {
/*  787 */                               ohand.setType(Material.TORCH);
                               }
                             }
                           }
/*  791 */                         if (cha == 185) {
/*  792 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  794 */                             ItemStack ohand = pl.getInventory().getItemInMainHand();
/*  795 */                             if ((ohand != null) && (ohand.getType() == Material.IRON_AXE)) {
/*  796 */                               ohand.setType(Material.STICK);
                               }
                             }
                           }
/*  800 */                         if (cha == 187) {
/*  801 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  803 */                             ItemStack ohand = pl.getInventory().getItemInMainHand();
/*  804 */                             if ((ohand != null) && (ohand.getType() == Material.IRON_SWORD)) {
/*  805 */                               ohand.setType(Material.SEEDS);
                               }
                             }
                           }
/*  809 */                         if (cha == 189) {
/*  810 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  812 */                             ItemStack ohand = pl.getInventory().getItemInMainHand();
/*  813 */                             if ((ohand != null) && (ohand.getType() == Material.IRON_HOE)) {
/*  814 */                               ohand.setType(Material.BREAD);
                               }
                             }
                           }
/*  818 */                         if (cha == 191) {
/*  819 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  821 */                             ItemStack ohand = pl.getInventory().getItemInMainHand();
/*  822 */                             if ((ohand != null) && (ohand.getType() == Material.IRON_PICKAXE)) {
/*  823 */                               ohand.setType(Material.BOAT);
                               }
                             }
                           }
/*  827 */                         if (cha == 193) {
/*  828 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  830 */                             ItemStack ohand = pl.getInventory().getItemInMainHand();
/*  831 */                             if ((ohand != null) && (ohand.getType() == Material.IRON_SPADE)) {
/*  832 */                               ohand.setType(Material.BONE);
                               }
                             }
                           }
/*  836 */                         if (cha == 195) {
/*  837 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  839 */                             ItemStack ohand = pl.getInventory().getItemInMainHand();
/*  840 */                             if ((ohand != null) && (ohand.getType() == Material.GOLD_AXE)) {
/*  841 */                               ohand.setType(Material.STONE);
                               }
                             }
                           }
/*  845 */                         if (cha == 197) {
/*  846 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  848 */                             ItemStack ohand = pl.getInventory().getItemInMainHand();
/*  849 */                             if ((ohand != null) && (ohand.getType() == Material.GOLD_SWORD)) {
/*  850 */                               ohand.setType(Material.YELLOW_FLOWER);
                               }
                             }
                           }
/*  854 */                         if (cha == 199) {
/*  855 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  857 */                             ItemStack ohand = pl.getInventory().getItemInMainHand();
/*  858 */                             if ((ohand != null) && (ohand.getType() == Material.GOLD_HOE)) {
/*  859 */                               ohand.setType(Material.YELLOW_GLAZED_TERRACOTTA);
                               }
                             }
                           }
/*  863 */                         if (cha == 201) {
/*  864 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  866 */                             ItemStack ohand = pl.getInventory().getItemInMainHand();
/*  867 */                             if ((ohand != null) && (ohand.getType() == Material.GOLD_SPADE)) {
/*  868 */                               ohand.setType(Material.BIRCH_FENCE);
                               }
                             }
                           }
/*  872 */                         if (cha == 203) {
/*  873 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  875 */                             ItemStack ohand = pl.getInventory().getItemInMainHand();
/*  876 */                             if ((ohand != null) && (ohand.getType() == Material.GOLD_PICKAXE)) {
/*  877 */                               ohand.setType(Material.SPONGE);
                               }
                             }
                           }
/*  881 */                         if (cha == 205) {
/*  882 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  884 */                             ItemStack ohand = pl.getInventory().getItemInMainHand();
/*  885 */                             if ((ohand != null) && (ohand.getType() == Material.DIAMOND_AXE)) {
/*  886 */                               ohand.setType(Material.BEACON);
                               }
                             }
                           }
/*  890 */                         if (cha == 207) {
/*  891 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  893 */                             ItemStack ohand = pl.getInventory().getItemInMainHand();
/*  894 */                             if ((ohand != null) && (ohand.getType() == Material.DIAMOND_SWORD)) {
/*  895 */                               ohand.setType(Material.WATER_BUCKET);
                               }
                             }
                           }
/*  899 */                         if (cha == 209) {
/*  900 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  902 */                             ItemStack ohand = pl.getInventory().getItemInMainHand();
/*  903 */                             if ((ohand != null) && (ohand.getType() == Material.DIAMOND_HOE)) {
/*  904 */                               ohand.setType(Material.SULPHUR);
                               }
                             }
                           }
/*  908 */                         if (cha == 211) {
/*  909 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  911 */                             ItemStack ohand = pl.getInventory().getItemInMainHand();
/*  912 */                             if ((ohand != null) && (ohand.getType() == Material.DIAMOND_PICKAXE)) {
/*  913 */                               ohand.setType(Material.TRIPWIRE_HOOK);
                               }
                             }
                           }
/*  917 */                         if (cha == 213) {
/*  918 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  920 */                             ItemStack ohand = pl.getInventory().getItemInMainHand();
/*  921 */                             if ((ohand != null) && (ohand.getType() == Material.DIAMOND_SPADE)) {
/*  922 */                               ohand.setType(Material.BOOK);
                               }
                             }
                           }
/*  926 */                         if ((cha == 160) && 
/*  927 */                           (boots != null) && (boots.getType() == Material.LEATHER_BOOTS)) {
/*  928 */                           boots.setType(null);
                           }
                           
/*  931 */                         if ((cha == 162) && 
/*  932 */                           (boots != null) && (boots.getType() == Material.IRON_BOOTS)) {
/*  933 */                           boots.setType(Material.LEATHER_BOOTS);
                           }
                           
/*  936 */                         if ((cha == 164) && 
/*  937 */                           (boots != null) && (boots.getType() == Material.GOLD_BOOTS)) {
/*  938 */                           boots.setType(Material.IRON_BOOTS);
                           }
                           
/*  941 */                         if ((cha == 166) && 
/*  942 */                           (boots != null) && (boots.getType() == Material.DIAMOND_BOOTS)) {
/*  943 */                           boots.setType(Material.GOLD_BOOTS);
                           }
                           
/*  946 */                         if ((cha == 168) && 
/*  947 */                           (pboots != null) && (pboots.getType() == Material.LEATHER_BOOTS)) {
/*  948 */                           pboots.setType(null);
                           }
                           
/*  951 */                         if ((cha == 170) && 
/*  952 */                           (pboots != null) && (pboots.getType() == Material.IRON_BOOTS)) {
/*  953 */                           pboots.setType(Material.LEATHER_BOOTS);
                           }
                           
/*  956 */                         if ((cha == 172) && 
/*  957 */                           (pboots != null) && (pboots.getType() == Material.GOLD_BOOTS)) {
/*  958 */                           pboots.setType(Material.IRON_BOOTS);
                           }
                           
/*  961 */                         if ((cha == 174) && 
/*  962 */                           (pboots != null) && (pboots.getType() == Material.DIAMOND_BOOTS)) {
/*  963 */                           pboots.setType(Material.GOLD_BOOTS);
                           }
                           
/*  966 */                         if (cha == 176) {
/*  967 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  969 */                             ItemStack bot = pl.getInventory().getBoots();
/*  970 */                             if ((bot != null) && (bot.getType() == Material.LEATHER_BOOTS)) {
/*  971 */                               bot.setType(null);
                               }
                             }
                           }
/*  975 */                         if (cha == 178) {
/*  976 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  978 */                             ItemStack bot = pl.getInventory().getBoots();
/*  979 */                             if ((bot != null) && (bot.getType() == Material.IRON_BOOTS)) {
/*  980 */                               bot.setType(Material.LEATHER_BOOTS);
                               }
                             }
                           }
/*  984 */                         if (cha == 180) {
/*  985 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  987 */                             ItemStack bot = pl.getInventory().getBoots();
/*  988 */                             if ((bot != null) && (bot.getType() == Material.GOLD_BOOTS)) {
/*  989 */                               bot.setType(Material.IRON_BOOTS);
                               }
                             }
                           }
/*  993 */                         if (cha == 184) {
/*  994 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/*  996 */                             ItemStack bot = pl.getInventory().getBoots();
/*  997 */                             if ((bot != null) && (bot.getType() == Material.DIAMOND_BOOTS)) {
/*  998 */                               bot.setType(Material.GOLD_BOOTS);
                               }
                             }
                           }
/* 1002 */                         if ((cha == 186) && 
/* 1003 */                           (legins != null) && (legins.getType() == Material.LEATHER_LEGGINGS)) {
/* 1004 */                           legins.setType(null);
                           }
                           
/* 1007 */                         if ((cha == 188) && 
/* 1008 */                           (legins != null) && (legins.getType() == Material.IRON_LEGGINGS)) {
/* 1009 */                           legins.setType(Material.LEATHER_LEGGINGS);
                           }
                           
/* 1012 */                         if ((cha == 200) && 
/* 1013 */                           (legins != null) && (legins.getType() == Material.GOLD_LEGGINGS)) {
/* 1014 */                           legins.setType(Material.IRON_LEGGINGS);
                           }
                           
/* 1017 */                         if ((cha == 222) && 
/* 1018 */                           (legins != null) && (legins.getType() == Material.DIAMOND_LEGGINGS)) {
/* 1019 */                           legins.setType(Material.GOLD_LEGGINGS);
                           }
                           
/* 1022 */                         if ((cha == 224) && 
/* 1023 */                           (plegins != null) && (plegins.getType() == Material.LEATHER_LEGGINGS)) {
/* 1024 */                           plegins.setType(null);
                           }
                           
/* 1027 */                         if ((cha == 226) && 
/* 1028 */                           (plegins != null) && (plegins.getType() == Material.IRON_LEGGINGS)) {
/* 1029 */                           plegins.setType(Material.LEATHER_LEGGINGS);
                           }
                           
/* 1032 */                         if ((cha == 228) && 
/* 1033 */                           (plegins != null) && (plegins.getType() == Material.GOLD_LEGGINGS)) {
/* 1034 */                           plegins.setType(Material.IRON_LEGGINGS);
                           }
                           
/* 1037 */                         if ((cha == 230) && 
/* 1038 */                           (plegins != null) && (plegins.getType() == Material.DIAMOND_LEGGINGS)) {
/* 1039 */                           plegins.setType(Material.GOLD_LEGGINGS);
                           }
                           
/* 1042 */                         if (cha == 232) {
/* 1043 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/* 1045 */                             ItemStack leg = pl.getInventory().getLeggings();
/* 1046 */                             if ((leg != null) && (leg.getType() == Material.LEATHER_LEGGINGS)) {
/* 1047 */                               leg.setType(null);
                               }
                             }
                           }
/* 1051 */                         if (cha == 234) {
/* 1052 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/* 1054 */                             ItemStack leg = pl.getInventory().getLeggings();
/* 1055 */                             if ((leg != null) && (leg.getType() == Material.IRON_LEGGINGS)) {
/* 1056 */                               leg.setType(Material.LEATHER_LEGGINGS);
                               }
                             }
                           }
/* 1060 */                         if (cha == 236) {
/* 1061 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/* 1063 */                             ItemStack leg = pl.getInventory().getLeggings();
/* 1064 */                             if ((leg != null) && (leg.getType() == Material.GOLD_LEGGINGS)) {
/* 1065 */                               leg.setType(Material.IRON_LEGGINGS);
                               }
                             }
                           }
/* 1069 */                         if (cha == 238) {
/* 1070 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/* 1072 */                             ItemStack leg = pl.getInventory().getLeggings();
/* 1073 */                             if ((leg != null) && (leg.getType() == Material.DIAMOND_LEGGINGS)) {
/* 1074 */                               leg.setType(Material.GOLD_LEGGINGS);
                               }
                             }
                           }
/* 1078 */                         if ((cha == 240) && 
/* 1079 */                           (ches != null) && (ches.getType() == Material.LEATHER_CHESTPLATE)) {
/* 1080 */                           ches.setType(null);
                           }
                           
/* 1083 */                         if ((cha == 242) && 
/* 1084 */                           (ches != null) && (ches.getType() == Material.IRON_CHESTPLATE)) {
/* 1085 */                           ches.setType(Material.LEATHER_CHESTPLATE);
                           }
                           
/* 1088 */                         if ((cha == 244) && 
/* 1089 */                           (ches != null) && (ches.getType() == Material.GOLD_CHESTPLATE)) {
/* 1090 */                           ches.setType(Material.IRON_CHESTPLATE);
                           }
                           
/* 1093 */                         if ((cha == 246) && 
/* 1094 */                           (ches != null) && (ches.getType() == Material.DIAMOND_CHESTPLATE)) {
/* 1095 */                           ches.setType(Material.GOLD_CHESTPLATE);
                           }
                           
/* 1098 */                         if ((cha == 248) && 
/* 1099 */                           (pches != null) && (pches.getType() == Material.LEATHER_CHESTPLATE)) {
/* 1100 */                           pches.setType(null);
                           }
                           
/* 1103 */                         if ((cha == 250) && 
/* 1104 */                           (pches != null) && (pches.getType() == Material.IRON_CHESTPLATE)) {
/* 1105 */                           pches.setType(Material.LEATHER_CHESTPLATE);
                           }
                           
/* 1108 */                         if ((cha == 252) && 
/* 1109 */                           (pches != null) && (pches.getType() == Material.GOLD_CHESTPLATE)) {
/* 1110 */                           pches.setType(Material.IRON_CHESTPLATE);
                           }
                           
/* 1113 */                         if ((cha == 254) && 
/* 1114 */                           (pches != null) && (pches.getType() == Material.DIAMOND_CHESTPLATE)) {
/* 1115 */                           pches.setType(Material.GOLD_CHESTPLATE);
                           }
                           
/* 1118 */                         if (cha == 256) {
/* 1119 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/* 1121 */                             ItemStack che = pl.getInventory().getChestplate();
/* 1122 */                             if ((che != null) && (che.getType() == Material.LEATHER_CHESTPLATE)) {
/* 1123 */                               che.setType(null);
                               }
                             }
                           }
/* 1127 */                         if (cha == 258) {
/* 1128 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/* 1130 */                             ItemStack che = pl.getInventory().getChestplate();
/* 1131 */                             if ((che != null) && (che.getType() == Material.IRON_CHESTPLATE)) {
/* 1132 */                               che.setType(Material.LEATHER_CHESTPLATE);
                               }
                             }
                           }
/* 1136 */                         if (cha == 260) {
/* 1137 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/* 1139 */                             ItemStack che = pl.getInventory().getChestplate();
/* 1140 */                             if ((che != null) && (che.getType() == Material.GOLD_CHESTPLATE)) {
/* 1141 */                               che.setType(Material.IRON_CHESTPLATE);
                               }
                             }
                           }
/* 1145 */                         if (cha == 262) {
/* 1146 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/* 1148 */                             ItemStack che = pl.getInventory().getChestplate();
/* 1149 */                             if ((che != null) && (che.getType() == Material.DIAMOND_CHESTPLATE)) {
/* 1150 */                               che.setType(Material.GOLD_CHESTPLATE);
                               }
                             }
                           }
/* 1154 */                         if ((cha == 266) && 
/* 1155 */                           (helmet != null) && (helmet.getType() == Material.LEATHER_HELMET)) {
/* 1156 */                           helmet.setType(null);
                           }
/* 1158 */                         if ((cha == 268) && 
/* 1159 */                           (helmet != null) && (helmet.getType() == Material.IRON_HELMET)) {
/* 1160 */                           helmet.setType(Material.LEATHER_HELMET);
                           }
/* 1162 */                         if ((cha == 270) && 
/* 1163 */                           (helmet != null) && (helmet.getType() == Material.GOLD_HELMET)) {
/* 1164 */                           helmet.setType(Material.IRON_HELMET);
                           }
/* 1166 */                         if ((cha == 272) && 
/* 1167 */                           (helmet != null) && (helmet.getType() == Material.DIAMOND_HELMET)) {
/* 1168 */                           helmet.setType(Material.GOLD_HELMET);
                           }
/* 1170 */                         if ((cha == 266) && 
/* 1171 */                           (phelmet != null) && (phelmet.getType() == Material.LEATHER_HELMET)) {
/* 1172 */                           phelmet.setType(null);
                           }
/* 1174 */                         if ((cha == 268) && 
/* 1175 */                           (phelmet != null) && (phelmet.getType() == Material.IRON_HELMET)) {
/* 1176 */                           phelmet.setType(Material.LEATHER_HELMET);
                           }
/* 1178 */                         if ((cha == 270) && 
/* 1179 */                           (phelmet != null) && (phelmet.getType() == Material.GOLD_HELMET)) {
/* 1180 */                           phelmet.setType(Material.IRON_HELMET);
                           }
/* 1182 */                         if ((cha == 272) && 
/* 1183 */                           (phelmet != null) && (phelmet.getType() == Material.DIAMOND_HELMET)) {
/* 1184 */                           phelmet.setType(Material.GOLD_HELMET);
                           }
/* 1186 */                         if (cha == 274) {
/* 1187 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/* 1189 */                             ItemStack hel = pl.getInventory().getHelmet();
/* 1190 */                             if ((hel != null) && (hel.getType() == Material.LEATHER_HELMET)) {
/* 1191 */                               hel.setType(null);
                               }
                             }
                           }
/* 1195 */                         if (cha == 276) {
/* 1196 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/* 1198 */                             ItemStack hel = pl.getInventory().getHelmet();
/* 1199 */                             if ((hel != null) && (hel.getType() == Material.IRON_HELMET)) {
/* 1200 */                               hel.setType(Material.LEATHER_HELMET);
                               }
                             }
                           }
/* 1204 */                         if (cha == 278) {
/* 1205 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/* 1207 */                             ItemStack hel = pl.getInventory().getHelmet();
/* 1208 */                             if ((hel != null) && (hel.getType() == Material.GOLD_HELMET)) {
/* 1209 */                               hel.setType(Material.IRON_HELMET);
                               }
                             }
                           }
/* 1213 */                         if (cha == 280) {
/* 1214 */                           for (Player pl : Bukkit.getOnlinePlayers())
                             {
/* 1216 */                             ItemStack hel = pl.getInventory().getHelmet();
/* 1217 */                             if ((hel != null) && (hel.getType() == Material.DIAMOND_HELMET)) {
/* 1218 */                               hel.setType(Material.GOLD_HELMET);
                               }
                             }
                           }
/* 1222 */                         if (cha == 282)
                           {
/* 1224 */                           ItemStack get = plajer.getInventory().getItemInMainHand();
/* 1225 */                           p.getInventory().addItem(new ItemStack[] { get });
/* 1226 */                           plajer.getInventory().setItemInMainHand(null);
                           }
/* 1228 */                         if (cha == 284)
                           {
/* 1230 */                           ItemStack get = p.getInventory().getItemInMainHand();
/* 1231 */                           plajer.getInventory().addItem(new ItemStack[] { get });
/* 1232 */                           p.getInventory().setItemInMainHand(null);
                           }
/* 1234 */                         if (cha == 286)
                           {
/* 1236 */                           ItemStack get = plajer.getInventory().getItemInMainHand();
/* 1237 */                           ItemStack truck = p.getInventory().getItemInMainHand();
/* 1238 */                           p.getInventory().addItem(new ItemStack[] { get });
/* 1239 */                           plajer.getInventory().addItem(new ItemStack[] { truck });
                           }
/* 1241 */                         if (cha == 215)
                           {
/* 1256 */                           new BukkitRunnable()
                             {
	@Override
                               public void run()
                               {
/* 1247 */                               Location loc = p.getLocation();
/* 1248 */                               Arrow arrow = (Arrow)p.getWorld().spawn(loc.add(ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D), ThreadLocalRandom.current().nextDouble(10.0D, 20.0D), 
/* 1249 */                                 ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D)), Arrow.class);
/* 1250 */                               arrow.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
/* 1251 */                               arrow.setVelocity(direction);
/* 1252 */                               if (p.isDead()) {
/* 1253 */                                 cancel();
                                 }
                               }
/* 1256 */                           }.runTaskTimer(Ultimate.getInstance(), 0L, 2L);
                           }
/* 1258 */                         if (cha == 217)
                           { 
/* 1273 */                           new BukkitRunnable()
                             {
	@Override
                               public void run()
                               {
/* 1264 */                               Location loc = plajer.getLocation();
/* 1265 */                               Arrow arrow = (Arrow)plajer.getWorld().spawn(loc.add(ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D), ThreadLocalRandom.current().nextDouble(10.0D, 20.0D), 
/* 1266 */                                 ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D)), Arrow.class);
/* 1267 */                               arrow.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
/* 1268 */                               arrow.setVelocity(direction);
/* 1269 */                               if (plajer.isDead()) {
/* 1270 */                                 cancel();
                                 }
                               }
/* 1273 */                           }.runTaskTimer(Ultimate.getInstance(), 0L, 2L);
                           }
/* 1275 */                         if (cha == 239)
                           { 
/* 1293 */                           new BukkitRunnable()
                             {
	@Override
                               public void run()
                               {
/* 1281 */                               for (Player pl : Bukkit.getOnlinePlayers() )
                                 {
/* 1283 */                                 Location loc = pl.getLocation();
/* 1284 */                                 Arrow arrow = (Arrow)pl.getWorld().spawn(loc.add(ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D), ThreadLocalRandom.current().nextDouble(10.0D, 20.0D), 
/* 1285 */                                   ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D)), Arrow.class);
/* 1286 */                                 arrow.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
/* 1287 */                                 arrow.setVelocity(direction);
/* 1288 */                                 if (pl.isDead()) {
/* 1289 */                                   cancel();
                                   }
                                 }
                               }
/* 1293 */                           }.runTaskTimer(Ultimate.getInstance(), 0L, 2L);
                           }
/* 1295 */                         if ((cha == 241) && 
/* 1296 */                           (plajer != null))
                           {
/* 1298 */                           p.getInventory().setBoots(boots);
/* 1299 */                           p.getInventory().setHelmet(helmet);
/* 1300 */                           p.getInventory().setChestplate(ches);
/* 1301 */                           p.getInventory().setLeggings(legins);
/* 1302 */                           p.getInventory().setItemInMainHand(hand);
/* 1303 */                           p.getInventory().setItemInOffHand(poff);
                           }
/* 1305 */                         if ((cha == 243) && 
/* 1306 */                           (plajer != null))
                           {
/* 1308 */                           p.getInventory().setBoots(boots);
/* 1309 */                           p.getInventory().setHelmet(helmet);
/* 1310 */                           p.getInventory().setChestplate(ches);
/* 1311 */                           p.getInventory().setLeggings(legins);
/* 1312 */                           p.getInventory().setItemInMainHand(hand);
/* 1313 */                           p.getInventory().setItemInOffHand(poff);
/* 1314 */                           helmet.setType(null);
/* 1315 */                           boots.setType(null);
/* 1316 */                           ches.setType(null);
/* 1317 */                           legins.setType(null);
/* 1318 */                           hand.setType(null);
/* 1319 */                           poff.setType(null);
                           }
/* 1322 */                         if ((cha == 245) && 
/* 1323 */                           (plajer != null))
                           {
/* 1325 */                           ItemStack ofhelmet = plajer.getInventory().getHelmet();
/* 1326 */                           ItemStack oflegs = plajer.getInventory().getLeggings();
/* 1327 */                           ItemStack ofbots = plajer.getInventory().getBoots();
/* 1328 */                           ItemStack ofchest = plajer.getInventory().getChestplate();
/* 1329 */                           ItemStack ohand = plajer.getInventory().getItemInMainHand();
/* 1330 */                           ItemStack ofhand = plajer.getInventory().getItemInOffHand();
/* 1331 */                           ItemStack pofhelmet = p.getInventory().getHelmet();
/* 1332 */                           ItemStack pofches = p.getInventory().getChestplate();
/* 1333 */                           ItemStack poflegs = p.getInventory().getLeggings();
/* 1334 */                           ItemStack pofbots = p.getInventory().getBoots();
/* 1335 */                           ItemStack pofhand = p.getInventory().getItemInMainHand();
/* 1336 */                           ItemStack poofhand = p.getInventory().getItemInOffHand();
/* 1337 */                           p.getInventory().setBoots(ofbots);
/* 1338 */                           p.getInventory().setHelmet(ofhelmet);
/* 1339 */                           p.getInventory().setChestplate(ofchest);
/* 1340 */                           p.getInventory().setLeggings(oflegs);
/* 1341 */                           p.getInventory().setItemInMainHand(ohand);
/* 1342 */                           p.getInventory().setItemInOffHand(ofhand);
                             
/* 1344 */                           plajer.getInventory().setBoots(pofbots);
/* 1345 */                           plajer.getInventory().setHelmet(pofhelmet);
/* 1346 */                           plajer.getInventory().setChestplate(pofches);
/* 1347 */                           plajer.getInventory().setLeggings(poflegs);
/* 1348 */                           plajer.getInventory().setItemInMainHand(pofhand);
/* 1349 */                           plajer.getInventory().setItemInOffHand(poofhand);
                           }
/* 1351 */                         if (cha == 247) {
/* 1352 */                           for (Player pl : Bukkit.getOnlinePlayers()){ 
                               
/* 1354 */                             ItemStack all = pl.getInventory().getItemInMainHand();
/* 1355 */                             p.getWorld().dropItemNaturally(p.getLocation(), all);
                             }
                           }
/* 1358 */                         if (cha == 249)
                           {
/* 1377 */                           new BukkitRunnable()
                             {
	@Override
                               public void run()
                               {
/* 1364 */                               ItemStack poti = new ItemStack(Material.SPLASH_POTION);
/* 1365 */                               PotionMeta meta = (PotionMeta)poti.getItemMeta();
/* 1366 */                               meta.setColor(Color.FUCHSIA);
/* 1367 */                               meta.addCustomEffect(new PotionEffect(PotionEffectType.BLINDNESS, to, to), true);
/* 1368 */                               poti.setItemMeta(meta);
/* 1369 */                               Location loc = plajer.getLocation();
/* 1370 */                               ThrownPotion pot = (ThrownPotion)plajer.getWorld().spawn(loc.add(ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D), ThreadLocalRandom.current().nextDouble(10.0D, 20.0D), 
/* 1371 */                                 ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D)), ThrownPotion.class);
/* 1372 */                               pot.setItem(new ItemStack(poti));
/* 1373 */                               if (plajer.isDead()) {
/* 1374 */                                 cancel();
                                 }
                               }
/* 1377 */                           }.runTaskTimer(Ultimate.getInstance(), 0L, 2L);
                           }
/* 1379 */                         if (cha == 251)
                           { 
/* 1398 */                           new BukkitRunnable()
                             {
@Override
                               public void run()
                               {
/* 1385 */                               ItemStack poti = new ItemStack(Material.SPLASH_POTION);
/* 1386 */                               PotionMeta meta = (PotionMeta)poti.getItemMeta();
/* 1387 */                               meta.setColor(Color.FUCHSIA);
/* 1388 */                               meta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, to, to), true);
/* 1389 */                               poti.setItemMeta(meta);
/* 1390 */                               Location loc = plajer.getLocation();
/* 1391 */                               ThrownPotion pot = (ThrownPotion)plajer.getWorld().spawn(loc.add(ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D), ThreadLocalRandom.current().nextDouble(10.0D, 20.0D), 
/* 1392 */                                 ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D)), ThrownPotion.class);
/* 1393 */                               pot.setItem(new ItemStack(poti));
/* 1394 */                               if (plajer.isDead()) {
/* 1395 */                                 cancel();
                                 }
                               }
/* 1398 */                           }.runTaskTimer(Ultimate.getInstance(), 0L, 2L);
                           }
/* 1400 */                         if (cha == 253)
                           {
   
/* 1419 */                           new BukkitRunnable()
                             {
	@Override
                               public void run()
                               {
/* 1406 */                               ItemStack poti = new ItemStack(Material.SPLASH_POTION);
/* 1407 */                               PotionMeta meta = (PotionMeta)poti.getItemMeta();
/* 1408 */                               meta.setColor(Color.FUCHSIA);
/* 1409 */                               meta.addCustomEffect(new PotionEffect(PotionEffectType.WITHER, to, to), true);
/* 1410 */                               poti.setItemMeta(meta);
/* 1411 */                               Location loc = plajer.getLocation();
/* 1412 */                               ThrownPotion pot = (ThrownPotion)plajer.getWorld().spawn(loc.add(ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D), ThreadLocalRandom.current().nextDouble(10.0D, 20.0D), 
/* 1413 */                                 ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D)), ThrownPotion.class);
/* 1414 */                               pot.setItem(new ItemStack(poti));
/* 1415 */                               if (plajer.isDead()) {
/* 1416 */                                 cancel();
                                 }
                               }
/* 1419 */                           }.runTaskTimer(Ultimate.getInstance(), 0L, 2L);
                           }
/* 1421 */                         if (cha == 255)
                           {
/* 1441 */                           new BukkitRunnable()
                             {
	@Override
                               public void run()
                               {
/* 1428 */                               ItemStack poti = new ItemStack(Material.SPLASH_POTION);
/* 1429 */                               PotionMeta meta = (PotionMeta)poti.getItemMeta();
/* 1430 */                               meta.setColor(Color.FUCHSIA);
/* 1431 */                               meta.addCustomEffect(new PotionEffect(PotionEffectType.CONFUSION, to, to), true);
/* 1432 */                               poti.setItemMeta(meta);
/* 1433 */                               Location loc = plajer.getLocation();
/* 1434 */                               ThrownPotion pot = (ThrownPotion)plajer.getWorld().spawn(loc.add(ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D), ThreadLocalRandom.current().nextDouble(10.0D, 20.0D), 
/* 1435 */                                 ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D)), ThrownPotion.class);
/* 1436 */                               pot.setItem(new ItemStack(poti));
/* 1437 */                               if (plajer.isDead()) {
/* 1438 */                                 cancel();
                                 }
                               }
/* 1441 */                           }.runTaskTimer(Ultimate.getInstance(), 0L, 2L);
                           }
/* 1443 */                         if (cha == 257)
                           {
   
   
/* 1462 */                           new BukkitRunnable()
                             {
	@Override
                               public void run()
                               {
/* 1449 */                               ItemStack poti = new ItemStack(Material.SPLASH_POTION);
/* 1450 */                               PotionMeta meta = (PotionMeta)poti.getItemMeta();
/* 1451 */                               meta.setColor(Color.FUCHSIA);
/* 1452 */                               meta.addCustomEffect(new PotionEffect(PotionEffectType.HARM, to, to), true);
/* 1453 */                               poti.setItemMeta(meta);
/* 1454 */                               Location loc = plajer.getLocation();
/* 1455 */                               ThrownPotion pot = (ThrownPotion)plajer.getWorld().spawn(loc.add(ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D), ThreadLocalRandom.current().nextDouble(10.0D, 20.0D), 
/* 1456 */                                 ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D)), ThrownPotion.class);
/* 1457 */                               pot.setItem(new ItemStack(poti));
/* 1458 */                               if (plajer.isDead()) {
/* 1459 */                                 cancel();
                                 }
                               }
/* 1462 */                           }.runTaskTimer(Ultimate.getInstance(), 0L, 2L);
                           }
/* 1464 */                         if (cha == 259)
                           {
   
/* 1483 */                           new BukkitRunnable()
                             {
	@Override
                               public void run()
                               {
/* 1470 */                               ItemStack poti = new ItemStack(Material.SPLASH_POTION);
/* 1471 */                               PotionMeta meta = (PotionMeta)poti.getItemMeta();
/* 1472 */                               meta.setColor(Color.FUCHSIA);
/* 1473 */                               meta.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, to, to), true);
/* 1474 */                               poti.setItemMeta(meta);
/* 1475 */                               Location loc = plajer.getLocation();
/* 1476 */                               ThrownPotion pot = (ThrownPotion)plajer.getWorld().spawn(loc.add(ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D), ThreadLocalRandom.current().nextDouble(10.0D, 20.0D), 
/* 1477 */                                 ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D)), ThrownPotion.class);
/* 1478 */                               pot.setItem(new ItemStack(poti));
/* 1479 */                               if (plajer.isDead()) {
/* 1480 */                                 cancel();
                                 }
                               }
/* 1483 */                           }.runTaskTimer(Ultimate.getInstance(), 0L, 2L);
                           }
/* 1485 */                         if (cha == 261)
                           {
   
/* 1504 */                           new BukkitRunnable()
                             {
	@Override
                               public void run()
                               {
/* 1491 */                               ItemStack poti = new ItemStack(Material.SPLASH_POTION);
/* 1492 */                               PotionMeta meta = (PotionMeta)poti.getItemMeta();
/* 1493 */                               meta.setColor(Color.FUCHSIA);
/* 1494 */                               meta.addCustomEffect(new PotionEffect(PotionEffectType.BLINDNESS, to, to), true);
/* 1495 */                               poti.setItemMeta(meta);
/* 1496 */                               Location loc = p.getLocation();
/* 1497 */                               ThrownPotion pot = (ThrownPotion)p.getWorld().spawn(loc.add(ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D), ThreadLocalRandom.current().nextDouble(10.0D, 20.0D), 
/* 1498 */                                 ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D)), ThrownPotion.class);
/* 1499 */                               pot.setItem(new ItemStack(poti));
/* 1500 */                               if (plajer.isDead()) {
/* 1501 */                                 cancel();
                                 }
                               }
/* 1504 */                           }.runTaskTimer(Ultimate.getInstance(), 0L, 2L);
                           }
/* 1506 */                         if (cha == 263)
                           {
   
   
/* 1526 */                           new BukkitRunnable()
                             {
	@Override
                               public void run()
                               {
/* 1513 */                               ItemStack poti = new ItemStack(Material.SPLASH_POTION);
/* 1514 */                               PotionMeta meta = (PotionMeta)poti.getItemMeta();
/* 1515 */                               meta.setColor(Color.FUCHSIA);
/* 1516 */                               meta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, to, to), true);
/* 1517 */                               poti.setItemMeta(meta);
/* 1518 */                               Location loc = p.getLocation();
/* 1519 */                               ThrownPotion pot = (ThrownPotion)p.getWorld().spawn(loc.add(ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D), ThreadLocalRandom.current().nextDouble(10.0D, 20.0D), 
/* 1520 */                                 ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D)), ThrownPotion.class);
/* 1521 */                               pot.setItem(new ItemStack(poti));
/* 1522 */                               if (plajer.isDead()) {
/* 1523 */                                 cancel();
                                 }
                               }
/* 1526 */                           }.runTaskTimer(Ultimate.getInstance(), 0L, 2L);
                           }
/* 1528 */                         if (cha == 265)
                           {
   
   
/* 1547 */                           new BukkitRunnable()
                             {
	@Override
                               public void run()
                               {
/* 1534 */                               ItemStack poti = new ItemStack(Material.SPLASH_POTION);
/* 1535 */                               PotionMeta meta = (PotionMeta)poti.getItemMeta();
/* 1536 */                               meta.setColor(Color.FUCHSIA);
/* 1537 */                               meta.addCustomEffect(new PotionEffect(PotionEffectType.WITHER, to, to), true);
/* 1538 */                               poti.setItemMeta(meta);
/* 1539 */                               Location loc = p.getLocation();
/* 1540 */                               ThrownPotion pot = (ThrownPotion)p.getWorld().spawn(loc.add(ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D), ThreadLocalRandom.current().nextDouble(10.0D, 20.0D), 
/* 1541 */                                 ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D)), ThrownPotion.class);
/* 1542 */                               pot.setItem(new ItemStack(poti));
/* 1543 */                               if (plajer.isDead()) {
/* 1544 */                                 cancel();
                                 }
                               }
/* 1547 */                           }.runTaskTimer(Ultimate.getInstance(), 0L, 2L);
                           }
/* 1549 */                         if (cha == 267)
                           {
   
   
/* 1568 */                           new BukkitRunnable()
                             {
	@Override
                               public void run()
                               {
/* 1555 */                               ItemStack poti = new ItemStack(Material.SPLASH_POTION);
/* 1556 */                               PotionMeta meta = (PotionMeta)poti.getItemMeta();
/* 1557 */                               meta.setColor(Color.FUCHSIA);
/* 1558 */                               meta.addCustomEffect(new PotionEffect(PotionEffectType.CONFUSION, to, to), true);
/* 1559 */                               poti.setItemMeta(meta);
/* 1560 */                               Location loc = plajer.getLocation();
/* 1561 */                               ThrownPotion pot = (ThrownPotion)plajer.getWorld().spawn(loc.add(ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D), ThreadLocalRandom.current().nextDouble(10.0D, 20.0D), 
/* 1562 */                                 ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D)), ThrownPotion.class);
/* 1563 */                               pot.setItem(new ItemStack(poti));
/* 1564 */                               if (plajer.isDead()) {
/* 1565 */                                 cancel();
                                 }
                               }
/* 1568 */                           }.runTaskTimer(Ultimate.getInstance(), 0L, 2L);
                           }
/* 1570 */                         if (cha == 269)
                           {
/* 1589 */                           new BukkitRunnable()
                             {
	@Override
                               public void run()
                               {
/* 1576 */                               ItemStack poti = new ItemStack(Material.SPLASH_POTION);
/* 1577 */                               PotionMeta meta = (PotionMeta)poti.getItemMeta();
/* 1578 */                               meta.setColor(Color.FUCHSIA);
/* 1579 */                               meta.addCustomEffect(new PotionEffect(PotionEffectType.HARM, to, to), true);
/* 1580 */                               poti.setItemMeta(meta);
/* 1581 */                               Location loc = p.getLocation();
/* 1582 */                               ThrownPotion pot = (ThrownPotion)p.getWorld().spawn(loc.add(ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D), ThreadLocalRandom.current().nextDouble(10.0D, 20.0D), 
/* 1583 */                                 ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D)), ThrownPotion.class);
/* 1584 */                               pot.setItem(new ItemStack(poti));
/* 1585 */                               if (plajer.isDead()) {
/* 1586 */                                 cancel();
                                 }
                               }
/* 1589 */                           }.runTaskTimer(Ultimate.getInstance(), 0L, 2L);
                           }
/* 1591 */                         if (cha == 271)
                           {
/* 1610 */                           new BukkitRunnable()
                             {
	@Override
                               public void run()
                               {
/* 1597 */                               ItemStack poti = new ItemStack(Material.SPLASH_POTION);
/* 1598 */                               PotionMeta meta = (PotionMeta)poti.getItemMeta();
/* 1599 */                               meta.setColor(Color.FUCHSIA);
/* 1600 */                               meta.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, to, to), true);
/* 1601 */                               poti.setItemMeta(meta);
/* 1602 */                               Location loc = p.getLocation();
/* 1603 */                               ThrownPotion pot = (ThrownPotion)p.getWorld().spawn(loc.add(ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D), ThreadLocalRandom.current().nextDouble(10.0D, 20.0D), 
/* 1604 */                                 ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D)), ThrownPotion.class);
/* 1605 */                               pot.setItem(new ItemStack(poti));
/* 1606 */                               if (plajer.isDead()) {
/* 1607 */                                 cancel();
                                 }
                               }
/* 1610 */                           }.runTaskTimer(Ultimate.getInstance(), 0L, 2L);
                           }
/* 1612 */                         if (cha == 273)
                           {
/* 1634 */                           new BukkitRunnable()
                             {
	@Override
                               public void run()
                               {
/* 1618 */                               for (Player pl : Bukkit.getOnlinePlayers() )
                                 {
/* 1620 */                                 ItemStack poti = new ItemStack(Material.SPLASH_POTION);
/* 1621 */                                 PotionMeta meta = (PotionMeta)poti.getItemMeta();
/* 1622 */                                 meta.setColor(Color.FUCHSIA);
/* 1623 */                                 meta.addCustomEffect(new PotionEffect(PotionEffectType.BLINDNESS, to, to), true);
/* 1624 */                                 poti.setItemMeta(meta);
/* 1625 */                                 Location loc = pl.getLocation();
/* 1626 */                                 ThrownPotion pot = (ThrownPotion)pl.getWorld().spawn(loc.add(ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D), ThreadLocalRandom.current().nextDouble(10.0D, 20.0D), 
/* 1627 */                                   ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D)), ThrownPotion.class);
/* 1628 */                                 pot.setItem(new ItemStack(poti));
/* 1629 */                                 if (plajer.isDead()) {
/* 1630 */                                   cancel();
                                   }
                                 }
                               }
/* 1634 */                           }.runTaskTimer(Ultimate.getInstance(), 0L, 2L);
                           }
/* 1636 */                         if (cha == 275)
                           {
/* 1658 */                           new BukkitRunnable()
                             {
	@Override
                               public void run()
                               {
/* 1642 */                               for (Player pl : Bukkit.getOnlinePlayers())
                                 {
/* 1644 */                                 ItemStack poti = new ItemStack(Material.SPLASH_POTION);
/* 1645 */                                 PotionMeta meta = (PotionMeta)poti.getItemMeta();
/* 1646 */                                 meta.setColor(Color.FUCHSIA);
/* 1647 */                                 meta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, to, to), true);
/* 1648 */                                 poti.setItemMeta(meta);
/* 1649 */                                 Location loc = pl.getLocation();
/* 1650 */                                 ThrownPotion pot = (ThrownPotion)pl.getWorld().spawn(loc.add(ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D), ThreadLocalRandom.current().nextDouble(10.0D, 20.0D), 
/* 1651 */                                   ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D)), ThrownPotion.class);
/* 1652 */                                 pot.setItem(new ItemStack(poti));
                                 if (plajer.isDead()) {
                                   cancel();
                                   }
                                 }
                               }
                           }.runTaskTimer(Ultimate.getInstance(), 40L, 40L);
                           }
                         if (cha == 277)
                           {
                           new BukkitRunnable()
                             {
	                       @Override
                             public void run()
                             {
                               for (Player pl : Bukkit.getOnlinePlayers() )
                               {
                                 ItemStack poti = new ItemStack(Material.SPLASH_POTION);
                                 PotionMeta meta = (PotionMeta)poti.getItemMeta();
                                 meta.setColor(Color.FUCHSIA);
                                 meta.addCustomEffect(new PotionEffect(PotionEffectType.WITHER, to, to), true);
                                 poti.setItemMeta(meta);
                                 Location loc = pl.getLocation();
                                 ThrownPotion pot = (ThrownPotion)pl.getWorld().spawn(loc.add(ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D), ThreadLocalRandom.current().nextDouble(10.0D, 20.0D), 
                                   ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D)), ThrownPotion.class);
                                 pot.setItem(new ItemStack(poti));
                                 if (plajer.isDead()) {
                                   cancel();
                                 }
                               }
                             }
                           }.runTaskTimer(Ultimate.getInstance(), 40L, 40L);
                         }
                         if (cha == 279)
                         {
                           new BukkitRunnable()
                           {
	                                 @Override
                             public void run()
                             {
                               for (Player pl : Bukkit.getOnlinePlayers())
                               {
                                 ItemStack poti = new ItemStack(Material.SPLASH_POTION);
                                 PotionMeta meta = (PotionMeta)poti.getItemMeta();
                                 meta.setColor(Color.FUCHSIA);
                                 meta.addCustomEffect(new PotionEffect(PotionEffectType.CONFUSION, to, to), true);
                                 poti.setItemMeta(meta);
                                 Location loc = pl.getLocation();
                                 ThrownPotion pot = (ThrownPotion)pl.getWorld().spawn(loc.add(ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D), ThreadLocalRandom.current().nextDouble(10.0D, 20.0D), 
                                   ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D)), ThrownPotion.class);
                                 pot.setItem(new ItemStack(poti));
                                 if (plajer.isDead()) {
                                   cancel();
                                 }
                               }
                             }
                           }.runTaskTimer(Ultimate.getInstance(), 0L, 2L);
                         }
                         if (cha == 281)
                         {
                           new BukkitRunnable()
                           {
                        	@Override
                             public void run()
                             {
                               for (Player pl : Bukkit.getOnlinePlayers() )
                               {
                                 ItemStack poti = new ItemStack(Material.SPLASH_POTION);
                                 PotionMeta meta = (PotionMeta)poti.getItemMeta();
                                 meta.setColor(Color.FUCHSIA);
                                 meta.addCustomEffect(new PotionEffect(PotionEffectType.HARM, to, to), true);
                                 poti.setItemMeta(meta);
                                 Location loc = pl.getLocation();
                                 ThrownPotion pot = (ThrownPotion)pl.getWorld().spawn(loc.add(ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D), ThreadLocalRandom.current().nextDouble(10.0D, 20.0D), 
                                   ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D)), ThrownPotion.class);
                                 pot.setItem(new ItemStack(poti));
                                 if (plajer.isDead()) {
                                   cancel();
                                 }
                               }
                             }
                           }.runTaskTimer(Ultimate.getInstance(), 0L, 2L);
                         }
                         if (cha == 283)
                         {
                           new BukkitRunnable()
                           {
	                       @Override
                             public void run()
                             {
                               for (Player pl : Bukkit.getOnlinePlayers() )
                               {
                                 ItemStack poti = new ItemStack(Material.SPLASH_POTION);
                                 PotionMeta meta = (PotionMeta)poti.getItemMeta();
                                 meta.setColor(Color.FUCHSIA);
                                 meta.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, to, to), true);
                                 poti.setItemMeta(meta);
                                 Location loc = pl.getLocation();
                                 ThrownPotion pot = (ThrownPotion)pl.getWorld().spawn(loc.add(ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D), ThreadLocalRandom.current().nextDouble(10.0D, 20.0D), 
                                   ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D)), ThrownPotion.class);
                                 pot.setItem(new ItemStack(poti));
                                 if (plajer.isDead()) {
                                   cancel();
                                 }
                               }
                             }
                           }.runTaskTimer(Ultimate.getInstance(), 0L, 2L);
                         }
                         if (cha == 285)
                         {
                           double x = ThreadLocalRandom.current().nextDouble(200.0D, -200.0D);
                           double z = ThreadLocalRandom.current().nextDouble(300.0D, -300.0D);
                           double y = ThreadLocalRandom.current().nextDouble(100.0D, -100.0D);
                           Location l = p.getLocation();
                           p.teleport(l.add(x, y, z));
                         }
                         if (cha == 287)
                         {
                           double x = ThreadLocalRandom.current().nextDouble(200.0D, -200.0D);
                           double z = ThreadLocalRandom.current().nextDouble(300.0D, -300.0D);
                           double y = ThreadLocalRandom.current().nextDouble(100.0D, -100.0D);
                           Location l = plajer.getLocation();
                           plajer.teleport(l.add(x, y, z));
                         }
                         if (cha == 289) {
                           for (Player pl : Bukkit.getOnlinePlayers()){
                             
                             double x = ThreadLocalRandom.current().nextDouble(200.0D);
                             double z = ThreadLocalRandom.current().nextDouble(300.0D);
                             double y = ThreadLocalRandom.current().nextDouble(100.0D);
                             Location l = pl.getLocation();
                             pl.teleport(l.add(x, y, z));
                           }
                         }
                         if (cha == 291) {
                           p.getWorld().createExplosion(p.getLocation(), to);
                         }
                         if (cha == 293) {
                           plajer.getWorld().createExplosion(plajer.getLocation(), to);
                         }
                         if (cha == 295) {
                           for (Player pl : Bukkit.getOnlinePlayers()){
                             
                             pl.getWorld().createExplosion(pl.getLocation(), to);
                           }
                         }
                         if (cha == 297) {
                           p.getWorld().strikeLightning(p.getLocation());
                         }
                         if (cha == 299) {
                           plajer.getWorld().strikeLightning(plajer.getLocation());
                         }
                         if (cha == 301) {
                           for (Player pl : Bukkit.getOnlinePlayers()){
                             
                             pl.getWorld().strikeLightning(pl.getLocation());
                           }
                         }
                         if (cha == 288) {
                           p.getInventory().clear();
                         }
                         if (cha == 290) {
                           plajer.getInventory().clear();
                         }
                         if (cha == 292) {
                           for(Player pl : Bukkit.getOnlinePlayers()){
                             
                             PlayerInventory ta = pl.getInventory();
                             ta.clear();
                           }
                         }
                         if (cha == 294)
                         {
                           p.getInventory().addItem(new ItemStack[] { plahand });
                           plajer.getInventory().setItemInMainHand(null);
                         }
                         if (cha == 296)
                         {
                           plajer.getInventory().addItem(new ItemStack[] { phand });
                           p.getInventory().setItemInMainHand(null);
                         }
                         if (cha == 298)
                         {
                           p.getInventory().addItem(new ItemStack[] { poff });
                           plajer.getInventory().setItemInOffHand(null);
                         }
                         if (cha == 300)
                         {
                           plajer.getInventory().addItem(new ItemStack[] { off });
                           p.getInventory().setItemInOffHand(null);
                         }
                         if (cha == 302)
                         {
                           p.setNoDamageTicks(0);
                           plajer.damage(to);
                         }
                         
                         if (cha == 303)
                         { 
                           new BukkitRunnable()
                           {
	                       @Override
                             public void run()
                             {
                               for (Player pl : Bukkit.getOnlinePlayers())
                               {
                                 Location loc = pl.getLocation();
                                 ExperienceOrb ex = (ExperienceOrb)pl.getWorld().spawn(loc.add(ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D), ThreadLocalRandom.current().nextDouble(10.0D, 20.0D), 
                                   ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D)), ExperienceOrb.class);
                                 ex.setExperience(1);
                                 if (plajer.isDead()) {
                                   cancel();
                                 }
                               }
                             }
                           }.runTaskTimer(Ultimate.getInstance(), 0L, 2L);
                         }
                         
 
                         if (cha == 305)
                         {
                           new BukkitRunnable()
                           {
	                       @Override
                             public void run()
                             {
                               Location loc = p.getLocation();
                               ExperienceOrb ex = (ExperienceOrb)p.getWorld().spawn(loc.add(ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D), ThreadLocalRandom.current().nextDouble(10.0D, 20.0D), 
                                 ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D)), ExperienceOrb.class);
                               ex.setExperience(1);
                               if (plajer.isDead()) {
                                 cancel();
                               }
                             }
                           }.runTaskTimer(Ultimate.getInstance(), 0L, 2L);
                         }
                         
                         if (cha == 304)
                         { 
                           new BukkitRunnable()
                           {
	                       @Override
                             public void run()
                             {
                               Location loc = plajer.getLocation();
                               ExperienceOrb ex = (ExperienceOrb)plajer.getWorld().spawn(loc.add(ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D), ThreadLocalRandom.current().nextDouble(10.0D, 20.0D), 
                                 ThreadLocalRandom.current().nextDouble(-3.0D, 3.0D)), ExperienceOrb.class);
                               ex.setExperience(1);
                               if (plajer.isDead()) {
                                 cancel();
                               }
                             }
                           }.runTaskTimer(Ultimate.getInstance(), 0L, 2L);
                         }
                       }
                     }
                     
 
 
 
 
                     if ((entity instanceof Monster))
                     {
                       ((Monster)entity).setNoDamageTicks(0);
                       ((Monster)entity).damage(to);
                     }
                     if ((entity instanceof Animals))
                     {
                       ((Animals)entity).setNoDamageTicks(0);
                       ((Animals)entity).damage(to);
                     }
                   }
                 }
               }
               Cooldown.setCooldown(e.getPlayer(), guns.getInt("GunList.HapeningGun.waittimer"));
             }  else  {
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