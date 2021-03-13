package Mooner;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import static Mooner.GrapplingHook.config;
import static Mooner.GrapplingHook.plugin;
import static Mooner.Utils.*;

public class HookGUI {
    private Inventory inventory;
    private Player player;
    private int page;
    private final Click listener = new Click();

    public HookGUI(Player player, int index) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            page = index;
            this.player = player;
            ArrayList<String> ItemNames = new ArrayList<>(config.getConfigurationSection("Grappling Hooks").getKeys(false));

            int sizes = (int) Math.ceil((double) (ItemNames.size() - (index * 45)) / 9);
            sizes++;
            if(sizes > 6) sizes = 6;
            inventory = Bukkit.createInventory(player, sizes * 9, "Grappling Hooks List");

                    if (page != 0) {
                        inventory.setItem(inventory.getSize() - 9, createItem(Material.ARROW, 1, 0, "&aBack"));
                    }
                    if ((index + 1) * 45 < ItemNames.size()) {
                        inventory.setItem(inventory.getSize() - 1, createItem(Material.ARROW, 1, 0, "&aNext"));
                    }

            player.sendMessage(chat("&d" + Arrays.toString(ItemNames.toArray())));
            for (int i = 0; i < 45; i++) {
                if(i + (index * 45) >= ItemNames.size()) break;
                String s = ItemNames.get(i + (index * 45));
                String multiplier = "x0.0";
                String yPower = "0.0";

                if(!config.isSet("Grappling Hooks." + s + ".Multiplier")) {
                    config.set("Grappling Hooks." + s + ".Multiplier", 3);
                }

                if(config.getDouble("Grappling Hooks." + s + ".Multiplier") == 3) {
                    multiplier = "x" + parseDouble(config.getDouble("Grappling Hooks." + s + ".Multiplier")) + " &d(Default value)";
                } else {
                    multiplier = "x" + parseDouble(config.getDouble("Grappling Hooks." + s + ".Multiplier"));
                }

                if(!config.isSet("Grappling Hooks." + s + ".Max Y Power")) {
                    config.set("Grappling Hooks." + s + ".Max Y Power", 1.5);
                }

                if(config.getDouble("Grappling Hooks." + s + ".Max Y Power") == 1.5) {
                    yPower = parseDouble(config.getDouble("Grappling Hooks." + s + ".Max Y Power")) + " &d(Default value)";
                } else {
                    yPower = parseDouble(config.getDouble("Grappling Hooks." + s + ".Max Y Power"));
                }

                if(!config.isSet("Grappling Hooks." + s + ".Cooldown")) {
                    config.set("Grappling Hooks." + s + ".Cooldown", 2);
                }

                inventory.setItem(i, createItem(Material.FISHING_ROD, 1, 0, s, " &7Multiplier: &a" + multiplier, " &7Max Y Power: &a" + yPower, "&8Cooldown: &3" + config.getDouble("Grappling Hooks." + s + ".Cooldown") + "s"));

                player.sendMessage(chat("&e" + i));
            }

            Bukkit.getScheduler().runTask(plugin, () -> {
                Bukkit.getPluginManager().registerEvents(listener, plugin);
                player.openInventory(inventory);
            });
        });
    }

    public class Click implements Listener {
        @EventHandler
        public void onClick(InventoryClickEvent e) {
            if(e.getInventory().equals(inventory)) {
                e.setCancelled(true);
                if(e.getCurrentItem() == null) return;
                if(e.getCurrentItem().getType() == Material.FISHING_ROD) {
                    if (player.getInventory().firstEmpty() != -1) {
                        player.getInventory().addItem(createItem(Material.FISHING_ROD, 1, 0, e.getCurrentItem().getItemMeta().getDisplayName()));
                        player.playSound(player.getLocation(), Sound.NOTE_PLING, 2, 2);
                    } else {
                        player.sendMessage(chat("&cNot enough space in the inventory!"));
                        player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 2, (float) 0.5);
                    }
                } else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(chat("&aBack"))) {
                    if(e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
                        new HookGUI(player, page - 1);
                        HandlerList.unregisterAll(this);
                        inventory = null;
                        player = null;
                    }
                } else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(chat("&aNext"))) {
                    if(e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
                        new HookGUI(player, page + 1);
                        HandlerList.unregisterAll(this);
                        inventory = null;
                        player = null;
                    }
                }
            }
        }

        @EventHandler
        public void onClose(InventoryCloseEvent e){
            if(inventory.equals(e.getInventory())) {
                HandlerList.unregisterAll(this);
                inventory = null;
                player = null;
            }
        }
    }
}
