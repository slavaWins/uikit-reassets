package org.slavawins.uikit.helpers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DupeProtection {


    public static void protectAllInventory(Inventory inventory) {
        for (ItemStack item : inventory.getContents()) {
            addItem(item);
        }
    }

    public static void scanPlayer(Player player) {
        if (player == null) return;

        scaniventory(player.getInventory());
        scaniventory(player.getEnderChest());

    }

    public static void scaniventory(Inventory inventory) {
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (check(item)) {
                inventory.setItem(i, null);
            }
        }
    }

    public static void remove(ItemStack item) {
        if (item == null) return;
       // System.out.println("remove dupe tag");
        ItemMeta meta = item.getItemMeta();
        MetaContainerHelper.RemoveString(meta, "dupeproto");
        item.setItemMeta(meta);
    }

    public static boolean check(ItemStack item) {
        if (item == null) return false;
        if (item.getType() == Material.AIR) return false;
        return MetaContainerHelper.GetString(item.getItemMeta(), "dupeproto").equalsIgnoreCase("1");
    }

    public static void addItem(ItemStack item) {
        if (item == null) return;
        if (item.getType() == Material.AIR) return;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;
        MetaContainerHelper.Set(meta, "dupeproto", "1");
        item.setItemMeta(meta);
    }
}
