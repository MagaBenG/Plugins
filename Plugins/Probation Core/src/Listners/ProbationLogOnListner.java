package Listners;


import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class ProbationLogOnListner implements Listener {
    @EventHandler
    public void normalLogin(PlayerLoginEvent event) {
    
    }
 
    @EventHandler(priority = EventPriority.HIGH)
    public void highLogin(PlayerLoginEvent event) {

    }
}
