package gamelisteners;
import gamemanager.Arena;
import gamemanager.ArenaCollectors;
import gamemanager.ArenaManager;
import gamemanager.TeamManager;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Banner;
import org.bukkit.material.Wool;
import org.bukkit.scheduler.BukkitRunnable;

import ultimate.main.ConfigManager;
import ultimate.main.Ultimate;
import util.Util;

public class MoveOnSpawner implements Listener{
	
    private Arena arena;
   
    private Arena getArena(){
    return this.arena;
    }

    
   @EventHandler
   public void onSpawner(PlayerMoveEvent e) {
     Player p = e.getPlayer();
     Block down = e.getFrom().getBlock().getRelative(BlockFace.DOWN);
     FileConfiguration ms = ConfigManager.getInstance().getMessages();
     String bluename = ms.getString("Wool-Blue");
     String redname = ms.getString("Wool-Blue");
     String aquaname = ms.getString("Wool-Blue");
     String magentaname = ms.getString("Wool-Blue");
     String bannerblue = ms.getString("Banner-Blue");
     String bannerred = ms.getString("Banner-Red");
     TeamManager tm = new TeamManager();
     ArenaManager am = new ArenaManager();
     if (ArenaManager.getArenas() == null && am.isEmpty()) {
    	 return;
    }  try{
     if (getArena().getSpawnerBlue() == null || getArena().getSpawnerAqua() == null || getArena().getSpawnerRed() == null || getArena().getSpawnerMagenta() == null) {
      return;
     }
     }catch(NullPointerException ex){
    	 
     }
     if (tm.getBluesMembers().contains(p.getUniqueId()) && down.getType() == Material.MOB_SPAWNER && down.getLocation().equals(ArenaCollectors.getArena(p).getSpawnerBlue())) {
      ItemStack bluei = new Wool(DyeColor.BLUE).toItemStack(1);
      ItemMeta blue = bluei.getItemMeta();
       blue.setDisplayName(Util.Chat(bluename));
       bluei.setItemMeta(blue);
       
       new BukkitRunnable() {
         public void run()
         {
           down.getLocation().getWorld().dropItemNaturally(down.getLocation().add(0.5D, 0.5D, 0.5D), bluei);
           if (Util.hasThisItem(p, bluei)) {
             cancel();
           }
           
         }
       }.runTaskTimer(Ultimate.getInstance(), 20L, 20L);
     }
     if (tm.getBluesMembers().contains(p.getUniqueId()) && down.getType() == Material.MOB_SPAWNER && down.equals(ArenaCollectors.getArena(p).getSpawnerAqua())) {
       ItemStack aquai = new Wool(DyeColor.LIGHT_BLUE).toItemStack(1);
       ItemMeta aqua = aquai.getItemMeta();
       aqua.setDisplayName(Util.Chat(aquaname));
       aquai.setItemMeta(aqua);
       
       new BukkitRunnable()
      {
        public void run()
         {
           down.getLocation().getWorld().dropItemNaturally(down.getLocation().add(0.5D, 0.5D, 0.5D), aquai);
                      if (Util.hasThisItem(p, aquai)) {
             cancel();
           }           
        }
      }.runTaskTimer(Ultimate.getInstance(), 20L, 20L);
     }     
 

     if ((tm.getRedMembers().contains(p.getUniqueId())) && (down.getType() == Material.MOB_SPAWNER) && (down.equals(ArenaCollectors.getArena(p).getSpawnerRed()))) {
       ItemStack redi = new Wool(DyeColor.RED).toItemStack(1);
      ItemMeta red = redi.getItemMeta();
      red.setDisplayName(Util.Chat(redname));
      redi.setItemMeta(red);
      new BukkitRunnable()
       {
         public void run()
        {
          down.getLocation().getWorld().dropItemNaturally(down.getLocation().add(0.5D, 0.5D, 0.5D), redi);
          if (Util.hasThisItem(p, redi)) {
             cancel();
        }
           
         }
       }.runTaskTimer(Ultimate.getInstance(), 20L, 20L);
     }

 
     if (tm.getRedMembers().contains(p.getUniqueId()) && down.getType() == Material.MOB_SPAWNER && down.equals(ArenaCollectors.getArena(p).getSpawnerMagenta())) {
        ItemStack magi = new Wool(DyeColor.MAGENTA).toItemStack(1);
       ItemMeta mag = magi.getItemMeta();
       mag.setDisplayName(Util.Chat(magentaname));
       magi.setItemMeta(mag);
       new BukkitRunnable()
      {
         public void run()
         {
           down.getLocation().getWorld().dropItemNaturally(down.getLocation().add(0.5D, 0.5D, 0.5D), magi);
           if (Util.hasThisItem(p, magi)) {
             cancel();
           }
                    }
       }.runTaskTimer(Ultimate.getInstance(), 20L, 20L);
     }
     
     if (tm.getRedMembers().contains(p.getUniqueId()) && down.getType() == Material.MOB_SPAWNER && down.equals(ArenaCollectors.getArena(p).getStandRed())) {
         ItemStack magi = new ItemStack(Material.BANNER, 1, (short) 1);
        ItemMeta mag = magi.getItemMeta();
        mag.setDisplayName(Util.Chat(magentaname));
        magi.setItemMeta(mag);
        new BukkitRunnable()
       {
          public void run()
          {
            down.getLocation().getWorld().dropItemNaturally(down.getLocation().add(0.5D, 0.5D, 0.5D), magi);
            if (Util.hasThisItem(p, magi)) {
              cancel();
            }
                     }
        }.runTaskTimer(Ultimate.getInstance(), 20L, 20L);
      }
}
}
