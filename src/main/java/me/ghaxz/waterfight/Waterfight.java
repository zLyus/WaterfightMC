package me.ghaxz.waterfight;

import me.ghaxz.waterfight.commands.InventoryCommand;
import me.ghaxz.waterfight.eventhandlers.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

/**
 * TODO: Rewrite Inventory system
 */

public final class Waterfight extends JavaPlugin {
    private static Waterfight instance;
    private ArrayList<String> deathMessages;
    private HashMap<Player, HashMap<Material, Integer>> inventoryConfigs;
    private HashMap<Material, Integer> defaultInventoryConfig;
    private HashMap<String, Integer> playerKills;

    private final int inventoryOffset = 36;
    private final int configInventorySize = 9;

    @Override
    public void onEnable() {
        instance = this;
        addPlayers();
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

        inventoryConfigs = new HashMap<>();
        defaultInventoryConfig = new HashMap<Material, Integer>() {{
            put(Material.WOOD_SWORD, 36);
            put(Material.SNOW_BALL, 37);
            put(Material.FISHING_ROD, 38);
            put(Material.ENDER_PEARL, 39);
        }};

        this.getCommand("inventory").setExecutor(new InventoryCommand());

        Bukkit.getPluginManager().registerEvents(new OnEntityDamage(), this);
        Bukkit.getPluginManager().registerEvents(new OnInventoryClick(), this);
        Bukkit.getPluginManager().registerEvents(new OnItemDamage(), this);
        Bukkit.getPluginManager().registerEvents(new OnPlayerDeath(), this);
        Bukkit.getPluginManager().registerEvents(new OnPlayerLogin(), this);
        Bukkit.getPluginManager().registerEvents(new OnPlayerRespawn(), this);
    }

    @Override
    public void onDisable() {

    }

    public void openInventoryConfig(Player player) {
        Inventory configInventory = Bukkit.createInventory(player, configInventorySize, "Configure Inventory");
        HashMap<Material, Integer> playerInventoryConfig = inventoryConfigs.getOrDefault(player, defaultInventoryConfig);

        playerInventoryConfig.forEach((key, value) -> {
            configInventory.setItem(value - inventoryOffset, new ItemStack(key, 1));
        });

        player.openInventory(configInventory);

        playerInventoryConfig.forEach((key, value) -> {
            Bukkit.broadcastMessage(key + " at " + value);
        });
    }

    public void addPlayers() {
        Bukkit.getServer().getOnlinePlayers().forEach(Player -> {
            playerKills.put(Player.getName(),0);
        });
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

    public void setInventoryConfigSlot(Player player, int slot, Material item) {
        if (!inventoryConfigs.containsKey(player)) {
            inventoryConfigs.put(player, (HashMap<Material, Integer>) defaultInventoryConfig.clone());
        }

        HashMap<Material, Integer> playerConfig = inventoryConfigs.get(player);

        playerConfig.put(item, slot + inventoryOffset);
    }

    public void playerAddItems(Player player, ItemStack items) {
        HashMap<Material, Integer> configuration = inventoryConfigs.get(player);
        PlayerInventory inventory = player.getInventory();

        if(configuration.containsKey(items.getType())) {
            if (!inventory.contains(items)) {
                inventory.setItem(configuration.get(items.getType()), items);
            } else {
                inventory.addItem(items);
            }
        }
    }

    public static Waterfight getInstance() {
        return instance;
    }

    public ArrayList<String> getDeathMessages() {
        return deathMessages;
    }

}
