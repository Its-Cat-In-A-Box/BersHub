package berber.bershub.Commands;

import org.bukkit.command.Command;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandRegister {
    public CommandRegister(JavaPlugin s){
        s.getCommand("hub").setExecutor(new CtpToSpawn(s));
    }
}
