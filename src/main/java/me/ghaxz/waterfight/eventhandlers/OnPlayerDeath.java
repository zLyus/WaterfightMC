package me.ghaxz.waterfight.eventhandlers;

import me.ghaxz.waterfight.Waterfight;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class OnPlayerDeath implements Listener {

    @EventHandler
    public void onPlayerDeath(EntityDamageByEntityEvent event) {
        // Killstreak
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player attacker = (Player) event.getDamager();
            int killStreak = Waterfight.getInstance().getPlayerKills().get(attacker.getName());
            if(killStreak >= 3) {
                Bukkit.getServer().getOnlinePlayers().forEach(Player -> {
                    Player.sendMessage(attacker.getName() + "is on a Killstreak of" + killStreak);
                });
            }
            // Adding Pearls and Snowballs
            int itemCount = countSnowballs(attacker);
            addItems(attacker, (10 - itemCount), killStreak);
            // Adding a kill for the Killer
                Waterfight.getInstance().addKill(attacker);

            attacker.setPlayerListName(attacker.getDisplayName() + killStreak);
        }
    }


    private int countSnowballs(Player player) {
        int count = 0;
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getType() == Material.SNOW_BALL) {
                count += item.getAmount();
            }
        }
        return count;
    }

    private void addItems(Player player, int snowBallAmount, int enderPearlAmount) {
        ItemStack enderpearl = new ItemStack(Material.ENDER_PEARL, enderPearlAmount);
        ItemStack snowball = new ItemStack(Material.SNOW_BALL, snowBallAmount);
        Waterfight.getInstance().playerAddItems(player, enderpearl);
        Waterfight.getInstance().playerAddItems(player, snowball);

    }

}
