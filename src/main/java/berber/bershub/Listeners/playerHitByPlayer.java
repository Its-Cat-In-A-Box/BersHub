package berber.bershub.Listeners;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class playerHitByPlayer implements Listener {
    List<String> adminToolLore;

    public playerHitByPlayer() {
        adminToolLore.set(0, "Admin tool provided by Ber's Hub");
    }

    @EventHandler
    public void onPlayerHitByPlayer(EntityDamageByEntityEvent e){


        if (!e.getDamager().getType().equals(EntityType.PLAYER)) return;
        if (!e.getEntity().getType().equals(EntityType.PLAYER)) return;

        Player damager = (Player) e.getDamager();
        Player victim = (Player) e.getEntity();

        ItemStack weapon = damager.getItemInHand();
        if (weapon.getItemMeta().getLore() == adminToolLore){

        }
    }
}
