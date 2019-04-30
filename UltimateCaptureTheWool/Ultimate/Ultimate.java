/*     */ package ultimate.main;
/*     */ 
/*     */ import commands.AdminCmd;
/*     */ import commands.Arenacmd;
/*     */ import commands.GameCmd;
/*     */ import de.robingrether.idisguise.api.DisguiseAPI;
/*     */ import gamelisteners.MoveOnSpawner;
/*     */ import gamelisteners.PickUpBanner;
/*     */ import gamelisteners.PickUpWools;
/*     */ import gamelisteners.PlaceBanner;
/*     */ import gamelisteners.PlaceWool;
/*     */ import gamemanager.ArenaManager;
/*     */ import gamemanager.TeamManager;
/*     */ import gamemanager.WorldManager;
/*     */ import guns.CustomGun;
/*     */ import guns.DragonFireBallGun;
/*     */ import guns.EggsGun;
/*     */ import guns.FangEvokerGun;
/*     */ import guns.FireBallGun;
/*     */ import guns.FireworkGun;
/*     */ import guns.LlamaSpitGun;
/*     */ import guns.PearlGun;
import guns.ShulkerBulletGun;
/*     */ import guns.SnowBallGun;
/*     */ import guns.TNTGun;
/*     */ import guns.ThunderGun;
/*     */ import guns.WitheSkullGun;
/*     */ import guns.specialguns.BerserkGun;
/*     */ import guns.specialguns.BlindGun;
/*     */ import guns.specialguns.BlockGun;
/*     */ import guns.specialguns.CatharsisGun;
/*     */ import guns.specialguns.ConfuseGun;
/*     */ import guns.specialguns.CuningGun;
/*     */ import guns.specialguns.DarknessGun;
/*     */ import guns.specialguns.DopingGun;
/*     */ import guns.specialguns.HapeningGun;
/*     */ import guns.specialguns.HonestGun;
/*     */ import guns.specialguns.HungryGun;
/*     */ import guns.specialguns.JokerGun;
/*     */ import guns.specialguns.LoveGun;
import guns.specialguns.PeepingGun;
/*     */ import guns.specialguns.ScapeGun;
/*     */ import guns.specialguns.SlowlessGun;
/*     */ import guns.specialguns.VenomGun;
/*     */ import guns.specialguns.WizzardGun;

/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.Random;

/*     */ import listener.ClickEvent;
/*     */ import listener.ClickGuns;
/*     */ import listener.ClickOnMenu;
/*     */ import listener.CustomDeath;
/*     */ import listener.InteractOnBlocks;
/*     */ import listener.OnJoin;
/*     */ import listener.SelectorInfected;
/*     */ import listener.SpecialBlocks;
/*     */ import listener.ToDamage;
/*     */ import listener.WoolSytem;
/*     */ import listener.onQuit;
/*     */ import net.milkbowl.vault.economy.Economy;

