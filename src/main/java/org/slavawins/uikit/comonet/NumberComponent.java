package org.slavawins.uikit.comonet;

import org.bukkit.Bukkit;
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
        String value = current + "";


        for (int i = 0; i < value.length(); i++) {

            String _char = value.substring(i, i + 1);

            int pos = x + i;
            if (x > 9) continue;

            BtnMenuCoreContract btn;

            ItemStack item = ReassetsGet.item("UIKIT_ITEMS_NUMBER_" + _char);
            if (item == null) continue;

            if (btns.size() >= i + 1) {
                btn = btns.get(i);
            } else {
                btn = menuBase.AddButtonItem(pos, y, item, null, true);

                Bukkit.broadcastMessage("btn create");
            }
            btn.setItem(item);

            btns.add(btn);

        }
    }

    @Override
    public void setVal(Number i) {
        super.setVal(i);
    }


    public void plus(Number i) {
        int val = current.intValue();
        val += i.intValue();

        setVal(val);
    }

}
