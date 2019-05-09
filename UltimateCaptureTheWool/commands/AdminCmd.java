package commands;
import java.text.NumberFormat;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import ultimate.main.ConfigManager;
import ultimate.main.PlayerDataManager;
import util.Util;

 public class AdminCmd implements CommandExecutor{
	 
	 @Override
         public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
            FileConfiguration messages = ConfigManager.getInstance().getMessages();
            NumberFormat nf = NumberFormat.getInstance();
            if (!(sender instanceof Player)){
       if ((cmd.getName().equalsIgnoreCase("ultimatecapturethewooladmin")) || (cmd.getName().equalsIgnoreCase("uctwa"))) {
         if (args[0].equalsIgnoreCase("reload")) {
           ConfigManager cg = ConfigManager.getInstance();
           cg.reloadAchievements();
           cg.reloadEnchants();
           cg.reloadGuns();
           cg.ReloadMessages();
           cg.reloadParticles();
           cg.reloadPets();
           Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("Files-Reloaded")));         
         }
         
         if (args[0].equalsIgnoreCase("level")) {
           if (args[1].equalsIgnoreCase("set")) {
             Player target = Bukkit.getServer().getPlayer(args[2]);
             if (target == null) {
               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
             } else {
               try {
                 double set = Integer.valueOf(args[3]);
                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
                 if (set >= 0.0D) {
                   pm.SetPlayerXP(set);
                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Level-Set-to").replace("%player%", target.getName()).replace("%level%", nf.format(set))));
                   target.sendMessage(Util.Chat(messages.getString("AdminTools.SetLevel.message").replace("%level%", nf.format(set))));
                   Util.SendInstantTitle(target, messages.getString("AdminTools.SetLevel.Title"), messages.getString("AdminTools.SetLevel.Subtitle").replace("%level%", nf.format(set)), 60);
                   Util.PlaySound(target, Sound.valueOf(messages.getString("AdminTools.SetLevel.Sound")));
                 } else {
                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               } catch (NumberFormatException io) {
                 Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
               }
             }
           }
           
           if (args[1].equalsIgnoreCase("add")) {
             Player target = Bukkit.getServer().getPlayer(args[2]);
             if (target == null) {
               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
             } else {
               try {
                 double add = Integer.valueOf(args[3]);
                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
                 if (add >= 0.0D) {
                   pm.GiveXP(add);
                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Level-added-to").replace("%player%", target.getName()).replace("%level%", nf.format(add))));
                   target.sendMessage(Util.Chat(messages.getString("AdminTools.LevelUp.message").replace("%level%", nf.format(add))));
                   Util.SendInstantTitle(target, messages.getString("AdminTools.LevelUp.Title"), messages.getString("AdminTools.LevelUp.Subtitle").replace("%level%", nf.format(add)), 60);
                   Util.PlaySound(target, Sound.valueOf(messages.getString("AdminTools.LevelUp.Sound")));
                 } else {
                	 Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                	 }
               } catch (NumberFormatException io) {
                 Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
               }
             }
           }
           
           if (args[1].equalsIgnoreCase("take")) {
            Player target = Bukkit.getServer().getPlayer(args[2]);
             if (target == null) {
               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
               try {
                 double take = Integer.valueOf(args[3]);
                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
                 if (take >= 0.0D) {
                   pm.TakeXP(take);
                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Level-Removed-to").replace("%player%", target.getName()).replace("%level%", nf.format(take))));
                   target.sendMessage(Util.Chat(messages.getString("AdminTools.TakeLevel.message").replace("%level%", nf.format(take))));
                   Util.SendInstantTitle(target, messages.getString("AdminTools.TakeLevel.Title"), messages.getString("AdminTools.TakeLevel.Subtitle").replace("%level%", nf.format(take)), 60);
                   Util.PlaySound(target, Sound.valueOf(messages.getString("AdminTools.TakeLevel.Sound")));
                 } else {
                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
                 } catch (NumberFormatException io) {
                 Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
               }
             }
           }           
           if (args[1].equalsIgnoreCase("reset")) {
        	   Player target = Bukkit.getServer().getPlayer(args[2]);
             if (target == null) {
               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
             } else {
               PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
               pm.SetPlayerXP(0.0D);
               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Level-Reset-to").replace("%player%", target.getName())));
             }
           }
         }          
         if (args[0].equalsIgnoreCase("money")) {
           if (args[1].equalsIgnoreCase("set")) {
             Player target = Bukkit.getServer().getPlayer(args[2]);
             if (target == null) {
               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
             } else {
                 try {
/*  127 */                 double set = Integer.valueOf(args[3]).intValue();
/*  128 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  129 */                 if (set >= 0.0D) {
/*  130 */                   pm.SetPlayerMoney(set);
/*  131 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Money-Set-to").replace("%player%", target.getName()).replace("%balance%", nf.format(set))));
                   } else {
/*  133 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  136 */                 Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
/*  140 */           if (args[1].equalsIgnoreCase("add")) {
/*  141 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  142 */             if (target == null) {
/*  143 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  146 */                 double add = Integer.valueOf(args[3]).intValue();
/*  147 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  148 */                 if (add >= 0.0D) {
/*  149 */                   pm.GiveMoney(add);
/*  150 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Money-added-to").replace("%player%", target.getName()).replace("%balance%", nf.format(add))));
                   } else {
/*  152 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  155 */                 Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
/*  159 */           if (args[1].equalsIgnoreCase("take")) {
/*  160 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  161 */             if (target == null) {
/*  162 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  165 */                 double take = Integer.valueOf(args[3]).intValue();
/*  166 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  167 */                 if (take >= 0.0D) {
/*  168 */                   pm.TakeMoney(take);
/*  169 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Money-Removed-to").replace("%player%", target.getName()).replace("%balance%", nf.format(take))));
                   } else {
/*  171 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  174 */                 Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
             
/*  179 */           if (args[1].equalsIgnoreCase("reset")) {
/*  180 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  181 */             if (target == null) {
/*  182 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
/*  184 */               PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  185 */               pm.SetPlayerMoney(0.0D);
/*  186 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Money-Reset-to").replace("%player%", target.getName())));
               }
             }
           }
           
   
/*  192 */         if (args[0].equalsIgnoreCase("kills")) {
/*  193 */           if (args[1].equalsIgnoreCase("set")) {
/*  194 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  195 */             if (target == null) {
/*  196 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  199 */                 int set = Integer.valueOf(args[3]).intValue();
/*  200 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  201 */                 if (set >= 0) {
/*  202 */                   pm.SetPlayerKills(set);
/*  203 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Kills-Set-to").replace("%player%", target.getName()).replace("%kills%", nf.format(set))));
                   } else {
/*  205 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  208 */                 Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
/*  212 */           if (args[1].equalsIgnoreCase("add")) {
/*  213 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  214 */             if (target == null) {
/*  215 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  218 */                 int add = Integer.valueOf(args[3]).intValue();
/*  219 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  220 */                 if (add >= 0) {
/*  221 */                   pm.addPlayerKills(add);
/*  222 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Kills-added-to").replace("%player%", target.getName()).replace("%kills%", nf.format(add))));
                   } else {
/*  224 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  227 */                 Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
             
/*  232 */           if (args[1].equalsIgnoreCase("take")) {
/*  233 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  234 */             if (target == null) {
/*  235 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  238 */                 int take = Integer.valueOf(args[3]).intValue();
/*  239 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  240 */                 if (take >= 0) {
/*  241 */                   pm.RemoveKills(take);
/*  242 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Kills-Removed-to").replace("%player%", target.getName()).replace("%kills%", nf.format(take))));
                   } else {
/*  244 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  247 */                 Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
             
/*  252 */           if (args[1].equalsIgnoreCase("reset")) {
/*  253 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  254 */             if (target == null) {
/*  255 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
/*  257 */               PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  258 */               pm.SetPlayerKills(0);
/*  259 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Kills-Reset-to").replace("%player%", target.getName())));
               }
             }
           }
           
   
   
/*  266 */         if (args[0].equalsIgnoreCase("deaths")) {
/*  267 */           if (args[1].equalsIgnoreCase("set")) {
/*  268 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  269 */             if (target == null) {
/*  270 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  273 */                 int set = Integer.valueOf(args[3]).intValue();
/*  274 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  275 */                 if (set >= 0) {
/*  276 */                   pm.SetPlayerDeaths(0);
/*  277 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Deaths-Set-to").replace("%player%", target.getName()).replace("%deaths%", nf.format(set))));
                   } else {
/*  279 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  282 */                 Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
/*  286 */           if (args[1].equalsIgnoreCase("add")) {
/*  287 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  288 */             if (target == null) {
/*  289 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  292 */                 int add = Integer.valueOf(args[3]).intValue();
/*  293 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  294 */                 if (add >= 0) {
/*  295 */                   pm.addPlayerDeaths(add);
/*  296 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Deaths-added-to").replace("%player%", target.getName()).replace("%deaths%", nf.format(add))));
                   } else {
/*  298 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  301 */                 Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
/*  305 */           if (args[1].equalsIgnoreCase("take")) {
/*  306 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  307 */             if (target == null) {
/*  308 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  311 */                 int take = Integer.valueOf(args[3]).intValue();
/*  312 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  313 */                 if (take >= 0) {
/*  314 */                   pm.RemoveDeaths(take);
/*  315 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Deaths-Removed-to").replace("%player%", target.getName()).replace("%deaths%", nf.format(take))));
                   } else {
/*  317 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  320 */                 Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
/*  324 */           if (args[1].equalsIgnoreCase("reset")) {
/*  325 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  326 */             if (target == null) {
/*  327 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
/*  329 */               PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  330 */               pm.SetPlayerDeaths(0);
/*  331 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Deaths-Reset-to").replace("%player%", target.getName())));
               }
             }
           }
           
   
/*  337 */         if (args[0].equalsIgnoreCase("losses")) {
/*  338 */           if (args[1].equalsIgnoreCase("set")) {
/*  339 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  340 */             if (target == null) {
/*  341 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  344 */                 int set = Integer.valueOf(args[3]).intValue();
/*  345 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  346 */                 if (set >= 0) {
/*  347 */                   pm.SetPlayerLosses(set);
/*  348 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Losses-Set-to").replace("%player%", target.getName()).replace("%losses%", nf.format(set))));
                   } else {
/*  350 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  353 */                 Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
/*  357 */           if (args[1].equalsIgnoreCase("add")) {
/*  358 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  359 */             if (target == null) {
/*  360 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  363 */                 int add = Integer.valueOf(args[3]).intValue();
/*  364 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  365 */                 if (add >= 0) {
/*  366 */                   pm.AddPlayerLosses(add);
/*  367 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Losses-added-to").replace("%player%", target.getName()).replace("%losses%", nf.format(add))));
                   } else {
/*  369 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  372 */                 Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
             
/*  377 */           if (args[1].equalsIgnoreCase("take")) {
/*  378 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  379 */             if (target == null) {
/*  380 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  383 */                 int take = Integer.valueOf(args[3]).intValue();
/*  384 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  385 */                 if (take >= 0) {
/*  386 */                   pm.RemoveLosses(take);
/*  387 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Losses-Removed-to").replace("%player%", target.getName()).replace("%losses%", nf.format(take))));
                   } else {
/*  389 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  392 */                 Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
/*  396 */           if (args[1].equalsIgnoreCase("reset")) {
/*  397 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  398 */             if (target == null) {
/*  399 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
/*  401 */               PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  402 */               pm.SetPlayerLosses(0);
/*  403 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Losses-Reset-to").replace("%player%", target.getName())));
               }
             }
           }
           
   
/*  409 */         if (args[0].equalsIgnoreCase("wins")) {
/*  410 */           if (args[1].equalsIgnoreCase("set")) {
/*  411 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  412 */             if (target == null) {
/*  413 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  416 */                 int set = Integer.valueOf(args[3]).intValue();
/*  417 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  418 */                 if (set >= 0) {
/*  419 */                   pm.SetPlayerWins(set);
/*  420 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Wins-Set-to").replace("%player%", target.getName()).replace("%wins%", nf.format(set))));
                   } else {
/*  422 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  425 */                 Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
/*  429 */           if (args[1].equalsIgnoreCase("add")) {
/*  430 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  431 */             if (target == null) {
/*  432 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  435 */                 int add = Integer.valueOf(args[3]).intValue();
/*  436 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  437 */                 if (add >= 0) {
/*  438 */                   pm.SetPlayerWins(add);
/*  439 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Wins-added-to").replace("%player%", target.getName()).replace("%wins%", nf.format(add))));
                   } else {
/*  441 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  444 */                 Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
/*  448 */           if (args[1].equalsIgnoreCase("take")) {
/*  449 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  450 */             if (target == null) {
/*  451 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  454 */                 int take = Integer.valueOf(args[3]).intValue();
/*  455 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  456 */                 if (take >= 0) {
/*  457 */                   pm.RemoveWins(take);
/*  458 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Wins-Removed-to").replace("%player%", target.getName()).replace("%wins%", nf.format(take))));
                   } else {
/*  460 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  463 */                 Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
/*  467 */           if (args[1].equalsIgnoreCase("reset")) {
/*  468 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  469 */             if (target == null) {
/*  470 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
/*  472 */               PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  473 */               pm.SetPlayerWins(0);
/*  474 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Wins-Reset-to").replace("%player%", target.getName())));
               }
             }
           }
           
   
/*  480 */         if (args[0].equalsIgnoreCase("achievement")) {
/*  481 */           if (args[1].equalsIgnoreCase("add")) {
/*  482 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  483 */             if (target == null) {
/*  484 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  487 */                 String add = String.valueOf(args[3]);
/*  488 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  489 */                 if (add.length() >= 0) {
/*  490 */                   pm.AddAchievement(add);
/*  491 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Achievement-added-to").replace("%player%", target.getName()).replace("%achievement%", add)));
/*  492 */                   target.sendMessage(Util.Chat(messages.getString("AdminTools.AchievementAdded.message").replace("%achievement%", add)));
/*  493 */                   Util.SendInstantTitle(target, messages.getString("AdminTools.AchievementAdded.Title"), messages.getString("AdminTools.AchievementAdded.Subtitle").replace("%achievement%", add), 60);
/*  494 */                   Util.PlaySound(target, Sound.valueOf(messages.getString("AdminTools.AchievementAdded.Sound")));
                   } else {
/*  496 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-achievement")));
                   }
                 } catch (StringIndexOutOfBoundsException io) {
/*  499 */                 Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-achievement")));
                 }
               }
             }
/*  503 */           if (args[1].equalsIgnoreCase("remove")) {
/*  504 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  505 */             if (target == null) {
/*  506 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  509 */                 String rev = String.valueOf(args[3]);
/*  510 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  511 */                 if (rev.length() >= 0) {
/*  512 */                   pm.RemoveAchievement(rev);
/*  513 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Achievement-Removed-to").replace("%player%", target.getName()).replace("%achievement%", rev)));
/*  514 */                   target.sendMessage(Util.Chat(messages.getString("AdminTools.AchievementRemoved.message").replace("%achievement%", rev)));
/*  515 */                   Util.SendInstantTitle(target, messages.getString("AdminTools.AchievementRemoved.Title"), messages.getString("AdminTools.AchievementRemoved.Subtitle").replace("%achievement%", rev), 60);
/*  516 */                   Util.PlaySound(target, Sound.valueOf(messages.getString("AdminTools.AchievementRemoved.Sound")));
                   } else {
/*  518 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-achievement")));
                   }
                 } catch (StringIndexOutOfBoundsException io) {
/*  521 */                 Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-achievement")));
                 }
               }
             }
/*  525 */           if (args[1].equalsIgnoreCase("clear")) {
/*  526 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  527 */             if (target == null) {
/*  528 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
/*  530 */               PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  531 */               pm.ClearAchievement();
/*  532 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Achievement-Reset-to").replace("%player%", target.getName())));
               }
             }
           }
           
   
/*  538 */         if (args[0].equalsIgnoreCase("permission")) {
/*  539 */           if (args[1].equalsIgnoreCase("add")) {
/*  540 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  541 */             if (target == null) {
/*  542 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  545 */                 String add = String.valueOf(args[3]);
/*  546 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  547 */                 if (add.length() >= 0) {
/*  548 */                   pm.AddAchievement(add);
/*  549 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Permisssion-added-to").replace("%player%", target.getName()).replace("%permission%", add)));
                   } else {
/*  551 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-permission")));
                   }
                 } catch (StringIndexOutOfBoundsException io) {
/*  554 */                 Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-permission")));
                 }
               }
             }
/*  558 */           if (args[1].equalsIgnoreCase("remove")) {
/*  559 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  560 */             if (target == null) {
/*  561 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  564 */                 String rev = String.valueOf(args[3]);
/*  565 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  566 */                 if (rev.length() >= 0) {
/*  567 */                   pm.RemovePermission(rev);
/*  568 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Permission-Removed-to").replace("%player%", target.getName()).replace("%permission%", rev)));
                   } else {
/*  570 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-permission")));
                   }
                 } catch (StringIndexOutOfBoundsException io) {
/*  573 */                 Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-permission")));
                 }
               }
             }
/*  577 */           if (args[1].equalsIgnoreCase("clear")) {
/*  578 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  579 */             if (target == null) {
/*  580 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
/*  582 */               PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  583 */               pm.ClearPermission();
/*  584 */               Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Permission-Reset-to").replace("%player%", target.getName())));
               }
             }
           }
         }
/*  589 */       else if (args.length > 3) {
/*  590 */         Bukkit.getConsoleSender().sendMessage("args leng uctwa 3");
         }
       }
       else {
/*  594 */       Player p = (Player)sender;
         
/*  596 */       if ((cmd.getName().equalsIgnoreCase("ultimatecapturethewooladmin")) || (cmd.getName().equalsIgnoreCase("uctwa"))) {
/*  597 */         if (args[0].equalsIgnoreCase("reload")) {
/*  598 */           ConfigManager cg = ConfigManager.getInstance();
/*  599 */           cg.reloadAchievements();
/*  600 */           cg.reloadEnchants();
/*  601 */           cg.reloadGuns();
/*  602 */           cg.ReloadMessages();
/*  603 */           cg.reloadParticles();
/*  604 */           cg.reloadPets();
/*  605 */           p.sendMessage(Util.Chat(messages.getString("Files-Reloaded")));
           }
/*  607 */         if (args[0].equalsIgnoreCase("level")) {
/*  608 */           if (args[1].equalsIgnoreCase("set")) {
/*  609 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  610 */             if (target == null) {
/*  611 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  614 */                 double set = Integer.valueOf(args[3]).intValue();
/*  615 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  616 */                 if (set >= 0.0D) {
/*  617 */                   pm.SetPlayerXP(set);
/*  618 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Level-Set-to").replace("%player%", target.getName()).replace("%level%", nf.format(set))));
/*  619 */                   target.sendMessage(Util.Chat(messages.getString("AdminTools.SetLevel.message").replace("%level%", nf.format(set))));
/*  620 */                   Util.SendInstantTitle(target, messages.getString("AdminTools.SetLevel.Title"), messages.getString("AdminTools.SetLevel.Subtitle").replace("%level%", nf.format(set)), 60);
/*  621 */                   Util.PlaySound(target, Sound.valueOf(messages.getString("AdminTools.SetLevel.Sound")));
                   } else {
/*  623 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  626 */                 p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
             
/*  631 */           if (args[1].equalsIgnoreCase("add")) {
/*  632 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  633 */             if (target == null) {
/*  634 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  637 */                 double add = Integer.valueOf(args[3]).intValue();
/*  638 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  639 */                 if (add >= 0.0D) {
/*  640 */                   pm.GiveXP(add);
/*  641 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Level-added-to").replace("%player%", target.getName()).replace("%level%", nf.format(add))));
/*  642 */                   target.sendMessage(Util.Chat(messages.getString("AdminTools.LevelUp.message").replace("%level%", nf.format(add))));
/*  643 */                   Util.SendInstantTitle(target, messages.getString("AdminTools.LevelUp.Title"), messages.getString("AdminTools.LevelUp.Subtitle").replace("%level%", nf.format(add)), 60);
/*  644 */                   Util.PlaySound(target, Sound.valueOf(messages.getString("AdminTools.LevelUp.Sound")));
                   } else {
/*  646 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  649 */                 p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
             
/*  654 */           if (args[1].equalsIgnoreCase("take")) {
/*  655 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  656 */             if (target == null) {
/*  657 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  660 */                 double take = Integer.valueOf(args[3]).intValue();
/*  661 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  662 */                 if (take >= 0.0D) {
/*  663 */                   pm.TakeXP(take);
/*  664 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Level-Removed-to").replace("%player%", target.getName()).replace("%level%", nf.format(take))));
/*  665 */                   target.sendMessage(Util.Chat(messages.getString("AdminTools.TakeLevel.message").replace("%level%", nf.format(take))));
/*  666 */                   Util.SendInstantTitle(target, messages.getString("AdminTools.TakeLevel.Title"), messages.getString("AdminTools.TakeLevel.Subtitle").replace("%level%", nf.format(take)), 60);
/*  667 */                   Util.PlaySound(target, Sound.valueOf(messages.getString("AdminTools.TakeLevel.Sound")));
                   } else {
/*  669 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  672 */                 p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
             
/*  677 */           if (args[1].equalsIgnoreCase("reset")) {
/*  678 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  679 */             if (target == null) {
/*  680 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
/*  682 */               PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  683 */               pm.SetPlayerXP(0.0D);
/*  684 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Level-Reset-to").replace("%player%", target.getName())));
               }
             }
           }
           
   
/*  690 */         if (args[0].equalsIgnoreCase("money")) {
/*  691 */           if (args[1].equalsIgnoreCase("set")) {
/*  692 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  693 */             if (target == null) {
/*  694 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  697 */                 double set = Integer.valueOf(args[3]).intValue();
/*  698 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  699 */                 if (set >= 0.0D) {
/*  700 */                   pm.SetPlayerMoney(set);
/*  701 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Money-Set-to").replace("%player%", target.getName()).replace("%balance%", nf.format(set))));
                   } else {
/*  703 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  706 */                 p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
/*  710 */           if (args[1].equalsIgnoreCase("add")) {
/*  711 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  712 */             if (target == null) {
/*  713 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  716 */                 double add = Integer.valueOf(args[3]).intValue();
/*  717 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  718 */                 if (add >= 0.0D) {
/*  719 */                   pm.GiveMoney(add);
/*  720 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Money-added-to").replace("%player%", target.getName()).replace("%balance%", nf.format(add))));
                   } else {
/*  722 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  725 */                 p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
/*  729 */           if (args[1].equalsIgnoreCase("take")) {
/*  730 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  731 */             if (target == null) {
/*  732 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  735 */                 double take = Integer.valueOf(args[3]).intValue();
/*  736 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  737 */                 if (take >= 0.0D) {
/*  738 */                   pm.TakeMoney(take);
/*  739 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Money-Removed-to").replace("%player%", target.getName()).replace("%balance%", nf.format(take))));
                   } else {
/*  741 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  744 */                 p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
             
/*  749 */           if (args[1].equalsIgnoreCase("reset")) {
/*  750 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  751 */             if (target == null) {
/*  752 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
/*  754 */               PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  755 */               pm.SetPlayerMoney(0.0D);
/*  756 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Money-Reset-to").replace("%player%", target.getName())));
               }
             }
           }
           
   
/*  762 */         if (args[0].equalsIgnoreCase("kills")) {
/*  763 */           if (args[1].equalsIgnoreCase("set")) {
/*  764 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  765 */             if (target == null) {
/*  766 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  769 */                 int set = Integer.valueOf(args[3]).intValue();
/*  770 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  771 */                 if (set >= 0) {
/*  772 */                   pm.SetPlayerKills(set);
/*  773 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Kills-Set-to").replace("%player%", target.getName()).replace("%kills%", nf.format(set))));
                   } else {
/*  775 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  778 */                 p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
/*  782 */           if (args[1].equalsIgnoreCase("add")) {
/*  783 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  784 */             if (target == null) {
/*  785 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  788 */                 int add = Integer.valueOf(args[3]).intValue();
/*  789 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  790 */                 if (add >= 0) {
/*  791 */                   pm.addPlayerKills(add);
/*  792 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Kills-added-to").replace("%player%", target.getName()).replace("%kills%", nf.format(add))));
                   } else {
/*  794 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  797 */                 p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
             
/*  802 */           if (args[1].equalsIgnoreCase("take")) {
/*  803 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  804 */             if (target == null) {
/*  805 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  808 */                 int take = Integer.valueOf(args[3]).intValue();
/*  809 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  810 */                 if (take >= 0) {
/*  811 */                   pm.RemoveKills(take);
/*  812 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Kills-Removed-to").replace("%player%", target.getName()).replace("%kills%", nf.format(take))));
                   } else {
/*  814 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  817 */                 p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
             
/*  822 */           if (args[1].equalsIgnoreCase("reset")) {
/*  823 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  824 */             if (target == null) {
/*  825 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
/*  827 */               PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  828 */               pm.SetPlayerKills(0);
/*  829 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Kills-Reset-to").replace("%player%", target.getName())));
               }
             }
           }
           
   
   
/*  836 */         if (args[0].equalsIgnoreCase("deaths")) {
/*  837 */           if (args[1].equalsIgnoreCase("set")) {
/*  838 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  839 */             if (target == null) {
/*  840 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  843 */                 int set = Integer.valueOf(args[3]).intValue();
/*  844 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  845 */                 if (set >= 0) {
/*  846 */                   pm.SetPlayerDeaths(0);
/*  847 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Deaths-Set-to").replace("%player%", target.getName()).replace("%deaths%", nf.format(set))));
                   } else {
/*  849 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  852 */                 p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
/*  856 */           if (args[1].equalsIgnoreCase("add")) {
/*  857 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  858 */             if (target == null) {
/*  859 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  862 */                 int add = Integer.valueOf(args[3]).intValue();
/*  863 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  864 */                 if (add >= 0) {
/*  865 */                   pm.addPlayerDeaths(add);
/*  866 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Deaths-added-to").replace("%player%", target.getName()).replace("%deaths%", nf.format(add))));
                   } else {
/*  868 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  871 */                 p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
/*  875 */           if (args[1].equalsIgnoreCase("take")) {
/*  876 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  877 */             if (target == null) {
/*  878 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  881 */                 int take = Integer.valueOf(args[3]).intValue();
/*  882 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  883 */                 if (take >= 0) {
/*  884 */                   pm.RemoveDeaths(take);
/*  885 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Deaths-Removed-to").replace("%player%", target.getName()).replace("%deaths%", nf.format(take))));
                   } else {
/*  887 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  890 */                 p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
/*  894 */           if (args[1].equalsIgnoreCase("reset")) {
/*  895 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  896 */             if (target == null) {
/*  897 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
/*  899 */               PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  900 */               pm.SetPlayerDeaths(0);
/*  901 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Deaths-Reset-to").replace("%player%", target.getName())));
               }
             }
           }
           
   
/*  907 */         if (args[0].equalsIgnoreCase("losses")) {
/*  908 */           if (args[1].equalsIgnoreCase("set")) {
/*  909 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  910 */             if (target == null) {
/*  911 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  914 */                 int set = Integer.valueOf(args[3]).intValue();
/*  915 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  916 */                 if (set >= 0) {
/*  917 */                   pm.SetPlayerLosses(set);
/*  918 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Losses-Set-to").replace("%player%", target.getName()).replace("%losses%", nf.format(set))));
                   } else {
/*  920 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  923 */                 p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
/*  927 */           if (args[1].equalsIgnoreCase("add")) {
/*  928 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  929 */             if (target == null) {
/*  930 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  933 */                 int add = Integer.valueOf(args[3]).intValue();
/*  934 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  935 */                 if (add >= 0) {
/*  936 */                   pm.AddPlayerLosses(add);
/*  937 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Losses-added-to").replace("%player%", target.getName()).replace("%losses%", nf.format(add))));
                   } else {
/*  939 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  942 */                 p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
             
/*  947 */           if (args[1].equalsIgnoreCase("take")) {
/*  948 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  949 */             if (target == null) {
/*  950 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  953 */                 int take = Integer.valueOf(args[3]).intValue();
/*  954 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  955 */                 if (take >= 0) {
/*  956 */                   pm.RemoveLosses(take);
/*  957 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Losses-Removed-to").replace("%player%", target.getName()).replace("%losses%", nf.format(take))));
                   } else {
/*  959 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  962 */                 p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
/*  966 */           if (args[1].equalsIgnoreCase("reset")) {
/*  967 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  968 */             if (target == null) {
/*  969 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
/*  971 */               PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  972 */               pm.SetPlayerLosses(0);
/*  973 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Losses-Reset-to").replace("%player%", target.getName())));
               }
             }
           }
           
   
/*  979 */         if (args[0].equalsIgnoreCase("wins")) {
/*  980 */           if (args[1].equalsIgnoreCase("set")) {
/*  981 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/*  982 */             if (target == null) {
/*  983 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/*  986 */                 int set = Integer.valueOf(args[3]).intValue();
/*  987 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/*  988 */                 if (set >= 0) {
/*  989 */                   pm.SetPlayerWins(set);
/*  990 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Wins-Set-to").replace("%player%", target.getName()).replace("%wins%", nf.format(set))));
                   } else {
/*  992 */                   Bukkit.getConsoleSender().sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/*  995 */                 p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
/*  999 */           if (args[1].equalsIgnoreCase("add")) {
/* 1000 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/* 1001 */             if (target == null) {
/* 1002 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/* 1005 */                 int add = Integer.valueOf(args[3]).intValue();
/* 1006 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/* 1007 */                 if (add >= 0) {
/* 1008 */                   pm.SetPlayerWins(add);
/* 1009 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Wins-added-to").replace("%player%", target.getName()).replace("%wins%", nf.format(add))));
                   } else {
/* 1011 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/* 1014 */                 p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
/* 1018 */           if (args[1].equalsIgnoreCase("take")) {
/* 1019 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/* 1020 */             if (target == null) {
/* 1021 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/* 1024 */                 int take = Integer.valueOf(args[3]).intValue();
/* 1025 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/* 1026 */                 if (take >= 0) {
/* 1027 */                   pm.RemoveWins(take);
/* 1028 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Wins-Removed-to").replace("%player%", target.getName()).replace("%wins%", nf.format(take))));
                   } else {
/* 1030 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                   }
                 } catch (NumberFormatException io) {
/* 1033 */                 p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-amount")));
                 }
               }
             }
/* 1037 */           if (args[1].equalsIgnoreCase("reset")) {
/* 1038 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/* 1039 */             if (target == null) {
/* 1040 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
/* 1042 */               PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/* 1043 */               pm.SetPlayerWins(0);
/* 1044 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Wins-Reset-to").replace("%player%", target.getName())));
               }
             }
           }
           
   
/* 1050 */         if (args[0].equalsIgnoreCase("achievement")) {
/* 1051 */           if (args[1].equalsIgnoreCase("add")) {
/* 1052 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/* 1053 */             if (target == null) {
/* 1054 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/* 1057 */                 String add = String.valueOf(args[3]);
/* 1058 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/* 1059 */                 if (add.length() >= 0) {
/* 1060 */                   pm.AddAchievement(add);
/* 1061 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Achievement-added-to").replace("%player%", target.getName()).replace("%achievement%", add)));
/* 1062 */                   target.sendMessage(Util.Chat(messages.getString("AdminTools.AchievementAdded.message").replace("%achievement%", add)));
/* 1063 */                   Util.SendInstantTitle(target, messages.getString("AdminTools.AchievementAdded.Title"), messages.getString("AdminTools.AchievementAdded.Subtitle").replace("%achievement%", add), 60);
/* 1064 */                   Util.PlaySound(target, Sound.valueOf(messages.getString("AdminTools.AchievementAdded.Sound")));
                   } else {
/* 1066 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-achievement")));
                   }
                 } catch (StringIndexOutOfBoundsException io) {
/* 1069 */                 p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-achievement")));
                 }
               }
             }
/* 1073 */           if (args[1].equalsIgnoreCase("remove")) {
/* 1074 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/* 1075 */             if (target == null) {
/* 1076 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/* 1079 */                 String rev = String.valueOf(args[3]);
/* 1080 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/* 1081 */                 if (rev.length() >= 0) {
/* 1082 */                   pm.RemoveAchievement(rev);
/* 1083 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Achievement-Removed-to").replace("%player%", target.getName()).replace("%achievement%", rev)));
/* 1084 */                   target.sendMessage(Util.Chat(messages.getString("AdminTools.AchievementRemoved.message").replace("%achievement%", rev)));
/* 1085 */                   Util.SendInstantTitle(target, messages.getString("AdminTools.AchievementRemoved.Title"), messages.getString("AdminTools.AchievementRemoved.Subtitle").replace("%achievement%", rev), 60);
/* 1086 */                   Util.PlaySound(target, Sound.valueOf(messages.getString("AdminTools.AchievementRemoved.Sound")));
                   } else {
/* 1088 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-achievement")));
                   }
                 } catch (StringIndexOutOfBoundsException io) {
/* 1091 */                 p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-achievement")));
                 }
               }
             }
/* 1095 */           if (args[1].equalsIgnoreCase("clear")) {
/* 1096 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/* 1097 */             if (target == null) {
/* 1098 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
/* 1100 */               PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/* 1101 */               pm.ClearAchievement();
/* 1102 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Achievement-Reset-to").replace("%player%", target.getName())));
               }
             }
           }
           
   
/* 1108 */         if (args[0].equalsIgnoreCase("permission")) {
/* 1109 */           if (args[1].equalsIgnoreCase("add")) {
/* 1110 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/* 1111 */             if (target == null) {
/* 1112 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/* 1115 */                 String add = String.valueOf(args[3]);
/* 1116 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/* 1117 */                 if (add.length() >= 0) {
/* 1118 */                   pm.AddAchievement(add);
/* 1119 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Permisssion-added-to").replace("%player%", target.getName()).replace("%permission%", add)));
                   } else {
/* 1121 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-permission")));
                   }
                 } catch (StringIndexOutOfBoundsException io) {
/* 1124 */                 p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-permission")));
                 }
               }
             }
/* 1128 */           if (args[1].equalsIgnoreCase("remove")) {
/* 1129 */             Player target = Bukkit.getServer().getPlayer(args[2]);
/* 1130 */             if (target == null) {
/* 1131 */               p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
               } else {
                 try {
/* 1134 */                 String rev = String.valueOf(args[3]);
/* 1135 */                 PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
/* 1136 */                 if (rev.length() >= 0) {
/* 1137 */                   pm.RemovePermission(rev);
/* 1138 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Permission-Removed-to").replace("%player%", target.getName()).replace("%permission%", rev)));
                   } else {
/* 1140 */                   p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-permission")));
                   }
                 } catch (StringIndexOutOfBoundsException io) {
/* 1143 */                 p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-permission")));
                 }
               }
             }
/* 1147 */           if (args[1].equalsIgnoreCase("clear")) {
/* 1148 */             Player target = Bukkit.getServer().getPlayer(args[2]);
            if (target == null) {
               p.sendMessage(Util.Chat(messages.getString("AdminTools.Invalid-player")));
            } else {
              PlayerDataManager pm = new PlayerDataManager(target.getUniqueId());
              pm.ClearPermission();
               p.sendMessage(Util.Chat(messages.getString("AdminTools.Permission-Reset-to").replace("%player%", target.getName())));
          }
        }
       }
       }
      

     return true;
    }
     return false;
 }
}
