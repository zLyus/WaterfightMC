package me.lyus.waterfight.eventhandlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;

public class OnItemDamage implements Listener {

    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent event) {
        event.setCancelled(true);
    }
}
