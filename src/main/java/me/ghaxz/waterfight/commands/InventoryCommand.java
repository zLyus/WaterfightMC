package me.ghaxz.waterfight.commands;

import me.ghaxz.waterfight.Waterfight;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InventoryCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Bukkit.broadcastMessage("Received inventory command");

        if(!(commandSender instanceof Player)) {
            return false;
        }

        Player player = (Player) commandSender;

        player.sendMessage("deez");

        Waterfight.getInstance().openInventoryConfig(player);

        return true;
    }
}
