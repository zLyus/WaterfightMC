package me.ghaxz.waterfight.eventhandlers;

import me.ghaxz.waterfight.Waterfight;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class OnInventoryClick implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(!(event.getWhoClicked() instanceof Player)) return;

        Player player = (Player) event.getWhoClicked();
        Inventory clickedInventory = event.getClickedInventory();

        if (clickedInventory == null ||
                !clickedInventory.getTitle().equals("Configure Inventory") ||
                event.getAction() != InventoryAction.PLACE_ALL) return;

        event.setCancelled(true);

        int slot = event.getSlot();
        if (event.getCursor() != null && event.getCursor().getType() != Material.AIR) {
            Material itemType = event.getCurrentItem().getType();
            Bukkit.broadcastMessage("calling set inventory");
            Waterfight.getInstance().setInventoryConfigSlot(player, slot, itemType);
        }
    }
}
