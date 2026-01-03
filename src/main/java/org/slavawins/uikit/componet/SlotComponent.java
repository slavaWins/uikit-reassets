package org.slavawins.uikit.componet;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.slavawins.reassets.integration.ReassetsCheck;
import org.slavawins.reassets.integration.ReassetsGet;
import org.slavawins.uikit.menucore.BtnMenuCoreContract;
import org.slavawins.uikit.menus.craft.CraftBaseMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class SlotComponent extends BaseComponent<Number> {


    private final CraftBaseMenu craftMenu;
    public BtnMenuCoreContract slot;
    public BtnMenuCoreContract warning;

    public List<String> nativeFilterItems = new ArrayList<>();

    public Function<ItemStack, Boolean> filter = this::filterNative;
    public Consumer<Void> onSetItemEvent = null;

    public boolean SHOWING_WARNIG = true;


    public Boolean filterNative(ItemStack itemStack) {
        craftMenu.onUserCanselCraftingProcces();
        String reasetItemId = ReassetsCheck.isReasset(itemStack);

        return nativeFilterItems.contains(reasetItemId);
    }

    public void addNativeFilterItemId(String id) {
        nativeFilterItems.add(id);
    }

    public SlotComponent(CraftBaseMenu menuBase, int x, int y, Function<ItemStack, Boolean> filter) {
        super(menuBase, x, y, 1);
        this.craftMenu = menuBase;

        if (filter != null) {
            this.filter = filter;
        }

        slot = menuBase.addButton(x, y, null, "slot", "d", this::cliclSlot);
        slot.isLocked = false;
        slot.item = null;


        warning = menuBase.addButtonItem(x - 1, y, ReassetsGet.item("reassets/items/icon/icon_invalid.png"), null, true);
        warningHide();

        // slot.eventCurrentItemClick = this::onSlotToItem;
    }


    public void warningHide() {
        // System.out.println("HIDE");
        if (!SHOWING_WARNIG) return;
        warning.visible = true;
        warning.setVisible(false);
        warning.item.setItemMeta(warning.item.getItemMeta());
    }

    public void warningShow(String msg) {
        if (!SHOWING_WARNIG) return;
        ItemMeta meta = warning.item.getItemMeta();
        meta.setDisplayName(msg);
        warning.item.setItemMeta(meta);
        warning.setVisible(true);
    }

    private void cliclSlot(BtnMenuCoreContract btnMenuCoreContract) {


        if (menuBase.player.getItemOnCursor().getType() == Material.AIR) {

            if (slot.item == null) return;

            menuBase.player.setItemOnCursor(slot.item);
            slot.item = null;
            slot.setItem(null);
            onChangeItem();
            return;
        }

        if (menuBase.player.getItemOnCursor().getType() != Material.AIR) {


            if (filter == null) return;


            if (!filter.apply(menuBase.player.getItemOnCursor())) {
                warningShow("Предмет не подходит");
                return;
            }
            warningHide();


            if (slot.item != null) {
                ItemStack inhand = menuBase.player.getItemOnCursor();
                menuBase.player.setItemOnCursor(slot.item);

                slot.item = null;
                if(inhand!=null) {

                    slot.setItem(inhand);
               }
                onChangeItem();

                return;
            }


            slot.setItem(menuBase.player.getItemOnCursor());
            onChangeItem();
            menuBase.player.setItemOnCursor(null);

            return;
        }
    }


    public void onChangeItem() {
        if(onSetItemEvent==null)return;
        onSetItemEvent.accept(null);
    }

    List<BtnMenuCoreContract> contents = new ArrayList<>();

    @Override
    public void onMenuClosed() {
        if (slot.item != null) {
            if(slot.DISABLED_GIVE_PLAYER_ON_CLOSE_MENU)return;
            menuBase.player.getInventory().addItem(slot.item);
            slot.item = null;

        }
    }

    public boolean isEmpty() {
        return slot.item == null;
    }

    public boolean moveToSlot(SlotComponent slotTo) {
        if (slotTo.slot.item != null) return false;
        warningHide();

        slotTo.slot.setItem(slot.item);
        slot.item = null;
        slot.setItem(null);
        onChangeItem();
        return true;
    }


    public boolean take(int amount) {
        if (getAmount() < amount) {
            warningShow("Не хватает предмета. Нужно " + amount + " шт.");
            return false;
        }


        boolean isEmptyNext = false;
        if(slot.item.getAmount()-amount<=0)isEmptyNext =true;

        slot.item.setAmount(slot.item.getAmount() - amount);

        if(isEmptyNext){
            slot.setItem(null);
        }
        return true;
    }

    public void updatePackage() {
        if(  slot.item==null)return;
        slot.item.setItemMeta(slot.item.getItemMeta());
    }

    public int getAmount() {
        if (slot.item == null) return 0;
        return slot.item.getAmount();
    }

    public void addAmount(int val) {
        if (getAmount() == 0) return;
        setAmount(getAmount() + val);
    }

    public void setItem(ItemStack item) {
        slot.setItem(item);
        onChangeItem();
    }

    public void setAmount(int val) {
        if (slot.item == null) return;
        slot.item.setAmount(val);
        onChangeItem();
    }
}
