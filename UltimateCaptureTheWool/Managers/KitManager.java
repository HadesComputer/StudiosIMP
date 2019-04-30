/*    */ package gamemanager;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class KitManager
/*    */ {
/* 16 */   private static List<KitManager> kits = new ArrayList();
/*    */   
/* 18 */   private static HashMap<String, KitManager> playerkits = new HashMap();
/*    */   
/* 20 */   private static List<ItemStack> items = new ArrayList();
/*    */   
/* 22 */   private static List<ItemStack> helmet = new ArrayList();
/*    */   
/*    */   private String displayname;
/*    */   
/*    */   private String name;
/*    */   
/*    */   private String permission;
/*    */   private ItemStack displayitem;
/*    */   
/*    */   public KitManager(Player p, String name, String permission, String DisplayName, ItemStack displayitem, HashMap<ItemStack, Inventory> items)
/*    */   {
/* 33 */     this.permission = ("uctw.kit" + name);
/* 34 */     this.name = name;
/* 35 */     this.displayitem = displayitem;
/*    */   }
/*    */ }


/* Location:              C:\Users\Pcs Exabyte\Desktop\Servidor\Village Defense\plugins\UltimateCaptureTheWool.jar!\gamemanager\KitManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */