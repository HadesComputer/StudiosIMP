package gamemanager;
 
import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World.Environment;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

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
   private Location sign;
   private Countdown down;
   private Detector detector;
   private Timmer timer;
   private int requiredplayers;
   private int maxplayers;
   private int starttime;
   private ArenaStates states;
   private Teams teams;
   private List<String> inv = new ArrayList<>();
   private int redpoints = 0;
   private int bluepoints = 0;
   private List<Player> players = new ArrayList<>();
   private List<UUID> blues = new ArrayList<>();
   private List<UUID> reds = new ArrayList<>();
   private NumberFormat nf = NumberFormat.getInstance();

   public Arena(String name, String prefix, Location spawnblue, Location spawnred, Location spawnspec, Location winblue
		   , Location winred, Location winaqua, Location winmagenta, Location stantblue, Location stanred,
		               Location red, Location blue, Location aqua, Location magenta, int startime, int minplayers, int maxplayers,
		                              Location lobby, Location mainlobby, List<String> inv, Location winstandblue, Location winstandred, Location sign)
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
     this.sign = sign;
     this.down = new Countdown(this);
     this.timer = new Timmer(this);
     this.detector = new Detector(this);
     this.states = ArenaStates.WAITING;
   }
   
   public void addPlayer(Player p)
   {
     FileConfiguration ms = ConfigManager.getInstance().getMessages();
     FileConfiguration cg = Ultimate.getInstance().getConfig();
     int pos1 = cg.getInt("LobbySelectorLeavePosition");
     int pos2 = cg.getInt("LobbyTeamSelectorPosition");
     if(!isFull()){
    	 if(this.players.contains(p)){
    		 p.sendMessage(Util.Chat(ms.getString("You-Has-In-Arena")));
    	 }else{
     this.players.add(p);
     p.getInventory().clear();
     p.setHealth(20.0D);
     p.setFoodLevel(10);
     addInventory(p);
     p.sendMessage(Util.Chat(ms.getString("Join").replace("%arena%", getName()).replace("[PREFIX]", getPrefix()).replace("%player%", p.getName()).replace("%players%", nf.format(getPlayers().size())).replace("%maxplayers%", nf.format(getMaxPlayers())) ));
     if(getLobbyLocation() != null){
     p.teleport(getLobbyLocation());
     }else{
    	 p.sendMessage(Util.Chat(ms.getString("Not-Exist-Location")));
     }
     ItemStack teams = new ItemStack(Material.valueOf(cg.getString("LobbyTeamSelectorIcon").toUpperCase()));
     ItemMeta team = teams.getItemMeta();
     team.setDisplayName(Util.Chat(cg.getString("LobbyTeamSelectorName")));
     teams.setItemMeta(team);
     ItemStack leave = new ItemStack(Material.valueOf(cg.getString("LobbySelectorLeaveIcon").toUpperCase()));
     ItemMeta leav = leave.getItemMeta();
     leav.setDisplayName(Util.Chat(cg.getString("LobbySelectorLeaveName")));
     leave.setItemMeta(leav);
     p.getInventory().setItem(pos2, teams);
     p.getInventory().setItem(pos1, leave);
     for (Player pl : getPlayers()) {
       pl.sendMessage(Util.Chat(ms.getString("Has-Join").replace("%player%", p.getName()).replace("%maxplayers%", nf.format(getMaxPlayers())).replace("%players%", nf.format(getPlayers().size()))));
     }
     
     if (this.down.isRunning()){
    	 for(Player pl : getPlayers()){
    		 pl.sendMessage(Util.Chat(ms.getString("Start-in").replace("%time%", nf.format(getStartTime())).replace("[PREFIX]", getPrefix()) ));
    	 }
    	 this.down.start(getStartTime());
     }
     
    	 }
     }else{
    	 p.sendMessage(Util.Chat(ms.getString("Is-Full")));
     }
   }

   
   public boolean isFull() {
	   return this.players.size() == this.maxplayers;
   }
   
   public void leave(Player p) {
	     FileConfiguration ms = ConfigManager.getInstance().getMessages();
	   if(this.players.contains(p)){
     if(getMainLobby() != null){
     p.teleport(getMainLobby());
     this.players.remove(p);
     }else{
    	 p.sendMessage(Util.Chat(ms.getString("Not-Exist-Location")));
     }
     removeForTeam(p);
     p.getInventory().clear();
     p.setHealth(20.0D);
     p.setFoodLevel(10);
     p.sendMessage(Util.Chat(ms.getString("Leave").replace("%arena%", getName()).replace("[PREFIX]", getPrefix())));
     for (Player pl : getPlayers()) {
       pl.sendMessage(Util.Chat(ms.getString("Was-Leave").replace("%player%", p.getName()).replace("%maxplayers%", nf.format(getMaxPlayers())).replace("%players%", nf.format(getPlayers().size()))));
     }
   }else{
	   p.sendMessage(Util.Chat(ms.getString("You-Dont-Has-In-Arena")));
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
     states.setState(ArenaStates.RESTARTING);
     WorldManager wm = new WorldManager();
     this.down.cancel();
     this.players.clear();
     this.bluepoints = 0;
     this.redpoints = 0;
     this.clearTeams();
     wm.unloadWorld(getName());
     wm.deleteWorld(getName());
     Ultimate.getInstance();
     File backup = new File(Ultimate.getInstance().getDataFolder() + File.separator + "mapdata" + File.separator + getName());
     File target = new File(Ultimate.getInstance().getServer().getWorldContainer() + File.separator + getName());
     target.delete();
     
     wm.copyWorld(backup, target);
     wm.loadWorld(getName(), Environment.valueOf(ConfigManager.getInstance().getArenas().getString("arenas." + getName() + ".worldtype")));
     Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Arena " + getName() + "All Restarted");
     states.setState(ArenaStates.WAITING);
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
   
   public List<String> getInventory() {
	   return this.inv;
   }
   
   public void addInventory(Player p){
	   FileConfiguration arenas = ConfigManager.getInstance().getArenas();
	  for(String key : arenas.getConfigurationSection("arenas").getKeys(false)){
	   for(String items : arenas.getStringList("arenas." + name +".inventory")){
		   for(String s : items.split(":")){
			   inv.add(s);
		   }
		   ItemStack item;
		   int amount;
		   short data;
		   //Enchantment enchant = Enchantment.getByName(inv.get(3).toUpperCase());
		   //int level;
		   Material material = Material.matchMaterial(inv.get(0).toUpperCase());
		   
		 //  if(enchant == null){
			   
		  // }
		   if(material == null){
			   material = Material.AIR;
		   }
		   
		   if(Util.isInt(inv.get(1))){
			   amount = Integer.parseInt(inv.get(1));
		   }else{
			   amount = 0;
		   }
		   
		   if(Util.isInt(inv.get(2))){
			   data = (short) Integer.parseInt(inv.get(2));
		   }else{
			   data = 0;
		   }
		   
		//   if(Util.isInteger(inv.get(4))){
			//   level = Integer.parseInt(inv.get(4));
		  // }else{
			//   level = 1;
		   //}
		   
		   if(material != null && amount != -0 && data != -0){
		   item = new ItemStack(material, amount, data);
		   p.getInventory().addItem(item);
		   p.sendMessage("debug item");
		   }
		   
//		   if(material != null && amount != -1 && data != -1 && enchant != null && level != 0){
	//		   item = new ItemStack(material, amount, data);
		//	   item.addEnchantment(enchant, level);
			//   p.getInventory().addItem(item);
			  // p.sendMessage("debug enchanment");
		  // }
	   }
	   }
   }

   public boolean contains(Player p) {
	return players.contains(p);
   }
   
   public boolean isInTeam(Player p)
   {
     if (blues.contains(p.getUniqueId()) || reds.contains(p.getUniqueId())) {
      return false;
     }
     return true;
   }
   
   public void removeForTeam(Player p) {
     if (reds.contains(p.getUniqueId())) {
       reds.remove(p.getUniqueId());
      }
     if (blues.contains(p.getUniqueId())) {
       blues.remove(p.getUniqueId());
      }
    }
   
   public void clearTeams()
   {
     blues.clear();
     reds.clear();
   }
   
   public List<UUID> getRedMembers() {
     return reds;
   }
   
   public List<UUID> getBluesMembers() {
     return blues;
   }
   
   public Teams getTeams(){
	   return this.teams;
   }
   
   
   public void addToTeam(Teams team, Player p) {
     FileConfiguration mess = ConfigManager.getInstance().getMessages();
     String tblue = mess.getString("Teams.Blue.Title");
     String sblue = mess.getString("Teams.Blue.Subtitle");
     int stblue = mess.getInt("Teams.Blue.Stayin");
     String tred = mess.getString("Teams.Red.Title");
     String sred = mess.getString("Teams.Red.Subtitle");
     int stred = mess.getInt("Teams.Red.Stayin");
     if (!isInTeam(p)) {
       switch (team) {
        case BLUE: 
         blues.add(p.getUniqueId());
         ItemStack helmetblue = new ItemStack(Material.LEATHER_HELMET);
         ItemStack chesblue = new ItemStack(Material.LEATHER_CHESTPLATE);
         ItemStack legsblue = new ItemStack(Material.LEATHER_LEGGINGS);
         ItemStack botsblue = new ItemStack(Material.LEATHER_BOOTS);
         LeatherArmorMeta hblue = (LeatherArmorMeta)helmetblue.getItemMeta();
         LeatherArmorMeta cblue = (LeatherArmorMeta)chesblue.getItemMeta();
         LeatherArmorMeta lblue = (LeatherArmorMeta)legsblue.getItemMeta();
         LeatherArmorMeta botblue = (LeatherArmorMeta)botsblue.getItemMeta();
         hblue.setColor(Color.BLUE);
         cblue.setColor(Color.BLUE);
         lblue.setColor(Color.BLUE);
         botblue.setColor(Color.BLUE);
         helmetblue.setItemMeta(hblue);
         chesblue.setItemMeta(cblue);
         legsblue.setItemMeta(lblue);
         botsblue.setItemMeta(botblue);
         p.getInventory().setHelmet(helmetblue);
         p.getInventory().setChestplate(chesblue);
         p.getInventory().setLeggings(legsblue);
         p.getInventory().setBoots(botsblue);
         Util.SendInstantTitle(p, tblue, sblue, stblue);
         Util.PlaySound(p, Sound.valueOf(mess.getString("Teams.Red.Sound").toUpperCase()));
         p.sendMessage(Util.Chat(mess.getString("Teams.Blue.Message")));
         break;
        case RED: 
         ItemStack helmetred = new ItemStack(Material.LEATHER_HELMET);
         ItemStack chesred = new ItemStack(Material.LEATHER_CHESTPLATE);
         ItemStack lesred = new ItemStack(Material.LEATHER_LEGGINGS);
         ItemStack botsred = new ItemStack(Material.LEATHER_BOOTS);
         LeatherArmorMeta helred = (LeatherArmorMeta)helmetred.getItemMeta();
         LeatherArmorMeta chestred = (LeatherArmorMeta)chesred.getItemMeta();
         LeatherArmorMeta legtred = (LeatherArmorMeta)lesred.getItemMeta();
         LeatherArmorMeta botred = (LeatherArmorMeta)botsred.getItemMeta();
         helred.setColor(Color.RED);
         chestred.setColor(Color.RED);
         legtred.setColor(Color.RED);
         botred.setColor(Color.RED);
         chesred.setItemMeta(chestred);
         lesred.setItemMeta(legtred);
         botsred.setItemMeta(botred);
         helmetred.setItemMeta(helred);
         p.getInventory().setHelmet(helmetred);
         p.getInventory().setChestplate(chesred);
         p.getInventory().setLeggings(lesred);
         p.getInventory().setBoots(botsred);
         reds.add(p.getUniqueId());
         Util.SendInstantTitle(p, tred, sred, stred);
         Util.PlaySound(p, Sound.valueOf(mess.getString("Teams.Red.Sound").toUpperCase()));
         p.sendMessage(Util.Chat(mess.getString("Teams.Red.Message")));
         break;
        }
        
      } else {
       p.sendMessage(Util.Chat(mess.getString("YouHave-Already-in-Team")));
      }
    }
   
   public Location getSign(){
	   return this.sign;
   }
   
   public Location setSign(Location sign){
	   return this.sign = sign;
   }

   public ArenaStates getState() {
	return this.states;
   }

   public ArenaStates setState(ArenaStates states) {
	return this.states = states;
   }
 }