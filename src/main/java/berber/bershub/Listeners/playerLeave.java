package berber.bershub.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.Random;

public class playerLeave implements Listener {
    private final Boolean enabled;
    private final Boolean disableVanLeaveMsg;
    private final String leaveMessage;
    private final String[] randomLeaveMsg;
    private String broadCastName;
    public playerLeave(JavaPlugin server){
        Bukkit.getLogger().info("Setting up player join");
        FileConfiguration config = server.getConfig();
        enabled = config.getBoolean("LeaveMessage.Enabled");
        disableVanLeaveMsg = config.getBoolean("LeaveMessage.DisableVanillaLeaveMsg");
        leaveMessage = config.getString("LeaveMessage.Text");
        broadCastName = config.getString("LeaveMessage.BroadcastName");
        randomLeaveMsg = config.getStringList("LeaveMessage.Variations").toArray(new String[0]);
        if (config.getBoolean("GlobalBroadcastName.Enabled")){
            if (config.getBoolean("LeaveMessage.BroadcastName.Override")){
                broadCastName = config.getString("LeaveMessage.BroadcastName.Text");
            }
            else{
                broadCastName = config.getString("GlobalBroadcastName.Text");
            }
        }else{
            if (!config.getString("LeaveMessage.BroadcastName.Text").isEmpty()){
                broadCastName = config.getString("LeaveMessage.BroadcastName.Text");
            }else{
                broadCastName = "Server";
            }
        }
    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){
        if (disableVanLeaveMsg) e.setQuitMessage("");
        if (!enabled) return;
        Random rand = new Random();
        Player player = e.getPlayer();
        StringBuilder finalizedString = new StringBuilder();
        String[] msg = leaveMessage.split(" ");
        finalizedString.append("[").append(broadCastName).append("]: ");
        for (String word : msg){
            if (Objects.equals(word, "=PLAYERNAME")){
                finalizedString.append(player.getName()).append(" ");
            }else if (Objects.equals(word, "=BYEMSGVAR")){
                finalizedString.append(randomLeaveMsg[rand.nextInt(randomLeaveMsg.length)]).append(" ");
            }else{
                finalizedString.append(word).append(" ");
            }
        }
        Bukkit.broadcastMessage(finalizedString.toString().trim());
    }
}
