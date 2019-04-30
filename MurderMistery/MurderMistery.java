package epic.main;
import murdermistery.handlers.JoinHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import util.Utils;

public class MurderMistery extends JavaPlugin{

	
	
	public PluginDescriptionFile pdf = getDescription();
	public String Name = ChatColor.DARK_RED+"["+ChatColor.RED+pdf.getName()+ChatColor.DARK_RED+"]";
	public String Vers = ChatColor.AQUA+"["+ChatColor.RED+pdf.getVersion()+ChatColor.AQUA+"]";
	private HologramsAPI hd;
	private static MurderMistery instance;
	Utils util = new Utils();
	
	public void onEnable(){
		instance = this;
		Bukkit.getConsoleSender().sendMessage(Name);
		Bukkit.getConsoleSender().sendMessage(util.chat("&bVersion:")+Vers);
		Bukkit.getConsoleSender().sendMessage(util.chat("&cEnabling Plugin and Data"));
		registerEvents();		
		if(!setupHolograms()){
			Bukkit.getConsoleSender().sendMessage(util.chat("&bHolographicDisplays Found"));
		}else{
			Bukkit.getConsoleSender().sendMessage(util.chat("&d&lHolographicDisplays not found, Disabling EpicMurderMysteri"));
			this.setEnabled(false);
		}
	}
	
	
	public void onDisable(){
		
	}
	
	
	public void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new JoinHandler(), this);
	}
	
	public static MurderMistery getInstance(){
		return instance;
	}
	
	public MurderMistery(){
		instance = this;
	}
	
		
		
		private boolean setupHolograms() {
		  if (getServer().getPluginManager().getPlugin("HolographicDisplays") == null) {
			 return false;
			}
		RegisteredServiceProvider<HologramsAPI> rsp = getServer().getServicesManager().getRegistration(HologramsAPI.class);
			if (rsp == null) {
			return false;
			}
			hd = (HologramsAPI)rsp.getProvider();
			return hd != null;
			}
	   
			public HologramsAPI getHologramsAPI(){
				return hd; 
				}
}
