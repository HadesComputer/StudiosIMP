/*    */ package ultimate.main;
/*    */ 
/*    */ import java.util.HashMap;
import java.util.UUID;

/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class Cooldown
/*    */ {
/*    */   public static HashMap<UUID, Double> cooldowns;
/*    */   
/*    */   public static void setupCooldown()
/*    */   {
/* 12 */     cooldowns = new HashMap<>();
/*    */   }
/*    */   
/*    */   public static void setCooldown(Player player, int seconds)
/*    */   {
/* 17 */     double delay = System.currentTimeMillis() + seconds * 1000;
/* 18 */     cooldowns.put(player.getUniqueId(), Double.valueOf(delay));
/*    */   }
/*    */   
/*    */   public static int getCooldown(Player player) {
/* 22 */     return Math.toIntExact(Math.round(((cooldowns.get(player.getUniqueId())).doubleValue() - System.currentTimeMillis()) / 1000.0D));
/*    */   }
/*    */   
/*    */   public static boolean checkCooldown(Player player)
/*    */   {
/* 27 */     if (!cooldowns.containsKey(player.getUniqueId()) || (cooldowns.get(player.getUniqueId())).doubleValue() <= System.currentTimeMillis()) {
/* 28 */       return true;
/*    */     }
/* 30 */     return false;
/*    */   }
/*    */ }
