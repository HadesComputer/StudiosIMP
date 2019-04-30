 package murdermistery.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import murdermistery.managers.RoleManager.Roles;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
public class Arena {

	private Location lobby;
	private Location mainlobby;
	private Location corpse;
	private Location spectator;
	private List<Location> golden = new ArrayList<>();
	private List<Location> spawns = new ArrayList<>();
	
	private List<String> players = new ArrayList<>();
	private List<UUID> playersingame = new ArrayList<>();
	private List<UUID> detectives = new ArrayList<>();
	private List<UUID> assassins = new ArrayList<>();
	private List<UUID> assistants = new ArrayList<>();
	private List<UUID> accomplices = new ArrayList<>();
	private List<UUID> innocents = new ArrayList<>();
	private int detective;
	private int assistant;
	private int assasin;
	private int accomplice;
	private int innocent;
	private int waittimer;
	private int requiredplayers;
	private int maxplayers;
	private int minutes;
	private int seconds;
	private Roles roles;
	private ArenaStates states;
	private Countdown down;
	private Timmer timer;
	private Detector detector;

	
	
	   public Arena(Location lobby, Location spectator, Location corpse, Location mainlobby, List<Location> golden , List<Location> spawns){
		   this.lobby = lobby;
		   this.spectator = spectator;
		   this.corpse = corpse;
		   this.mainlobby = mainlobby;
		   this.golden = golden;
		   this.spawns = spawns;
		   this.down = new Countdown(this);
		   this.timer = new Timmer(this);
		   this.detector = new Detector(this);
	   }
	
	   
	public void add(Player p){
		
		if(!down.isRunning() && players.size() >= requiredplayers){
			this.down.start(waittimer);
			if(players.size() < requiredplayers){
				this.down.cancel();
			}
		}
	}
	
	public boolean isFulle(){
		return this.players.size() == maxplayers;
	}
	public Location getLobby(){
		return lobby;
	}
	
	public Location setLobby(Location lobby){
		return this.lobby = lobby;
	}
	
	public Location getMainLobby(){
		return mainlobby;
	}
	
	public Location setMainLobby(Location mainlobby){
		return this.mainlobby = mainlobby;
	}
	
	public Location getSpectator(){
		return spectator;
	}
	
	public Location setSpectator(Location spectator){
		return this.spectator = spectator;
	}
	
	public Location getCorpse(){
		return corpse;
	}
	
	public Location setCorpse(Location corpse){
		return this.corpse = corpse;
	}
	
	public List<Location> getGoldenLocation(){
		return golden;
	}
	
	public boolean addGoldenLocation(Location golden){
		return this.golden.add(golden);
	}
	
	public List<Location> getSpawnLocation(){
		return spawns;
	}
	
	public boolean addSpawnLocation(Location spawn){
		return this.spawns.add(spawn);
	}
	
	
	public List<String> getPlayers(){
		return players;
	}
	
	public List<UUID> getPlayersInGame(){
		return playersingame;
	}
	
	public List<UUID> getDetectives(){
		return detectives;
	}
	
	public List<UUID> getAssistants(){
		return assistants;
	}
	
	public List<UUID> getAssassins(){
		return assassins;
	}
	
	public List<UUID> getAccomplices(){
		return accomplices;
	}
	
	public List<UUID> getInnocents(){
		return innocents;
	}
	
	public Roles setRole(Roles roles){
		return this.roles = roles;
	}
	
	public Roles getRole(){
		return this.roles;
	}
	
	public boolean isRole(Roles roles){
		return this.roles == roles;
	}
	
	public int getMaxInnocents(){
		return innocent;
	}
	
	public int setMaxInnocents(int innocent){
	 return this.innocent = innocent; 
	}
	
	public int getMaxAssasins(){
		return assasin;
	}
	
	public int setMaxAssasins(int assasin){
		return this.assasin = assasin;
	}
	
	public int getMaxAccomplices(){
		return accomplice;
	}
	
	public int setMaxAccomplices(int accomplice){
		return this.accomplice = accomplice;
	}
	
	public int getMaxDetectives(){
		return detective;
	}
	
	public int setMaxDetectives(int detective){
		return this.detective = detective;
	}
	
	public int getMaxAssistants(){
		return assistant;
	}
	
	public int setMaxAssistants(int assistant){
		return this.assistant = assistant;
	}
	
	public int getRequiredPlayers(){
		return this.requiredplayers;
	}
	
	public int setRequiredPlayers(int requiredplayers){
		return this.requiredplayers = requiredplayers;
	}
	
	public int getMaxPlayers(){
		return this.maxplayers;
	}
	
	public int setMaxPlayers(int maxplayers){
		return this.maxplayers = maxplayers;
	}
	
	public ArenaStates getStates(){
		return states;
	}
	
	public ArenaStates setStates(ArenaStates states){
		return this.states = states;
	}
	
	public boolean isState(ArenaStates states){
		return this.states == states;
	}
	
	public Countdown getCountdown(){
		return this.down;
	}
	
	public int getMinutes(){
		return this.minutes;
	}
	
	public int setMinutes(int minutes){
		return this.minutes = minutes;
	}
	
	public int getSeconds(){
		return this.seconds;
	}
	
	public int setSeconds(int seconds){
		return this.seconds = seconds;
	}
	
	public void addRole(){
		for(int i = 0;i<detective;i++ ){
		    Player detectiverole = Bukkit.getPlayer(playersingame.get(ThreadLocalRandom.current().nextInt(0, playersingame.size())));
		    detectiverole.sendMessage("detective");
			while(assassins.contains(detectiverole.getUniqueId()) || accomplices.contains(detectiverole.getUniqueId()) || assistants.contains(detectiverole.getUniqueId())){
				 detectiverole = Bukkit.getPlayer(playersingame.get(ThreadLocalRandom.current().nextInt(0, playersingame.size())));
				    detectiverole.sendMessage("detective");
			}
		}
		
		for(int i = 0;i<assistant;i++ ){
		    Player assistant = Bukkit.getPlayer(playersingame.get(ThreadLocalRandom.current().nextInt(0, playersingame.size())));
		    assistant.sendMessage("assistan");
			while(assassins.contains(assistant.getUniqueId()) || accomplices.contains(assistant.getUniqueId()) || assistants.contains(assistant.getUniqueId())){
				 assistant = Bukkit.getPlayer(playersingame.get(ThreadLocalRandom.current().nextInt(0, playersingame.size())));
				    assistant.sendMessage("detective");
			}
		}
		
		for(int i = 0;i<assasin;i++ ){
			
		}
		
		for(int i = 0;i<accomplice;i++ ){
			
		}
		
		for(int i = 0;i<innocent;i++ ){
			
		}
	}
	
	public Timmer getTimmer(){
		return this.timer;
	}
	
	public Detector getDetector(){
		return this.detector;
	}
}
