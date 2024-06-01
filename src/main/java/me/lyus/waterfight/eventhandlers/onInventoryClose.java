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
       if(event.getPlayer() instanceof Player) {
           Inventory inv = event.getInventory();
           Player player = (Player) event.getPlayer();
           if(Waterfight.getInstance().getInventoryConfigs().get(player.getName()) != null) {
               Waterfight.getInstance().getInventoryConfigs().get(player.getName()).setContents(inv.getContents());
           } else {
               Waterfight.getInstance().getInventoryConfigs().put(player.getName(), inv);
           }
       }
    }
}
