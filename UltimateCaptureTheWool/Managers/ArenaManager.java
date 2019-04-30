package gamemanager;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World.Environment;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import ultimate.main.ConfigManager;
 
public class ArenaManager{
	
   private static HashMap<String, Arena> arenas = new HashMap<>();
   public final Arena arena = null;
   
 
  
 
   public void registerArena(String name, String prefix, Location spawnblue, Location spawnred, Location spawnspec, Location winblue,
		   Location winaqua, Location winred, Location winmagenta, Location stantblue, Location stanred,
		                       Location blue, Location red, Location aqua, Location magenta, int startime, int minplayers, int maxplayers,
		                                      Location lobby, Location mainlobby, List<ItemStack> inv, Location winsred, Location winsblue)
   {
     arenas.put(name, new Arena(name, prefix, spawnblue, spawnred, spawnspec, winblue, winred, winaqua, winmagenta, stantblue, stanred, red, blue, aqua, magenta, startime, minplayers, maxplayers, lobby, mainlobby, inv, winsblue, winsred));
   }
   
   public Arena getArena(String name){
	   return arenas.get(name);
    }
   
   public static HashMap<String, Arena> getArenas() {
     return arenas;
   }
   
   public static HashMap<String, Arena> getAllArenas() {
     return arenas;
   }
   
   public void remove(String name) {
     arenas.remove(name);
     FileConfiguration arenas = ConfigManager.getInstance().getArenas();
     ConfigurationSection arena = arenas.getConfigurationSection("arenas");
     arena.set(name, null);
   }
   
   public boolean exists(String name) {
     return arenas.containsKey(name);
   }
   
