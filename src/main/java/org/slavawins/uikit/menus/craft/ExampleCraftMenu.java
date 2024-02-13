package org.slavawins.uikit.menus.craft;

import org.bukkit.inventory.ItemStack;
import org.slavawins.reassets.integration.ReassetsCheck;
import org.slavawins.uikit.componet.ButtonBackComponent;
import org.slavawins.uikit.componet.ProgressBarComponent;
import org.slavawins.uikit.componet.SlotComponent;

public class ExampleCraftMenu extends CraftBaseMenu {

    public ExampleCraftMenu(){
        super();

        setSingleBackground("/reassets/ui/window/win_craft_test.png", "Оружейка");
    }

    @Override
    public void onShow() {

        timeReceptWait = 10;

        new ButtonBackComponent(this, 6, 5, 3, this::CraftClick);
        slotInput = new SlotComponent(this, 2, 3, this::filterInputSlot);
        slotResult = new SlotComponent(this, 7, 3, null);

        progressBar = new ProgressBarComponent(this, 3, 3, 0f, 10f);
    }


    @Override
    public boolean validateCanStartCrafting() {
        return true;
    }


    @Override
    public void onResult() {

        if (!slotInput.moveToSlot(slotResult)) {
            return;
        }
    }

    @Override
    public Boolean filterInputSlot(ItemStack itemStack) {
        super.filterInputSlot(itemStack);


        String reasetItemId = ReassetsCheck.isReasset(itemStack);
        // System.out.println(reasetItemId);
        if (reasetItemId.indexOf("GUNS_MODELS_GUN") != 0) {
            return false;
        }

        return true;
    }
}
