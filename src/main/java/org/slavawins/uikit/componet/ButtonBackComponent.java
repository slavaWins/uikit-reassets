package org.slavawins.uikit.componet;

import org.bukkit.inventory.ItemStack;
import org.slavawins.reassets.integration.ReassetsGet;
import org.slavawins.uikit.CustomMaterial;
import org.slavawins.uikit.listeners.ReassetsMenu;
import org.slavawins.uikit.menucore.BtnMenuCoreContract;
import org.slavawins.uikit.menucore.MenuBase;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ButtonBackComponent extends BaseComponent<Number> {

    Consumer<BtnMenuCoreContract> event;


    public ButtonBackComponent(MenuBase menuBase, int x, int y, int width, Consumer<BtnMenuCoreContract> event) {
        super(menuBase, x, y, 1);
        this.event = event;

        for (int i = x; i <= x + width; i++) {
            BtnMenuCoreContract btn = menuBase.addButtonItem(i, y, ReassetsGet.item("reassets/items/button/button_empty.png"), this::Click, true);
            contents.add(btn);
        }
    }

    private void Click(BtnMenuCoreContract btnMenuCoreContract) {
       // System.out.println("CKLCIK EMP BTN");
        if (event != null) {
            event.accept(btnMenuCoreContract);
        }
    }


    List<BtnMenuCoreContract> contents = new ArrayList<>();

    @Override
    public void Render() {

    }

}
