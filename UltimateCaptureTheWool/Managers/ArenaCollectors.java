/*    */ package gamemanager;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ import ultimate.main.Ultimate;
/*    */ 
/*    */ 
/*    */ public class ArenaCollectors
/*    */ {
/* 14 */   private static Ultimate plugin = (Ultimate)JavaPlugin.getPlugin(Ultimate.class);
/* 15 */   private static List<Arena> arenas = new ArrayList<>();
/*    */   
/*    */ 
/*    */   public static Arena getArena(Player p)
/*    */   {
/* 20 */     if ((p == null) || (!p.isOnline())) {
/* 21 */       Iterator<Arena> var1 = arenas.iterator();
/* 22 */       Iterator<?> var2; for (; var1.hasNext(); 
/*    */           
/*    */ 
/* 25 */           var2.hasNext())
/*    */       {
/* 23 */         Arena arena = (Arena)var1.next();
/* 24 */         var2 = arena.getPlayers().iterator();
/* 26 */         Player plajer = (Player)var2.next();
/* 27 */         if (plajer.getUniqueId().equals(p.getUniqueId())) {
/* 28 */           return arena;
/*    */         }
/*    */       }
/*    */     }
/*    */     else {
/* 33 */       return null;
/*    */     }
/* 35 */     return null;
/*    */   }
/*    */   
/*    */   public static Arena getArena(String name)
/*    */   {
/* 40 */     Arena arena = null;
/* 41 */     Iterator<Arena> var1 = arenas.iterator();
/* 42 */     while (var1.hasNext()) {
/* 43 */       Arena lops = (Arena)var1.next();
/* 44 */       if (lops.getName().equalsIgnoreCase(name)) {
/* 45 */         arena = lops;
/* 46 */         break;
/*    */       }
/*    */     }
/* 49 */     return arena;
/*    */   }
/*    */   
/*    */ 
/*    */   public static void registerArenas()
/*    */   {
/* 55 */     getArenas().isEmpty();
/*    */   }
/*    */   
/*    */ 
/*    */   public static List<Arena> getArenas()
/*    */   {
/* 61 */     return arenas;
/*    */   }
/*    */ }


/* Location:              C:\Users\Pcs Exabyte\Desktop\Servidor\Village Defense\plugins\UltimateCaptureTheWool.jar!\gamemanager\ArenaCollectors.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */