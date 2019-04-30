/*     */ package ultimate.main;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ 
/*     */ public class ConfigManager
/*     */ {
/*  14 */   private Ultimate plugin = (Ultimate)Ultimate.getPlugin(Ultimate.class);
/*  15 */   private File messagesFILE = null;
/*  16 */   private File particlesFILE = null;
/*  17 */   private File gunsFILE = null;
/*  18 */   private File kitsFILE = null;
/*  19 */   private File enchantsFILE = null;
/*  20 */   private File petsFILE = null;
/*  21 */   private File achievementsFILE = null;
/*  22 */   private File arenasFILE = null;
/*  23 */   private FileConfiguration arenas = null;
/*  24 */   private FileConfiguration achievements = null;
/*  25 */   private FileConfiguration pets = null;
/*  26 */   private FileConfiguration particles = null;
/*  27 */   private FileConfiguration kits = null;
/*  28 */   private FileConfiguration enchants = null;
/*  29 */   private FileConfiguration guns = null;
/*  30 */   private FileConfiguration messages = null;
/*     */   
/*     */   public static ConfigManager instance;
/*     */   
/*     */   public ConfigManager()
/*     */   {
/*  36 */     instance = this;
/*     */   }
/*     */   
/*     */   public FileConfiguration getEnchants()
/*     */   {
/*  41 */     if (this.enchants == null) {
/*  42 */       reloadEnchants();
/*     */     }
/*     */     
/*  45 */     return this.enchants;
/*     */   }
/*     */   
/*     */   public void reloadEnchants() {
/*  49 */     if (this.enchants == null) {
/*  50 */       this.enchantsFILE = new File(this.plugin.getDataFolder(), "enchants.yml");
/*     */     }
/*  52 */     this.enchants = YamlConfiguration.loadConfiguration(this.enchantsFILE);
/*     */     try
/*     */     {
/*  55 */       java.io.Reader defConfigStream = new java.io.InputStreamReader(this.plugin.getResource("enchants.yml"), "UTF8");
/*  56 */       if (defConfigStream != null) {
/*  57 */         YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
/*  58 */         this.enchants.setDefaults(defConfig);
/*     */       }
/*     */     }
/*     */     catch (UnsupportedEncodingException u)
/*     */     {
/*  63 */       u.printStackTrace();
/*  64 */       Bukkit.getConsoleSender()
/*  65 */         .sendMessage(ChatColor.LIGHT_PURPLE + "Invalid Configuration in enchants.yml");
/*     */     }
/*     */   }
/*     */   
/*     */   public void SaveEnchants() {
/*     */     try {
/*  71 */       this.enchants.save(this.enchantsFILE);
/*     */     } catch (IOException I) {
/*  73 */       I.printStackTrace();
/*  74 */       Bukkit.getConsoleSender()
/*  75 */         .sendMessage(ChatColor.LIGHT_PURPLE + "Could not save enchants.yml!");
/*     */     }
/*     */   }
/*     */   
/*     */   public void RegisterEnchants() {
/*  80 */     this.enchantsFILE = new File(this.plugin.getDataFolder(), "enchants.yml");
/*  81 */     if (!this.enchantsFILE.exists()) {
/*  82 */       getEnchants().options().copyDefaults(true);
/*  83 */       Bukkit.getConsoleSender()
/*  84 */         .sendMessage(ChatColor.LIGHT_PURPLE + "The Config enchants.yml does not exist,creating file");
/*  85 */       SaveEnchants();
/*     */     }
/*     */     else {
/*  88 */       Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "Enchants loaded");
/*     */     }
/*     */   }
/*     */   
/*  92 */   public FileConfiguration getKits() { if (this.kits == null) {
/*  93 */       reloadKits();
/*     */     }
/*     */     
/*  96 */     return this.kits;
/*     */   }
/*     */   
/*     */   public void reloadKits() {
/* 100 */     if (this.kits == null) {
/* 101 */       this.kitsFILE = new File(this.plugin.getDataFolder(), "kits.yml");
/*     */     }
/*     */     
/* 104 */     this.kits = YamlConfiguration.loadConfiguration(this.kitsFILE);
/*     */     try
/*     */     {
/* 107 */       java.io.Reader defConfigStream = new java.io.InputStreamReader(this.plugin.getResource("kits.yml"), "UTF8");
/* 108 */       if (defConfigStream != null) {
/* 109 */         YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
/* 110 */         this.kits.setDefaults(defConfig);
/*     */       }
/*     */     }
/*     */     catch (UnsupportedEncodingException u)
/*     */     {
/* 115 */       u.printStackTrace();
/* 116 */       Bukkit.getConsoleSender()
/* 117 */         .sendMessage(ChatColor.YELLOW + "kits Invalid Config");
/*     */     }
/*     */   }
/*     */   
/*     */   public void SaveKits() {
/*     */     try {
/* 123 */       this.kits.save(this.kitsFILE);
/*     */     } catch (IOException I) {
/* 125 */       I.printStackTrace();
/* 126 */       Bukkit.getConsoleSender()
/* 127 */         .sendMessage(ChatColor.YELLOW + "Could not save kits.yml!");
/*     */     }
/*     */   }
/*     */   
/*     */   public void RegisterKits() {
/* 132 */     this.kitsFILE = new File(this.plugin.getDataFolder(), "kits.yml");
/* 133 */     if (!this.kitsFILE.exists()) {
/* 134 */       getKits().options().copyDefaults(true);
/* 135 */       Bukkit.getConsoleSender()
/* 136 */         .sendMessage(ChatColor.LIGHT_PURPLE + "The Config kits.yml does not exist,creating file");
/* 137 */       SaveKits();
/*     */     }
/*     */     else {
/* 140 */       Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "Kits loaded");
/*     */     }
/*     */   }
/*     */   
/* 144 */   public FileConfiguration getGuns() { if (this.guns == null) {
/* 145 */       reloadGuns();
/*     */     }
/*     */     
/* 148 */     return this.guns;
/*     */   }
/*     */   
/*     */   public void reloadGuns() {
/* 152 */     if (this.guns == null) {
/* 153 */       this.gunsFILE = new File(this.plugin.getDataFolder(), "guns.yml");
/*     */     }
/*     */     
/* 156 */     this.guns = YamlConfiguration.loadConfiguration(this.gunsFILE);
/*     */     try
/*     */     {
/* 159 */       java.io.Reader defConfigStream = new java.io.InputStreamReader(this.plugin.getResource("guns.yml"), "UTF8");
/* 160 */       if (defConfigStream != null) {
/* 161 */         YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
/* 162 */         this.guns.setDefaults(defConfig);
/*     */       }
/*     */     }
/*     */     catch (UnsupportedEncodingException u)
/*     */     {
/* 167 */       u.printStackTrace();
/* 168 */       Bukkit.getConsoleSender()
/* 169 */         .sendMessage(ChatColor.RED + "guns Invalid Configuration");
/*     */     }
/*     */   }
/*     */   
/*     */   public void SaveGuns() {
/*     */     try {
/* 175 */       this.guns.save(this.gunsFILE);
/*     */     } catch (IOException I) {
/* 177 */       I.printStackTrace();
/* 178 */       Bukkit.getConsoleSender()
/* 179 */         .sendMessage(ChatColor.RED + "Could not save guns.yml!");
/*     */     }
/*     */   }
/*     */   
/*     */   public void RegisterGuns() {
/* 184 */     this.gunsFILE = new File(this.plugin.getDataFolder(), "guns.yml");
/* 185 */     if (!this.gunsFILE.exists()) {
/* 186 */       getGuns().options().copyDefaults(true);
/* 187 */       Bukkit.getConsoleSender()
/* 188 */         .sendMessage(ChatColor.LIGHT_PURPLE + "The Config guns.yml does not exist,creating file");
/* 189 */       SaveGuns();
/*     */     }
/*     */     else {
/* 192 */       Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Guns loaded");
/*     */     }
/*     */   }
/*     */   
/* 196 */   public FileConfiguration getParticles() { if (this.particles == null) {
/* 197 */       reloadParticles();
/*     */     }
/*     */     
/* 200 */     return this.particles;
/*     */   }
/*     */   
/*     */   public void reloadParticles() {
/* 204 */     if (this.particles == null) {
/* 205 */       this.particlesFILE = new File(this.plugin.getDataFolder(), "particles.yml");
/*     */     }
/*     */     
/* 208 */     this.particles = YamlConfiguration.loadConfiguration(this.particlesFILE);
/*     */     try
/*     */     {
/* 211 */       java.io.Reader defConfigStream = new java.io.InputStreamReader(this.plugin.getResource("particles.yml"), "UTF8");
/* 212 */       if (defConfigStream != null) {
/* 213 */         YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
/* 214 */         this.particles.setDefaults(defConfig);
/*     */       }
/*     */     }
/*     */     catch (UnsupportedEncodingException u)
/*     */     {
/* 219 */       u.printStackTrace();
/* 220 */       Bukkit.getConsoleSender()
/* 221 */         .sendMessage(ChatColor.DARK_AQUA + "Particles Invalid Configuration");
/*     */     }
/*     */   }
/*     */   
/*     */   public void SaveParticles() {
/*     */     try {
/* 227 */       this.particles.save(this.particlesFILE);
/*     */     } catch (IOException I) {
/* 229 */       I.printStackTrace();
/* 230 */       Bukkit.getConsoleSender()
/* 231 */         .sendMessage(ChatColor.DARK_AQUA + "Could not save particles.yml!");
/*     */     }
/*     */   }
/*     */   
/*     */   public void RegisterParticles() {
/* 236 */     this.particlesFILE = new File(this.plugin.getDataFolder(), "particles.yml");
/* 237 */     if (!this.particlesFILE.exists()) {
/* 238 */       getParticles().options().copyDefaults(true);
/* 239 */       Bukkit.getConsoleSender()
/* 240 */         .sendMessage(ChatColor.LIGHT_PURPLE + "The Config particles.yml does not exist,creating file");
/* 241 */       SaveParticles();
/*     */     }
/*     */     else {
/* 244 */       Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA + "Particles loaded");
/*     */     }
/*     */   }
/*     */   
/* 248 */   public FileConfiguration getPets() { if (this.pets == null) {
/* 249 */       reloadPets();
/*     */     }
/*     */     
/* 252 */     return this.pets;
/*     */   }
/*     */   
/*     */   public void reloadPets() {
/* 256 */     if (this.pets == null) {
/* 257 */       this.petsFILE = new File(this.plugin.getDataFolder(), "pets.yml");
/*     */     }
/*     */     
/* 260 */     this.pets = YamlConfiguration.loadConfiguration(this.petsFILE);
/*     */     try
/*     */     {
/* 263 */       java.io.Reader defConfigStream = new java.io.InputStreamReader(this.plugin.getResource("pets.yml"), "UTF8");
/* 264 */       if (defConfigStream != null) {
/* 265 */         YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
/* 266 */         this.pets.setDefaults(defConfig);
/*     */       }
/*     */     }
/*     */     catch (UnsupportedEncodingException u)
/*     */     {
/* 271 */       u.printStackTrace();
/* 272 */       Bukkit.getConsoleSender()
/* 273 */         .sendMessage(ChatColor.GREEN + "Pets Invalid Configuration");
/*     */     }
/*     */   }
/*     */   
/*     */   public void SavePets() {
/*     */     try {
/* 279 */       this.pets.save(this.petsFILE);
/*     */     } catch (IOException I) {
/* 281 */       I.printStackTrace();
/* 282 */       Bukkit.getConsoleSender()
/* 283 */         .sendMessage(ChatColor.GREEN + "Could not save pets.yml!");
/*     */     }
/*     */   }
/*     */   
/*     */   public void RegisterPets() {
/* 288 */     this.petsFILE = new File(this.plugin.getDataFolder(), "pets.yml");
/* 289 */     if (!this.petsFILE.exists()) {
/* 290 */       getPets().options().copyDefaults(true);
/* 291 */       Bukkit.getConsoleSender()
/* 292 */         .sendMessage(ChatColor.LIGHT_PURPLE + "The Config pets.yml does not exist,creating file");
/* 293 */       SavePets();
/*     */     }
/*     */     else {
/* 296 */       Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Pets loaded");
/*     */     }
/*     */   }
/*     */   
/* 300 */   public FileConfiguration getMessages() { if (this.messages == null) {
/* 301 */       ReloadMessages();
/*     */     }
/*     */     
/* 304 */     return this.messages;
/*     */   }
/*     */   
/*     */   public void ReloadMessages() {
/* 308 */     if (this.messages == null) {
/* 309 */       this.messagesFILE = new File(this.plugin.getDataFolder(), "messages.yml");
/*     */     }
/*     */     
/* 312 */     this.messages = YamlConfiguration.loadConfiguration(this.messagesFILE);
/*     */     try
/*     */     {
/* 315 */       java.io.Reader defConfigStream = new java.io.InputStreamReader(this.plugin.getResource("messages.yml"), "UTF8");
/* 316 */       if (defConfigStream != null) {
/* 317 */         YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
/* 318 */         this.messages.setDefaults(defConfig);
/*     */       }
/*     */     }
/*     */     catch (UnsupportedEncodingException u)
/*     */     {
/* 323 */       u.printStackTrace();
/* 324 */       Bukkit.getConsoleSender()
/* 325 */         .sendMessage(ChatColor.DARK_PURPLE + "Messages Invalid Configuration");
/*     */     }
/*     */   }
/*     */   
/*     */   public void SaveMessages() {
/*     */     try {
/* 331 */       this.messages.save(this.messagesFILE);
/*     */     } catch (IOException I) {
/* 333 */       I.printStackTrace();
/* 334 */       Bukkit.getConsoleSender()
/* 335 */         .sendMessage(ChatColor.DARK_PURPLE + "Could not save messsages.yml!");
/*     */     }
/*     */   }
/*     */   
/*     */   public void RegisterMessages() {
/* 340 */     this.messagesFILE = new File(this.plugin.getDataFolder(), "messages.yml");
/* 341 */     if (!this.messagesFILE.exists()) {
/* 342 */       getMessages().options().copyDefaults(true);
/* 343 */       Bukkit.getConsoleSender()
/* 344 */         .sendMessage(ChatColor.LIGHT_PURPLE + "The Config messages.yml does not exist,creating file");
/* 345 */       SaveMessages();
/*     */     }
/*     */     else {
/* 348 */       Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_PURPLE + "Messages loaded");
/*     */     }
/*     */   }
/*     */   
/* 352 */   public FileConfiguration getAchievements() { if (this.achievements == null) {
/* 353 */       reloadAchievements();
/*     */     }
/*     */     
/* 356 */     return this.achievements;
/*     */   }
/*     */   
/*     */   public void reloadAchievements() {
/* 360 */     if (this.achievements == null) {
/* 361 */       this.achievementsFILE = new File(this.plugin.getDataFolder(), "achievements.yml");
/*     */     }
/*     */     
/* 364 */     this.achievements = YamlConfiguration.loadConfiguration(this.achievementsFILE);
/*     */     try
/*     */     {
/* 367 */       java.io.Reader defConfigStream = new java.io.InputStreamReader(this.plugin.getResource("achievements.yml"), "UTF8");
/* 368 */       if (defConfigStream != null) {
/* 369 */         YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
/* 370 */         this.achievements.setDefaults(defConfig);
/*     */       }
/*     */     }
/*     */     catch (UnsupportedEncodingException u)
/*     */     {
/* 375 */       u.printStackTrace();
/* 376 */       Bukkit.getConsoleSender()
/* 377 */         .sendMessage(ChatColor.GOLD + "Achievements Invalid Configuration");
/*     */     }
/*     */   }
/*     */   
/*     */   public void SaveAchivements() {
/*     */     try {
/* 383 */       this.achievements.save(this.achievementsFILE);
/*     */     } catch (IOException I) {
/* 385 */       I.printStackTrace();
/* 386 */       Bukkit.getConsoleSender()
/* 387 */         .sendMessage(ChatColor.GOLD + "Could not save achievements.yml!");
/*     */     }
/*     */   }
/*     */   
/*     */   public void RegisterAchievements() {
/* 392 */     this.achievementsFILE = new File(this.plugin.getDataFolder(), "achievements.yml");
/* 393 */     if (!this.achievementsFILE.exists()) {
/* 394 */       getAchievements().options().copyDefaults(true);
/* 395 */       Bukkit.getConsoleSender()
/* 396 */         .sendMessage(ChatColor.LIGHT_PURPLE + "The Config achievements.yml does not exist,creating file");
/* 397 */       SaveAchivements();
/*     */     }
/*     */     else {
/* 400 */       Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Achievements loaded");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public FileConfiguration getArenas()
/*     */   {
/* 408 */     if (this.arenas == null) {
/* 409 */       reloadArenas();
/*     */     }
/*     */     
/* 412 */     return this.arenas;
/*     */   }
/*     */   
/*     */   public void reloadArenas() {
/* 416 */     if (this.arenas == null) {
/* 417 */       this.arenasFILE = new File(this.plugin.getDataFolder(), "arenas.yml");
/*     */     }
/* 419 */     this.arenas = YamlConfiguration.loadConfiguration(this.arenasFILE);
/*     */     try
/*     */     {
/* 422 */       java.io.Reader defConfigStream = new java.io.InputStreamReader(this.plugin.getResource("arenas.yml"), "UTF8");
/* 423 */       if (defConfigStream != null) {
/* 424 */         YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
/* 425 */         this.arenas.setDefaults(defConfig);
/*     */       }
/*     */     }
/*     */     catch (UnsupportedEncodingException u)
/*     */     {
/* 430 */       u.printStackTrace();
/* 431 */       Bukkit.getConsoleSender()
/* 432 */         .sendMessage(ChatColor.RED + "Invalid Configuration in arenas.yml");
/*     */     }
/*     */   }
/*     */   
/*     */   public void SaveArenas() {
/*     */     try {
/* 438 */       this.arenas.save(this.arenasFILE);
/*     */     } catch (IOException I) {
/* 440 */       I.printStackTrace();
/* 441 */       Bukkit.getConsoleSender()
/* 442 */         .sendMessage(ChatColor.RED + "Could not save arenas.yml!");
/*     */     }
/*     */   }
/*     */   
/*     */   public void RegisterArenas() {
/* 447 */     this.arenasFILE = new File(this.plugin.getDataFolder(), "arenas.yml");
/* 448 */     if (!this.arenasFILE.exists()) {
/* 449 */       getArenas().options().copyDefaults(true);
/* 450 */       Bukkit.getConsoleSender()
/* 451 */         .sendMessage(ChatColor.RED + "The Config arenas.yml does not exist,creating file");
/* 452 */       SaveArenas();
/*     */     }
/*     */     else {
/* 455 */       Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Arenas loaded");
/*     */     }
/*     */   }
/*     */   
/*     */   public File getArenasFile() {
/* 460 */     return this.arenasFILE;
/*     */   }
/*     */   
/*     */   public static ConfigManager getInstance()
/*     */   {
/* 465 */     return instance;
/*     */   }
/*     */ }
