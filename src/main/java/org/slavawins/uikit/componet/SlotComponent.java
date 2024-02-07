package org.slavawins.uikit.componet;

import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.slavawins.uikit.CustomMaterial;
import org.slavawins.uikit.menucore.BtnMenuCoreContract;
import org.slavawins.uikit.menucore.MenuBase;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class SlotComponent extends BaseComponent<Number> {


    public BtnMenuCoreContract slot;

    public Function<ItemStack, Boolean> filter = (ItemStack btn) -> {
        return true;
    };

    public SlotComponent(MenuBase menuBase, int x, int y, Function<ItemStack, Boolean> filter) {
        super(menuBase, x, y, 1);

        this.filter = filter;

        slot = menuBase.addButton(x, y, null, "slot", "d", this::cliclSlot);
        slot.isLocked = false;
        slot.item = null;

        // slot.eventCurrentItemClick = this::onSlotToItem;
    }


    private void cliclSlot(BtnMenuCoreContract btnMenuCoreContract) {


        if (menuBase.player.getItemOnCursor().getType() == Material.AIR) {

            if (slot.item == null) return;

            menuBase.player.setItemOnCursor(slot.item);
            slot.item = null;
            slot.setItem(null);
            return;
        }

        if (menuBase.player.getItemOnCursor().getType() != Material.AIR) {


            if (filter == null) return;

            if (!filter.apply(menuBase.player.getItemOnCursor())) {
                return;
            }


            if (slot.item != null) {
                ItemStack inhand = menuBase.player.getItemOnCursor();
                menuBase.player.setItemOnCursor(slot.item);

                slot.item = null;
                slot.setItem(inhand);

                return;
            }


            slot.setItem(menuBase.player.getItemOnCursor());
            menuBase.player.setItemOnCursor(null);

            return;
        }
    }


    List<BtnMenuCoreContract> contents = new ArrayList<>();

    @Override
    public void onMenuClosed() {
        if (slot.item != null) {
            menuBase.player.getInventory().addItem(slot.item);
            slot.item = null;

        }
    }

    public boolean isEmpty() {
        return slot.item == null;
    }

    public boolean moveToSlot(SlotComponent slotTo) {
        if (slotTo.slot.item != null) return false;

        slotTo.slot.setItem(slot.item);
        slot.item = null;
        slot.setItem(null);
        return  true;
    }
}
