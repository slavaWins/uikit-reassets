package org.slavawins.uikit.menucore;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class BtnMenuCoreContract {

    public int x;
    public int y;
    public ItemStack item;
    public String action;
    public int id;
    public boolean isLocked = true;
    public Consumer<BtnMenuCoreContract> event = null;
    public String customData = "";
    public BiConsumer<ItemStack, ClickType> eventCurrentItemClick;

    public  boolean DISABLED_GIVE_PLAYER_ON_CLOSE_MENU = false;

    MenuBase menuBase;

    public void setItem(ItemStack item) {
        menuBase.setItemInButton(this, item);
    }

    public void updateId() {
        id = menuBase.posToId(x, y);
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
