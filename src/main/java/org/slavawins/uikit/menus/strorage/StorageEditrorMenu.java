package org.slavawins.uikit.menus.strorage;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.slavawins.uikit.menucore.InvSaver;
import org.slavawins.uikit.menucore.MenuBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StorageEditrorMenu extends MenuBase {


    private final String foolderPlugin;
    private final String indInv;

    public StorageEditrorMenu(String indInv, String foolderPlugin) {
        init();
        this.indInv = indInv;
        this.foolderPlugin = foolderPlugin;
        InvSaver.loadInventory(guiInventory, indInv, foolderPlugin);
        setTitle(indInv);
        isChestMode = true;
        isLockedAll = false;
    }


    @Override
    public void onCloseEvent() {
        InvSaver.saveInventory(guiInventory, indInv, foolderPlugin);
    }


    public static ItemStack getRandomItem(String indInv, String foolderPlugin) {
        List<ItemStack> itemStacks = getContent(indInv, foolderPlugin);

        if (itemStacks == null || itemStacks.isEmpty()) return null;

        Random rand = new Random();
        return itemStacks.get(rand.nextInt(itemStacks.size()));
    }

    public static List<ItemStack> getContent(String indInv, String foolderPlugin) {
        List<ItemStack> content = new ArrayList<>();

        Inventory inv = InvSaver.getInvLoad(indInv, 6, foolderPlugin);
        if (inv == null) return content;


        for (ItemStack item : inv.getContents()) {
            if (item == null) continue;
            if (item.getType() == Material.AIR) continue;
            content.add(item);
        }

        return content;
    }


}
