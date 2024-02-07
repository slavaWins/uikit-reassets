package org.slavawins.uikit.menus.craft;

import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;
import org.slavawins.reassets.integration.ReassetsCheck;
import org.slavawins.reassets.integration.ReassetsGet;
import org.slavawins.uikit.LayerImage;
import org.slavawins.uikit.Uikit;
import org.slavawins.uikit.componet.*;
import org.slavawins.uikit.menucore.BtnMenuCoreContract;
import org.slavawins.uikit.menucore.MenuBase;

public class CraftMenu extends MenuBase {

    private BukkitTask task;
    private ProgressBarComponent progressBar;
    private SlotComponent slot0;
    private SlotComponent slotResult;


    public CraftMenu() {

        isLockedPlayerInv = false;

        isChestMode = false;
        isLockedAll = true;
        LayerImage layer = new LayerImage();
        layer.left48();
        layer.addUnicode(ReassetsGet.image("/reassets/ui/window/win_craft_test.png"));
        layer.moveTostartLeft();
        setTitle(layer.build() + " Оружений верстак");
    }

    public boolean isRun = false;

    public void run(float seconds) {
        if (isRun) return;

        task = new CraftBlockerTask(this).runTaskTimer(Uikit.getInstanse(), 0, Math.round(seconds / 20f * 10f));    // 20 тикс * 10 сек
        isRun = true;
    }


    public void onUserCanselCraftingProcces() {
        if (!isRun) return;
        progressBar.setVal(0f);
        if (task == null) return;
        task.cancel();
        task = null;
    }

    public void onResult() {
        isRun = false;
        if (!slot0.moveToSlot(slotResult)) {
            return;
        }


    }

    public void Tick() {
        System.out.println("TICK");
        progressBar.increment(1f);
        if (progressBar.getVal() >= progressBar.valMax) {
            task.cancel();
            task = null;
            progressBar.setVal(0f);
            onResult();
        }
    }

    @Override
    public void onShow() {
        super.onShow();


        new ButtonBackComponent(this, 6, 5, 3, this::CraftClick);
        slot0 = new SlotComponent(this, 2, 3, this::FilterSlot);
        slotResult = new SlotComponent(this, 7, 3, null);

        progressBar = new ProgressBarComponent(this, 3, 3, 0f, 10f);
        //addButton(1, 4, Material.CREEPER_HEAD, "icon", "", null);
        //CheckboxComponent.AddToggleCheckBox(this, 2, 4, true, "On", "Off", this::Change);
    }


    private Boolean FilterSlot(ItemStack itemStack) {
        onUserCanselCraftingProcces();

        String reasetItemId = ReassetsCheck.isReasset(itemStack);
        System.out.println(reasetItemId);
        if (reasetItemId.indexOf("GUNS_MODELS_GUN") != 0) {
            return false;
        }

        return true;
    }


    private void CraftClick(BtnMenuCoreContract btnMenuCoreContract) {

        if (slot0.isEmpty()) return;

        onUserCanselCraftingProcces();
        if (isRun) return;

        run(10);
        isRun = true;
        System.out.println("CRAFT START");
    }

    @Override
    public void onCloseEvent() {

        onUserCanselCraftingProcces();
    }
}
