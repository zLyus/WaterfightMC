package me.lyus.waterfight;

import me.lyus.waterfight.commands.InventoryCommand;
import me.lyus.waterfight.eventhandlers.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

/**
 * TODO: Rewrite Inventory system
 */

public final class Waterfight extends JavaPlugin {
    private static Waterfight instance;
    private ArrayList<String> deathMessages;
    private HashMap<String, Integer> playerKills;
    private HashMap<String, Inventory> inventoryConfigs;
    private Inventory defaultInv;
    private Inventory sortInv;


    private final int inventoryOffset = 36;
    private final int configInventorySize = 9;

    @Override
    public void onEnable() {
        instance = this;

        inventoryConfigs = new HashMap<>();
        defaultInv = setUpDefaultInv();

        setUpSortInv();
        setUpDefaultInv();
        fillDefaultInv();
        playerKills = new HashMap<>();


        deathMessages = new ArrayList<>();
        Collections.addAll(deathMessages,
                "<Player> got dunked on by <Killer>",
                "<Player> jumped by <Killer>",
                "<Player> shouldn't have played with <Killer>",
                "<Player> underestimated <Killer>",
                "<Killer> ate the Ender Pearl of <Player>",
                "<Player> should not mess with <Killer> again"
        );

        this.getCommand("inventory").setExecutor(new InventoryCommand());

        Bukkit.getPluginManager().registerEvents(new OnEntityDamage(), this);
        Bukkit.getPluginManager().registerEvents(new OnItemDamage(), this);
        Bukkit.getPluginManager().registerEvents(new OnPlayerDeath(), this);
        Bukkit.getPluginManager().registerEvents(new OnPlayerLogin(), this);
        Bukkit.getPluginManager().registerEvents(new OnPlayerRespawn(), this);
    }

    @Override
    public void onDisable() {

    }

    public void setUpSortInv() {
        sortInv = Bukkit.createInventory(null, 9, "Sort Items");

        ItemStack woodsword = new ItemStack(Material.WOOD_SWORD, 1);
        ItemStack snowball = new ItemStack(Material.SNOW_BALL, 10);
        ItemStack rod = new ItemStack(Material.FISHING_ROD, 1);
        ItemStack enderpearl = new ItemStack(Material.ENDER_PEARL,1);
        sortInv.setItem(0, woodsword);
        sortInv.setItem(1, snowball);
        sortInv.setItem(2, rod);
        sortInv.setItem(3, enderpearl);
    }

    public Inventory getSortInv() {
        return sortInv;
    }

    public Inventory setUpDefaultInv() {
        Inventory inv = Bukkit.createInventory(null, 9, "Sort Items");

        ItemStack woodsword = new ItemStack(Material.WOOD_SWORD, 1);
        ItemStack snowball = new ItemStack(Material.SNOW_BALL, 10);
        ItemStack rod = new ItemStack(Material.FISHING_ROD, 1);
        ItemStack enderpearl = new ItemStack(Material.ENDER_PEARL,1);
        inv.setItem(0, woodsword);
        inv.setItem(1, snowball);
        inv.setItem(2, rod);
        inv.setItem(3, enderpearl);

        return inv;
    }

    public void fillDefaultInv() {
        Bukkit.getServer().getOnlinePlayers().forEach(Player -> {
            Player.getInventory().addItem(new ItemStack(Material.WOOD_SWORD, 1));
            Player.getInventory().addItem(new ItemStack(Material.SNOW_BALL, 10));
            Player.getInventory().addItem(new ItemStack(Material.FISHING_ROD, 1));
            Player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 1));
        });
    }

    public Inventory getDefaultInv() {
        return defaultInv;
    }

    public void setInventoryConfig(Player player, Inventory inventory) {
        inventoryConfigs.put(player.getName(), inventory);
    }

    public Inventory getCustomInv(Player player) {
        return inventoryConfigs.get(player.getName());
    }

    public void addKill(Player player) {
        playerKills.put(player.getName(), playerKills.get(player.getName() + 1));
    }

    public void addPlayer(Player player) {
        playerKills.put(player.getName(), 0);
    }

    public HashMap<String, Integer> getPlayerKills() {
        return playerKills;
    }

    public static Waterfight getInstance() {
        return instance;
    }

    public ArrayList<String> getDeathMessages() {
        return deathMessages;
    }

}
