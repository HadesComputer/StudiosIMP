/*    */ package gamelisteners;
/*    */ 
/*    */ import org.bukkit.entity.Arrow;
/*    */ import org.bukkit.entity.DragonFireball;
/*    */ import org.bukkit.entity.Egg;
/*    */ import org.bukkit.entity.Fireball;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.entity.Projectile;
/*    */ import org.bukkit.entity.ShulkerBullet;
/*    */ import org.bukkit.entity.Snowball;
/*    */ import org.bukkit.entity.WitherSkull;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.ProjectileHitEvent;
/*    */ import ultimate.main.PlayerDataManager;
/*    */ import util.Util;
/*    */ 
/*    */ public class SoundHit
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onHit(ProjectileHitEvent e)
/*    */   {
/* 24 */     Projectile shot = e.getEntity();
/*    */     
/* 26 */     if ((((shot instanceof Snowball)) || ((shot instanceof Egg)) || ((shot instanceof WitherSkull)) || ((shot instanceof Fireball)) || ((shot instanceof DragonFireball)) || ((shot instanceof ShulkerBullet)) || 
/* 27 */       ((shot instanceof Arrow))) && ((shot.getShooter() instanceof Player))) {
/* 28 */       Player p = (Player)shot.getShooter();
/* 29 */       PlayerDataManager pm = new PlayerDataManager(p.getUniqueId());
/* 30 */       pm.GetHitSound();
/*    */       
/*    */ 
/* 33 */       Util.PlaySound(p, pm.GetHitSound());
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Pcs Exabyte\Desktop\Servidor\Village Defense\plugins\UltimateCaptureTheWool.jar!\gamelisteners\SoundHit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */