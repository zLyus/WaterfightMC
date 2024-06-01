package me.lyus.waterfight.eventhandlers;

import me.lyus.waterfight.Waterfight;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


public class OnPlayerLogin implements Listener {

    @EventHandler
    public void onPlayer(org.bukkit.event.player.PlayerLoginEvent event) {
        if (event.getPlayer() != null) {
            Player player = event.getPlayer();

            player.setPlayerListName(player.getDisplayName() + Waterfight.getInstance().getPlayerKills().get(player.getName()));

            Bukkit.getServer().getOnlinePlayers().forEach(Player -> {
                Waterfight.getInstance().getInventoryConfigs().put(Player.getName(), Waterfight.getInstance().getDefaultInv());
            });

        }
    }
}
