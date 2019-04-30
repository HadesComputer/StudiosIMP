package guns;
import java.util.Collection;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Animals;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import ultimate.main.Ultimate;
 
 
public class CustomGun implements Listener{
   
   public CustomGun(Ultimate instance) {}
   
   @EventHandler
   public void Dragon(PlayerInteractEvent e)
   {
      Player p = e.getPlayer();
     ItemStack hand = p.getInventory().getItemInMainHand();
     ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
     if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.PHYSICAL)) {
       if (hand.getType() != null && hand.getType() == Material.DIAMOND_SWORD) {
          Location start = p.getLocation();
          Vector vec = start.getDirection();
         vec.normalize().multiply(0.5D);
         Location loc = p.getLocation();
          ArmorStand stand = (ArmorStand)p.getWorld().spawn(loc.add(0.0D, 0.0D, -2.0D), ArmorStand.class);
         stand.setArms(true);
         stand.setGravity(false);
         stand.setItemInHand(sword);
         stand.setVisible(true);
         stand.setMarker(true);
         stand.setBasePlate(false);
         stand.setRightArmPose(new EulerAngle(Math.toRadians(0.0D), Math.toRadians(p.getLocation().getPitch() * -1.0D), Math.toRadians(90.0D)));
         stand.setVelocity(p.getLocation().getDirection().multiply(1.0D));
         
 
 
 
         new BukkitRunnable()
         {
        	 public void run()
           {
             if (stand != null) {
               Collection<Entity> ent = p.getWorld().getNearbyEntities(start.add(vec), 1.0D, 1.0D, 1.0D);
               stand.teleport(start.add(vec));
               for (Entity entity : ent) {
                 if (entity instanceof Player) {
                   stand.teleport(start.add(vec));
                   Player plajer = (Player)entity;
                   plajer.setNoDamageTicks(0);
                   plajer.damage(15.0D);
                   stand.remove();
                 }
                 if (entity instanceof Animals) {
                   stand.teleport(start.add(vec));
                   ((Animals)entity).setNoDamageTicks(0);
                   ((Animals)entity).damage(15.0D);
                   stand.remove();
                 }
                 if ((entity instanceof Monster)) {
                   stand.teleport(start.add(vec));
                   ((Monster)entity).setNoDamageTicks(0);
                   ((Monster)entity).damage(15.0D);
                   stand.remove();
                 }
                 
               }
               
             }
           }
         }.runTaskTimer(Ultimate.getInstance(), 3L, 3L);
       } else {
         e.setCancelled(true);
       }
     }
   }
 }