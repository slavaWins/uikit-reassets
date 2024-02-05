package org.slavawins.uikit.componet;

import org.bukkit.inventory.ItemStack;
import org.slavawins.reassets.integration.ReassetsGet;
import org.slavawins.uikit.menucore.BtnMenuCoreContract;
import org.slavawins.uikit.menucore.MenuBase;

import java.util.ArrayList;
import java.util.List;

public class NumberComponent extends BaseComponent<Number> {


    List<BtnMenuCoreContract> btns = new ArrayList<>();

    public NumberComponent(MenuBase menuBase, int x, int y, Number val) {
        super(menuBase, x, y, val);
    }

    @Override
    public void Render() {
        if (current.intValue() < 0) current = 0;


        BtnMenuCoreContract btn;

        ItemStack item;
        if (current.intValue() <= 64) {
            item = ReassetsGet.item("UIKIT_ITEMS_NUMBER_" + current.toString());
        } else {
            item = ReassetsGet.item("UIKIT_ITEMS_NUMBER_64_PLUS");
        }

        if (item == null) {
            System.out.println("NO EXIST ICON");
            return;
        }


        btn = menuBase.addButtonItem(x, y, item, null, true);

        btn.updateId();
        btn.setItem(item);

        btns.add(btn);

    }

    @Override
    public void setVal(Number i) {
        super.setVal(i);
        Render();
    }


    public void plus(Number i) {
        int val = current.intValue();
        val += i.intValue();

        setVal(val);
    }

}
