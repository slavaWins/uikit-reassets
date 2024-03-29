package org.slavawins.uikit.listeners;

import org.bukkit.Material;
import org.slavawins.reassets.integration.ReassetsGet;
import org.slavawins.uikit.componet.CheckboxComponent;
import org.slavawins.uikit.componet.NumberBoardComponent;
import org.slavawins.uikit.componet.NumberComponent;
import org.slavawins.uikit.componet.TabComponent;
import org.slavawins.uikit.menucore.BtnMenuCoreContract;
import org.slavawins.uikit.menucore.MenuBase;

public class DevMenu extends MenuBase {
    NumberBoardComponent numberBoardComponent;

    TabComponent tab;
    private NumberComponent numberComponent;

    public DevMenu() {
        setBackgroundCenter(ReassetsGet.image("/reassets/ui/window/empty.png"), "UI KIT");
    }

    @Override
    public void onShow() {
        super.onShow();


        tab = new TabComponent(this, 5);

        tab.add(addButton(1, 1, Material.ACACIA_BUTTON, "Btn1", "descr", null));
        tab.add(addButton(2, 1, Material.WOODEN_AXE, "Btn2", "descr", null));
        tab.add(addButton(3, 1, Material.ACACIA_BUTTON, "Btn3", "descr", null));
        tab.add(addButton(4, 1, Material.WOODEN_HOE, "Btn4", "descr", null));
        tab.add(addButton(1, 1, Material.ACACIA_BUTTON, "Btn5", "descr", null));
        tab.add(addButton(1, 1, Material.ACACIA_BUTTON, "Btn6", "descr", null));
        tab.add(addButton(1, 1, Material.QUARTZ, "Btn7", "descr", null));
        tab.add(addButton(1, 1, Material.ACACIA_BUTTON, "Btn8", "descr", null));
        tab.add(addButton(1, 1, Material.ACACIA_BUTTON, "Btn9", "descr", null));
        tab.add(addButton(1, 1, Material.ACACIA_BUTTON, "Btn10", "descr", null));

        tab.Render();

        numberBoardComponent = new NumberBoardComponent(this, 7, 2, 22);
        numberBoardComponent.Render();


        addButton(1, 1, Material.DIAMOND, "icon", "", null);

        numberComponent = new NumberComponent(this, 2, 1, 22);
        numberComponent.Render();


        addButtonItem(3, 1, ReassetsGet.item("/reassets/items/button/plus.png"), this::Plus, true);


        addButton(1, 4, Material.CREEPER_HEAD, "icon", "", null);
        CheckboxComponent.AddToggleCheckBox(this, 2, 4, true, "On", "Off", this::Change);
    }

    private void Plus(BtnMenuCoreContract btnMenuCoreContract) {


        numberComponent.plus(1);

        numberBoardComponent.plus(1);
    }


    void Change(Boolean val) {

        numberComponent.plus(1);

        numberBoardComponent.plus(1);
    }
}
