package me.ghaxz.waterfight.eventhandlers;

import me.ghaxz.waterfight.Waterfight;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


public class OnPlayerLogin implements Listener {

    @EventHandler
    public void onPlayer(org.bukkit.event.player.PlayerLoginEvent event) {
        if (event.getPlayer() != null) {
            Player player = event.getPlayer();

            player.setPlayerListName(player.getDisplayName() + Waterfight.getInstance().getPlayerKills().get(player.getName()));

            if (Waterfight.getInstance().getPlayerKills().containsKey(player.getName())) {
                Waterfight.getInstance().addPlayer(player);
            }

        }
    }
}
