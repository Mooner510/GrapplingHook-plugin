package Mooner;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Utils {
    public static ItemStack createItem(Material m, int amount, int damage, String name, String... lore) {
        ItemStack i = new ItemStack(m, amount, (short) damage);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(chat(name));
        ArrayList<String> l = new ArrayList<>();
        for (String s: lore) {
            l.add(chat(s));
        }
        im.setLore(l);
        i.setItemMeta(im);
        return i;
    }

    public static String chat(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String chatParser(String msg) {
        return msg.replace('§', '&');
    }
}
