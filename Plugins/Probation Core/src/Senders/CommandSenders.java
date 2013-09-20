package Senders;

import org.bukkit.Server;
 import org.bukkit.permissions.Permissible;
 
 public interface CommandSenders extends Permissible {
      public void sendMessage(String message);
      
      public void sendMessage(String[] messages);
      public Server getServer();
      public String getName();
}