package me.lyus.waterfight.eventhandlers;

import me.lyus.waterfight.Waterfight;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class onInventoryClose implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getInventory().equals(Waterfight.getInstance().getSortInv())) {
            Inventory sortInv = event.getInventory();
            Player player = (Player) event.getPlayer();
            Waterfight.getInstance().setInventoryConfig(player, sortInv);

        }
    }
}
