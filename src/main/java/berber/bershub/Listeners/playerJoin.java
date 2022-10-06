package berber.bershub.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class playerJoin implements Listener{
    private final Boolean enabled;
    private final Boolean disableVanJoinMsg;
    private final String welcomeMessage;
    private final String[] randomJoinMsg;
    private String broadCastName;
    public playerJoin(JavaPlugin server){
        Bukkit.getLogger().info("Setting up player join");
        FileConfiguration config = server.getConfig();
        enabled = config.getBoolean("JoinMessage.Enabled");
        disableVanJoinMsg = config.getBoolean("JoinMessage.DisableVanillaJoinMsg");
        welcomeMessage = config.getString("JoinMessage.Text");
        broadCastName = config.getString("JoinMessage.BroadcastName");

        randomJoinMsg = config.getStringList("JoinMessage.Variations").toArray(new String[0]);

        if (config.getBoolean("GlobalBroadcastName.Enabled")){
            if (config.getBoolean("JoinMessage.BroadcastName.Override")){
                broadCastName = config.getString("JoinMessage.BroadcastName.Text");
            }
            else{
                broadCastName = config.getString("GlobalBroadcastName.Text");
            }
        }else{
            if (!config.getString("JoinMessage.BroadcastName.Text").isEmpty()){
                broadCastName = config.getString("JoinMessage.BroadcastName.Text");
            }else{
                broadCastName = "Server";
            }
        }
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        if (disableVanJoinMsg) e.setJoinMessage("");
        if (!enabled) return;
        Random rand = new Random();
        Player player = e.getPlayer();
        StringBuilder finalizedString = new StringBuilder();
        String[] msg = welcomeMessage.split(" ");
        finalizedString.append("[").append(broadCastName).append("]: ");
        for (String word : msg){
            if (Objects.equals(word, "=PLAYERNAME")){
                finalizedString.append(player.getName()).append(" ");
            }else if (Objects.equals(word, "=WELCOMEMSGVAR")){
                finalizedString.append(randomJoinMsg[rand.nextInt(randomJoinMsg.length)]).append(" ");
            }else{
                finalizedString.append(word).append(" ");
            }
        }
        Bukkit.broadcastMessage(finalizedString.toString().trim());
    }
}
