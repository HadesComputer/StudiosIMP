package gamelisteners;
import gamemanager.Arena;
import gamemanager.ArenaCollectors;
import gamemanager.TeamManager;
import org.bukkit.DyeColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Wool;
import ultimate.main.ConfigManager;
import ultimate.main.Ultimate;
import util.Util;

public class PlaceWool implements Listener{

	public PlaceWool(Ultimate ultimate){
	
    }
   
   @EventHandler
   public void OnPlaceWool(BlockPlaceEvent e){
     Player p = e.getPlayer();
     Arena arena = ArenaCollectors.getArena(p);
     FileConfiguration ms = ConfigManager.getInstance().getMessages();
     FileConfiguration cg = Ultimate.getInstance().getConfig();
     String bluename = ms.getString("Wool-Blue");
     String redname = ms.getString("Wool-Red");
     String aquaname = ms.getString("Wool-Aqua");
     String magentaname = ms.getString("Wool-Magenta");
     ItemStack blue = new Wool(DyeColor.BLUE).toItemStack(1);
     ItemStack red = new Wool(DyeColor.RED).toItemStack(1);
     ItemStack aqua = new Wool(DyeColor.LIGHT_BLUE).toItemStack(1);
     ItemStack magenta = new Wool(DyeColor.MAGENTA).toItemStack(1);
     TeamManager tm = new TeamManager();
     ItemStack hand = p.getInventory().getItemInMainHand();
     if (e.getBlock().equals(blue) && hand.getType() != null && hand.getType().equals(blue) && hand.getItemMeta() != null && hand.getItemMeta().getDisplayName().equals(Util.Chat(bluename)) && 
       e.getBlock().getLocation().equals(arena.getWinBlue())) {
       if (tm.getBluesMembers().contains(p.getUniqueId()))
       {
         for (Player pl : arena.getPlayers()) {
        	arena.addBluePoint();
           Util.SendInstantTitle(pl, ms.getString("Was-Placed-The-Wool-Blue"), ms.getString("By").replace("%player%", e.getPlayer().getCustomName()), 40);
           Util.PlaySound(pl, Sound.valueOf(cg.getString("SoundToPlaceWool").toUpperCase()));
         }
       } else {
         e.setCancelled(true);
       }
     }
     
 
     if (e.getBlock().equals(aqua) && hand.getType() != null && hand.getType().equals(aqua) && hand.getItemMeta() != null && hand.getItemMeta().getDisplayName().equals(Util.Chat(aquaname)) && 
       e.getBlock().getLocation().equals(arena.getWinAqua())) {
       if (tm.getBluesMembers().contains(p.getUniqueId()))
       {
         for (Player pl : arena.getPlayers()) {
        	 arena.addBluePoint();
           Util.SendInstantTitle(pl, ms.getString("Was-Placed-The-Wool-Aqua"), ms.getString("By").replace("%player%", e.getPlayer().getCustomName()), 40);
           Util.PlaySound(pl, Sound.valueOf(cg.getString("SoundToPlaceWool").toUpperCase()));
         }
       } else {
	     e.setCancelled(true);
       }
     }
     
 
 
     if (e.getBlock().equals(red) && hand.getType() != null && hand.getType().equals(red) && hand.getItemMeta() != null && hand.getItemMeta().getDisplayName().equals(Util.Chat(redname)) && 
       e.getBlock().getLocation().equals(arena.getWinRed())) {
       if (tm.getRedMembers().contains(p.getUniqueId()))
       {
         for (Player pl : arena.getPlayers()) {
        	 arena.addRedPoint();
           Util.SendInstantTitle(pl, ms.getString("Was-Placed-The-Wool-Red"), ms.getString("By").replace("%player%", e.getPlayer().getCustomName()), 40);
           Util.PlaySound(pl, Sound.valueOf(cg.getString("SoundToPlaceWool").toUpperCase()));
         }
       } else {
         e.setCancelled(true);
       }
     }
       
 
     if (e.getBlock().equals(magenta) && hand.getType() != null && hand.getType().equals(magenta) && hand.getItemMeta() != null && hand.getItemMeta().getDisplayName().equals(Util.Chat(magentaname)) && 
       e.getBlock().getLocation().equals(arena.getWinMagenta())) {
       if (tm.getRedMembers().contains(p.getUniqueId()))
       {
         for (Player pl : arena.getPlayers()) {
        	 arena.addRedPoint();
           Util.SendInstantTitle(pl, ms.getString("Was-Placed-The-Wool-Magenta"), ms.getString("By").replace("%player%", e.getPlayer().getCustomName()), 40);
           Util.PlaySound(pl, Sound.valueOf(cg.getString("SoundToPlaceWool").toUpperCase()));
         }
       } else {
         e.setCancelled(true);
       }
     }
   }
 }
