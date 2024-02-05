package org.slavawins.uikit.comonet;

import org.bukkit.inventory.ItemStack;
import org.slavawins.reassets.integration.ReassetsGet;
import org.slavawins.uikit.menucore.BtnMenuCoreContract;
import org.slavawins.uikit.menucore.MenuBase;

import java.util.ArrayList;
import java.util.List;

public class NumberBoardComponent extends BaseComponent<Number> {


    List<BtnMenuCoreContract> btns = new ArrayList<>();

    public NumberBoardComponent(MenuBase menuBase, int x, int y, Number val) {
        super(menuBase, x, y, val);
    }

    @Override
    public void Render() {
        String value = current.toString();


        for (int i = 0; i < value.length(); i++) {


            String _char = value.substring(i, i + 1);

            int pos = x + i;
            if (pos> 9) continue;

            BtnMenuCoreContract btn;

            ItemStack item = ReassetsGet.item("UIKIT_ITEMS_BOARD_" + _char);
            if (item == null) {
                System.out.println("NO EXIST ICON");
                continue;
            }

            if (btns.size() >= i + 1) {
                btn = btns.get(i);
            } else {
                btn = menuBase.AddButtonItem(pos, y, item, null, true);

            }
            btn.updateId();
            btn.setItem(item);

            btns.add(btn);

        }
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
