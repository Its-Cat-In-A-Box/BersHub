package berber.bershub.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class CtpToSpawn implements CommandExecutor {
    public Location hub;
    public CtpToSpawn(JavaPlugin s){
        hub = new Location(Bukkit.getWorld(s.getConfig().getString("Commands.hub.world")),
                s.getConfig().getDouble("x"),s.getConfig().getDouble())
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }
}
