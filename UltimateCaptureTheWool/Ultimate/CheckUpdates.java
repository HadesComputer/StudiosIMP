/*    */ package ultimate.main;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.InputStreamReader;
/*    */ import java.net.MalformedURLException;
/*    */ import java.net.URL;
/*    */ import java.net.URLConnection;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ public class CheckUpdates
/*    */ {
/* 13 */   private int project = 0;
/*    */   private URL checkURL;
/* 15 */   private String newVersion = "";
/*    */   private JavaPlugin plugin;
/*    */   
/*    */   public CheckUpdates(JavaPlugin plugin, int projectID) {
/* 19 */     this.plugin = plugin;
/* 20 */     this.newVersion = plugin.getDescription().getVersion();
/* 21 */     this.project = projectID;
/*    */     try {
/* 23 */       this.checkURL = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + projectID);
/*    */     }
/*    */     catch (MalformedURLException localMalformedURLException) {}
/*    */   }
/*    */   
/*    */   public int getProjectID() {
/* 29 */     return this.project;
/*    */   }
/*    */   
/*    */   public JavaPlugin getPlugin() {
/* 33 */     return this.plugin;
/*    */   }
/*    */   
/*    */   public String getLatestVersion() {
/* 37 */     return this.newVersion;
/*    */   }
/*    */   
/*    */   public String getResourceURL() {
/* 41 */     return "https://www.spigotmc.org/resources/" + this.project;
/*    */   }
/*    */   
/*    */   public boolean checkForUpdates() throws Exception {
/* 45 */     URLConnection con = this.checkURL.openConnection();
/* 46 */     this.newVersion = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
/* 47 */     return !this.plugin.getDescription().getVersion().equals(this.newVersion);
/*    */   }
/*    */ }
