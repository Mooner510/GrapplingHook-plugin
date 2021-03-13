package Mooner;

import com.google.common.base.Charsets;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static Mooner.Utils.*;

public class GrapplingHook extends JavaPlugin implements Listener {

    public static GrapplingHook plugin;
    public static FileConfiguration config;
    public static HashMap<Player, Integer> noFallDamage = new HashMap<>();
    public static HashMap<Player, Integer> coolDowns = new HashMap<>();
    private static Integer warnDelay;
    private static Integer Cooldown;
    private static String warnMsg;
    private static FileInputStream stream;

    public void reload() {
        File f = new File("plugins/GrapplingHook/", "config.yml");

        if(!f.exists()) {
            InputStream stream = this.getResource("config.yml");

            FileConfiguration config1 = null;
            try {
                config1 = YamlConfiguration.loadConfiguration(new InputStreamReader(stream, "euc-kr"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            try {
                config1.save(new File("plugins/GrapplingHook/", "config.yml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            stream = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            config = YamlConfiguration.loadConfiguration(new InputStreamReader(stream, "euc-kr"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        config = YamlConfiguration.loadConfiguration(new File("plugins/GrapplingHook/", "config.yml"));
        if(config.isSet("invincible time")) warnDelay = (Integer) (int) Math.round(config.getDouble("invincible time") * 20); else warnDelay = 20;
        if(config.isSet("warn message")) warnMsg = config.getString("warn message"); else warnMsg = "&cWhow! Slow down there!";
    }

    @Override
    public void onEnable() {
        plugin=this;

        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Grappling Hook plugin " + ChatColor.WHITE + "was " + ChatColor.GREEN + "Disabled!");
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "   - Plugin Created by Mooner510");

        if(!new File("plugins/GrapplingHook/", "config.yml").exists()) {
            InputStream stream = this.getResource("config.yml");

            FileConfiguration config1 = YamlConfiguration.loadConfiguration(new InputStreamReader(stream, Charsets.UTF_8));

            try {
                config1.save(new File("plugins/GrapplingHook/", "config.yml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        reload();

        Bukkit.getServer().getPluginManager().registerEvents(this, this);

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () ->
                    Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
                            for(Map.Entry<Player, Integer> entry: noFallDamage.entrySet()) {
                                if (!entry.getKey().isFlying() && entry.getKey().getLocation().subtract(0, 0.01, 0).getBlock().getType().isSolid()) {
                                    noFallDamage.replace(entry.getKey(), entry.getValue() - 1);
                                } else if(!entry.getValue().equals(warnDelay)) {
                                    noFallDamage.replace(entry.getKey(), entry.getValue() - 1);
                                }
                                if(entry.getValue() <= 0) {
                                    noFallDamage.remove(entry.getKey());
                                }
                            }
                            for(Map.Entry<Player, Integer> entry: coolDowns.entrySet()) {
                                coolDowns.replace(entry.getKey(), entry.getValue() - 1);
                                if(entry.getValue() <= 0) {
                                    coolDowns.remove(entry.getKey());
                                }
                            }
                    }, 0, 1));
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Grappling Hook plugin " + ChatColor.WHITE + "was " + ChatColor.RED + "Disabled!");
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "   - Plugin Created by Mooner510");
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            Player p = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                if (noFallDamage.containsKey(p)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        ItemStack i = player.getItemInHand();
        if (cmd.getName().equalsIgnoreCase("grapplinghook") || cmd.getName().equalsIgnoreCase("gh")) {
            new HookGUI(player, 0);
            return true;
        }
        return false;
    }

    @EventHandler
    public void onFishing(PlayerFishEvent event) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            if(event.getState() == PlayerFishEvent.State.CAUGHT_ENTITY || event.getState() == PlayerFishEvent.State.CAUGHT_FISH) return;
            if(event.getState() == PlayerFishEvent.State.FAILED_ATTEMPT || event.getState() == PlayerFishEvent.State.IN_GROUND) {
                FishHook hook = event.getHook();
                Player p = event.getPlayer();
                reload();
                p.sendMessage(chat("&b" + warnDelay));
                if(coolDowns.containsKey(p)) {
                    p.sendMessage(chat(warnMsg.replaceAll("%cooltime%", "" + parseDouble((double) coolDowns.get(p) / 20)).replaceAll("%cooldown%", "" + parseDouble((double)coolDowns.get(p) / 20))));
                    return;
                }
                if(config.isSet("Grappling Hooks")) {
                    if (config.getConfigurationSection("Grappling Hooks").getKeys(false).contains(chatParser(p.getItemInHand().getItemMeta().getDisplayName()))) {
                        Vector v = new Vector(hook.getLocation().getX() - p.getLocation().getX(), 2.5, hook.getLocation().getZ() - p.getLocation().getZ());
                        if(config.isSet("Grappling Hooks." + chatParser(p.getItemInHand().getItemMeta().getDisplayName()) + ".Max Y Power")) {
                            if(config.getDouble("Grappling Hooks." + chatParser(p.getItemInHand().getItemMeta().getDisplayName()) + ".Max Y Power") == 0) {
                                v = new Vector(hook.getLocation().getX() - p.getLocation().getX(), hook.getLocation().getY() - p.getLocation().getY(), hook.getLocation().getZ() - p.getLocation().getZ());
                            } else {
                                v = new Vector(hook.getLocation().getX() - p.getLocation().getX(), 2.5, hook.getLocation().getZ() - p.getLocation().getZ());
                            }
                        }

                        if(config.isSet("Grappling Hooks." + chatParser(p.getItemInHand().getItemMeta().getDisplayName()) + ".Multiplier")) {
                            v = v.normalize().multiply(config.getDouble("Grappling Hooks." + chatParser(p.getItemInHand().getItemMeta().getDisplayName()) + ".Multiplier"));
                        } else {
                            v = v.normalize().multiply(3);
                        }

                        if(config.isSet("Grappling Hooks." + chatParser(p.getItemInHand().getItemMeta().getDisplayName()) + ".Max Y Power")) {
                            if(config.getDouble("Grappling Hooks." + chatParser(p.getItemInHand().getItemMeta().getDisplayName()) + ".Max Y Power") > 0) {
                                if (v.getY() > config.getDouble("Grappling Hooks." + chatParser(p.getItemInHand().getItemMeta().getDisplayName()) + ".Max Y Power")) {
                                    v.setY(config.getDouble("Grappling Hooks." + chatParser(p.getItemInHand().getItemMeta().getDisplayName()) + ".Max Y Power"));
                                }
                            }
                        } else if (v.getY() > 1.5) v.setY(1.5);

                        p.setVelocity(v);
                        noFallDamage.put(p, warnDelay + 8);

                        if(config.isSet("Grappling Hooks." + chatParser(p.getItemInHand().getItemMeta().getDisplayName()) + ".Cooldown")) {
                            if (config.getDouble("Grappling Hooks." + chatParser(p.getItemInHand().getItemMeta().getDisplayName()) + ".Cooldown") >= 0)
                                Cooldown = (Integer) (int) Math.round(config.getDouble("Grappling Hooks." + chatParser(p.getItemInHand().getItemMeta().getDisplayName()) + ".Cooldown") * 20);
                        } else {
                            Cooldown = 40;
                        }

                        coolDowns.put(p, Cooldown);
                    }
                }
            }
        });
    }
}