   public void loadArenas() throws IOException {
     arenas.clear();
     if (ConfigManager.getInstance().getArenasFile().exists()) {
       try
       {
         FileConfiguration base = ConfigManager.getInstance().getArenas();
         if (base.getConfigurationSection("arenas") != null) {
        	 for (String key : base.getConfigurationSection("arenas").getKeys(false))
          {
 
 
             String name = base.getString("arenas." + key + ".name");
             String prefix = base.getString("arenas." + key + ".prefix");
             String world = base.getString("arenas." + key + ".world");
             WorldManager wm = new WorldManager();
             wm.loadWorld(world, Environment.valueOf(base.getString("arenas." + key + "worldtype").toUpperCase()));
             
 
             int maxplayers = base.getInt("arenas." + key + ".maxplayers");
             int minplayers = base.getInt("arenas." + key + ".minplayers");
             int waitimer = base.getInt("arenas." + key + ".countdown");
             
 
             double spawnbluex = base.getDouble("arenas." + key + ".spawnblue.x");
             double spawnbluey = base.getDouble("arenas." + key + ".spawnblue.y");
             double spawnbluez = base.getDouble("arenas." + key + ".spawnblue.z");
             float spawnbluepitch = (float)base.getDouble("arenas." + key + ".spawnblue.pitch");
             float spawnblueyaw = (float)base.getDouble("arenas." + key + ".spawnblue.yaw");
             Location spawnblue = new Location(Bukkit.getWorld(world), spawnbluex, spawnbluey, spawnbluez, spawnblueyaw, spawnbluepitch);
             
 
             double spawnredx = base.getDouble("arenas." + key + "spawned.x");
             double spawnredy = base.getDouble("arenas." + key + "spawned.y");
             double spawnredz = base.getDouble("arenas." + key + "spawned.z");
             float spawnredpitch = (float)base.getDouble("arenas." + key + "spawned.pitch");
             float spawnredyaw = (float)base.getDouble("arenas." + key + "spawned.yaw");
             Location spawnred = new Location(Bukkit.getWorld(world), spawnredx, spawnredy, spawnredz, spawnredyaw, spawnredpitch);
             
             
             double spawnspecx = base.getDouble("arenas." + key + ".spawnspectator.x");
             double spawnspecy = base.getDouble("arenas." + key + ".spawnspectator.y");
             double spawnspecz = base.getDouble("arenas." + key + ".spawnspectator.z");
             float spawnspecpitch = (float)base.getDouble("arenas." + key + ".spawnspectator.pitch");
             float spawnspecyaw = (float)base.getDouble("arenas." + key + ".spawnspectator.yaw");
             Location spawnspec = new Location(Bukkit.getWorld(name), spawnspecx, spawnspecy, spawnspecz, spawnspecyaw, spawnspecpitch);
             
 
             double lobbyx = base.getDouble("arenas." + key + ".lobby.x");
             double lobbyy = base.getDouble("arenas." + key + ".lobby.y");
             double lobbyz = base.getDouble("arenas." + key + ".lobby.z");
             float lobbypitch = (float)base.getDouble("arenas." + key + ".lobby.pitch");
             float lobbyyaw = (float)base.getDouble("arenas." + key + ".lobby.yaw");
             Location lobby = new Location(Bukkit.getWorld(name), lobbyx, lobbyy, lobbyz, lobbyyaw, lobbypitch);
             
 
             double mainlobbyx = base.getDouble("arenas." + key + ".mainlobby.x");
             double mainlobbyy = base.getDouble("arenas." + key + ".mainlobby.y");
             double mainlobbyz = base.getDouble("arenas." + key + ".mainlobby.z");
             float mainlobbypitch = (float)base.getDouble("arenas." + key + ".mainlobby.pitch");
             float mainlobbyyaw = (float)base.getDouble("arenas." + key + ".mainlobby.yaw");
             Location mainlobby = new Location(Bukkit.getWorld(name), mainlobbyx, mainlobbyy, mainlobbyz, mainlobbyyaw, mainlobbypitch);
             
 
             double wbx = base.getDouble("arenas." + key + ".winpositionblue.y");
             double wby = base.getDouble("arenas." + key + ".winpositionblue.y");
             double wbz = base.getDouble("arenas." + key + ".winpositionblue.z");
             Location winblue = new Location(Bukkit.getWorld(world), wbx, wby, wbz);
             
 
             double wrx = base.getDouble("arenas." + key + ".winpositionred.x");
             double wry = base.getDouble("arenas." + key + ".winpositionred.y");
             double wrz = base.getDouble("arenas." + key + ".winpositionred.z");
             Location winred = new Location(Bukkit.getWorld(world), wrx, wry, wrz);
             
 
             double wax = base.getDouble("arenas." + key + ".winpositionaqua.x");
             double way = base.getDouble("arenas." + key + ".winpositionaqua.y");
             double waz = base.getDouble("arenas." + key + ".winpositionaqua.z");
             Location winaqua = new Location(Bukkit.getWorld(world), wax, way, waz);
             
 
             double wmx = base.getDouble("arenas." + key + ".winpositionmagenta.x");
             double wmy = base.getDouble("arenas." + key + ".winpositionmagenta.y");
             double wmz = base.getDouble("arenas." + key + ".winpositionmagenta.z");
             Location winmagenta = new Location(Bukkit.getWorld(world), wmx, wmy, wmz);
             
 
 
             double sprx = base.getDouble("arenas." + key + ".spawnerred.x");
             double spry = base.getDouble("arenas." + key + ".spawnrred.y");
             double sprz = base.getDouble("arenas." + key + ".spawnrred.z");
             Location spawnerred = new Location(Bukkit.getWorld(world), sprx, spry, sprz);
             

 
             double spbx = base.getDouble("arenas." + key + ".spawnerblue.x");
             double spby = base.getDouble("arenas." + key + ".spawnerblue.y");
             double spbz = base.getDouble("arenas." + key + ".spawnerblue.z");
             Location spawnerblue = new Location(Bukkit.getWorld(world), spbx, spby, spbz);
             
 
 
             double spmx = base.getDouble("arenas." + key + ".spawnermagenta.x");
             double spmy = base.getDouble("arenas." + key + ".spawnermagenta.y");
             double spmz = base.getDouble("arenas." + key + ".spawnermagenta.z");
             Location spawnermagenta = new Location(Bukkit.getWorld(world), spmx, spmy, spmz);
             
 
 
             double spax = base.getDouble("arenas." + key + ".spawneraqua.x");
             double spay = base.getDouble("arenas." + key + ".spawneraqua.y");
             double spaz = base.getDouble("arenas." + key + ".spawneraqua.z");
             Location spawneraqua = new Location(Bukkit.getWorld(world), spax, spay, spaz);

 
 
             double stbx = base.getDouble("arenas." + key + ".bannerblue.x");
             double stby = base.getDouble("arenas." + key + ".bannerblue.y");
             double stbz = base.getDouble("arenas." + key + ".bannerblue.z");
             Location stanblue = new Location(Bukkit.getWorld(world), stbx, stby, stbz);
             
 

             double strx = base.getDouble("arenas." + key + ".bannerred.x");
             double stry = base.getDouble("arenas." + key + ".bannerred.y");
             double strz = base.getDouble("arenas." + key + ".bannerred.z");
             Location stanred = new Location(Bukkit.getWorld(world), strx, stry, strz);
             
 
 
             double wstbx = base.getDouble("arenas." + key + ".winpositionbannerblue.x");
             double wstby = base.getDouble("arenas." + key + ".winpositionbannerblue.y");
             double wstbz = base.getDouble("arenas." + key + ".winpositionbannerblue.z");
             Location winstandblue = new Location(Bukkit.getWorld(world), wstbx, wstby, wstbz);
             
 
 
             double wstrx = base.getDouble("arenas." + key + ".winpositionbannerred.x");
             double wstry = base.getDouble("arenas." + key + ".winpositionbannerred.y");
             double wstrz = base.getDouble("arenas." + key + ".winpositionbannerred.z");
             Location winstandred = new Location(Bukkit.getWorld(world), wstrx, wstry, wstrz);
             
             arenas.put(name, new Arena(name, prefix, spawnblue, spawnred, spawnspec, winblue, winred, winaqua, winmagenta, stanblue, stanred, spawnerred, spawnerblue, spawneraqua, spawnermagenta, waitimer, minplayers, maxplayers, lobby, mainlobby, null, winstandblue, winstandred));
             Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "Arena Succefully loaded" + name + "!!!");
           }
         } else {
           Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Arenas could not be empty");
         }
       } catch (Exception e) {
         Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Could not save arenas.yml");
       }
     }
   }
   
   public boolean isEmpty()
   {
     return arenas.isEmpty();
   }
 }
