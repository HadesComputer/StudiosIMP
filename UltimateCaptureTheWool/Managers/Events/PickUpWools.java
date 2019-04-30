/*    */ package gamelisteners;
/*    */ 
/*    */ import gamemanager.Arena;
/*    */ import gamemanager.ArenaCollectors;
/*    */ import gamemanager.TeamManager;
/*    */ import java.util.List;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.bukkit.entity.Item;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.entity.EntityPickupItemEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ import ultimate.main.ConfigManager;
/*    */ import ultimate.main.Ultimate;
/*    */ import util.Util;
/*    */ 
/*    */ public class PickUpWools implements org.bukkit.event.Listener
/*    */ {
/*    */   public PickUpWools(Ultimate ultimate) {}
/*    */   
/*    */   @EventHandler
/*    */   public void onPick(EntityPickupItemEvent e)
/*    */   {
/* 26 */     Player p = (Player)e.getEntity();
/* 27 */     FileConfiguration ms = ConfigManager.getInstance().getMessages();
/* 28 */     FileConfiguration cg = Ultimate.getInstance().getConfig();
/* 29 */     TeamManager tm = new TeamManager();
/*    */     
/* 31 */     if (((e.getEntity() instanceof Player)) && 
/* 32 */       (e.getItem().getItemStack().hasItemMeta()) && (e.getItem().getItemStack().getItemMeta() != null) && 
/* 33 */       (e.getItem().getItemStack().getItemMeta().getDisplayName().equals(Util.Chat(ms.getString("Wool-Blue"))))) {
/* 34 */       if (tm.getBluesMembers().contains(p.getUniqueId())) {
/* 35 */         for (Player pl : ArenaCollectors.getArena(p).getPlayers()) {
/* 36 */           Util.SendInstantTitle(pl, ms.getString("Wool-Blue-Was-Pickup"), ms.getString("By").replace("%player%", p.getCustomName()), 60);
/* 37 */           Util.PlaySound(pl, Sound.valueOf(cg.getString("SoundToPickUpWool").toUpperCase()));
/*    */         }
/*    */       } else {
/* 40 */         e.setCancelled(true);
/*    */       }
/*    */     }
/*    */     
/* 44 */     if (((e.getEntity() instanceof Player)) && 
/* 45 */       (e.getItem().getItemStack().hasItemMeta()) && (e.getItem().getItemStack().getItemMeta() != null) && 
/* 46 */       (e.getItem().getItemStack().getItemMeta().getDisplayName().equals(Util.Chat(ms.getString("Wool-Red"))))) {
/* 47 */       if (tm.getRedMembers().contains(p.getUniqueId())) {
/* 48 */         for (Player pl : ArenaCollectors.getArena(p).getPlayers()) {
/* 49 */           Util.SendInstantTitle(pl, ms.getString("Wool-Red-Was-Pickup"), ms.getString("By").replace("%player%", p.getCustomName()), 60);
/* 50 */           Util.PlaySound(pl, Sound.valueOf(cg.getString("SoundToPickUpWool").toUpperCase()));
/*    */         }
/*    */       } else {
/* 53 */         e.setCancelled(true);
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 58 */     if (((e.getEntity() instanceof Player)) && 
/* 59 */       (e.getItem().getItemStack().hasItemMeta()) && (e.getItem().getItemStack().getItemMeta() != null) && 
/* 60 */       (e.getItem().getItemStack().getItemMeta().getDisplayName().equals(Util.Chat(ms.getString("Wool-Magenta"))))) {
/* 61 */       if (tm.getRedMembers().contains(p.getUniqueId())) {
/* 62 */         for (Player pl : ArenaCollectors.getArena(p).getPlayers()) {
/* 63 */           Util.SendInstantTitle(pl, ms.getString("Wool-Magenta-Was-Pickup"), ms.getString("By").replace("%player%", p.getCustomName()), 60);
/* 64 */           Util.PlaySound(pl, Sound.valueOf(cg.getString("SoundToPickUpWool").toUpperCase()));
/*    */         }
/*    */       } else {
/* 67 */         e.setCancelled(true);
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 72 */     if (((e.getEntity() instanceof Player)) && 
/* 73 */       (e.getItem().getItemStack().hasItemMeta()) && (e.getItem().getItemStack().getItemMeta() != null) && 
/* 74 */       (e.getItem().getItemStack().getItemMeta().getDisplayName().equals(Util.Chat(ms.getString("Wool-Aqua"))))) {
/* 75 */       if (tm.getBluesMembers().contains(p.getUniqueId()))
/*    */       {
/* 77 */         for (Player pl : ArenaCollectors.getArena(p).getPlayers()) {
/* 78 */           Util.SendInstantTitle(pl, ms.getString("Wool-Aqua-Was-Pickup"), ms.getString("By").replace("%player%", p.getCustomName()), 60);
/* 79 */           Util.PlaySound(pl, Sound.valueOf(cg.getString("SoundToPickUpWool").toUpperCase()));
/*    */         }
/*    */       } else {
/* 82 */         e.setCancelled(true);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Pcs Exabyte\Desktop\Servidor\Village Defense\plugins\UltimateCaptureTheWool.jar!\gamelisteners\PickUpWools.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */