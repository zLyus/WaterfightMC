package me.lyus.waterfight.eventhandlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OnPlayerDropItem implements Listener {

    @EventHandler
    public void onPlayerDropItem(org.bukkit.event.player.PlayerDropItemEvent event) {
        event.setCancelled(true);
    }
}
