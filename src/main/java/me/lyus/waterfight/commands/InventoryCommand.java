package me.lyus.waterfight.commands;

import me.lyus.waterfight.Waterfight;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

public class InventoryCommand implements CommandExecutor, Listener {
    private Inventory inv = Waterfight.getInstance().getDefaultInv();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("huh");
        if (sender instanceof Player) {
            Player player = (Player) sender;
            inv = Waterfight.getInstance().getCustomInv(player);
            player.openInventory(inv);
            return true;
        } else {
            sender.sendMessage("This command can only be used by a player.");
            return false;
        }
    }

}
