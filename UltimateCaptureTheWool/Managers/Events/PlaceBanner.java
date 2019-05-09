package gamelisteners;
import gamemanager.ArenaManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import ultimate.main.ConfigManager;
import ultimate.main.Ultimate;
import util.Util;
   
public class PlaceBanner implements Listener{
	
     public PlaceBanner(Ultimate ultimate) {}
     
     @EventHandler
     public void OnPlaceWool(BlockPlaceEvent e)
     {
  	   ArenaManager am = new ArenaManager();
/* 29 */     Player p = e.getPlayer();
/* 30 */     FileConfiguration ms = ConfigManager.getInstance().getMessages();
/* 31 */     FileConfiguration cg = Ultimate.getInstance().getConfig();
/* 32 */     String bluename = ms.getString("Banner-Blue");
/* 33 */     String redname = ms.getString("Banner-Red");
/* 34 */     ItemStack blue = new ItemStack(Material.BANNER, 11);
/* 35 */     ItemStack red = new ItemStack(Material.BANNER, 14);
/* 37 */     ItemStack hand = p.getInventory().getItemInMainHand();
/* 38 */     if ((e.getBlock().equals(blue)) && (hand.getType() != null) && (hand.getType().equals(blue)) && (hand.getItemMeta() != null) && (hand.getItemMeta().getDisplayName().equals(Util.Chat(bluename))) && 
/* 39 */       (e.getBlock().getLocation().equals(am.getArena(p).getWinStandBlue()))) {
/* 40 */       if (am.getArena(p).getBluesMembers().contains(p.getUniqueId()))
         {
/* 42 */         for (Player pl : Bukkit.getOnlinePlayers()) {
/* 43 */           Util.SendInstantTitle(pl, ms.getString("Banner-Blue-Was-Placed"), ms.getString("By").replace("%player%", e.getPlayer().getCustomName()), 40);
/* 44 */           Util.PlaySound(pl, Sound.valueOf(cg.getString("SoundToPlaceBanner").toUpperCase()));
           }
         } else {
/* 47 */         e.setCancelled(true);
         }
       }
       
   
   
   
/* 54 */     if ((e.getBlock().equals(red)) && (hand.getType() != null) && (hand.getType().equals(red)) && (hand.getItemMeta() != null) && (hand.getItemMeta().getDisplayName().equals(Util.Chat(redname))) && 
/* 55 */       (e.getBlock().getLocation().equals(am.getArena(p).getWinStandRed()))) {
/* 56 */       if (am.getArena(p).getRedMembers().contains(p.getUniqueId()))
         {
/* 58 */         for (Player pl : Bukkit.getOnlinePlayers()) {
/* 59 */           Util.SendInstantTitle(pl, ms.getString("Banner-Red-Was-Placed"), ms.getString("By").replace("%player%", e.getPlayer().getCustomName()), 40);
/* 60 */           Util.PlaySound(pl, Sound.valueOf(cg.getString("SoundToPlaceBanner").toUpperCase()));
           }
         } else {
/* 63 */         e.setCancelled(true);
         }
       }
     }
   }
