package berber.bershub;

import berber.bershub.Listeners.playerJoin;
import berber.bershub.Listeners.playerLeave;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public final class BersHub extends JavaPlugin {
    Logger logger;
    @Override
    public void onEnable() {
        logger = Bukkit.getLogger();
        logger.info("Ber's Hub is up and running! Nice to meet you :D");
        //Configuration files
        saveDefaultConfig();
        //Register Commands

        //Register SQL

        //Register Join msg
        getServer().getPluginManager().registerEvents(new playerJoin(this), this);
        //Register Leave msg
        getServer().getPluginManager().registerEvents(new playerLeave(this), this);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
