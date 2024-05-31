package me.ghaxz.waterfight.eventhandlers;

import me.ghaxz.waterfight.Waterfight;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Material.SNOW_BALL;

public class OnPlayerRespawn implements Listener {

    @EventHandler
    public void onPlayerRespawn  (PlayerRespawnEvent event) {
        if(event.getPlayer() != null) {
            Player player = event.getPlayer();
            ItemStack enderpearl = new ItemStack(Material.ENDER_PEARL, 1);
            ItemStack snowball = new ItemStack(Material.SNOW_BALL, 10);
            ItemStack woodensword = new ItemStack(Material.WOOD_SWORD, 1);
            ItemStack rod = new ItemStack(Material.FISHING_ROD, 1);
            Waterfight.getInstance().playerAddItems(player, enderpearl);
            Waterfight.getInstance().playerAddItems(player, snowball);
            Waterfight.getInstance().playerAddItems(player, woodensword);
            Waterfight.getInstance().playerAddItems(player, rod);
        }

    }
}
