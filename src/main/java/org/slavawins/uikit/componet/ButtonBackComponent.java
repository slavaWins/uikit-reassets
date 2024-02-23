package org.slavawins.uikit.componet;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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


    public ButtonBackComponent(MenuBase menuBase, int x, int y, int width, int height, String name, Consumer<BtnMenuCoreContract> event) {
        super(menuBase, x, y, 1);
        this.event = event;

        ItemStack  _item =  ReassetsGet.item("reassets/items/button/button_empty.png").clone();

        ItemMeta m =  _item.getItemMeta();


        m.setDisplayName(name);
        _item.setItemMeta(m);


        for (int iy = x; iy <= y + height; iy++) {
            for (int i = x; i <= x + width; i++) {
                BtnMenuCoreContract btn = menuBase.addButtonItem(i, iy, _item.clone() , this::Click, true);
                contents.add(btn);
            }
        }
    }

    public ButtonBackComponent(MenuBase menuBase, int x, int y, int width, Consumer<BtnMenuCoreContract> event) {
        super(menuBase, x, y, 1);
        this.event = event;

        ItemStack  _item =  ReassetsGet.item("reassets/items/button/button_empty.png");
        ItemMeta m =  _item.getItemMeta();
        m.setDisplayName("Кнопка");

        for (int i = x; i <= x + width; i++) {
            BtnMenuCoreContract btn = menuBase.addButtonItem(i, y,_item.clone(), this::Click, true);
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
