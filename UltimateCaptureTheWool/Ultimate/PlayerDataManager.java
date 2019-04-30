/*     */ package ultimate.main;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Sound;
/*     */
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public class PlayerDataManager
/*     */ {
/*     */   UUID u;
/*     */   File Pdata;
/*     */   File mdata;
/*     */   FileConfiguration PdataCg;
/*     */   
/*     */   public PlayerDataManager(UUID u)
/*     */   {
/*  24 */     this.u = u;
/*     */     
/*  26 */     this.Pdata = new File("plugins/UltimateCaptureTheWool/player_data/" + u + ".yml");
/*  27 */     this.PdataCg = YamlConfiguration.loadConfiguration(this.Pdata);
/*     */   }
/*     */   
/*  30 */   public void CreatePlayerDataFolder() { File folder = new File(Ultimate.getInstance().getDataFolder() + File.separator + "player_data");
/*  31 */     if (!folder.exists()) folder.mkdirs();
/*  32 */     File arenas = new File(Ultimate.getInstance().getDataFolder() + File.separator + "arenas");
/*  33 */     File kit = new File(Ultimate.getInstance().getDataFolder() + File.separator + "kits");
/*  34 */     if (!arenas.exists()) arenas.mkdirs();
/*  35 */     if (!kit.exists()) kit.mkdirs();
/*     */   }
/*     */   
/*     */   public void CreatePlayerData() {
/*     */     try {
/*  40 */       this.Pdata.createNewFile();
/*     */     } catch (IOException IO) {
/*  42 */       IO.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void PlayerDefaults() {
/*  47 */     if (this.Pdata.length() <= 0L) {
/*  48 */       this.PdataCg.set("Stats.XP", Integer.valueOf(Ultimate.getInstance().getConfig().getInt("FirstJoin.DefaultXpOnFirstJoin")));
/*  49 */       this.PdataCg.set("Stats.Wins", Integer.valueOf(0));
/*  50 */       this.PdataCg.set("Stats.Losses", Integer.valueOf(0));
/*  51 */       this.PdataCg.set("Stats.Kills", Integer.valueOf(0));
/*  52 */       this.PdataCg.set("Stats.Deaths", Integer.valueOf(0));
/*  53 */       this.PdataCg.set("Stats.??????", Integer.valueOf(0));
/*  54 */       this.PdataCg.set("Stats.Money", Integer.valueOf(Ultimate.getInstance().getConfig().getInt("FirstJoin.DefaultEcoOnFirstJoin")));
/*  55 */       this.PdataCg.set("Stats.Kilsound", "NONE");
/*  56 */       this.PdataCg.set("Stats.Deathsound", "NONE");
/*  57 */       this.PdataCg.set("Stats.Particles", "NONE");
/*  58 */       this.PdataCg.set("Stats.Projectile", "NONE");
/*  59 */       this.PdataCg.set("Stats.HitSound", "NONE");
/*  60 */       this.PdataCg.set("Stats.Name", "%PLAYER%");
/*  61 */       this.PdataCg.set("Stats.Permissions", "NONE");
/*  62 */       this.PdataCg.set("Stats.Achievements", "NONE");
/*  63 */       AddDefaultsPermissions();
/*  64 */       AddDefaultsAchievement();
/*  65 */       SavePlayerConfig();
/*     */     }
/*     */   }
/*     */   
/*     */   public FileConfiguration getPlayerConfig() {
/*  70 */     return this.PdataCg;
/*     */   }
/*     */   
/*     */   public void SavePlayerConfig() {
/*     */     try {
/*  75 */       getPlayerConfig().save(this.Pdata);
/*     */     } catch (IOException e) {
/*  77 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public double GetPlayerMoney()
/*     */   {
/*  83 */     return this.PdataCg.getDouble("Stats.Money");
/*     */   }
/*     */   
/*     */   public void SetPlayerMoney(double amount) {
/*  87 */     this.PdataCg.set("Stats.Money", Double.valueOf(amount));
/*  88 */     SavePlayerConfig();
/*     */   }
/*     */   
/*     */   public void TakeMoney(double amount) {
/*  92 */     this.PdataCg.set("Stats.Money", Double.valueOf(GetPlayerMoney() - amount));
/*  93 */     SavePlayerConfig();
/*     */   }
/*     */   
/*     */   public void GiveMoney(double amount) {
/*  97 */     this.PdataCg.set("Stats.Money", Double.valueOf(GetPlayerMoney() + amount));
/*  98 */     SavePlayerConfig();
/*     */   }
/*     */   
/*     */   public double GetPlayerXP() {
/* 102 */     return this.PdataCg.getDouble("Stats.XP");
/*     */   }
/*     */   
/*     */   public void SetPlayerXP(double amount) {
/* 106 */     this.PdataCg.set("Stats.XP", Double.valueOf(amount));
/* 107 */     SavePlayerConfig();
/*     */   }
/*     */   
/*     */   public void TakeXP(double amount) {
/* 111 */     this.PdataCg.set("Stats.XP", Double.valueOf(GetPlayerXP() - amount));
/* 112 */     SavePlayerConfig();
/*     */   }
/*     */   
/*     */   public void GiveXP(double amount) {
/* 116 */     this.PdataCg.set("Stats.XP", Double.valueOf(GetPlayerXP() + amount));
/* 117 */     SavePlayerConfig();
/*     */   }
/*     */   
/*     */   public int GetPlayerKills()
/*     */   {
/* 122 */     return this.PdataCg.getInt("Stats.Kills");
/*     */   }
/*     */   
/*     */   public void addPlayerKills(int amount) {
/* 126 */     this.PdataCg.set("Stats.Kills", Integer.valueOf(GetPlayerKills() + amount));
/* 127 */     SavePlayerConfig();
/*     */   }
/*     */   
/*     */   public void RemoveKills(int amount) {
/* 131 */     this.PdataCg.set("Stats.Kills", Integer.valueOf(GetPlayerKills() - amount));
/* 132 */     SavePlayerConfig();
/*     */   }
/*     */   
/*     */   public void SetPlayerKills(int amount) {
/* 136 */     this.PdataCg.set("Stats.Kills", Integer.valueOf(amount));
/* 137 */     SavePlayerConfig();
/*     */   }
/*     */   
/*     */   public int GetPlayerDeaths() {
/* 141 */     return this.PdataCg.getInt("Stats.Deaths");
/*     */   }
/*     */   
/*     */   public void addPlayerDeaths(int amount) {
/* 145 */     this.PdataCg.set("Stats.Deaths", Integer.valueOf(GetPlayerDeaths() + amount));
/* 146 */     SavePlayerConfig();
/*     */   }
/*     */   
/*     */   public void RemoveDeaths(int amount) {
/* 150 */     this.PdataCg.set("Stats.Deaths", Integer.valueOf(GetPlayerDeaths() - amount));
/* 151 */     SavePlayerConfig();
/*     */   }
/*     */   
/*     */   public void SetPlayerDeaths(int amount) {
/* 155 */     this.PdataCg.set("Stats.Deaths", Integer.valueOf(amount));
/* 156 */     SavePlayerConfig();
/*     */   }
/*     */   
/*     */   public int GetPlayerLosses() {
/* 160 */     return this.PdataCg.getInt("Stats.Losses");
/*     */   }
/*     */   
/*     */   public void SetPlayerLosses(int amount) {
/* 164 */     this.PdataCg.set("Stats.Losses", Integer.valueOf(amount));
/* 165 */     SavePlayerConfig();
/*     */   }
/*     */   
/*     */   public void AddPlayerLosses(int amount) {
/* 169 */     this.PdataCg.set("Stats.Losses", Integer.valueOf(GetPlayerLosses() + amount));
/* 170 */     SavePlayerConfig();
/*     */   }
/*     */   
/*     */   public void RemoveLosses(int amount) {
/* 174 */     this.PdataCg.set("Stats.Losses", Integer.valueOf(GetPlayerLosses() - amount));
/* 175 */     SavePlayerConfig();
/*     */   }
/*     */   
/*     */   public int GetPlayerWins() {
/* 179 */     return this.PdataCg.getInt("Stats.Wins");
/*     */   }
/*     */   
/*     */   public void SetPlayerWins(int amount) {
/* 183 */     this.PdataCg.set("Stats.Wins", Integer.valueOf(amount));
/* 184 */     SavePlayerConfig();
/*     */   }
/*     */   
/*     */   public void addPlayerWins(int amount) {
/* 188 */     this.PdataCg.set("Stats.Wins", Integer.valueOf(GetPlayerWins() + amount));
/* 189 */     SavePlayerConfig();
/*     */   }
/*     */   
/* 192 */   public void RemoveWins(int amount) { this.PdataCg.set("Stats.Wins", Integer.valueOf(GetPlayerWins() - amount));
/* 193 */     SavePlayerConfig();
/*     */   }
/*     */   
/*     */   public Sound GetDeathSound()
/*     */   {
/* 198 */     return Sound.valueOf(this.PdataCg.getString("Stats.Deathsound"));
/*     */   }
/*     */   
/*     */   public void SetDeathSound(String sound) {
/* 202 */     this.PdataCg.set("Stats.Deathsound", sound);
/* 203 */     SavePlayerConfig();
/*     */   }
/*     */   
/* 206 */   public Sound GetKillSound() { return Sound.valueOf(this.PdataCg.getString("Stats.Kilsound").toUpperCase()); }
/*     */   
/*     */   public void SetKillSound(String sound)
/*     */   {
/* 210 */     this.PdataCg.set("Stats.Kilsound", sound);
/* 211 */     SavePlayerConfig();
/*     */   }
/*     */   
/*     */   public Sound GetHitSound() {
/* 215 */     return Sound.valueOf(this.PdataCg.getString("Stats.HitSound").toUpperCase());
/*     */   }
/*     */   
/*     */   public void SetHitSound(String sound) {
/* 219 */     this.PdataCg.set("Stats.HitSound", sound);
/* 220 */     SavePlayerConfig();
/*     */   }
/*     */   
/*     */   public String GetParticle() {
/* 224 */     return this.PdataCg.getString("Stats.Particles");
/*     */   }
/*     */   
/*     */   public void SetParticle(String particle) {
/* 228 */     this.PdataCg.set("Stats.Particles", particle);
/* 229 */     SavePlayerConfig();
/*     */   }
/*     */   
/*     */   public String GetProjectile() {
/* 233 */     return this.PdataCg.getString("Stats.Projectile");
/*     */   }
/*     */   
/*     */   public void SetProjectile(String particle) {
/* 237 */     this.PdataCg.set("Stats.Projectile", particle);
/* 238 */     SavePlayerConfig();
/*     */   }
/*     */   
/* 241 */   public boolean AddAchievement(String achievement) { List<String> efective = this.PdataCg.getStringList("Stats.Achievements");
/* 242 */     if (efective.contains(achievement)) {
/* 243 */       return false;
/*     */     }
/* 245 */     efective.add(achievement);
/* 246 */     this.PdataCg.set("Stats.Achievements", efective);
/* 247 */     SavePlayerConfig();
/* 248 */     return true;
/*     */   }
/*     */   
/*     */   public boolean RemoveAchievement(String achievement)
/*     */   {
/* 253 */     List<String> efective = this.PdataCg.getStringList("Stats.Achievements");
/* 254 */     if (efective.contains(achievement)) {
/* 255 */       efective.remove(achievement);
/* 256 */       this.PdataCg.set("Stats.Achievements", efective);
/* 257 */       SavePlayerConfig();
/* 258 */       return true;
/*     */     }
/* 260 */     return false;
/*     */   }
/*     */   
/*     */   public boolean CheckAchievement(String achievement)
/*     */   {
/* 265 */     List<String> check = this.PdataCg.getStringList("Stats.Achievements");
/* 266 */     return check.contains(achievement);
/*     */   }
/*     */   
/*     */   public boolean ClearAchievement() {
/* 270 */     List<String> check = this.PdataCg.getStringList("Stats.Achievements");
/* 271 */     check.clear();
/* 272 */     SavePlayerConfig();
/* 273 */     return true;
/*     */   }
/*     */   
/* 276 */   public void AddDefaultsAchievement() { List<String> achievements = Ultimate.getInstance().getConfig().getStringList("FirstJoin.DefaultAchievements");
/* 277 */     List<String> adq = this.PdataCg.getStringList("Stats.Achievements");
/* 278 */     adq.addAll(achievements);
/* 279 */     this.PdataCg.set("Stats.Achievements", achievements);
/* 280 */     SavePlayerConfig();
/*     */   }
/*     */   
/* 283 */   public void Playername(Player p) { this.PdataCg.set("Stats.Name", "%PLAYER%".replace("%PLAYER%", p.getName()));
/* 284 */     SavePlayerConfig();
/*     */   }
/*     */   
/*     */   public int GetPlayerCoins() {
/* 288 */     return this.PdataCg.getInt("Stats.Points");
/*     */   }
/*     */   
/*     */   public void TakeCoins(int amount)
/*     */   {
/* 293 */     this.PdataCg.set("Stats.Points", Integer.valueOf(GetPlayerCoins() - amount));
/* 294 */     SavePlayerConfig();
/*     */   }
/*     */   
/*     */   public boolean CheckCoins(int point)
/*     */   {
/* 299 */     if (this.PdataCg.getInt("Stats.Point") >= point) {
/* 300 */       return true;
/*     */     }
/* 302 */     return false;
/*     */   }
/*     */   
/*     */   public boolean AddPermission(String permission) {
/* 306 */     List<String> efective = this.PdataCg.getStringList("Stats.Permissions");
/* 307 */     if (efective.contains(permission)) {
/* 308 */       return false;
/*     */     }
/* 310 */     efective.add(permission);
/* 311 */     this.PdataCg.set("Stats.Permissions", efective);
/* 312 */     SavePlayerConfig();
/* 313 */     return true;
/*     */   }
/*     */   
/*     */   public boolean RemovePermission(String permission)
/*     */   {
/* 318 */     List<String> efective = this.PdataCg.getStringList("Stats.Permissions");
/* 319 */     if (efective.contains(permission)) {
/* 320 */       efective.remove(permission);
/* 321 */       this.PdataCg.set("Stats.Permissions", efective);
/* 322 */       SavePlayerConfig();
/* 323 */       return true;
/*     */     }
/* 325 */     return false;
/*     */   }
/*     */   
/*     */   public boolean CheckPermission(String permission) {
/* 329 */     List<String> check = this.PdataCg.getStringList("Stats.Permissions");
/* 330 */     return check.contains(permission);
/*     */   }
/*     */   
/*     */   public boolean ClearPermission() {
/* 334 */     List<String> check = this.PdataCg.getStringList("Stats.Permissions");
/* 335 */     check.clear();
/* 336 */     return true;
/*     */   }
/*     */   
/* 339 */   public void AddDefaultsPermissions() { List<String> permissions = Ultimate.getInstance().getConfig().getStringList("FirstJoin.DefaultPermissions");
/* 340 */     List<String> adq = this.PdataCg.getStringList("Stats.Permissions");
/* 341 */     adq.addAll(permissions);
/* 342 */     this.PdataCg.set("Stats.Permissions", permissions);
/* 343 */     SavePlayerConfig();
/*     */   }
/*     */   
/*     */   public boolean CheckMoney(double money) {
/* 347 */     if (this.PdataCg.getDouble("Stats.Money") >= money) {
/* 348 */       return true;
/*     */     }
/* 350 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean CheckXp(double xp)
/*     */   {
/* 356 */     if (this.PdataCg.getDouble("Stats.XP") >= xp) {
/* 357 */       return true;
/*     */     }
/* 359 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 364 */   public float SetDisplayXp() { return this.PdataCg.getInt("Stats.XP"); }
/*     */   
/*     */   public void CreateMapsDataFolder() {
/* 367 */     this.mdata = new File(Ultimate.getInstance().getDataFolder() + File.separator + "mapdata");
/* 368 */     if (!this.mdata.exists()) {
/* 369 */       Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "The directory mapdata does not exist, creating new file");
/* 370 */       this.mdata.mkdirs();
/*     */     }
/*     */   }
/*     */ }