/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Biome;
/*     */ import org.bukkit.generator.ChunkGenerator;
/*     */ import org.bukkit.plugin.PluginDescriptionFile;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ import org.bukkit.plugin.RegisteredServiceProvider;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ 
/*     */ public class Ultimate extends JavaPlugin
/*     */ {
/*  77 */   PluginDescriptionFile pdffile = getDescription();
/*  78 */   public String Version = ChatColor.AQUA + this.pdffile.getVersion();
/*  79 */   public String Name = ChatColor.LIGHT_PURPLE + "[" + ChatColor.AQUA + this.pdffile.getName() + ChatColor.LIGHT_PURPLE + "]";
/*  80 */   private static Economy econ = null;
/*  81 */   public static DisguiseAPI dis = null;
/*     */   public String RConfig;
/*     */   public static Ultimate instance;
/*     */   public static ConfigManager cm;
/*     */   public static PlayerDataManager pdm;
/*     */   public static Ultimate plugin;
/*     */   private ArenaManager am;
/*     */   private WorldManager wm;
/*     */   
/*  90 */   public void onEnable() { 
	instance = this;
/*  91 */     plugin = this;
/*  92 */     CheckUpdates updater = new CheckUpdates(plugin, 64700);
/*  93 */     Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "------------------------");
/*  94 */     Bukkit.getConsoleSender().sendMessage(this.Name);
/*  95 */     Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "loading Plugin And Data");
/*  96 */     Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "(Version:" + ChatColor.LIGHT_PURPLE + "(" + Version + ")");
/*  97 */     Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "-Plugin Has Been Enabled-");
/*  98 */     Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "------------------------");
/*  99 */     if (!setupEconomy()) {
/* 100 */       Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Vault Found");
/*     */     } else {
/* 102 */       Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Vault Not Found");
/*     */     }
/* 104 */     if (!setupDisguises()) {
/* 105 */       Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "iDisguises Found");
/*     */     } else {
/* 107 */       Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_PURPLE + "iDisguises not Found");
/*     */     }
/* 109 */     RegisterCommands();
/* 110 */     RegisterEvents();
/* 111 */     RegisterConfig();
/* 112 */     RegisterFiles();
/* 113 */     RegisterConfiguration();
/* 114 */     RegisterPlayers();
/* 115 */     RegisterGuns();
/* 116 */     RegisterGameListener();
/* 117 */     RegisterMapCommands();
/* 118 */     RegisterMapsFolder();
/* 119 */     this.wm = new WorldManager();
/* 120 */     this.am = new ArenaManager();
/*     */     try {
/* 122 */       this.am.loadArenas();
/*     */     }
/*     */     catch (IOException localIOException) {}
/* 125 */     RegisterAdminCommands();
/* 126 */     Cooldown.setupCooldown();
/* 127 */     TeamManager.setUpBlue();
/* 128 */     TeamManager.setUpRed();
/*     */     try {
/* 130 */       if (!updater.checkForUpdates()) {
/* 131 */         Bukkit.getConsoleSender().sendMessage(this.Name + ChatColor.DARK_PURPLE + " No Updated Avaliables");
/*     */       }
/*     */       else {
/* 134 */         Bukkit.getConsoleSender().sendMessage(this.Name + ChatColor.LIGHT_PURPLE + " An Update Was Found, Version" + updater.getLatestVersion() + " Donwload " + updater.getResourceURL());
/*     */       }
/*     */     } catch (Exception localException) {}
/*     */   }
/*     */   
/*     */   public void onDisable() {
/* 140 */     Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "------------------------");
/* 141 */     Bukkit.getConsoleSender().sendMessage(this.Name);
/* 142 */     Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_PURPLE + "Plugin Has Been Disabled");
/* 143 */     Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "------------------------");
/*     */   }
/*     */   
/*     */   private boolean setupEconomy() {
/* 147 */     if (getServer().getPluginManager().getPlugin("Vault") == null) {
/* 148 */       return false;
/*     */     }
/* 150 */     RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
/* 151 */     if (rsp == null) {
/* 152 */       return false;
/*     */     }
/* 154 */     econ = (Economy)rsp.getProvider();
/* 155 */     return econ != null;
/*     */   }
/*     */   
/* 158 */   public static Economy getEconomy() { return econ; }
/*     */   
/*     */   private boolean setupDisguises()
/*     */   {
/* 162 */     if (getServer().getPluginManager().getPlugin("iDisguise") == null) {
/* 163 */       return false;
/*     */     }
/* 165 */     RegisteredServiceProvider<DisguiseAPI> rsp = getServer().getServicesManager().getRegistration(DisguiseAPI.class);
/* 166 */     if (rsp == null) {
/* 167 */       return false;
/*     */     }
/* 169 */     dis = (DisguiseAPI)rsp.getProvider();
/* 170 */     return dis != null;
/*     */   }
/*     */   
/*     */   public static DisguiseAPI getDisguises() {
/* 174 */     return dis;
/*     */   }
/*     */   
/*     */   public void RegisterEvents() {
/* 178 */     PluginManager pm = getServer().getPluginManager();
/* 179 */     pm.registerEvents(new ClickEvent(this), this);
/* 180 */     pm.registerEvents(new OnJoin(this), this);
/* 181 */     pm.registerEvents(new InteractOnBlocks(this), this);
/* 182 */     pm.registerEvents(new SpecialBlocks(this), this);
/* 183 */     pm.registerEvents(new CustomDeath(this), this);
/* 184 */     pm.registerEvents(new WoolSytem(this), this);
/* 185 */     pm.registerEvents(new ClickOnMenu(this), this);
/* 186 */     pm.registerEvents(new ClickGuns(this), this);
/* 187 */     pm.registerEvents(new SelectorInfected(this), this);
/* 188 */     pm.registerEvents(new ToDamage(this), this);
/* 189 */     pm.registerEvents(new onQuit(this), this);
/*     */   }
/*     */   
/*     */   public void RegisterGuns() {
/* 193 */     PluginManager pm = getServer().getPluginManager();
/* 194 */     pm.registerEvents(new ShulkerBulletGun(this), this);
/* 195 */     pm.registerEvents(new FangEvokerGun(this), this);
/* 196 */     pm.registerEvents(new PearlGun(this), this);
/* 197 */     pm.registerEvents(new SnowBallGun(this), this);
/* 198 */     pm.registerEvents(new TNTGun(this), this);
/* 199 */     pm.registerEvents(new DragonFireBallGun(this), this);
/* 200 */     pm.registerEvents(new EggsGun(this), this);
/* 201 */     pm.registerEvents(new FireBallGun(this), this);
/* 202 */     pm.registerEvents(new ThunderGun(this), this);
/* 203 */     pm.registerEvents(new WitheSkullGun(this), this);
/* 204 */     pm.registerEvents(new LlamaSpitGun(this), this);
/* 205 */     pm.registerEvents(new FireworkGun(this), this);
/* 206 */     pm.registerEvents(new BlindGun(this), this);
/* 207 */     pm.registerEvents(new BerserkGun(this), this);
/* 208 */     pm.registerEvents(new DarknessGun(this), this);
/* 209 */     pm.registerEvents(new DopingGun(this), this);
/* 210 */     pm.registerEvents(new CatharsisGun(this), this);
/* 211 */     pm.registerEvents(new WizzardGun(this), this);
/* 212 */     pm.registerEvents(new LoveGun(this), this);
/* 213 */     pm.registerEvents(new JokerGun(this), this);
/* 214 */     pm.registerEvents(new VenomGun(this), this);
/* 215 */     pm.registerEvents(new ScapeGun(this), this);
/* 216 */     pm.registerEvents(new HungryGun(this), this);
/* 217 */     pm.registerEvents(new HonestGun(this), this);
/* 218 */     pm.registerEvents(new DopingGun(this), this);
/* 219 */     pm.registerEvents(new PeepingGun(this), this);
/* 220 */     pm.registerEvents(new BlockGun(this), this);
/* 221 */     pm.registerEvents(new CuningGun(this), this);
/* 222 */     pm.registerEvents(new SlowlessGun(this), this);
/* 223 */     pm.registerEvents(new HapeningGun(this), this);
/* 224 */     pm.registerEvents(new ConfuseGun(this), this);
/* 225 */     pm.registerEvents(new CustomGun(this), this);
/*     */   }
/*     */   
/*     */   public void RegisterGameListener()
/*     */   {
/* 230 */     PluginManager pm = getServer().getPluginManager();
/* 231 */     pm.registerEvents(new MoveOnSpawner(), this);
/* 232 */     pm.registerEvents(new PlaceWool(this), this);
/* 233 */     pm.registerEvents(new PickUpWools(this), this);
/* 234 */     pm.registerEvents(new PlaceBanner(this), this);
/* 235 */     pm.registerEvents(new PickUpBanner(this), this);
/*     */   }
/*     */   
/*     */   public void RegisterMapCommands() {
/* 239 */     getCommand("ultimatecapturethewoolmap").setExecutor(new Arenacmd(this));
/*     */   }
/*     */   
/*     */   public void RegisterCommands()
/*     */   {
/* 244 */     getCommand("ultimatecapturethewool").setExecutor(new GameCmd(this));
/*     */   }
/*     */   
/*     */ 
/* 248 */   public void RegisterAdminCommands() { getCommand("ultimatecapturethewooladmin").setExecutor(new AdminCmd()); }
/*     */   
/*     */   public void RegisterConfig() {
/* 251 */     File config = new File(getDataFolder(), "config.yml");
/* 252 */     this.RConfig = config.getPath();
/* 253 */     if (!config.exists()) {
/* 254 */       config.getParentFile().mkdirs();
/* 255 */       saveResource("config.yml", false);
/*     */     }
/*     */   }
/*     */   
/*     */   public Ultimate() {
/* 260 */     instance = this;
/*     */   }
/*     */   
/*     */ 
/* 264 */   public static Ultimate getInstance() { return instance; }
/*     */   
/*     */   public static void RegisterFiles() {
/* 267 */     cm = new ConfigManager();
/* 268 */     cm.RegisterEnchants();
/* 269 */     cm.RegisterGuns();
/* 270 */     cm.RegisterKits();
/* 271 */     cm.RegisterPets();
/* 272 */     cm.RegisterParticles();
/* 273 */     cm.RegisterMessages();
/* 274 */     cm.RegisterAchievements();
/* 275 */     cm.RegisterArenas();
/*     */   }
/*     */   
/*     */   private void RegisterConfiguration() {
/* 279 */     saveDefaultConfig();
/*     */   }
/*     */   
/*     */   public static void RegisterPlayers() {
/* 283 */     pdm = new PlayerDataManager(null);
/* 284 */     pdm.CreatePlayerDataFolder();
/*     */   }
/*     */   
/*     */   public void RegisterMapsFolder() {
/* 288 */     pdm = new PlayerDataManager(null);
/* 289 */     pdm.CreateMapsDataFolder();
/*     */   }
/*     */   
/*     */   public void setGameRule(World world, String rule, String boolea) {
/* 293 */     world.setGameRuleValue(rule, boolea);
/*     */   }
/*     */   
/*     */   public ChunkGenerator getChunkGenerator() {
/* 297 */    return new ChunkGenerator()
/*     */     {
/*     */       @SuppressWarnings("unused")
public ChunkGenerator.ChunkData generateChunkData(World world, Random rando, int x, int y, int z, ChunkGenerator.BiomeGrid chunk) {
/* 300 */         ChunkGenerator.ChunkData chunkData = createChunkData(world);
/* 301 */         for (int i = 0; i < 16; i++) {
/* 302 */           for (int j = 0; j < 16; j++) {
/* 303 */             chunk.setBiome(i, j, Biome.VOID);
/*     */           }
/*     */         }
/* 306 */         return chunkData;
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */   public static WorldManager getWorldManager() {
/* 312 */     return instance.wm;
/*     */   }
/*     */   
/*     */   public static ArenaManager getArenaManager() {
/* 316 */     return instance.am;
/*     */   }
/*     */ }
