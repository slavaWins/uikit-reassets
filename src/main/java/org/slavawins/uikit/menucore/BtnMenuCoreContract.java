package org.slavawins.uikit.menucore;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.function.Consumer;

public class BtnMenuCoreContract {

    public int x;
    public int y;
    public ItemStack item;
    public String action;
    public int id;
    public boolean isLocked = true;
    public Consumer<BtnMenuCoreContract> event = null;
    public String customData = "";

    MenuBase menuBase;

    public void setItem(ItemStack item) {
        menuBase.SetItemInButton(this, item);
    }

    public void updateId() {
        id = menuBase.PosToId(x, y);
    }

    public boolean visible = true;

    public void setVisible(boolean b) {
        visible = b;
        menuBase.onSetVisible(this);
    }

    public void setName(String s) {
        if (item == null) return;
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(s);
        item.setItemMeta(itemMeta);
    }
}
