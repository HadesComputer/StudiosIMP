package commands;

import gamemanager.ArenaManager;
import gamemanager.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import ultimate.main.ConfigManager;
import ultimate.main.Ultimate;
import util.Util;
public class Arenacmd implements CommandExecutor{
	
	
  public Arenacmd(Ultimate ultimate) {
	
  }
       @Override
   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
     {
     FileConfiguration mes = ConfigManager.getInstance().getMessages();
     ArenaManager am = new ArenaManager();
     FileConfiguration fm = ConfigManager.getInstance().getArenas();
     if (!(sender instanceof Player))
       {
       Bukkit.getConsoleSender().sendMessage(Util.Chat(mes.getString("Console-Dont-Use-this-cmd")));
       } else {
       Player p = (Player)sender;
       ConfigurationSection arenas = fm.getConfigurationSection("arenas");
       if (arenas == null) {
         System.out.print("arenas has null");
         }
       if ((cmd.getName().equalsIgnoreCase("ultimatecapturethewoolmap")) || (cmd.getName().equalsIgnoreCase("uctwm"))) {
         if (args[0].equalsIgnoreCase("create")) {
             if (args.length > 3) {
                 p.sendMessage(Util.Chat(mes.getString("Too-Much-args")));
                 }
               if (args.length < 3) {
                 p.sendMessage(Util.Chat(mes.getString("Too-Many-args")));
                 }
                    WorldManager wm = new WorldManager();
            if(args[2].equalsIgnoreCase("normal")){      
            	try{
                    String name = args[1];
                    if (am.exists(name)) {
                      p.sendMessage(Util.Chat(mes.getString("Already-Exists")));
                      }
                    am.registerArena(name, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 30, 2, 10, null, null, null, null, null, null);
                     p.sendMessage(Util.Chat(mes.getString("Created").replace("%arena%", am.getArena(name).getName())));
                        Environment enviroment = Environment.NORMAL;

             ConfigurationSection arena = arenas.createSection(name);
               arena.set("name", name);
               arena.set("worldtype", "NORMAL");
               arena.set("prefix", name);
               arena.set("world", name);
               arena.set("countdown", Integer.valueOf(45));
               arena.set("minplayers", Integer.valueOf(2));
               arena.set("maxplayers", Integer.valueOf(10));
               arena.set("spawnblue.x", Integer.valueOf(0));
               arena.set("spawnblue.y", Integer.valueOf(0));
               arena.set("spawnblue.z", Integer.valueOf(0));
               arena.set("spawnblue.yaw", Integer.valueOf(0));
               arena.set("spawnblue.pitch", Integer.valueOf(0));
               arena.set("spawnred.x", Integer.valueOf(0));
               arena.set("spawnred.y", Integer.valueOf(0));
               arena.set("spawnred.z", Integer.valueOf(0));
               arena.set("spawnred.yaw", Integer.valueOf(0));
               arena.set("spawnred.pitch", Integer.valueOf(0));
               arena.set("spawnspectator.x", Integer.valueOf(0));
               arena.set("spawnspectator.y", Integer.valueOf(0));
               arena.set("spawnspectator.z", Integer.valueOf(0));
               arena.set("spawnspectator.yaw", Integer.valueOf(0));
               arena.set("spawnspectator.pitch", Integer.valueOf(0));
               arena.set("lobby.world", name);
               arena.set("lobby.x", Integer.valueOf(0));
               arena.set("lobby.y", Integer.valueOf(0));
               arena.set("lobby.z", Integer.valueOf(0));
               arena.set("lobby.yaw", Integer.valueOf(0));
               arena.set("lobby.pitch", Integer.valueOf(0));
               arena.set("mainlobby.world", name);
               arena.set("mainlobby.x", Integer.valueOf(0));
               arena.set("mainlobby.y", Integer.valueOf(0));
               arena.set("mainlobby.z", Integer.valueOf(0));
               arena.set("mainlobby.yaw", Integer.valueOf(0));
               arena.set("mainlobby.pitch", Integer.valueOf(0));
               arena.set("winpositionblue.x", Integer.valueOf(0));
               arena.set("winpositionblue.y", Integer.valueOf(0));
               arena.set("winpositionblue.z", Integer.valueOf(0));
               arena.set("winpositionred.x", Integer.valueOf(0));
               arena.set("winpositionred.y", Integer.valueOf(0));
               arena.set("winpositionred.z", Integer.valueOf(0));
               arena.set("winpositionaqua.x", Integer.valueOf(0));
               arena.set("winpositionaqua.y", Integer.valueOf(0));
               arena.set("winpositionaqua.z", Integer.valueOf(0));
               arena.set("winpositionmagenta.x", Integer.valueOf(0));
               arena.set("winpositionmagenta.y", Integer.valueOf(0));
               arena.set("winpositionmagenta.z", Integer.valueOf(0));
               arena.set("spawnerred.x", Integer.valueOf(0));
               arena.set("spawnerred.y", Integer.valueOf(0));
               arena.set("spawnerred.z", Integer.valueOf(0));
               arena.set("spawnerblue.x", Integer.valueOf(0));
               arena.set("spawnerblue.y", Integer.valueOf(0));
               arena.set("spawnerblue.z", Integer.valueOf(0));
               arena.set("spawnermagenta.x", Integer.valueOf(0));
               arena.set("spawnermagenta.y", Integer.valueOf(0));
               arena.set("spawnermagenta.z", Integer.valueOf(0));
               arena.set("spawneraqua.x", Integer.valueOf(0));
               arena.set("spawneraqua.y", Integer.valueOf(0));
               arena.set("spawneraqua.z", Integer.valueOf(0));
               arena.set("bannerblue.x", Integer.valueOf(0));
               arena.set("bannerblue.y", Integer.valueOf(0));
               arena.set("bannerblue.z", Integer.valueOf(0));
               arena.set("bannerred.x", Integer.valueOf(0));
               arena.set("bannerred.y", Integer.valueOf(0));
               arena.set("bannerred.z", Integer.valueOf(0));
               arena.set("sign.x", Integer.valueOf(0));
               arena.set("sign.y", Integer.valueOf(0));
               arena.set("sign.z", Integer.valueOf(0));
               arena.set("sign.world", name);
               arena.set("winpositionbannerblue.x", Integer.valueOf(0));
               arena.set("winpositionbannerblue.y", Integer.valueOf(0));
               arena.set("winpositionbannerblue.z", Integer.valueOf(0));
               arena.set("winpositionbannerred.x", Integer.valueOf(0));
               arena.set("winpositionbannerred.y", Integer.valueOf(0));
               arena.set("winpositionbannerred.z", Integer.valueOf(0));
             ConfigManager.getInstance().SaveArenas(); 
             wm.CreateEmptyWorld(name, enviroment);
             wm.loadWorld(name, enviroment);
             Location loc = new Location(Bukkit.getWorld(arena.getString("world")), 15.0,10.0,15.0);
             p.teleport(loc);
             p.setGameMode(GameMode.CREATIVE);
                }catch(StringIndexOutOfBoundsException  e){
                    p.sendMessage(Util.Chat(mes.getString("Invalid-Args")));
                }
            }
            if(args[2].equalsIgnoreCase("nether")){  
            	try{
                    String name = args[1];
                    if (am.exists(name)) {
                      p.sendMessage(Util.Chat(mes.getString("Already-Exists")));
                      }
                    am.registerArena(name, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 30, 2, 10, null, null, null, null, null, null);
                     p.sendMessage(Util.Chat(mes.getString("Created").replace("%arena%", am.getArena(name).getName())));
                Environment enviroment = Environment.NETHER;

     ConfigurationSection arena = arenas.createSection(name);
     arena.set("name", name);
     arena.set("worldtype", "NETHER");
     arena.set("prefix", name);
     arena.set("world", name);
     arena.set("countdown", Integer.valueOf(45));
     arena.set("minplayers", Integer.valueOf(2));
     arena.set("maxplayers", Integer.valueOf(10));
     arena.set("spawnblue.x", Integer.valueOf(0));
     arena.set("spawnblue.y", Integer.valueOf(0));
     arena.set("spawnblue.z", Integer.valueOf(0));
     arena.set("spawnblue.yaw", Integer.valueOf(0));
     arena.set("spawnblue.pitch", Integer.valueOf(0));
     arena.set("spawnred.x", Integer.valueOf(0));
     arena.set("spawnred.y", Integer.valueOf(0));
     arena.set("spawnred.z", Integer.valueOf(0));
     arena.set("spawnred.yaw", Integer.valueOf(0));
     arena.set("spawnred.pitch", Integer.valueOf(0));
     arena.set("spawnspectator.x", Integer.valueOf(0));
     arena.set("spawnspectator.y", Integer.valueOf(0));
     arena.set("spawnspectator.z", Integer.valueOf(0));
     arena.set("spawnspectator.yaw", Integer.valueOf(0));
     arena.set("spawnspectator.pitch", Integer.valueOf(0));
     arena.set("lobby.world", name);
     arena.set("lobby.x", Integer.valueOf(0));
     arena.set("lobby.y", Integer.valueOf(0));
     arena.set("lobby.z", Integer.valueOf(0));
     arena.set("lobby.yaw", Integer.valueOf(0));
     arena.set("lobby.pitch", Integer.valueOf(0));
     arena.set("mainlobby.world", name);
     arena.set("mainlobby.x", Integer.valueOf(0));
     arena.set("mainlobby.y", Integer.valueOf(0));
     arena.set("mainlobby.z", Integer.valueOf(0));
     arena.set("mainlobby.yaw", Integer.valueOf(0));
     arena.set("mainlobby.pitch", Integer.valueOf(0));
     arena.set("winpositionblue.x", Integer.valueOf(0));
     arena.set("winpositionblue.y", Integer.valueOf(0));
     arena.set("winpositionblue.z", Integer.valueOf(0));
     arena.set("winpositionred.x", Integer.valueOf(0));
     arena.set("winpositionred.y", Integer.valueOf(0));
     arena.set("winpositionred.z", Integer.valueOf(0));
     arena.set("winpositionaqua.x", Integer.valueOf(0));
     arena.set("winpositionaqua.y", Integer.valueOf(0));
     arena.set("winpositionaqua.z", Integer.valueOf(0));
     arena.set("winpositionmagenta.x", Integer.valueOf(0));
     arena.set("winpositionmagenta.y", Integer.valueOf(0));
     arena.set("winpositionmagenta.z", Integer.valueOf(0));
     arena.set("spawnerred.x", Integer.valueOf(0));
     arena.set("spawnerred.y", Integer.valueOf(0));
     arena.set("spawnerred.z", Integer.valueOf(0));
     arena.set("spawnerblue.x", Integer.valueOf(0));
     arena.set("spawnerblue.y", Integer.valueOf(0));
     arena.set("spawnerblue.z", Integer.valueOf(0));
     arena.set("spawnermagenta.x", Integer.valueOf(0));
     arena.set("spawnermagenta.y", Integer.valueOf(0));
     arena.set("spawnermagenta.z", Integer.valueOf(0));
     arena.set("spawneraqua.x", Integer.valueOf(0));
     arena.set("spawneraqua.y", Integer.valueOf(0));
     arena.set("spawneraqua.z", Integer.valueOf(0));
     arena.set("bannerblue.x", Integer.valueOf(0));
     arena.set("bannerblue.y", Integer.valueOf(0));
     arena.set("bannerblue.z", Integer.valueOf(0));
     arena.set("bannerred.x", Integer.valueOf(0));
     arena.set("bannerred.y", Integer.valueOf(0));
     arena.set("bannerred.z", Integer.valueOf(0));
     arena.set("sign.x", Integer.valueOf(0));
     arena.set("sign.y", Integer.valueOf(0));
     arena.set("sign.z", Integer.valueOf(0));
     arena.set("sign.world", name);
     arena.set("winpositionbannerblue.x", Integer.valueOf(0));
     arena.set("winpositionbannerblue.y", Integer.valueOf(0));
     arena.set("winpositionbannerblue.z", Integer.valueOf(0));
     arena.set("winpositionbannerred.x", Integer.valueOf(0));
     arena.set("winpositionbannerred.y", Integer.valueOf(0));
     arena.set("winpositionbannerred.z", Integer.valueOf(0));
     ConfigManager.getInstance().SaveArenas(); 
     wm.CreateEmptyWorld(name, enviroment);
     wm.loadWorld(name, enviroment);
     Location loc = new Location(Bukkit.getWorld(arena.getString("world")), 15.0,10.0,15.0);
     p.teleport(loc);
     p.setGameMode(GameMode.CREATIVE);
            	}catch(StringIndexOutOfBoundsException e){
                    p.sendMessage(Util.Chat(mes.getString("Invalid-Args")));
            	}
    }
            
            if(args[2].equalsIgnoreCase("end")){      
            	try{
                    String name = args[1];
                    if (am.exists(name)) {
                      p.sendMessage(Util.Chat(mes.getString("Already-Exists")));
                      }
                    am.registerArena(name, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 30, 2, 10, null, null, null, null, null, null);
                     p.sendMessage(Util.Chat(mes.getString("Created").replace("%arena%", am.getArena(name).getName())));
                Environment enviroment = Environment.THE_END;

     ConfigurationSection arena = arenas.createSection(name);
     arena.set("name", name);
     arena.set("worldtype", "THE_END");
     arena.set("prefix", name);
     arena.set("world", name);
     arena.set("countdown", Integer.valueOf(45));
     arena.set("minplayers", Integer.valueOf(2));
     arena.set("maxplayers", Integer.valueOf(10));
     arena.set("spawnblue.x", Integer.valueOf(0));
     arena.set("spawnblue.y", Integer.valueOf(0));
     arena.set("spawnblue.z", Integer.valueOf(0));
     arena.set("spawnblue.yaw", Integer.valueOf(0));
     arena.set("spawnblue.pitch", Integer.valueOf(0));
     arena.set("spawnred.x", Integer.valueOf(0));
     arena.set("spawnred.y", Integer.valueOf(0));
     arena.set("spawnred.z", Integer.valueOf(0));
     arena.set("spawnred.yaw", Integer.valueOf(0));
     arena.set("spawnred.pitch", Integer.valueOf(0));
     arena.set("spawnspectator.x", Integer.valueOf(0));
     arena.set("spawnspectator.y", Integer.valueOf(0));
     arena.set("spawnspectator.z", Integer.valueOf(0));
     arena.set("spawnspectator.yaw", Integer.valueOf(0));
     arena.set("spawnspectator.pitch", Integer.valueOf(0));
     arena.set("lobby.world", name);
     arena.set("lobby.x", Integer.valueOf(0));
     arena.set("lobby.y", Integer.valueOf(0));
     arena.set("lobby.z", Integer.valueOf(0));
     arena.set("lobby.yaw", Integer.valueOf(0));
     arena.set("lobby.pitch", Integer.valueOf(0));
     arena.set("mainlobby.world", name);
     arena.set("mainlobby.x", Integer.valueOf(0));
     arena.set("mainlobby.y", Integer.valueOf(0));
     arena.set("mainlobby.z", Integer.valueOf(0));
     arena.set("mainlobby.yaw", Integer.valueOf(0));
     arena.set("mainlobby.pitch", Integer.valueOf(0));
     arena.set("winpositionblue.x", Integer.valueOf(0));
     arena.set("winpositionblue.y", Integer.valueOf(0));
     arena.set("winpositionblue.z", Integer.valueOf(0));
     arena.set("winpositionred.x", Integer.valueOf(0));
     arena.set("winpositionred.y", Integer.valueOf(0));
     arena.set("winpositionred.z", Integer.valueOf(0));
     arena.set("winpositionaqua.x", Integer.valueOf(0));
     arena.set("winpositionaqua.y", Integer.valueOf(0));
     arena.set("winpositionaqua.z", Integer.valueOf(0));
     arena.set("winpositionmagenta.x", Integer.valueOf(0));
     arena.set("winpositionmagenta.y", Integer.valueOf(0));
     arena.set("winpositionmagenta.z", Integer.valueOf(0));
     arena.set("spawnerred.x", Integer.valueOf(0));
     arena.set("spawnerred.y", Integer.valueOf(0));
     arena.set("spawnerred.z", Integer.valueOf(0));
     arena.set("spawnerblue.x", Integer.valueOf(0));
     arena.set("spawnerblue.y", Integer.valueOf(0));
     arena.set("spawnerblue.z", Integer.valueOf(0));
     arena.set("spawnermagenta.x", Integer.valueOf(0));
     arena.set("spawnermagenta.y", Integer.valueOf(0));
     arena.set("spawnermagenta.z", Integer.valueOf(0));
     arena.set("spawneraqua.x", Integer.valueOf(0));
     arena.set("spawneraqua.y", Integer.valueOf(0));
     arena.set("spawneraqua.z", Integer.valueOf(0));
     arena.set("bannerblue.x", Integer.valueOf(0));
     arena.set("bannerblue.y", Integer.valueOf(0));
     arena.set("bannerblue.z", Integer.valueOf(0));
     arena.set("bannerred.x", Integer.valueOf(0));
     arena.set("bannerred.y", Integer.valueOf(0));
     arena.set("bannerred.z", Integer.valueOf(0));
     arena.set("sign.x", Integer.valueOf(0));
     arena.set("sign.y", Integer.valueOf(0));
     arena.set("sign.z", Integer.valueOf(0));
     arena.set("sign.world", name);
     arena.set("winpositionbannerblue.x", Integer.valueOf(0));
     arena.set("winpositionbannerblue.y", Integer.valueOf(0));
     arena.set("winpositionbannerblue.z", Integer.valueOf(0));
     arena.set("winpositionbannerred.x", Integer.valueOf(0));
     arena.set("winpositionbannerred.y", Integer.valueOf(0));
     arena.set("winpositionbannerred.z", Integer.valueOf(0));
     ConfigManager.getInstance().SaveArenas(); 
     wm.CreateEmptyWorld(name, enviroment);
     wm.loadWorld(name, enviroment);
     Location loc = new Location(Bukkit.getWorld(arena.getString("world")), 15.0,10.0,15.0);
     p.teleport(loc);
     p.setGameMode(GameMode.CREATIVE);
            	}catch(StringIndexOutOfBoundsException e){
                    p.sendMessage(Util.Chat(mes.getString("Invalid-Args")));
            	}
    }
           
       }    
         if (args[0].equalsIgnoreCase("remove")) {
           if (args.length > 1) {
             p.sendMessage(Util.Chat(mes.getString("Too-Much-args")));
             }
           if (args.length < 1) {
             p.sendMessage(Util.Chat(mes.getString("Too-Many-args")));
             }
           String name = args[1];
           if (!am.exists(name)) {
             p.sendMessage(Util.Chat(mes.getString("Not-Exists")));
             }
             
           p.sendMessage(Util.Chat(mes.getString("Removed").replace("%arena%", am.getArena(name).getName())));
           am.remove(name);
           }
           
         if (args[0].equalsIgnoreCase("set"))
           {
           if (args[1].equalsIgnoreCase("lobby")) {
             if (args.length > 3) {
               p.sendMessage(Util.Chat(mes.getString("Too-Much-args")));
               }
             if (args.length < 3) {
               p.sendMessage(Util.Chat(mes.getString("Too-Many-args")));
               }
               try {
               String name = args[2];
               if (!am.exists(name)) {
                 p.sendMessage(Util.Chat(mes.getString("Not-Exists")));
                }
                        ConfigurationSection sec = arenas.getConfigurationSection(name);
               am.getArena(name).setLobbyLocation(p.getLocation());
               sec.set("lobby.world", String.valueOf(p.getLocation().getWorld().getName()));
               sec.set("lobby.x", Double.valueOf(p.getLocation().getX()));
               sec.set("lobby.y", Double.valueOf(p.getLocation().getY()));
               sec.set("lobby.z", Double.valueOf(p.getLocation().getZ()));
               sec.set("lobby.yaw", Float.valueOf(p.getLocation().getYaw()));
               sec.set("lobby.pitch", Float.valueOf(p.getLocation().getPitch()));
               ConfigManager.getInstance().SaveArenas(); 
               p.sendMessage(Util.Chat(mes.getString("Set-Lobby").replace("%arena%", am.getArena(name).getName())));
               } catch (StringIndexOutOfBoundsException e) {
               p.sendMessage(Util.Chat(mes.getString("Invalid-Args")));
               }
             }
           if (args[1].equalsIgnoreCase("mainlobby")) {
             if (args.length > 3) {
               p.sendMessage(Util.Chat(mes.getString("Too-Much-args")));
               }
             if (args.length < 3) {
               p.sendMessage(Util.Chat(mes.getString("Too-Many-args")));
               }
               try {
               String name = args[3];
               if (!am.exists(name)) {
                 p.sendMessage(Util.Chat(mes.getString("Not-Exists")));
                 }
                 
                        am.getArena(name).setMainLobby(p.getLocation());
                        ConfigurationSection sec = arenas.getConfigurationSection(name);
                        sec.set("mainlobby.world", String.valueOf(p.getLocation().getWorld().getName()));
                        sec.set("mainlobby.x", Double.valueOf(p.getLocation().getX()));
                        sec.set("mainlobby.y", Double.valueOf(p.getLocation().getY()));
                        sec.set("mainlobby.z", Double.valueOf(p.getLocation().getZ()));
                        sec.set("mainlobby.yaw", Float.valueOf(p.getLocation().getYaw()));
                        sec.set("mainlobby.pitch", Float.valueOf(p.getLocation().getPitch()));
                        ConfigManager.getInstance().SaveArenas(); 
               p.sendMessage(Util.Chat(mes.getString("Set-Main-Lobby").replace("%arena%", am.getArena(name).getName())));
               } catch (StringIndexOutOfBoundsException e) {
               p.sendMessage(Util.Chat(mes.getString("Invalid-Args")));
               }
             }
           if (args[1].equalsIgnoreCase("spawn")) {
             if (args[2].equalsIgnoreCase("blue")) {
               if (args.length > 3) {
                 p.sendMessage(Util.Chat(mes.getString("Too-Much-args")));
                 }
               if (args.length < 3) {
                 p.sendMessage(Util.Chat(mes.getString("Too-Many-args")));
                 }
                 try {
                 String name = args[3];
                 if (!am.exists(name)) {
                   p.sendMessage(Util.Chat(mes.getString("Not-Exists")));
                   }
                   
                          am.getArena(name).setSpawnBlue(p.getLocation());
                          ConfigurationSection sec = arenas.getConfigurationSection(name);
                          sec.set("spawnblue.x", Double.valueOf(p.getLocation().getX()));
                          sec.set("spawnblue.y", Double.valueOf(p.getLocation().getY()));
                          sec.set("spawnblue.z", Double.valueOf(p.getLocation().getZ()));
                          sec.set("spawnblue.yaw", Float.valueOf(p.getLocation().getYaw()));
                          sec.set("spawnblue.pitch", Float.valueOf(p.getLocation().getPitch()));
                          ConfigManager.getInstance().SaveArenas(); 
                 p.sendMessage(Util.Chat(mes.getString("Spawn-Red").replace("%arena%", am.getArena(name).getName())));
                 } catch (StringIndexOutOfBoundsException e) {
                 p.sendMessage(Util.Chat(mes.getString("Invalid-Args")));
                 }
               }
             if (args[2].equalsIgnoreCase("red")) {
               if (args.length > 3) {
                 p.sendMessage(Util.Chat(mes.getString("Too-Much-args")));
                 }
               if (args.length < 3) {
                 p.sendMessage(Util.Chat(mes.getString("Too-Many-args")));
                 }
                 try {
                 String name = args[3];
                 if (!am.exists(name)) {
                   p.sendMessage(Util.Chat(mes.getString("Not-Exists")));
                   }
                   
                       am.getArena(name).setSpawnRed(p.getLocation());
                       ConfigurationSection sec = arenas.getConfigurationSection(name);
                       sec.set("spawnred.x", Double.valueOf(p.getLocation().getX()));
                       sec.set("spawnred.y", Double.valueOf(p.getLocation().getY()));
                       sec.set("spawnred.z", Double.valueOf(p.getLocation().getZ()));
                       sec.set("spawnred.yaw", Float.valueOf(p.getLocation().getYaw())); 
                       sec.set("spawnred.pitch", Float.valueOf(p.getLocation().getPitch()));
                       ConfigManager.getInstance().SaveArenas(); 
                 p.sendMessage(Util.Chat(mes.getString("Spawn-Red").replace("%arena%", am.getArena(name).getName())));
                 } catch (StringIndexOutOfBoundsException e) {
                 p.sendMessage(Util.Chat(mes.getString("Invalid-Args")));
                 }
               }
             }
             
           if (args[2].equalsIgnoreCase("spectator")) {
             if (args.length > 3) {
               p.sendMessage(Util.Chat(mes.getString("Too-Much-args")));
               }
             if (args.length < 3) {
               p.sendMessage(Util.Chat(mes.getString("Too-Many-args")));
               }
               try {
               String name = args[3];
               if (!am.exists(name)) {
                 p.sendMessage(Util.Chat(mes.getString("Not-Exists")));
                 }
                   am.getArena(name).setSpectatorLocation(p.getLocation());
                   ConfigurationSection sec = arenas.getConfigurationSection(name);
                   sec.set("spawnspectator.x", Double.valueOf(p.getLocation().getX()));
                   sec.set("spawnspectator.y", Double.valueOf(p.getLocation().getY()));
                   sec.set("spawnspectator.z", Double.valueOf(p.getLocation().getZ()));
                   sec.set("spawnspectator.yaw", Float.valueOf(p.getLocation().getYaw()));
                   sec.set("spawnspectator.pitch", Float.valueOf(p.getLocation().getPitch()));
                   ConfigManager.getInstance().SaveArenas(); 
               p.sendMessage(Util.Chat(mes.getString("Spawn-Spectator").replace("%arena%", am.getArena(name).getName())));
               } catch (StringIndexOutOfBoundsException e) {
               p.sendMessage(Util.Chat(mes.getString("Invalid-Args")));
               }
             }
           if (args[1].equalsIgnoreCase("win")) {
             if (args[2].equalsIgnoreCase("blue")) {
                 try {
                 String name = args[3];
                 if (!am.exists(name)) {
                   p.sendMessage(Util.Chat(mes.getString("Not-Exists")));
                   }
                 Location towin = p.getLocation();
                 am.getArena(name).setWinLocationBlue(towin);
                 ConfigurationSection sec = arenas.getConfigurationSection(name);
                 sec.set("winpositionblue.x", Double.valueOf(p.getLocation().getX()));
                 sec.set("winpositionblue.y", Double.valueOf(p.getLocation().getY()));
                 sec.set("winpositionblue.z", Double.valueOf(p.getLocation().getZ()));
                 ConfigManager.getInstance().SaveArenas(); 
                 p.sendMessage(Util.Chat(mes.getString("Win-Blue").replace("%arena%", am.getArena(name).getName())));
                 }
                 catch (StringIndexOutOfBoundsException e) {
                 p.sendMessage(Util.Chat(mes.getString("Invalid-Args")));
                 }
               }
             if (args[2].equalsIgnoreCase("aqua")) {
               if (args.length > 3) {
                 p.sendMessage(Util.Chat(mes.getString("Too-Much-args")));
                 }
               if (args.length < 3) {
                 p.sendMessage(Util.Chat(mes.getString("Too-Many-args")));
                 }
                 try {
                 String name = args[3];
                 if (!am.exists(name)) {
                   p.sendMessage(Util.Chat(mes.getString("Not-Exists")));
                   }
                 Location towin = p.getLocation();
                 am.getArena(name).setWinAquaLocation(towin);
                 ConfigurationSection sec = arenas.getConfigurationSection(name);
                 sec.set("winpositionaqua.x", Double.valueOf(p.getLocation().getX()));
                 sec.set("winpositionaqua.y", Double.valueOf(p.getLocation().getY()));
                 sec.set("winpositionaqua.z", Double.valueOf(p.getLocation().getZ()));
                 ConfigManager.getInstance().SaveArenas(); 
                 p.sendMessage(Util.Chat(mes.getString("Win-Aqua").replace("%arena%", am.getArena(name).getName())));
                 }
                 catch (StringIndexOutOfBoundsException e) {
                 p.sendMessage(Util.Chat(mes.getString("Invalid-Args")));
                 }
               }
             if (args[2].equalsIgnoreCase("red")) {
               if (args.length > 3) {
                 p.sendMessage(Util.Chat(mes.getString("Too-Much-args")));
                 }
               if (args.length < 3) {
                 p.sendMessage(Util.Chat(mes.getString("Too-Many-args")));
                 }
                 try {
                 String name = args[3];
                 if (!am.exists(name)) {
                   p.sendMessage(Util.Chat(mes.getString("Not-Exists")));
                   }
                 Location towin = p.getLocation();
                 am.getArena(name).setWinLocationRed(towin);
                 ConfigurationSection sec = arenas.getConfigurationSection(name);
                 sec.set("winpositionred.x", Double.valueOf(p.getLocation().getX()));
                 sec.set("winpositionred.y", Double.valueOf(p.getLocation().getY()));
                 sec.set("winpositionred.z", Double.valueOf(p.getLocation().getZ()));
                 ConfigManager.getInstance().SaveArenas(); 
                 p.sendMessage(Util.Chat(mes.getString("Win-Red").replace("%arena%", am.getArena(name).getName())));
                 }
                 catch (StringIndexOutOfBoundsException e) {
                 p.sendMessage(Util.Chat(mes.getString("Invalid-Args")));
                 }
               }
             if (args[2].equalsIgnoreCase("magenta")) {
               if (args.length > 3) {
                 p.sendMessage(Util.Chat(mes.getString("Too-Much-args")));
                 }
               if (args.length < 3) {
                 p.sendMessage(Util.Chat(mes.getString("Too-Many-args")));
                 }
                 try {
                 String name = args[3];
                 if (!am.exists(name)) {
                   p.sendMessage(Util.Chat(mes.getString("Not-Exists")));
                   }
                 Location towin = p.getLocation();
                 am.getArena(name).setWinMagentaLocation(towin);
                 ConfigurationSection sec = arenas.getConfigurationSection(name);
                 sec.set("winpositionmagenta.x", Double.valueOf(p.getLocation().getX()));
                 sec.set("winpositionmagenta.y", Double.valueOf(p.getLocation().getY()));
                 sec.set("winpositionmagenta.z", Double.valueOf(p.getLocation().getZ()));
                 ConfigManager.getInstance().SaveArenas(); 
                 p.sendMessage(Util.Chat(mes.getString("Win-Magenta").replace("%arena%", am.getArena(name).getName())));
                 }
                 catch (StringIndexOutOfBoundsException e) {
                 p.sendMessage(Util.Chat(mes.getString("Invalid-Args")));
                 }
               }
             }
             
           if (args[1].equalsIgnoreCase("spawner")) {
             if (args[2].equalsIgnoreCase("red")) {
               if (args.length > 3) {
                 p.sendMessage(Util.Chat(mes.getString("Too-Much-args")));
                 }
               if (args.length < 3) {
                 p.sendMessage(Util.Chat(mes.getString("Too-Many-args")));
                 }
                 try {
                 String name = args[3];
                 if (!am.exists(name)) {
                   p.sendMessage(Util.Chat(mes.getString("Not-Exists")));
                   }
                 Location down = p.getLocation();
                 Block todown = down.getBlock();
                 todown.setType(Material.MOB_SPAWNER);
                 CreatureSpawner a = (CreatureSpawner)todown.getState();
                 a.setSpawnedType(EntityType.AREA_EFFECT_CLOUD);
                 am.getArena(name).setSpawnerRed(p.getLocation());
                 ConfigurationSection sec = arenas.getConfigurationSection(name);
                 sec.set("spawnerred.x", Double.valueOf(p.getLocation().getX()));
                 sec.set("spawnerred.y", Double.valueOf(p.getLocation().getY()));
                 sec.set("spawnerred.z", Double.valueOf(p.getLocation().getZ()));
                 ConfigManager.getInstance().SaveArenas(); 
                 p.sendMessage(Util.Chat(mes.getString("Spawner-Red").replace("%arena%", am.getArena(name).getName())));
                 }
                 catch (StringIndexOutOfBoundsException e) {
                 p.sendMessage(Util.Chat(mes.getString("Invalid-Args")));
                 }
               }
             if (args[2].equalsIgnoreCase("blue")) {
               if (args.length > 3) {
                 p.sendMessage(Util.Chat(mes.getString("Too-Much-args")));
                 }
               if (args.length < 3) {
                 p.sendMessage(Util.Chat(mes.getString("Too-Many-args")));
                 }
                 try {
                 String name = args[3];
                 if (!am.exists(name)) {
                   p.sendMessage(Util.Chat(mes.getString("Not-Exists")));
                   }
                 Location down = p.getLocation();
                 Block todown = down.getBlock();
                todown.setType(Material.MOB_SPAWNER);
                 CreatureSpawner a = (CreatureSpawner)todown.getState();
                 a.setSpawnedType(EntityType.AREA_EFFECT_CLOUD);
                 am.getArena(name).setSpawnerBlue(p.getLocation());
                 ConfigurationSection sec = arenas.getConfigurationSection(name);
                 sec.set("spawnerblue.x", Double.valueOf(p.getLocation().getX()));
                 sec.set("spawnerblue.y", Double.valueOf(p.getLocation().getY()));
                 sec.set("spawnerblue.z", Double.valueOf(p.getLocation().getZ()));
                 ConfigManager.getInstance().SaveArenas(); 
                 p.sendMessage(Util.Chat(mes.getString("Spawner-Blue").replace("%arena%", am.getArena(name).getName())));
                 }
                 catch (StringIndexOutOfBoundsException e) {
                 p.sendMessage(Util.Chat(mes.getString("Invalid-Args")));
                 }
               }
             if (args[2].equalsIgnoreCase("aqua")) {
               if (args.length > 3) {
                 p.sendMessage(Util.Chat(mes.getString("Too-Much-args")));
                 }
               if (args.length < 3) {
                 p.sendMessage(Util.Chat(mes.getString("Too-Many-args")));
                 }
                 try {
                 String name = args[3];
                 if (!am.exists(name)) {
                   p.sendMessage(Util.Chat(mes.getString("Not-Exists")));
                   }
                 Location down = p.getLocation();
                 Block todown = down.getBlock();
                 todown.setType(Material.MOB_SPAWNER);
                 CreatureSpawner a = (CreatureSpawner)todown.getState();
                 a.setSpawnedType(EntityType.AREA_EFFECT_CLOUD);
                 am.getArena(name).setSpawnerAqua(p.getLocation());
                 ConfigurationSection sec = arenas.getConfigurationSection(name);
                 sec.set("spawneraqua.x", Double.valueOf(p.getLocation().getX()));
                 sec.set("spawneraqua.y", Double.valueOf(p.getLocation().getY()));
                 sec.set("spawneraqua.z", Double.valueOf(p.getLocation().getZ()));
                 ConfigManager.getInstance().SaveArenas(); 
                 p.sendMessage(Util.Chat(mes.getString("Spawner-Aqua").replace("%arena%", am.getArena(name).getName())));
                 }
                 catch (StringIndexOutOfBoundsException e) {
                 p.sendMessage(Util.Chat(mes.getString("Invalid-Args")));
                 }
               }
             if (args[2].equalsIgnoreCase("magenta")) {
               if (args.length > 3) {
                 p.sendMessage(Util.Chat(mes.getString("Too-Much-args")));
                 }
               if (args.length < 3) {
                 p.sendMessage(Util.Chat(mes.getString("Too-Many-args")));
                 }
                 try {
                 String name = args[3];
                 if (!am.exists(name)) {
                   p.sendMessage(Util.Chat(mes.getString("Not-Exists")));
                   }
                 Location down = p.getLocation();
                 Block todown = down.getBlock();
                 todown.setType(Material.MOB_SPAWNER);
                 CreatureSpawner a = (CreatureSpawner)todown.getState();
                 a.setSpawnedType(EntityType.AREA_EFFECT_CLOUD);
                 am.getArena(name).setSpawnerMagenta(p.getLocation());
                 ConfigurationSection sec = arenas.getConfigurationSection(name);
                 sec.set("spawnermagenta.x", Double.valueOf(p.getLocation().getX()));
                 sec.set("spawnermagenta.y", Double.valueOf(p.getLocation().getY()));
                 sec.set("spawnermagenta.z", Double.valueOf(p.getLocation().getZ()));
                 ConfigManager.getInstance().SaveArenas(); 
                 }
                 catch (StringIndexOutOfBoundsException e) {
                 p.sendMessage(Util.Chat(mes.getString("Invalid-Args")));
                 }
               }
               
             if (args[1].equalsIgnoreCase("minplayers")) {
               if (args.length > 4) {
                 p.sendMessage(Util.Chat(mes.getString("Too-Much-args")));
                 }
               if (args.length < 4) {
                 p.sendMessage(Util.Chat(mes.getString("Too-Many-args")));
                 }
                 try {
                 String name = args[3];
                 int amount = Integer.valueOf(args[4]).intValue();
                 if (!am.exists(name)) {
                   p.sendMessage(Util.Chat(mes.getString("Not-Exists")));
                   }
                 am.getArena(name).setRequiredPlayers(amount);
                 ConfigurationSection sec = arenas.getConfigurationSection(name);
                 sec.set("minplayers", amount);
                 ConfigManager.getInstance().SaveArenas(); 
                 p.sendMessage(Util.Chat(mes.getString("Set-Min-Players").replace("%arena%", am.getArena(name).getName()).replace("%minplayers%", String.valueOf(am.getArena(name).getRequiredPlayers()))));
                 }
                 catch (NumberFormatException e) {
                 p.sendMessage(Util.Chat(mes.getString("Invalid-Args")));
                 }
               }
               
   
             if (args[1].equalsIgnoreCase("maxplayers")) {
               if (args.length > 4) {
                 p.sendMessage(Util.Chat(mes.getString("Too-Much-args")));
                 }
               if (args.length < 4) {
                 p.sendMessage(Util.Chat(mes.getString("Too-Many-args")));
                 }
                 try {
                 String name = args[3];
                 int amount = Integer.valueOf(args[4]).intValue();
                 if (!am.exists(name)) {
                   p.sendMessage(Util.Chat(mes.getString("Not-Exists")));
                   }
                 am.getArena(name).setMaxPlayers(amount);
                 ConfigurationSection sec = arenas.getConfigurationSection(name);
                 sec.set("maxplayers", amount);
                 ConfigManager.getInstance().SaveArenas(); 
                 p.sendMessage(Util.Chat(mes.getString("Set-Max-Players").replace("%arena%", am.getArena(name).getName()).replace("%maxplayers%", String.valueOf(am.getArena(name).getMaxPlayers()))));
                 }
                 catch (NumberFormatException e) {
                 p.sendMessage(Util.Chat(mes.getString("Invalid-Args")));
                 }
               }
               
             if (args[1].equalsIgnoreCase("countdown")) {
               if (args.length > 4) {
                 p.sendMessage(Util.Chat(mes.getString("Too-Much-args")));
                 }
               if (args.length < 4) {
                 p.sendMessage(Util.Chat(mes.getString("Too-Many-args")));
                 }
                 try {
                 String name = args[3];
                 int amount = Integer.valueOf(args[4]).intValue();
                 if (!am.exists(name)) {
                   p.sendMessage(Util.Chat(mes.getString("Not-Exists")));
                   }
                 am.getArena(name).setStartTime(amount);
                 ConfigurationSection sec = arenas.getConfigurationSection(name);
                 sec.set("countdown", amount);
                 ConfigManager.getInstance().SaveArenas(); 
                 p.sendMessage(Util.Chat(mes.getString("Waittimer").replace("%arena%", am.getArena(name).getName()).replace("%time%", String.valueOf(am.getArena(name).getCountdonw()))));
                 }
                 catch (NumberFormatException e) {
                 p.sendMessage(Util.Chat(mes.getString("Invalid-Args")));
                 }
               }
               
             if (args[1].equalsIgnoreCase("prefix")) {
               if (args.length > 4) {
                 p.sendMessage(Util.Chat(mes.getString("Too-Much-args")));
                 }
               if (args.length < 4) {
                 p.sendMessage(Util.Chat(mes.getString("Too-Many-args")));
                 }
                 try {
                 String name = args[3];
                 String prefix = args[4];
                 if (!am.exists(name)) {
                   p.sendMessage(Util.Chat(mes.getString("Not-Exists")));
                   }
                 am.getArena(name).setPrefix(prefix);
                 ConfigurationSection sec = arenas.getConfigurationSection(name);
                 sec.set("prefix", prefix);
                 ConfigManager.getInstance().SaveArenas(); 
                 p.sendMessage(Util.Chat(mes.getString("Set-Prefix").replace("%arena%", am.getArena(name).getName()).replace("%prefix%", am.getArena(name).getPrefix())));
                 }
                 catch (StringIndexOutOfBoundsException e) {
                 p.sendMessage(Util.Chat(mes.getString("Invalid-Args")));
                 }
               }
               
   
             if (args[1].equalsIgnoreCase("banner")) {
               if (args[2].equalsIgnoreCase("red")) {
                 if (args.length > 3) {
                   p.sendMessage(Util.Chat(mes.getString("Too-Much-args")));
                   }
                 if (args.length < 3) {
                   p.sendMessage(Util.Chat(mes.getString("Too-Many-args")));
                   }
                   try {
                   String name = args[3];
                   if (!am.exists(name)) {
                     p.sendMessage(Util.Chat(mes.getString("Not-Exists")));
                     }
                   am.getArena(name).setStandRed(p.getLocation());
                   ConfigurationSection sec = arenas.getConfigurationSection(name);
                   sec.set("bannerred.x", Double.valueOf(p.getLocation().getX()));
                   sec.set("bannerred.y", Double.valueOf(p.getLocation().getY()));
                   sec.set("bannerred.z", Double.valueOf(p.getLocation().getZ()));
                   ConfigManager.getInstance().SaveArenas(); 
                   p.sendMessage(Util.Chat(mes.getString("Banner-Red").replace("%arena%", am.getArena(name).getName())));
                   }
                   catch (StringIndexOutOfBoundsException e) {
                   p.sendMessage(Util.Chat(mes.getString("Invalid-Args")));
                   }
                 }
                 
               if (args[2].equalsIgnoreCase("blue")) {
                 if (args.length > 3) {
                   p.sendMessage(Util.Chat(mes.getString("Too-Much-args")));
                   }
                 if (args.length < 3) {
                   p.sendMessage(Util.Chat(mes.getString("Too-Many-args")));
                   }
                   try {
                   String name = args[3];
                   if (!am.exists(name)) {
                     p.sendMessage(Util.Chat(mes.getString("Not-Exists")));
                     }
                   am.getArena(name).setStandBlue(p.getLocation());
                   ConfigurationSection sec = arenas.getConfigurationSection(name);
                   sec.set("bannerblue.x", Double.valueOf(p.getLocation().getX()));
                   sec.set("bannerblue.y", Double.valueOf(p.getLocation().getY()));
                   sec.set("bannerblue.z", Double.valueOf(p.getLocation().getZ()));
                   ConfigManager.getInstance().SaveArenas(); 
                   p.sendMessage(Util.Chat(mes.getString("Banner-Blue").replace("%arena%", am.getArena(name).getName())));
                   }
                   catch (StringIndexOutOfBoundsException e) {
                   p.sendMessage(Util.Chat(mes.getString("Invalid-Args")));
                   }
                 }
                 
               if (args[1].equalsIgnoreCase("winbanner")) {
                 if (args[2].equalsIgnoreCase("blue")) {
                   if (args.length > 2) {
                     p.sendMessage(Util.Chat(mes.getString("Too-Much-args")));
                     }
                   if (args.length < 2) {
                     p.sendMessage(Util.Chat(mes.getString("Too-Many-args")));
                     }
                     try {
                     String name = args[3];
                     if (!am.exists(name)) {
                       p.sendMessage(Util.Chat(mes.getString("Not-Exists")));
                       }
                     am.getArena(name).setStandBlue(p.getLocation());
                     ConfigurationSection sec = arenas.getConfigurationSection(name);
                     sec.set("winpositionbannerblue.x", Double.valueOf(p.getLocation().getX()));
                     sec.set("winpositionbannerblue.y", Double.valueOf(p.getLocation().getY()));
                     sec.set("winpositionbannerblue.z", Double.valueOf(p.getLocation().getZ()));
                     ConfigManager.getInstance().SaveArenas(); 
                     p.sendMessage(Util.Chat(mes.getString("Set-Win-Banner-Red").replace("%arena%", am.getArena(name).getName())));
                     }
                     catch (StringIndexOutOfBoundsException e) {
                     p.sendMessage(Util.Chat(mes.getString("Invalid-Args")));
                     }
                   }
                   if(args[2].equalsIgnoreCase("red")){
                   if (args.length > 2) {
                     p.sendMessage(Util.Chat(mes.getString("Too-Much-args")));
                     }
                   if (args.length < 2) {
                     p.sendMessage(Util.Chat(mes.getString("Too-Many-args")));
                    }
                     try {
                     String name = args[3];
                     if (!am.exists(name)) {
                       p.sendMessage(Util.Chat(mes.getString("Not-Exists")));
                       }
                     am.getArena(name).setStandBlue(p.getLocation());
                     ConfigurationSection sec = arenas.getConfigurationSection(name);
                     sec.set("winpositionbannerred.x", Double.valueOf(p.getLocation().getX()));
                     sec.set("winpositionbannerred.y", Double.valueOf(p.getLocation().getY()));
                     sec.set("winpositionbannerred.z", Double.valueOf(p.getLocation().getZ()));
                     ConfigManager.getInstance().SaveArenas(); 
                     p.sendMessage(Util.Chat(mes.getString("Set-Win-Banner-Blue").replace("%arena%", am.getArena(name).getName())));
                     }
                     catch (StringIndexOutOfBoundsException e) {
                     p.sendMessage(Util.Chat(mes.getString("Invalid-Args")));
                     }
                   }
                   
                   

                 }
               }
             }
           }
         }
         
       return true;
       }
     return false;
     }
   }
