package berber.bershub;

import berber.bershub.configurations.configurations;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class BersHub extends JavaPlugin {
    Logger logger;
    @Override
    public void onEnable() {
        logger = Bukkit.getLogger();
        logger.info(ChatColor.GREEN + "Ber's Hub is up and running! Nice to meet you :D");
        //Configuration files
        try{
            saveDefaultConfig();
            configurations configurator = new configurations(getConfig());
            configurator.setupConfiguration();
        }catch(Exception e){
            logger.warning(ChatColor.YELLOW + "Opps, there seems to be a problem! You may copy the " +
                    "following gibberish and ask for assistance in the discord server\n");
            e.printStackTrace();
        }

        Bukkit.getLogger().info(ChatColor.GREEN + "Configuration file has been setup, please restart ");
        //Register Commands

        //Register SQL

    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
