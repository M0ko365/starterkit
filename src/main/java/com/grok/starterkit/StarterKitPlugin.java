package com.grok.starterkit;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class StarterKitPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("StarterKitPlugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("StarterKitPlugin has been disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("starterkit")) {
            if (!sender.hasPermission("starterkit.give")) {
                sender.sendMessage("§cYou don't have permission to use this command!");
                return true;
            }

            Player target;
            if (args.length == 0) {
                if (sender instanceof Player) {
                    target = (Player) sender;
                } else {
                    sender.sendMessage("§cUsage: /starterkit [player]");
                    return true;
                }
            } else {
                target = getServer().getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage("§cPlayer not found!");
                    return true;
                }
            }

            giveStarterKit(target);
            sender.sendMessage("§aGave starter kit to " + target.getName() + "!");
            return true;
        }
        return false;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            giveStarterKit(player);
            getLogger().info("Gave starter kit to new player: " + player.getName());
        }
    }

    private void giveStarterKit(Player player) {
        List<String> kitItems = getConfig().getStringList("starter-kit");
        
        for (String itemStr : kitItems) {
            try {
                ItemStack item = parseItemString(itemStr);
                if (item != null) {
                    player.getInventory().addItem(item);
                }
            } catch (Exception e) {
                getLogger().warning("Failed to give item: " + itemStr + " - " + e.getMessage());
            }
        }
    }

    private ItemStack parseItemString(String itemStr) {
        String[] parts = itemStr.split(":");
        Material material = Material.matchMaterial(parts[0].trim().toUpperCase());
        if (material == null) {
            getLogger().warning("Invalid material: " + parts[0]);
            return null;
        }
        
        int amount = 1;
        if (parts.length > 1) {
            try {
                amount = Integer.parseInt(parts[1].trim());
            } catch (NumberFormatException e) {
                getLogger().warning("Invalid amount in: " + itemStr);
            }
        }
        
        return new ItemStack(material, amount);
    }
}
