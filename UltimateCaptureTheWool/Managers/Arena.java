package gamemanager;
 
import com.google.common.collect.Maps;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ultimate.main.ConfigManager;
import ultimate.main.Ultimate;
import util.Util; 
public class Arena{

   private Location spawnbluelocation;
   private Location spawnredlocation;
   private Location spectatorlocation;
   private Location winblue;
   private Location winred;
   private Location winaqua;
   private Location winmangenta;
   private Location standblue;
   private Location standred;
   private Location winstandblue;
   private Location winstandred;
   private String prefix;
   private String name;
   private Location lobby;
   private Location mainlobby;
   private Location spawnblue;
   private Location spawnred;
   private Location spawnaqua;
   private Location spawnmagenta;
   private Countdown down;
   private Detector detector;
   private Timmer timer;
   private int requiredplayers;
   private int maxplayers;
   private int starttime;
   private ArenaStates states;
   private List<ItemStack> inv = new ArrayList<>();
   private int redpoints = 0;
   private int bluepoints = 0;
   private List<Player> players = new ArrayList<>();
   private Map<Player, Arena> playersingame = Maps.newHashMap();

   public Arena(String name, String prefix, Location spawnblue, Location spawnred, Location spawnspec, Location winblue, Location winred, Location winaqua, Location winmagenta, Location stantblue, Location stanred, Location red, Location blue, Location aqua, Location magenta, int startime, int minplayers, int maxplayers, Location lobby, Location mainlobby, List<ItemStack> inv, Location winstandblue, Location winstandred)
   {
     this.name = name;
     this.prefix = prefix;
     this.spawnbluelocation = spawnblue;
     this.spawnredlocation = spawnred;
     this.spectatorlocation = spawnspec;
     this.winblue = winblue;
     this.winred = winred;
     this.winaqua = winaqua;
     this.winmangenta = winmagenta;
     this.spawnblue = blue;
     this.spawnred = red;
     this.spawnaqua = aqua;
     this.spawnmagenta = magenta;
     this.starttime = startime;
     this.requiredplayers = minplayers;
     this.maxplayers = maxplayers;
     this.lobby = lobby;
     this.mainlobby = mainlobby;
     this.inv = inv;
     this.standblue = stantblue;
     this.standred = stanred;
     this.winstandblue = winstandblue;
     this.winstandred = winstandred;
     this.down = new Countdown(this);
     this.timer = new Timmer(this);
     this.detector = new Detector(this);
   }
   
   public void addPlayer(Player p)
   {
     FileConfiguration ms = ConfigManager.getInstance().getMessages();
     FileConfiguration cg = Ultimate.getInstance().getConfig();
     int pos1 = cg.getInt("LobbySelectorLeavePosition");
     int pos2 = cg.getInt("LobbyTeamSelectorPosition");
     this.players.add(p);
     p.getInventory().clear();
     p.setHealth(20.0D);
     p.setFoodLevel(10);
     p.teleport(getLobbyLocation());
     ItemStack teams = new ItemStack(Material.valueOf(cg.getString("LobbyTeamSelectorIcon").toUpperCase()), p.getInventory().getItem(pos1).getAmount() + 1);
     ItemMeta team = teams.getItemMeta();
     team.setDisplayName(Util.Chat(cg.getString("LobbyTeamSelectorName")));
     ItemStack leave = new ItemStack(Material.valueOf(cg.getString("LobbySelectorLeaveIcon").toUpperCase()), p.getInventory().getItem(pos2).getAmount() + 1);
     ItemMeta leav = leave.getItemMeta();
     leav.setDisplayName(Util.Chat(cg.getString("LobbySelectorLeaveName")));
     p.getInventory().setItem(pos2, teams);
     p.getInventory().setItem(pos1, leave);
     for (Player pl : getPlayers()) {
       pl.sendMessage(Util.Chat(ms.getString("Has-Join").replace("%player%", p.getName()).replace("%maxplayers%", String.valueOf(getMaxPlayers())).replace("%minplayers%", String.valueOf(getRequiredPlayers()))));
     }
     
     if (!this.down.isRunning() && this.players.size() >= this.requiredplayers){
       this.down.start(this.starttime);
     }
     if (this.down.isRunning() && this.players.size() < this.requiredplayers) {
       for (Player pl : this.players) {
         pl.sendMessage(Util.Chat(ms.getString("There-Are-Not-Players-Required")));
         setState(ArenaStates.WAITING);
       }
     }
   }

   
   public boolean isFull() {
	   return this.players.size() == this.maxplayers;
   }
   
   public void leave(Player p) {
     FileConfiguration ms = ConfigManager.getInstance().getMessages();
     this.players.remove(p.getUniqueId());
     TeamManager tm = new TeamManager();
     tm.removeForTeam(p);
     p.getInventory().clear();
     p.setHealth(20.0D);
     p.setFoodLevel(10);
     for (Player pl : getPlayers()) {
       pl.sendMessage(Util.Chat(ms.getString("Was-Leave").replace("%player%", p.getName()).replace("%maxplayers%", String.valueOf(getMaxPlayers())).replace("%minplayers%", String.valueOf(getRequiredPlayers()))));
     }
   }
   
   public List<Player> getPlayers() {
     return this.players;
   }
   
   public Location getSpanwBlue() {
     return this.spawnbluelocation;
   }
   
   public Location getSpawnRed() {
     return this.spawnredlocation;
   }
   
   public Location getWinBlue() {
     return this.winblue;
   }
   
