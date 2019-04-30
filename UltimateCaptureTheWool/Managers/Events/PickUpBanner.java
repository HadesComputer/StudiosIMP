/*    */ package gamelisteners;
/*    */ import gamemanager.ArenaCollectors;
/*    */ import gamemanager.TeamManager;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.EntityPickupItemEvent;
/*    */ import ultimate.main.ConfigManager;
/*    */ import ultimate.main.Ultimate;
/*    */ import util.Util;
/*    */ 
/*    */ public class PickUpBanner implements Listener
/*    */ {
/*    */   public PickUpBanner(Ultimate ultimate) {}
/*    */   
/*    */   @EventHandler
/*    */   public void onPick(EntityPickupItemEvent e)
/*    */   {
/* 27 */     Player p = (Player)e.getEntity();
/* 28 */     FileConfiguration ms = ConfigManager.getInstance().getMessages();
/* 29 */     FileConfiguration cg = Ultimate.getInstance().getConfig();
/* 30 */     TeamManager tm = new TeamManager();
/* 31 */     if (((e.getEntity() instanceof Player)) && 
/* 32 */       (e.getItem().getItemStack().hasItemMeta()) && (e.getItem().getItemStack().getItemMeta() != null) && 
/* 33 */       (e.getItem().getItemStack().getItemMeta().getDisplayName().equals(Util.Chat(ms.getString("Banner-Blue"))))) {
/* 34 */       if (tm.getBluesMembers().contains(p.getUniqueId())) {
/* 35 */         for (Player pl : ArenaCollectors.getArena(p).getPlayers()) {
/* 36 */           Util.SendInstantTitle(pl, ms.getString("Banner-Blue-Was-PickUp"), ms.getString("By").replace("%player%", p.getCustomName()), 60);
/* 37 */           Util.PlaySound(pl, Sound.valueOf(cg.getString("SoundToPickUpBanner").toUpperCase()));
/*    */         }
/*    */       } else {
/* 40 */         e.setCancelled(true);
/*    */       }
/*    */     }
/*    */     
/* 44 */     if (((e.getEntity() instanceof Player)) && 
/* 45 */       (e.getItem().getItemStack().hasItemMeta()) && (e.getItem().getItemStack().getItemMeta() != null) && 
/* 46 */       (e.getItem().getItemStack().getItemMeta().getDisplayName().equals(Util.Chat(ms.getString("Banner-Red"))))) {
/* 47 */       if (tm.getRedMembers().contains(p.getUniqueId())) {
/* 48 */         for (Player pl : ArenaCollectors.getArena(p).getPlayers()) {
/* 49 */           Util.SendInstantTitle(pl, ms.getString("Banner-Red-Was-PickUp"), ms.getString("By").replace("%player%", p.getCustomName()), 60);
/* 50 */           Util.PlaySound(pl, Sound.valueOf(cg.getString("SoundToPickUpBanner").toUpperCase()));
/*    */         }
/*    */       } else {
/* 53 */         e.setCancelled(true);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Pcs Exabyte\Desktop\Servidor\Village Defense\plugins\UltimateCaptureTheWool.jar!\gamelisteners\PickUpBanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */