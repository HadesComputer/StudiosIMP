/*     */ package gamemanager;
/*     */ 
/*     */ import java.util.ArrayList;
import java.util.List;
/*     */ import java.util.UUID;

/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ import org.bukkit.inventory.meta.LeatherArmorMeta;

/*     */ import ultimate.main.ConfigManager;
/*     */ import util.Util;
/*     */ 
/*     */ public class TeamManager
/*     */ {
/*     */   private static List<UUID> reds;
/*     */   private static List<UUID> blues;
/*     */   
/*     */   public static enum Teams
/*     */   {
/*  22 */     BLUE,  RED;
/*     */   }
/*     */   
/*     */   public boolean isInTeam(Player p)
/*     */   {
/*  27 */     if ((blues.contains(p.getUniqueId())) || (reds.contains(p.getUniqueId()))) {
/*  28 */       return false;
/*     */     }
/*  30 */     return true;
/*     */   }
/*     */   
/*     */   public void removeForTeam(Player p) {
/*  34 */     if (reds.contains(p.getUniqueId())) {
/*  35 */       reds.remove(p.getUniqueId());
/*     */     }
/*  37 */     if (blues.contains(p.getUniqueId())) {
/*  38 */       blues.remove(p.getUniqueId());
/*     */     }
/*     */   }
/*     */   
/*     */   public void addToTeam(Teams team, Player p) {
/*  43 */     FileConfiguration mess = ConfigManager.getInstance().getMessages();
/*  44 */     String tblue = mess.getString("Teams.Blue.Title");
/*  45 */     String sblue = mess.getString("Teams.Blue.Subtitle");
/*  46 */     int stblue = mess.getInt("Teams.Blue.Stayin");
/*  47 */     String tred = mess.getString("Teams.Red.Title");
/*  48 */     String sred = mess.getString("Teams.Red.Subtitle");
/*  49 */     int stred = mess.getInt("Teams.Red.Stayin");
/*  50 */     if (isInTeam(p)) {
/*  51 */       switch (team) {
/*     */       case BLUE: 
/*  53 */         blues.add(p.getUniqueId());
/*  54 */         ItemStack helmetblue = new ItemStack(Material.LEATHER_HELMET);
/*  55 */         ItemStack chesblue = new ItemStack(Material.LEATHER_CHESTPLATE);
/*  56 */         ItemStack legsblue = new ItemStack(Material.LEATHER_LEGGINGS);
/*  57 */         ItemStack botsblue = new ItemStack(Material.LEATHER_BOOTS);
/*  58 */         LeatherArmorMeta hblue = (LeatherArmorMeta)helmetblue.getItemMeta();
/*  59 */         LeatherArmorMeta cblue = (LeatherArmorMeta)chesblue.getItemMeta();
/*  60 */         LeatherArmorMeta lblue = (LeatherArmorMeta)legsblue.getItemMeta();
/*  61 */         LeatherArmorMeta botblue = (LeatherArmorMeta)botsblue.getItemMeta();
/*  62 */         hblue.setColor(Color.BLUE);
/*  63 */         cblue.setColor(Color.BLUE);
/*  64 */         lblue.setColor(Color.BLUE);
/*  65 */         botblue.setColor(Color.BLUE);
/*  66 */         helmetblue.setItemMeta(hblue);
/*  67 */         chesblue.setItemMeta(cblue);
/*  68 */         legsblue.setItemMeta(lblue);
/*  69 */         botsblue.setItemMeta(botblue);
/*  70 */         p.getInventory().setHelmet(helmetblue);
/*  71 */         p.getInventory().setChestplate(chesblue);
/*  72 */         p.getInventory().setLeggings(legsblue);
/*  73 */         p.getInventory().setBoots(botsblue);
/*  74 */         Util.SendInstantTitle(p, tblue, sblue, stblue);
/*  75 */         Util.PlaySound(p, org.bukkit.Sound.valueOf(mess.getString("Teams.Red.Sound").toUpperCase()));
/*  76 */         p.sendMessage(Util.Chat(mess.getString("Teams.Blue.Message")));
/*  77 */         break;
/*     */       case RED: 
/*  79 */         ItemStack helmetred = new ItemStack(Material.LEATHER_HELMET);
/*  80 */         ItemStack chesred = new ItemStack(Material.LEATHER_CHESTPLATE);
/*  81 */         ItemStack lesred = new ItemStack(Material.LEATHER_LEGGINGS);
/*  82 */         ItemStack botsred = new ItemStack(Material.LEATHER_BOOTS);
/*  83 */         LeatherArmorMeta helred = (LeatherArmorMeta)helmetred.getItemMeta();
/*  84 */         LeatherArmorMeta chestred = (LeatherArmorMeta)chesred.getItemMeta();
/*  85 */         LeatherArmorMeta legtred = (LeatherArmorMeta)lesred.getItemMeta();
/*  86 */         LeatherArmorMeta botred = (LeatherArmorMeta)botsred.getItemMeta();
/*  87 */         helred.setColor(Color.RED);
/*  88 */         chestred.setColor(Color.RED);
/*  89 */         legtred.setColor(Color.RED);
/*  90 */         botred.setColor(Color.RED);
/*  91 */         chesred.setItemMeta(chestred);
/*  92 */         lesred.setItemMeta(legtred);
/*  93 */         botsred.setItemMeta(botred);
/*  94 */         helmetred.setItemMeta(helred);
/*  95 */         p.getInventory().setHelmet(helmetred);
/*  96 */         p.getInventory().setChestplate(chesred);
/*  97 */         p.getInventory().setLeggings(lesred);
/*  98 */         p.getInventory().setBoots(botsred);
/*  99 */         reds.add(p.getUniqueId());
/* 100 */         Util.SendInstantTitle(p, tred, sred, stred);
/* 101 */         Util.PlaySound(p, org.bukkit.Sound.valueOf(mess.getString("Teams.Red.Sound").toUpperCase()));
/* 102 */         p.sendMessage(Util.Chat(mess.getString("Teams.Red.Message")));
/*     */       }
/*     */       
/*     */     } else {
/* 106 */       p.sendMessage(Util.Chat(mess.getString("YouHave-Already-in-Team")));
/*     */     }
/*     */   }
/*     */   
/*     */   public void clearTeams()
/*     */   {
/* 112 */     blues.clear();
/* 113 */     reds.clear();
/*     */   }
/*     */   
/*     */   public List<UUID> getRedMembers() {
/* 117 */     return reds;
/*     */   }
/*     */   
/*     */   public List<UUID> getBluesMembers() {
/* 121 */     return blues;
/*     */   }
/*     */   
/*     */   public boolean compareToBlueTeam(Player p) {
/* 125 */     if ((blues.contains(p.getUniqueId())) && (blues.contains(Teams.BLUE))) {
/* 126 */       return true;
/*     */     }
/* 128 */     return false;
/*     */   }
/*     */   
/*     */   public boolean compareToRedTeam(Player p) {
/* 132 */     if ((reds.contains(p.getUniqueId())) && (reds.contains(Teams.RED))) {
/* 133 */       return true;
/*     */     }
/* 135 */     return false;
/*     */   }
/*     */   
/*     */   public static void setUpBlue()
/*     */   {
/* 140 */     blues = new ArrayList<>();
/*     */   }
/*     */   
/*     */   public static void setUpRed() {
/* 144 */     reds = new ArrayList<>();
/*     */   }
/*     */ }