   public Location getWinAqua() {
     return this.winaqua;
   }
   
   public Location getWinRed() {
     return this.winred;
   }
   
   public Location getWinMagenta() {
     return this.winmangenta;
   }
   
   public Location getStandRed() {
     return this.standred;
   }
   
   public Location getStandBlue() {
     return this.standblue;
   }
   
   public Location getSpawnSpectator() {
     return this.spectatorlocation;
   }
   
   public Location setStandBlue(Location stblue) {
     return this.standblue = stblue;
   }
   
   public Location setStandRed(Location stred) {
     return this.standred = stred;
   }
   
   public Location setSpawnBlue(Location blue) { 
	   return this.spawnbluelocation = blue; 
   }
   
   public Location setSpawnRed(Location red){
     return this.spawnredlocation = red;
   }
   
   public Location setWinLocationBlue(Location wblue) {
     return this.winblue = wblue;
   }
   
   public Location setWinLocationRed(Location wred) {
     return this.winred = wred;
   }
  
   public Location setWinAquaLocation(Location waqua) {
     return this.winaqua = waqua;
   }
   
   public Location setWinMagentaLocation(Location wmag) {
     return this.winmangenta = wmag;
   }
   
   public Location setSpectatorLocation(Location spec) {
     return this.spectatorlocation = spec;
   }
   
   public String getName() {
     return this.name;
   }
   
   public String getPrefix() {
     return this.prefix;
   }
   
   public String setName(String name) {
     return this.name = name;
   }
   
   public String setPrefix(String prefix) {
     return this.prefix = prefix;
   }
   
   public Location getSpawnerBlue() {
     return this.spawnblue;
   }
   
   public Location getSpawnerRed() {
     return this.spawnred;
   }

   public Location getSpawnerAqua() {
     return this.spawnaqua;
   }
   
   public Location getSpawnerMagenta() {
     return this.spawnmagenta;
   }
   
   public Location setSpawnerBlue(Location blue) {
     return this.spawnblue = blue;
   }
   
   public Location setSpawnerRed(Location red) {
     return this.spawnred = red;
   }
   
   public Location setSpawnerAqua(Location aqua) {
     return this.spawnaqua = aqua;
   }
   
   public Location setSpawnerMagenta(Location magenta) {
     return this.spawnmagenta = magenta;
   }
   
   public Countdown getCountdonw() {
     return this.down;
   }
   
   public Countdown setCountdow(Countdown dow) {
     return this.down = dow;
   }
   
   public void reset() {
     setState(ArenaStates.RESTARTING);
     WorldManager wm = new WorldManager();
     ArenaManager am = new ArenaManager();
     this.down.cancel();
     this.players.clear();
     this.bluepoints = 0;
     this.redpoints = 0;
     wm.unloadWorld(getName());
     wm.deleteWorld(getName());
     Ultimate.getInstance();
     File backup = new File(Ultimate.getInstance().getDataFolder() + File.separator + "mapdata" + File.separator + getName());
     File target = new File(Ultimate.getInstance().getServer().getWorldContainer() + File.separator + getName());
     target.delete();
     
     wm.copyWorld(backup, target);
     wm.loadWorld(getName(), Environment.valueOf(ConfigManager.getInstance().getArenas().getString("arenas." + getName() + ".worldtype")));
     Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Arena " + getName() + "All Restarted");
     setState(ArenaStates.WAITING);
   }
   
   public int getRequiredPlayers() {
     return this.requiredplayers;
   }
   
   public int setRequiredPlayers(int minplayers) {
     return this.requiredplayers = minplayers;
   }
   
   public int getMaxPlayers() {
	 return this.maxplayers;
   }
   
   public int setMaxPlayers(int maxplayer) {
     return this.maxplayers = maxplayer;
   }
   
   public int setStartTime(int startime) {
     return this.starttime = startime;
   }
   
   public int getStartTime() {
     return this.starttime;
   }
   
   public Map<Player, Arena> getPlayersingame() {
     return this.playersingame;
   }
   
   public Location getLobbyLocation() {
     return this.lobby;
   }
   
   public Location setLobbyLocation(Location loby) {
     return this.lobby = loby;
   }
   
   public Location getMainLobby() {
     return this.mainlobby;
   }
   
   public Location setMainLobby(Location newlobby) {
     return this.mainlobby = newlobby;
   }

   public ArenaStates getState() {
     return this.states;
   }
   
   public ArenaStates setState(ArenaStates state) {
     return this.states = state;
   }
   
   public boolean isState(ArenaStates state) {
     return this.states == state;
   }
   
   public int addBluePoint() {
     return this.bluepoints++;
   }
   
   public int addRedPoint() {
     return this.redpoints++;
   }
   
   public int getBluesPoints(){
	   return this.bluepoints;
   }
   
   public int getRedPoints(){
	   return this.redpoints;
   }
   
   public void setPlayersingame(Map<Player, Arena> playersingame) {
     this.playersingame = playersingame;
   }
   
   public Location getWinStandBlue() {
     return this.winstandblue;
   }
   public Location setWinStandBlue(Location winsblue) {
     return this.winstandblue = winsblue;
   }
   
   public Location getWinStandRed() {
     return this.winstandred;
   }
   
   public Location setWinStandRed(Location winsred) {
     return this.winstandred = winsred;
   }
   
   public Timmer getTimmer(){
	   return this.timer;
   }
   
   public Detector getDetector(){
	   return this.detector;
   }
   
   public void setInventory() {
	   
   }
   
   public void getInventory() {
	   
   }
 }