/*     */ package gamemanager;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Difficulty;
/*     */ 
/*     */ import org.bukkit.World;
/*     */ 
/*     */ import org.bukkit.WorldCreator;
/*     */
/*     */ import ultimate.main.Ultimate;
/*     */ 
/*     */ public class WorldManager
/*     */ {
/*     */   public World CreateEmptyWorld(String name, World.Environment enviroment)
/*     */   {
/*  25 */     if (Bukkit.getWorld(name) == null) {
/*  26 */       loadWorld(name, enviroment);
/*  27 */       return Bukkit.getWorld(name);
/*     */     }
/*  29 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean loadWorld(String name, World.Environment enviroment)
/*     */   {
/*  37 */     WorldCreator creator = new WorldCreator(name);
/*  38 */     creator.environment(enviroment);
/*  39 */     creator.generateStructures(false);
/*  40 */     creator.generatorSettings("2;0;1");
/*  41 */     Ultimate.getInstance().getChunkGenerator();
/*  42 */     World world = creator.createWorld();
/*  43 */     world.setSpawnFlags(true, true);
/*  44 */     world.setDifficulty(Difficulty.NORMAL);
/*  45 */     world.setPVP(true);
/*  46 */     world.setStorm(false);
/*  47 */     world.setThundering(false);
/*  48 */     world.setWeatherDuration(Integer.MAX_VALUE);
/*  49 */     world.setKeepSpawnInMemory(false);
/*  50 */     world.setTicksPerAnimalSpawns(1);
/*  51 */     world.setTicksPerMonsterSpawns(1);
/*  52 */     world.setAutoSave(false);
/*  53 */     Ultimate.getInstance().setGameRule(world, "doMobSpawning", "false");
/*  54 */     Ultimate.getInstance().setGameRule(world, "mobGriefing", "false");
/*  55 */     Ultimate.getInstance().setGameRule(world, "doFireTick", "false");
/*  56 */     Ultimate.getInstance().setGameRule(world, "showDeathMessages", "false");
/*  57 */     Ultimate.getInstance().setGameRule(world, "announceAdvancements", "false");
/*  58 */     boolean loaded = false;
/*  59 */     Iterator<World> var1 = Ultimate.getInstance().getServer().getWorlds().iterator();
/*     */     
/*  61 */     while (var1.hasNext()) {
/*  62 */       World w = (World)var1.next();
/*  63 */       if (w.getName().equals(w.getName())) {
/*  64 */         loaded = true;
/*  65 */         break;
/*     */       }
/*     */     }
/*  68 */     return loaded;
/*     */   }
/*     */   
/*     */   public void unloadWorld(String w)
/*     */   {
/*  73 */     World world = Ultimate.getInstance().getServer().getWorld(w);
/*  74 */     if (world != null) {
/*  75 */       Ultimate.getInstance().getServer().unloadWorld(w, false);
/*     */     }
/*     */   }
/*     */   
/*     */   public void copyWorld(File source, File target) {
/*     */     try {
/*  81 */       ArrayList<String> ignore = new ArrayList<>(Arrays.asList(new String[] { "uid.dat", "session.dat", "session.lock" }));
/*  82 */       if (!ignore.contains(source.getName()))
/*     */       {
/*  84 */         if (source.isDirectory()) {
/*  85 */           if ((!target.exists()) && (target.mkdirs())) {
/*  86 */             String[] files = source.list();
/*  87 */             if (files != null) {
/*  88 */               String[] var3 = files;
/*  89 */               int var4 = files.length;
/*     */               
/*  91 */               for (int lenght = 0; lenght < var4; lenght++) {
/*  92 */                 String file = var3[lenght];
/*  93 */                 File srcFILE = new File(source, file);
/*  94 */                 File destFILE = new File(target, file);
/*  95 */                 copyWorld(srcFILE, destFILE);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         else {
/* 101 */           InputStream en = new FileInputStream(source);
/* 102 */           OutputStream ot = new FileOutputStream(target);
/* 103 */           byte[] buf = new byte[1866];
/*     */           int lenght;
/* 105 */           while ((lenght = en.read(buf)) > 0){ 
/* 106 */             ot.write(buf, 0, lenght);
/*     */           }
/*     */           
/* 109 */           en.close();
/* 110 */           ot.close();
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (IOException var1) {
/* 115 */       Ultimate.getInstance().getServer().getConsoleSender().sendMessage("Failed to copy world");
/*     */     }
/*     */   }
/*     */   
/*     */   public void deleteWorld(String name) {
/* 120 */     unloadWorld(name);
/* 121 */     File target = new File(Ultimate.getInstance().getServer().getWorldContainer().getAbsolutePath(), name);
/* 122 */     deleteWorld(target);
/*     */   }
/*     */   
/*     */   public void deleteWorld(File path)
/*     */   {
/* 127 */     if (path.exists()) {
/* 128 */       File[] files = path.listFiles();
/* 129 */       if (files != null) {
/* 130 */         File[] var3 = files;
/* 131 */         int var4 = files.length;
/*     */         
/* 133 */         for (int var5 = 0; var5 < var4; var5++) {
/* 134 */           File file = var3[var5];
/* 135 */           if (file.isDirectory()) {
/* 136 */             deleteWorld(file);
/*     */           }
/*     */           else {
/* 139 */             file.delete();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 145 */     path.delete();
/*     */   }
/*     */ }


/* Location:              C:\Users\Pcs Exabyte\Desktop\Servidor\Village Defense\plugins\UltimateCaptureTheWool.jar!\gamemanager\WorldManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */