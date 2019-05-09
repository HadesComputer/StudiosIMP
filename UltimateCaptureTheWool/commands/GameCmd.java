   package commands;
   import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import gamemanager.Arena;
import gamemanager.ArenaManager;
import menus.Enchantments;
import menus.GunsMenu;
import menus.MenuGUI;
import menus.Trash;

   import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import ultimate.main.ConfigManager;
import ultimate.main.Ultimate;
import util.Util;
   
   
   
   
public class GameCmd implements CommandExecutor{
	   
     public GameCmd(Ultimate ultimate) {}
     
     
     @Override
     public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
     {
     FileConfiguration messages = ConfigManager.getInstance().getMessages();
     FileConfiguration config = Ultimate.getInstance().getConfig();
     if (!(sender instanceof Player))
       {
      Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("Console-Dont-Use-this-cmd")));
       } else {
       Player p = (Player)sender;
       GameMode mode = p.getGameMode();
       if ((mode.equals(GameMode.ADVENTURE)) || (mode.equals(GameMode.CREATIVE)) || (mode.equals(GameMode.SURVIVAL))) {
         if ((cmd.getName().equalsIgnoreCase("ultimatecapturethewool")) || (cmd.getName().equalsIgnoreCase("uctw"))) {
           if (args[0].equalsIgnoreCase("enchantments")) {
             p.playSound(p.getLocation(), Sound.valueOf(config.getString("SoundToOpenEnchantments")), 1.0F, 1.0F);
             p.openInventory(Enchantments.EnchantmentsGUI(p));
             }
           if (args[0].equalsIgnoreCase("trash")) {
             p.playSound(p.getLocation(), Sound.valueOf(config.getString("SoundToOpenTrash")), 1.0F, 1.0F);
             p.openInventory(Trash.TrashGUI(p));
             }
           if (args[0].equalsIgnoreCase("guns")) {
             p.playSound(p.getLocation(), Sound.valueOf(config.getString("SoundToOpenGuns")), 1.0F, 1.0F);
             p.openInventory(GunsMenu.GunsGUI(p));
             }
             
           if (args[0].equalsIgnoreCase("menu")) {
             p.playSound(p.getLocation(), Sound.valueOf(config.getString("SoundsToOpenMenu")), 1.0F, 1.0F);
             p.openInventory(MenuGUI.Menu(p));
             }
             
           if (args[0].equalsIgnoreCase("join")) {
               try {
               String name = args[1];
               ArenaManager am = new ArenaManager();
                   am.getArena(name).addPlayer(p);
               if (!am.exists(name)){
               p.sendMessage(Util.Chat(messages.getString("Already-Exists")));
                       }
               }
               catch (StringIndexOutOfBoundsException e) {
               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("Invalid-Args")));
               }
               
             }

               if(args[0].equalsIgnoreCase("randomjoin")){
            	   try{
            	   ArenaManager am = new ArenaManager();
            	   HashMap<String, Arena> arenas = ArenaManager.getArenas();
            	   List<String> strings = Lists.newArrayList(arenas.keySet());
            	   String arena = strings.get(ThreadLocalRandom.current().nextInt(strings.size()));
            	   if(am.getByPlayer(p)){
              		 p.sendMessage(Util.Chat(messages.getString("You-Has-In-Arena")));
            	   }else{
                   am.getArena(arena).addPlayer(p);
            	   }
            	   }catch(StringIndexOutOfBoundsException e){
            		   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("Invalid-Args")));
            	   }
               }
               
               if(args[0].equalsIgnoreCase("leave")){
            	   ArenaManager am = new ArenaManager();
            	   if(am.getByPlayer(p)){
              		 p.sendMessage(Util.Chat(messages.getString("You-Dont-Has-In-Arena")));
            	   }else{
                	 am.getArena(p).leave(p); 
            	   }
               }
           }
         } else {
         p.sendMessage(Util.Chat(messages.getString("Spectator-Dont-Use-cmd")));
         }
       return true;
       }
     return false;
     }
   }


