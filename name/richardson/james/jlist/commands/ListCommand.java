package name.richardson.james.jlist.commands;

import java.util.List;

import name.richardson.james.jlist.jList;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class ListCommand implements CommandExecutor {
  private jList plugin;
  
  public ListCommand(jList plugin) {
    this.plugin = plugin;
  }

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    String message = null;
    
    if (args.length == 0) {
      final List<Player> players = plugin.getOnlinePlayers();
      final String header =  String.format(ChatColor.GRAY + "Online (%d/%d): " + ChatColor.WHITE , players.size(), plugin.getServer().getMaxPlayers());
      message = plugin.createPlayerList(header, players);
    } else {
      final World world = plugin.getServer().getWorld(args[0]);
      if (world != null) {
        final List<Player> players = plugin.getOnlinePlayersInWorld(world);
        final String header =  String.format(ChatColor.GRAY + "%s (%d/%d): " + ChatColor.WHITE , world.getName(), players.size(), plugin.getServer().getOnlinePlayers().length);
        message = plugin.createPlayerList(header, players);
      } else { 
        sender.sendMessage(ChatColor.RED + "World does not exist or is not loaded!");
        return false;
      }
    }
    
    plugin.sendWrappedMessage(sender, message);
    return true;
  }
  
  
  
  
  

}
