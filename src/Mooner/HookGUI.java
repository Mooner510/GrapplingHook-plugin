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

import static Mooner.GrapplingHook.config;
import static Mooner.GrapplingHook.plugin;
import static Mooner.Utils.chat;
import static Mooner.Utils.createItem;

public class HookGUI {
    private Inventory inventory;
    private Player player;
    private int page;
    private final Click listener = new Click();

    public HookGUI(Player player, int index) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            page = index;
            this.player = player;
            inventory = Bukkit.createInventory(player, 54, "Bank Deposit");

            ArrayList<Object> ItemNames = new ArrayList<>(Arrays.asList(config.getConfigurationSection("Grappling Hook").getKeys(false).toArray()));

            if(page != 0) {
                inventory.setItem(45, createItem(Material.ARROW, 1, 0, "&aBack"));
            }
            if((page + 1) * 45 > ItemNames.size()) {
                inventory.setItem(53, createItem(Material.ARROW, 1, 0, "&aNext"));
            }

            for (int i = 0; i < 45; i++) {
                String s = (String) ItemNames.get(i + (page * 45));
                inventory.setItem(i, createItem(Material.FISHING_ROD, 1, 0, s, " &7Multiplier: &a" + config.getDouble("Grappling Hooks." + s + ".Multiplier"), " &7Max Y Power: &a" + config.getDouble("Grappling Hooks." + s + ".Max Y Power")));
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
                if(e.getSlot() < 45) {
                    if(e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.FISHING_ROD) {
                        if (player.getInventory().firstEmpty() != -1) {
                            player.getInventory().addItem(createItem(Material.FISHING_ROD, 1, 0, e.getCurrentItem().getItemMeta().getDisplayName()));
                            player.playSound(player.getLocation(), Sound.NOTE_PLING, 2, 2);
                        } else {
                            player.sendMessage(chat("&cNot enough space in the inventory!"));
                            player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 2, (float) 0.5);
                        }
                    }
                } else if(e.getSlot() == 45) {
                    if(e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
                        new HookGUI(player, page - 1);
                        HandlerList.unregisterAll(this);
                        inventory = null;
                        player = null;
                    }
                } else if(e.getSlot() == 53) {
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
