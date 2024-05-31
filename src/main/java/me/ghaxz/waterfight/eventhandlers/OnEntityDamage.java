package me.ghaxz.waterfight.eventhandlers;

import me.ghaxz.waterfight.Waterfight;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;
import java.util.Collections;

public class OnEntityDamage implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (event.getCause() == EntityDamageEvent.DamageCause.VOID) {
                ArrayList<String> deathMessages = Waterfight.getInstance().getDeathMessages();
                Collections.shuffle(deathMessages);

                Bukkit.getOnlinePlayers().forEach(Player-> {
                    if(player.getKiller() != null) {
                        String msg = deathMessages.get(0);
                        player.sendMessage(Player.getName() + msg
                                .replace("<Player", player.getDisplayName())
                                .replace("<Killer>", player.getKiller().getName()));
                    } else {
                        player.sendMessage(Player.getName() + "forgor how to swim");
                    }
                });

               player.teleport(player.getWorld().getSpawnLocation());
            }
        }
    }
}
