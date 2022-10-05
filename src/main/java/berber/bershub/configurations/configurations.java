package berber.bershub.configurations;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class configurations {
    FileConfiguration fileConfiguration;

    public configurations(FileConfiguration config) {
        fileConfiguration = config;
    }

    public void setupConfiguration(){
        fileConfiguration.addDefault("serverName", "My Server");
        fileConfiguration.addDefault("MOTD", "Welcome to my server!");
        fileConfiguration.addDefault("joinMessage", "[playerName] has joined the server");
        fileConfiguration.options().copyDefaults(true);

    }
}
