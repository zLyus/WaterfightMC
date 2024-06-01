package me.lyus.waterfight.eventhandlers;

import me.lyus.waterfight.Waterfight;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Material.SNOW_BALL;
import static org.bukkit.Material.getMaterial;

public class OnPlayerRespawn implements Listener {

    @EventHandler
    public void onPlayerRespawn  (PlayerRespawnEvent event) {
        if(event.getPlayer() != null) {
            Player player = event.getPlayer();
            if(Waterfight.getInstance().getCustomInv(player) != null) {
                player.getInventory().setContents(Waterfight.getInstance().getCustomInv(player).getContents());
            } else {
                player.getInventory().setContents(Waterfight.getInstance().getDefaultInv().getContents());
            }
        }

    }
}
